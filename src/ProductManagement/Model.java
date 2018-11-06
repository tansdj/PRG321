/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import bc_stationary_bll.Datahandling;
import bc_stationary_dll.Datahandler;
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
public class Model implements Datahandling{
    
    private String description;

    public Model(String description) {
        this.description = description;
    }

    public Model() {
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
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.description);
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
        final Model other = (Model) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Model> select() {
        ArrayList<Model> models = new ArrayList<Model>();
        Datahandler dh = Datahandler.dataInstance;
        try {
            ResultSet rs = dh.selectQuery(TableSpecifiers.MODEL.getTable());
            while(rs.next()){
                models.add(new Model(rs.getString("ModDescription")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return models;
    }

    @Override
    public int update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized int delete() {
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performDelete(TableSpecifiers.MODEL.getTable(), "`ModDescription` = '"+this.getDescription()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int insert() {
        String[][] modVals = new String[][]{{"STRING","ModDescription",this.getDescription()}};
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performInsert(TableSpecifiers.MODEL.getTable(), modVals);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
