/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import PersonManagement.User;
import bc_stationary_dll.Datahandler;
import bc_stationary_dll.TableSpecifiers;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya
 */
public class OrderItems implements Serializable{
    
    private Product product;
    private int qty;
    private Order orderToUpdate;

    public OrderItems() {
    }

    public OrderItems(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public OrderItems(Product product, int qty, Order orderToUpdate) {
        this.product = product;
        this.qty = qty;
        this.orderToUpdate = orderToUpdate;
    }
    
    
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = (qty<0)?0:qty;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = (product==null)?new Product():product;
    }

    @Override
    public String toString() {
        return this.product.getName() +" "+ this.product.getDescription() +" "+ this.qty;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.product);
        hash = 97 * hash + this.qty;
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
        final OrderItems other = (OrderItems) obj;
        if (this.qty != other.qty) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }
    
    public synchronized int insert() throws SQLException {
        String[][] itemVals = null;
            itemVals = new String[][]{{"INT", "OrderIDFK", "(SELECT `OrderIDPK` FROM `tblorder` INNER JOIN `tbluser` ON `UserIdFK` = `UserIDPK` WHERE `Username` = '"+this.orderToUpdate.getUser().getUsername()+"' AND `OrderDate`>`ReceivedDate` ORDER BY `OrderIDPK` DESC LIMIT 1)"},
            {"INT", "ProductIDFK", "(SELECT `ProductIDPK` FROM `tblproduct` WHERE `Name` = '" + this.getProduct().getName() + "')"},
            {"INT", "ItemQty", Integer.toString(this.qty)}};
        
        Datahandler dh = Datahandler.dataInstance;
        return dh.performInsert(TableSpecifiers.ORDER_ITEMS.getTable(), itemVals);

    } 

}
