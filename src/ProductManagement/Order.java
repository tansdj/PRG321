/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import PersonManagement.Address;
import PersonManagement.Contact;
import PersonManagement.Department;
import PersonManagement.Person;
import PersonManagement.User;
import static PersonManagement.User.decryptPassword;
import bc_stationary_bll.Datahandling;
import bc_stationary_dll.Datahandler;
import bc_stationary_dll.Datahelper;
import bc_stationary_dll.TableSpecifiers;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya 
 * Represents an Order entity that is created whenever a user
 * request is accepted. This class is used to maintain its status and history in
 * the database.
 */
public class Order implements Datahandling, Serializable {

    private User user;
    private Date orderDate;
    private Date receivedDate;
    private ArrayList<OrderItems> orderItems;
    private int id;

    public ArrayList<OrderItems> getOrderItems() {
        return orderItems;
    }

    public Order(User user, Date orderDate, Date receivedDate, ArrayList<OrderItems> orderItems, int id) {
        this.user = user;
        this.orderDate = orderDate;
        this.receivedDate = receivedDate;
        this.orderItems = orderItems;
        this.id = id;
    }

    public Order(User user, Date orderDate, Date receivedDate, ArrayList<OrderItems> orderItems) {
        this.user = user;
        this.orderDate = orderDate;
        this.receivedDate = receivedDate;
        this.orderItems = orderItems;
    }

    public Order(User user) {
        this.user = user;
    }

    public Order() {
    }

    public void setOrderItems(ArrayList<OrderItems> orderItems) {
        this.orderItems = (orderItems == null) ? new ArrayList<OrderItems>() : orderItems;
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
        this.user = (user == null) ? new User() : user;
    }

    @Override
    public String toString() {
        return "Order "+id+" ( Order Date: "+orderDate+" )";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + Objects.hashCode(this.orderDate);
        hash = 47 * hash + Objects.hashCode(this.receivedDate);
        hash = 47 * hash + Objects.hashCode(this.orderItems);
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
        if (!Objects.equals(this.orderItems, other.orderItems)) {
            return false;
        }
        return true;
    }

