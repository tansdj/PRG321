/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import PersonManagement.Person;
import PersonManagement.User;
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
public class UserRequest implements Datahandling{
    
    private User user;
    private Product product;
    private int quantity;
    private int priorityLevel;

    public UserRequest(User user, Product product, int quantity, int priorityLevel) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.priorityLevel = priorityLevel;
    }

    public UserRequest() {
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRequest{" + "user=" + user + ", product=" + product + ", quantity=" + quantity + ", priorityLevel=" + priorityLevel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.user);
        hash = 23 * hash + Objects.hashCode(this.product);
        hash = 23 * hash + this.quantity;
        hash = 23 * hash + this.priorityLevel;
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
        final UserRequest other = (UserRequest) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.priorityLevel != other.priorityLevel) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<UserRequest> select() {
        ArrayList<UserRequest> uReq = new ArrayList<UserRequest>();
        Datahandler dh = new Datahandler();
        ResultSet rs;
        try {
            rs = dh.selectQuerySpec("SELECT * FROM `tbluserrequest` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` INNER JOIN `tblproduct` ON `ProductIDFK` = `ProductIDPK`");
            while(rs.next()){
            uReq.add(new UserRequest(new User(new Person(),rs.getString("Username"),rs.getString("Password"),rs.getString("AccessLevel"),rs.getString("Status")),new Product(rs.getString("Name"),rs.getString("Description"),new Category(),rs.getString("Status"),
                        new Model(),rs.getDouble("CostPrice"),rs.getDouble("SalesPrice"),rs.getDate("EntryDate")),rs.getInt("Quantity"),rs.getInt("Priority")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uReq;
    }

    @Override
    public int update() {
        String[][] reqVals = new String[][]{{"INT","Quantity",Integer.toString(this.quantity)},{"INT","Priority",Integer.toString(this.priorityLevel)}};
        Datahandler dh = new Datahandler();
        try {
            return dh.performUpdate(TableSpecifiers.REQUEST.getTable(), reqVals, "`UserIDFK` = (SELECT `UserIDPK` FROM `tbluser` WHERE `Username` = '"+this.user.getUsername()+"') "
                    + "AND `ProductIDFK` = (SELECT `ProductIDPK` FROM `tblproduct` WHERE `Name` = '"+this.product.getName()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(UserRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int delete() {
        Datahandler dh = new Datahandler();
        try {
            return dh.performDelete(TableSpecifiers.REQUEST.getTable(), "`UserIDFK` = (SELECT `UserIDPK` FROM `tbluser` WHERE `Username` = '"+this.user.getUsername()+"') "
                    + "AND `ProductIDFK` = (SELECT `ProductIDPK` FROM `tblproduct` WHERE `Name` = '"+this.product.getName()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(UserRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int insert() {
        String[][] reqVals = new String[][]{{"INT","UserIDFK","(SELECT `UserIDPK` FROM `tbluser` WHERE `Username` = '"+this.user.getUsername()+"')"},
            {"INT","ProductIDFK","(SELECT `ProductIDPK` FROM `tblproduct` WHERE `Name` = '"+this.product.getName()+"')"},
            {"INT","Quantity",Integer.toString(this.quantity)},{"INT","Priority",Integer.toString(this.priorityLevel)}};
        Datahandler dh = new Datahandler();
        try {
            return dh.performInsert(TableSpecifiers.REQUEST.getTable(), reqVals);
        } catch (SQLException ex) {
            Logger.getLogger(UserRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
