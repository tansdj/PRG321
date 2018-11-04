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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya
 */
public class Department implements Datahandling,Serializable{
    
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name.equals(""))?"N.A":name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Department other = (Department) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Department> select() {
        ArrayList<Department> deps = new ArrayList<Department>();
        try {
            Datahandler dh = new Datahandler();
            ResultSet rs = dh.selectQuery(TableSpecifiers.DEPARTMENT.getTable());
            try {
                while(rs.next()){
                    deps.add(new Department(rs.getString("DepName")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return deps;
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deps;
    }

    @Override
    public int update() {
        return 0;
        
    }

    @Override
    public int delete() {
        Datahandler dh = new Datahandler();
        try {
            return dh.performDelete(TableSpecifiers.DEPARTMENT.getTable(), "`DepName` = '"+this.name+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int insert() {
        String[][] colValues = new String[][]{{"STRING","DepName",this.getName()}};
        Datahandler dh = new Datahandler();
        try {
            return dh.performInsert(TableSpecifiers.DEPARTMENT.getTable(), colValues);
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }


    
}
