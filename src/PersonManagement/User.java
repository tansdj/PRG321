/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonManagement;

import bc_stationary_bll.Datahandling;
import bc_stationary_dll.Datahandler;
import bc_stationary_dll.Datahelper;
import bc_stationary_dll.TableSpecifiers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya
 */
public class User implements Datahandling{
    
    private Person person;
    private String username;
    private String password;
    private String accessLevel;
    private String status;

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

    public User() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;//Add encryption
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        Datahandler dh = new Datahandler();
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.selectUser);
            while(rs.next()){
                users.add(new User(new Person(rs.getString("Name"),rs.getString("Surname"),rs.getString("IDNumber"),
                            new Address(), new Contact(),
                            new Department(),rs.getString("Campus")),
                            rs.getString("Username"),rs.getString("Password"),rs.getString("AccessLevel"),rs.getString("Status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public ArrayList<User> selectPending(){
        ArrayList<User> users = new ArrayList<User>();
        Datahandler dh = new Datahandler();
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.pendingUser);
            while(rs.next()){
                users.add(new User(new Person(rs.getString("Name"),rs.getString("Surname"),rs.getString("IDNumber"),
                            new Address(), new Contact(),
                            new Department(),rs.getString("Campus")),
                            rs.getString("Username"),rs.getString("Password"),rs.getString("AccessLevel"),rs.getString("Status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public User selectSpecUser(){
        User user = new User();
        Datahandler dh = new Datahandler();
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.specificUser(this.username));
            while(rs.next()){
                user = (new User(new Person(rs.getString("Name"),rs.getString("Surname"),rs.getString("IDNumber"),
                            new Address(), new Contact(),
                            new Department(),rs.getString("Campus")),
                            rs.getString("Username"),rs.getString("Password"),rs.getString("AccessLevel"),rs.getString("Status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public int update() {
       String[][] userVals = new String[][]{{"STRING","Password",this.getPassword()},{"STRING","AccessLevel",this.getAccessLevel()},
                                            {"STRING","Status",this.getStatus()}};
       Datahandler dh = new Datahandler();
        try {
            return dh.performUpdate(TableSpecifiers.USER.getTable(), userVals, "`Username` = '"+this.getUsername()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int delete() {
       Datahandler dh = new Datahandler();
        try {
            return dh.performDelete(TableSpecifiers.USER.getTable(),"`Username` = '"+this.getUsername()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int insert() {
        String[][] userVals = new String[][]{{"INT","PersonIDFK","(SELECT `PersonIDPK` FROM `tblperson` WHERE `IDNumber` = '"+this.person.getId()+"')"},{"STRING","Username",this.getUsername()},{"STRING","Password",this.getPassword()},
                                    {"STRING","AccessLevel",this.getAccessLevel()},{"STRING","Status",this.getStatus()}};
        Datahandler dh = new Datahandler();
        try {
            return dh.performInsert(TableSpecifiers.USER.getTable(), userVals);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public boolean testLogin()
    {
        boolean accessAllowed = false;
        ArrayList<User> users = new ArrayList<User>();
        users = select();
        for(User user: users)
        {
            if(user.username.equals(this.username)&&(user.password.equals(this.password)))
            {
               if(!user.status.toLowerCase().equals("pending")||(!user.status.toLowerCase().equals("disabled")))
               {
                   accessAllowed = true;
               }
            }
        }
        
        return accessAllowed;
    }
    
    public boolean testForExistingUser() // Check to see if username and password is unique
    {
        boolean userExists = false;
        ArrayList<User> users = new ArrayList<User>();
        users = select();
        for(User user: users)
        {
            if(user.username.equals(this.username)||(user.password.equals(this.password)))
            {
               userExists = true;
            }
        }
        
        return userExists;
    }

}
