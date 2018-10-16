/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import PersonManagement.User;
import bc_stationary_bll.Datahandling;
import java.util.ArrayList;
import java.util.Objects;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
