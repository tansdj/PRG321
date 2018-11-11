/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

import PersonManagement.*;
import ProductManagement.*;
import com.sun.corba.se.impl.io.IIOPOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya Each client is serviced on its own thread, that uses the below
 * run() method. Based on the method request received, this class performs the
 * necessary actions at the server side and sends the result back to the client.
 */
public class ClientServicer implements Runnable {

    private Socket client;

    public ClientServicer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            Communication c = (Communication) ois.readObject();
            Communication result = null;
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            switch (c.methodIdentifier) {
                case 1:
                    Department d1 = (Department) c.requestObject;
                    ArrayList<Department> deps = d1.select();
                    result = new Communication(deps);
                    oos.writeObject(result);
                    break;
                case 2:
                    Department d2 = (Department) c.requestObject;
                    result = new Communication(d2.delete());
                    oos.writeObject(result);
                    break;
                case 3:
                    Department d3 = (Department) c.requestObject;
                    result = new Communication(d3.insert());
                    oos.writeObject(result);
                    break;
                case 4:
                    Person p1 = (Person) c.requestObject;
                    ArrayList<Person> all_people = p1.select();
                    result = new Communication(all_people);
                    oos.writeObject(result);
                    break;
                case 5:
                    Person p2 = (Person) c.requestObject;
                    result = new Communication(p2.selectSpecPerson());
                    oos.writeObject(result);
                    break;
                case 6:
                    Person p3 = (Person) c.requestObject;
                    result = new Communication(p3.update());
                    oos.writeObject(result);
                    break;
                case 7:
                    Person p4 = (Person) c.requestObject;
                    result = new Communication(p4.delete());
                    oos.writeObject(result);
                    break;
                case 8:
                    Person p5 = (Person) c.requestObject;
                    result = new Communication(p5.insert());
                    oos.writeObject(result);
                    break;
                case 9:
                    SecurityQuestions sq1 = (SecurityQuestions) c.requestObject;
                    ArrayList<SecurityQuestions> questions = sq1.select();
                    result = new Communication(questions);
                    oos.writeObject(result);
                    break;
                case 10:
                    SecurityQuestions sq2 = (SecurityQuestions) c.requestObject;
                    result = new Communication(sq2.update());
                    oos.writeObject(result);
                    break;
                case 11:
                    SecurityQuestions sq3 = (SecurityQuestions) c.requestObject;
                    result = new Communication(sq3.delete());
                    oos.writeObject(result);
                    break;
                case 12:
                    SecurityQuestions sq4 = (SecurityQuestions) c.requestObject;
                    result = new Communication(sq4.insert());
                    oos.writeObject(result);
                    break;
                case 13:
                    User u1 = (User) c.requestObject;
                    ArrayList<User> all_users = u1.select();
                    result = new Communication(all_users);
                    oos.writeObject(result);
                    break;
                case 14:
                    User u2 = (User) c.requestObject;
                    ArrayList<User> pending_users = u2.selectPending();
                    result = new Communication(pending_users);
                    oos.writeObject(result);
                    break;
                case 15:
                    User u3 = (User) c.requestObject;
                    result = new Communication(u3.selectSpecUser());
                    oos.writeObject(result);
                    break;
                case 16:
                    User u4 = (User) c.requestObject;
                    result = new Communication(u4.update());
                    oos.writeObject(result);
                    break;
                case 17:
                    User u5 = (User) c.requestObject;
                    result = new Communication(u5.delete());
                    oos.writeObject(result);
                    break;
                case 18:
                    User u6 = (User) c.requestObject;
                    result = new Communication(u6.insert());
                    oos.writeObject(result);
                    break;
                case 19:
                    User u7 = (User) c.requestObject;
                    result = new Communication(u7.testLogin());
                    oos.writeObject(result);
                    break;
                case 20:
                    User u8 = (User) c.requestObject;
                    result = new Communication(u8.testForExistingUser());
                    oos.writeObject(result);
                    break;
                case 21:
                    UserSecurityQuestions usq1 = (UserSecurityQuestions) c.requestObject;
                    ArrayList<UserSecurityQuestions> all_questions = usq1.select();
                    result = new Communication(all_questions);
                    oos.writeObject(result);
                    break;
                case 22:
                    UserSecurityQuestions usq2 = (UserSecurityQuestions) c.requestObject;
                    result = new Communication(usq2.selectSpecUserQuestions());
                    oos.writeObject(result);
                    break;
                case 23:
                    UserSecurityQuestions usq3 = (UserSecurityQuestions) c.requestObject;
                    result = new Communication(usq3.update());
                    oos.writeObject(result);
                    break;
                case 24:
                    UserSecurityQuestions usq4 = (UserSecurityQuestions) c.requestObject;
                    result = new Communication(usq4.delete());
                    oos.writeObject(result);
                    break;
                case 25:
                    UserSecurityQuestions usq5 = (UserSecurityQuestions) c.requestObject;
                    result = new Communication(usq5.insert());
                    oos.writeObject(result);
                    break;
                case 26:
                    Category c1 = (Category) c.requestObject;
                    ArrayList<Category> cats = c1.select();
                    result = new Communication(cats);
                    oos.writeObject(result);
                    break;
                case 27:
                    Category c2 = (Category) c.requestObject;
                    result = new Communication(c2.insert());
                    oos.writeObject(result);
                    break;
                case 28:
                    Category c3 = (Category) c.requestObject;
                    result = new Communication(c3.delete());
                    oos.writeObject(result);
                    break;
                case 29:
                    Model m1 = (Model) c.requestObject;
                    ArrayList<Model> mods = m1.select();
                    result = new Communication(mods);
                    oos.writeObject(result);
                    break;
                case 30:
                    Model m2 = (Model) c.requestObject;
                    result = new Communication(m2.delete());
                    oos.writeObject(result);
                    break;
                case 31:
                    Model m3 = (Model) c.requestObject;
                    result = new Communication(m3.insert());
                    oos.writeObject(result);
                    break;
                case 32:
                    Order o1 = (Order) c.requestObject;
                    ArrayList<Order> orders = o1.select();
                    result = new Communication(orders);
                    oos.writeObject(result);
                    break;
                case 33:
                    Order o2 = (Order) c.requestObject;
                    ArrayList<Order> user_orders = o2.selectUserOrders();
                    result = new Communication(user_orders);
                    oos.writeObject(result);
                    break;
                case 34:
                    Order o3 = (Order) c.requestObject;
                    result = new Communication(o3.selectUserOpenOrder());
                    oos.writeObject(result);
                    break;
                case 35:
                    Order o4 = (Order) c.requestObject;
                    result = new Communication(o4.update());
                    oos.writeObject(result);
                    break;
                case 36:
                    Order o5 = (Order) c.requestObject;
                    result = new Communication(o5.delete());
                    oos.writeObject(result);
                    break;
                case 37:
                    Order o6 = (Order) c.requestObject;
                    result = new Communication(o6.insert());
                    oos.writeObject(result);
                    break;
                case 38:
                    OrderItems oi = (OrderItems) c.requestObject;
                    result = new Communication(oi.insert());
                    oos.writeObject(result);
                    break;
                case 39:
                    Product pd1 = (Product) c.requestObject;
                    ArrayList<Product> prods = pd1.select();
                    result = new Communication(prods);
                    oos.writeObject(result);
                    break;
                case 40:
                    Product pd2 = (Product) c.requestObject;
                    result = new Communication(pd2.selectSpecProduct());
                    oos.writeObject(result);
                    break;
                case 41:
                    Product pd3 = (Product) c.requestObject;
                    result = new Communication(pd3.update());
                    oos.writeObject(result);
                    break;
                case 42:
                    Product pd4 = (Product) c.requestObject;
                    result = new Communication(pd4.delete());
                    oos.writeObject(result);
                    break;
                case 43:
                    Product pd5 = (Product) c.requestObject;
                    result = new Communication(pd5.insert());
                    oos.writeObject(result);
                    break;
                case 44:
                    Stock s1 = (Stock) c.requestObject;
                    ArrayList<Stock> all_stock = s1.select();
                    result = new Communication(all_stock);
                    oos.writeObject(result);
                    break;
                case 45:
                    Stock s2 = (Stock) c.requestObject;
                    result = new Communication(s2.selectSpecStock());
                    oos.writeObject(result);
                    break;
                case 46:
                    Stock s3 = (Stock) c.requestObject;
                    result = new Communication(s3.update());
                    oos.writeObject(result);
                    break;
                case 47:
                    Stock s4 = (Stock) c.requestObject;
                    result = new Communication(s4.delete());
                    oos.writeObject(result);
                    break;
                case 48:
                    Stock s5 = (Stock) c.requestObject;
                    result = new Communication(s5.insert());
                    oos.writeObject(result);
                    break;
                case 49:
                    UserRequest ur1 = (UserRequest) c.requestObject;
                    ArrayList<UserRequest> all_requests = ur1.select();
                    result = new Communication(all_requests);
                    oos.writeObject(result);
                    break;
                case 50:
                    UserRequest ur2 = (UserRequest) c.requestObject;
                    ArrayList<UserRequest> backorder_requests = ur2.selectUnprocessed_ProductBackOrder();
                    result = new Communication(backorder_requests);
                    oos.writeObject(result);
                    break;
                case 51:
                    UserRequest ur3 = (UserRequest) c.requestObject;
                    ArrayList<UserRequest> product_backorder_requests = ur3.selectUnprocessed_Product_BackOrder();
                    result = new Communication(product_backorder_requests);
                    oos.writeObject(result);
                    break;
                case 52:
                    UserRequest ur4 = (UserRequest) c.requestObject;
                    ArrayList<UserRequest> all_user_requests = ur4.selectSpecUserRequest();
                    result = new Communication(all_user_requests);
                    oos.writeObject(result);
                    break;
                case 53:
                    UserRequest ur5 = (UserRequest) c.requestObject;
                    ArrayList<Product> product_requests = ur5.productsOnRequest();
                    result = new Communication(product_requests);
                    oos.writeObject(result);
                    break;
                case 54:
                    UserRequest ur6 = (UserRequest) c.requestObject;
                    result = new Communication(ur6.update());
                    oos.writeObject(result);
                    break;
                case 55:
                    UserRequest ur7 = (UserRequest) c.requestObject;
                    result = new Communication(ur7.insert());
                    oos.writeObject(result);
                    break;
                case 56:
                    UserRequest ur8 = (UserRequest) c.requestObject;
                    result = new Communication(ur8.delete());
                    oos.writeObject(result);
                    break;
                case 57:
                    Order o7 = (Order) c.requestObject;
                    ArrayList<User> users_with_open_order = o7.selectUsersWithOpenOrder();
                    result = new Communication(users_with_open_order);
                    oos.writeObject(result);
                    break;
                case 58:
                    UserRequest ur9 = (UserRequest) c.requestObject;
                    result = new Communication(ur9.updateUnprocessed());
                    oos.writeObject(result);
                    break;
                case 59:
                    UserRequest ur10 = (UserRequest) c.requestObject;
                    ArrayList<UserRequest> awaiting_purchase_requests = ur10.selectRequestsAwaitingPurchase();
                    result = new Communication(awaiting_purchase_requests);
                    oos.writeObject(result);
                    break;
                case 60:
                    UserRequest ur11 = (UserRequest) c.requestObject;
                    result = new Communication(ur11.updateAwaitingPurchase());
                    oos.writeObject(result);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientServicer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientServicer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServicer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            try {
//                oos.flush();
//                oos.close();
//                ois.close();
//                client.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ClientServicer.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

}
