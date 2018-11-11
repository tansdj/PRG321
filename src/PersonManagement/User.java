/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonManagement;

import bc_stationary_bll.Datahandling;
import bc_stationary_dll.*;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Tanya 
 * Represents a user object that has to be maintained in the
 * database. Therefore, a person at BC that has credentials to access the
 * stationary management system.
 */
public class User implements Datahandling, Serializable {
    private Person person;
    private String username;
    private String password;
    private String accessLevel;
    private String status;
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Person person, String username, String password, String accessLevel, String status) {
        this.person = person;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.status = status;
    }

    public User(String username, String password, String accessLevel, String status) {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.status = status;
    }

    public User() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = (status.equals("")) ? "N.A" : status;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = (accessLevel.equals("")) ? "N.A" : accessLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = (password.equals("")) ? "N.A" : password;//Add encryption
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = (username.equals("")) ? "N.A" : username;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = (person == null) ? new Person() : person;
    }

    @Override
    public String toString() {
        return person.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.person);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.accessLevel);
        hash = 97 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.accessLevel, other.accessLevel)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<User> select() {
        ArrayList<User> users = new ArrayList<User>();
        Datahandler dh = Datahandler.dataInstance;
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.selectUser);
            while (rs.next()) {
                String decryptedPassword = decryptPassword(rs.getString("Password"));
                users.add(new User(new Person(rs.getString("Name"), rs.getString("Surname"), rs.getString("IDNumber"),
                        new Address(), new Contact(),
                        new Department(), rs.getString("Campus")),
                        rs.getString("Username"), decryptedPassword, rs.getString("AccessLevel"), rs.getString("Status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    //Selects users that have not been granted access to the system by an administrator.
    public ArrayList<User> selectPending() {
        ArrayList<User> users = new ArrayList<User>();
        Datahandler dh = Datahandler.dataInstance;
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.pendingUser);
            while (rs.next()) {
                String decryptedPassword = decryptPassword(rs.getString("Password"));
                users.add(new User(new Person(rs.getString("Name"), rs.getString("Surname"), rs.getString("IDNumber"),
                        new Address(), new Contact(),
                        new Department(), rs.getString("Campus")),
                        rs.getString("Username"), decryptedPassword, rs.getString("AccessLevel"), rs.getString("Status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    //Returns a complete user object based on its username
    public User selectSpecUser() {
        User user = new User();
        Datahandler dh = Datahandler.dataInstance;
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.specificUser(this.username));
            while (rs.next()) {
                String decryptedPassword = decryptPassword(rs.getString("Password"));
                user = (new User(new Person(rs.getString("Name"), rs.getString("Surname"), rs.getString("IDNumber"),
                        new Address(), new Contact(),
                        new Department(), rs.getString("Campus")),
                        rs.getString("Username"), decryptedPassword, rs.getString("AccessLevel"), rs.getString("Status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public synchronized int update() {
        String encryptedPassword = encryptPassword(this.password);
        String[][] userVals = new String[][]{{"STRING", "Password", encryptedPassword}, {"STRING", "AccessLevel", this.getAccessLevel()},
        {"STRING", "Status", this.getStatus()}};
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performUpdate(TableSpecifiers.USER.getTable(), userVals, "`Username` = '" + this.getUsername() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int delete() {
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performDelete(TableSpecifiers.USER.getTable(), "`Username` = '" + this.getUsername() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int insert() {
        String encryptedPassword = encryptPassword(this.password);
        String[][] userVals = new String[][]{{"INT", "PersonIDFK", "(SELECT `PersonIDPK` FROM `tblperson` WHERE `IDNumber` = '" + this.person.getId() + "')"}, {"STRING", "Username", this.getUsername()}, {"STRING", "Password", encryptedPassword},
         {"STRING", "AccessLevel", this.getAccessLevel()}, {"STRING", "Status", this.getStatus()}};
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performInsert(TableSpecifiers.USER.getTable(), userVals);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    //Used to test whether a client has entered valid login credentials.
    public int testLogin() {
        ArrayList<User> users = new ArrayList<User>();
        users = select();
        for (User user : users) {
            if (user.username.equals(this.username) && (user.password.equals(this.password))) {
                if (user.status.toLowerCase().equals("pending")) {
                    return -1;
                }
                if (user.status.toLowerCase().equals("disabled")) {
                    return -1;
                }
                return 1;
            }
        }
        return 0;
    }

    public boolean testForExistingUser() // Check to see if username and password is unique
    {
        boolean userExists = false;
        ArrayList<User> users = new ArrayList<User>();
        users = select();
        for (User user : users) {
            if (user.username.equals(this.username) || (user.password.equals(this.password))) {
                userExists = true;
            }
        }

        return userExists;
    }

    //It is used to scramble the password entered by the user in order to store it safely in the database.
    public static String encryptPassword(String plainText) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Used to unscramble user input in order to check whether the correct credentials have been entered.
    public static String decryptPassword(String encryptedText) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

}
