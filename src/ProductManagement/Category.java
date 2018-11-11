/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import bc_stationary_bll.Datahandling;
import bc_stationary_dll.Datahandler;
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
 * Represents the category in which a product is listed.
 */
public class Category implements Datahandling, Serializable{
    
    private String description;

    public Category(String description) {
        this.description = description;
    }

    public Category() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = (description.equals(""))?"N.A":description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.description);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Category> select() {
        ArrayList<Category> cats = new ArrayList<Category>();
        Datahandler dh = Datahandler.dataInstance;
        try {
            ResultSet rs = dh.selectQuery(TableSpecifiers.CATEGORY.getTable());
            while(rs.next()){
                cats.add(new Category(rs.getString("CatDescription")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cats;
    }

    @Override
    public int update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized int delete() {
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performDelete(TableSpecifiers.CATEGORY.getTable(), "`CatDescription` = '"+this.getDescription()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int insert() {
        String[][] depVals = new String[][]{{"STRING","CatDescription",this.getDescription()}};
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performInsert(TableSpecifiers.CATEGORY.getTable(), depVals);
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
