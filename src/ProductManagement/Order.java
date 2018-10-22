/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import PersonManagement.User;
import bc_stationary_bll.Datahandling;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Tanya
 */
public class Order implements Datahandling{
    
    private User user;
    private Date orderDate; 
    private Date receivedDate;
    private OrderItems[] orderItems;

    public OrderItems[] getOrderItems() {
        return orderItems;
    }

    public Order(User user, Date orderDate, Date receivedDate, OrderItems[] orderItems) {
        this.user = user;
        this.orderDate = orderDate;
        this.receivedDate = receivedDate;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public void setOrderItems(OrderItems[] orderItems) {
        this.orderItems = orderItems;
    }


    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" + "user=" + user + ", orderDate=" + orderDate + ", receivedDate=" + receivedDate + ", orderItems=" + orderItems + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.user);
        hash = 61 * hash + Objects.hashCode(this.orderDate);
        hash = 61 * hash + Objects.hashCode(this.receivedDate);
        hash = 61 * hash + Arrays.deepHashCode(this.orderItems);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.receivedDate, other.receivedDate)) {
            return false;
        }
        if (!Arrays.deepEquals(this.orderItems, other.orderItems)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<?> select() {
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