    //Selects all orders of all users
    @Override
    public ArrayList<Order> select() {
        ArrayList<Order> orders = new ArrayList<Order>();
        Datahandler dh = Datahandler.dataInstance;
        ResultSet rs;
        try {
            rs = dh.selectQuerySpec(Datahelper.selectOrders);
            while (rs.next()) {
                ResultSet rs2 = dh.selectQuerySpec(Datahelper.selectOrderItems(rs.getInt("OrderIDPK")));
                ArrayList<OrderItems> items = new ArrayList<OrderItems>();
                while (rs2.next()) {
                    items.add(new OrderItems(new Product(rs.getString("Name"), rs.getString("Description"), new Category(), rs.getString("Status"),
                            new Model(), rs.getDouble("CostPrice"), rs.getDouble("SalesPrice"), rs.getDate("EntryDate")), rs.getInt("ItemQty")));
                }
                orders.add(new Order(new User(new Person(), rs.getString("Username"), rs.getString("Password"), rs.getString("AccessLevel"), rs.getString("Status")), rs.getDate("OrderDate"), rs.getDate("ReceivedDate"), items, rs.getInt("OrderIDPK")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    //Selects all the orders of a specific user
    public ArrayList<Order> selectUserOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();
        Datahandler dh = Datahandler.dataInstance;
        ResultSet rs;
        try {
            rs = dh.selectQuerySpec(Datahelper.specificUserOrders(this.user.getUsername()));
            while (rs.next()) {
                ResultSet rs2 = dh.selectQuerySpec(Datahelper.selectOrderItems(rs.getInt("OrderIDPK")));
                ArrayList<OrderItems> items = new ArrayList<OrderItems>();
                while (rs2.next()) {
                    items.add(new OrderItems(new Product(rs2.getString("Name"), rs2.getString("Description"), new Category(), rs2.getString("Status"),
                            new Model(), rs2.getDouble("CostPrice"), rs2.getDouble("SalesPrice"), rs2.getDate("EntryDate")), rs2.getInt("ItemQty")));
                }
                orders.add(new Order(new User(new Person(), rs.getString("Username"), rs.getString("Password"), rs.getString("AccessLevel"), rs.getString("Status")), rs.getDate("OrderDate"), rs.getDate("ReceivedDate"), items, rs.getInt("OrderIDPK")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    //Selects an order of a specific user that is still open. A user can only have a single open order at any point in time.
    public Order selectUserOpenOrder() {
        Order o = null;
        Datahandler dh = Datahandler.dataInstance;
        ResultSet rs;
        try {
            rs = dh.selectQuerySpec(Datahelper.specificUserOpenOrder(this.user.getUsername()));
            while (rs.next()) {
                ResultSet rs2 = dh.selectQuerySpec(Datahelper.selectOrderItems(rs.getInt("OrderIDPK")));
                ArrayList<OrderItems> items = new ArrayList<OrderItems>();
                while (rs2.next()) {
                    items.add(new OrderItems(new Product(rs2.getString("Name"), rs2.getString("Description"), new Category(), rs2.getString("Status"),
                            new Model(), rs2.getDouble("CostPrice"), rs2.getDouble("SalesPrice"), rs2.getDate("EntryDate")), rs2.getInt("ItemQty")));
                }
                o = new Order(new User(new Person(), rs.getString("Username"), rs.getString("Password"), rs.getString("AccessLevel"), rs.getString("Status")), rs.getDate("OrderDate"), rs.getDate("ReceivedDate"), items, rs.getInt("OrderIDPK"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    //Returns a list of all users that currently have open orders.
    public ArrayList<User> selectUsersWithOpenOrder() {
        ArrayList<User> users = new ArrayList<User>();
        Datahandler dh = Datahandler.dataInstance;
        ResultSet rs;
        try {
            rs = dh.selectQuerySpec(Datahelper.specificUserWithOpenOrder());
            while (rs.next()) {
                users.add(new User(new Person(rs.getString("Name"), rs.getString("Surname"), rs.getString("IDNumber"),
                        new Address(), new Contact(rs.getString("Cell"),rs.getString("Email")),
                        new Department(), rs.getString("Campus")),
                        rs.getString("Username"), decryptPassword(rs.getString("Password")), rs.getString("AccessLevel"), rs.getString("Status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public synchronized int update() {
        String[][] orderVals = new String[][]{{"DATE", "ReceivedDate", this.receivedDate.toString()}};
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performUpdate(TableSpecifiers.ORDER.getTable(), orderVals, "`OrderIDPK` = " + this.id);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int delete() {
        Datahandler dh = Datahandler.dataInstance;
        try {
            return dh.performDelete(TableSpecifiers.ORDER.getTable(), "`OrderIDPK` = " + this.id);
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int insert() {
        String[][] orderVals = new String[][]{{"INT", "UserIDFK", "(SELECT `UserIDPK` FROM `tbluser` WHERE `Username` = '" + this.user.getUsername() + "')"}, {"DATE", "OrderDate", this.orderDate.toString()},
        {"DATE", "ReceivedDate", this.receivedDate.toString()}};
        String[][] itemVals = null;
        for (OrderItems i : orderItems) {
            itemVals = new String[][]{{"INT", "OrderIDFK", "(SELECT `OrderIDPK` FROM `tblorder` ORDER BY `OrderIDPK` DESC LIMIT 1)"},
            {"INT", "ProductIDFK", "(SELECT `ProductIDPK` FROM `tblproduct` WHERE `Name` = '" + i.getProduct().getName() + "')"},
            {"INT", "ItemQty", Integer.toString(i.getQty())}};
        }
        Datahandler dh = Datahandler.dataInstance;
        try {
            int o = dh.performInsert(TableSpecifiers.ORDER.getTable(), orderVals);
            if (o >= 1) {
                int i = dh.performInsert(TableSpecifiers.ORDER_ITEMS.getTable(), itemVals);
                if (i >= 1) {
                    return 1;
                } else {
                    this.delete();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}
