/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import PersonManagement.User;
import java.util.Objects;

/**
 *
 * @author Tanya
 */
public class OrderItems {
    
    private Product product;
    private int qty;

    public OrderItems() {
    }

    public OrderItems(Product product, int qty) {
        this.product = product;
        this.qty = qty;
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
        return "OrderItems{" + "product=" + product + ", qty=" + qty + '}';
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

}
