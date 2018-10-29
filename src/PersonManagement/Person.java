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
import java.io.Serializable;
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
public class Person implements Datahandling,Serializable{
    
    private String name;
    private String surname;
    private String id;
    private Address address;
    private Contact contact;
    private Department department;
    private String campus;

    public Person(String name, String surname, String id, Address address, Contact contact, Department department, String campus) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.address = address;
        this.contact = contact;
        this.department = department;
        this.campus = campus;
    }

    public Person(String name, String surname, String id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public Person() {
    }
    
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = (campus.equals(""))?"N.A":campus;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = (department==null)?new Department():department;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = (contact==null)?new Contact():contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = (address==null)?new Address():address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (id.equals(""))?"N.A":id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = (surname.equals(""))?"N.A":surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name.equals(""))?"N.A":name;
    }

    @Override
    public String toString() {
        return name+" "+surname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.surname);
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.address);
        hash = 61 * hash + Objects.hashCode(this.contact);
        hash = 61 * hash + Objects.hashCode(this.department);
        hash = 61 * hash + Objects.hashCode(this.campus);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.campus, other.campus)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Person> select() {
        ArrayList<Person> person = new ArrayList<Person>();
        try {
            Datahandler dh = new Datahandler();
            ResultSet rs = dh.selectQuerySpec(Datahelper.selectPerson);
            try {
                while(rs.next()){
                    person.add(new Person(rs.getString("Name"),rs.getString("Surname"),rs.getString("IDNumber"),
                            new Address(rs.getString("Line1"),rs.getString("Line2"),rs.getString("City"),rs.getString("PostalCode")),
                            new Contact(rs.getString("Cell"),rs.getString("Email")),
                            new Department(rs.getString("DepName")),rs.getString("Campus")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return person;
    }

    public Person selectSpecPerson() throws SQLException{
        Person person = new Person();
        Datahandler dh = new Datahandler();
        ResultSet rs = dh.selectQuerySpec(Datahelper.specificPerson(this.id));
        try {
            while(rs.next()){
                person = (new Person(rs.getString("Name"),rs.getString("Surname"),rs.getString("IDNumber"),
                        new Address(rs.getString("Line1"),rs.getString("Line2"),rs.getString("City"),rs.getString("PostalCode")),
                        new Contact(rs.getString("Cell"),rs.getString("Email")),
                        new Department(rs.getString("DepName")),rs.getString("Campus")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return person;
    }
    
    @Override
    public synchronized int update() {
        try {
            String[][] personValues = new String[][]{{"STRING","Name",this.name},{"STRING","Surname",this.surname},
                {"INT","DepIDFK","(SELECT `DepartmentIDPK` FROM `tbldepartment` WHERE `DepName` = '"+this.department.getName()+"')"},{"STRING","Campus",this.campus}};
            String[][] addressValues = new String[][]{{"STRING","Line1",this.address.getLine1()},{"STRING","Line2",this.address.getLine2()},
                {"STRING","City",this.address.getCity()},{"STRING","PostalCode",this.address.getPostalCode()}};
            String[][] contactValues = new String[][]{{"STRING","Cell",this.contact.getCell()},{"STRING","Email",this.contact.getEmail()}};
            
            Datahandler dh = new Datahandler();
            int p = dh.performUpdate(TableSpecifiers.PERSON.getTable(), personValues, "`IDNumber` = '"+this.id+"'");
            int a = dh.performUpdate(TableSpecifiers.ADDRESS.getTable(), addressValues, "`AddressIDPK` = (SELECT `AddressIDFK` FROM `tblPerson` WHERE `IDNumber` = '"+this.id+"')");
            int c = dh.performUpdate(TableSpecifiers.CONTACT.getTable(), contactValues, "`ContactIDPK` = (SELECT `ContactIDFK` FROM `tblPerson` WHERE `IDNumber` = '"+this.id+"')");
            
            if((p>0)&&(a>0)&&(c>0)){
                return 1;
            }else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int delete() {
       Datahandler dh = new Datahandler();
        try {
            return dh.performDelete(TableSpecifiers.PERSON.getTable(), "`IDNumber` = '"+this.id+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int insert() {
        try {
            Datahandler dh = new Datahandler();
            
            String[][] addressValues = new String[][]{{"STRING","Line1",this.address.getLine1()},{"STRING","Line2",this.address.getLine2()},
                {"STRING","City",this.address.getCity()},{"STRING","PostalCode",this.address.getPostalCode()}};
            int a = dh.performInsert(TableSpecifiers.ADDRESS.getTable(), addressValues);
            String[][] contactValues = new String[][]{{"STRING","Cell",this.contact.getCell()},{"STRING","Email",this.contact.getEmail()}};
            int c = dh.performInsert(TableSpecifiers.CONTACT.getTable(), contactValues);
            String[][] personValues = new String[][]{{"STRING","IDNumber",this.id},{"STRING","Name",this.name},{"STRING","Surname",this.surname},
                {"INT","DepIDFK","(SELECT `DepartmentIDPK` FROM `tbldepartment` WHERE `DepName` = '"+this.department.getName()+"')"},
                {"INT","AddressIDFK","(SELECT `AddressIDPK` FROM `tblAddress` ORDER BY `AddressIDPK` DESC LIMIT 1)"},{"INT","ContactIDFK","(SELECT `ContactIDPK` FROM `tblContact` ORDER BY `ContactIDPK` DESC LIMIT 1)"},
                {"STRING","Campus",this.campus}};
            
            int p = dh.performInsert(TableSpecifiers.PERSON.getTable(), personValues);
            
            
            
            if((p>0)&&(a>0)&&(c>0)){
                return 1;
            }else{
                this.delete();
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
