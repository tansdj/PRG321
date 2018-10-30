/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import bc_stationary_bll.Datahandling;
import bc_stationary_dll.Datahandler;
import bc_stationary_dll.Datahelper;
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
public class Stock implements Datahandling{
    
    private Product product;
    public int quantity;

    public Stock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Stock() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = (quantity<0)?0:quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = (product==null)?new Product():product;
    }

    @Override
    public String toString() {
        return product.toString() +"\t"+ quantity;
    }
    
    public String reportToString()
    {
        String returnString = String.format("Stock for product %1$s is: %2$10d",
                product.getName(),
                quantity);
        return returnString;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.product);
        hash = 97 * hash + this.quantity;
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
        final Stock other = (Stock) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Stock> select() {
        ArrayList<Stock> stock = new ArrayList<Stock>();
        Datahandler dh = new Datahandler();
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.selectStock);
            while(rs.next()){
                stock.add(new Stock(new Product(rs.getString("Name"),rs.getString("Description"),new Category(rs.getString("CatDescription")),rs.getString("Status"),
                        new Model(rs.getString("ModDescription")),rs.getDouble("CostPrice"),rs.getDouble("SalesPrice"),rs.getDate("EntryDate")),rs.getInt("Quantity")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }
    
    public Stock selectSpecStock(){
        Stock stock = new Stock();
        Datahandler dh = new Datahandler();
        try {
            ResultSet rs = dh.selectQuerySpec(Datahelper.specificStock(this.getProduct().getName()));
            while(rs.next()){
                stock = (new Stock(new Product(rs.getString("Name"),rs.getString("Description"),new Category(rs.getString("CatDescription")),rs.getString("Status"),
                        new Model(rs.getString("ModDescription")),rs.getDouble("CostPrice"),rs.getDouble("SalesPrice"),rs.getDate("EntryDate")),rs.getInt("Quantity")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

    @Override
    public synchronized int update() {
       String[][] stock = new String[][]{{"INT","Quantity",Integer.toString(this.quantity)}};
       Datahandler dh = new Datahandler();
        try {
            return dh.performUpdate(TableSpecifiers.STOCK.getTable(), stock, "`ProductIDFK` = (SELECT `ProductIDPK` FROM `tblproduct` WHERE `Name` = '"+this.product.getName()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int delete() {
        Datahandler dh = new Datahandler();
        try {
            return dh.performDelete(TableSpecifiers.STOCK.getTable(), "`ProductIDFK` = (SELECT `ProductIDPK` FROM `tblproduct` WHERE `Name` = '"+this.product.getName()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int insert() {
        String[][] stock = new String[][]{{"INT","ProductIDFK"," (SELECT `ProductIDPK` FROM `tblproduct` WHERE `Name` = '"+this.product.getName()+"')"},{"INT","Quantity",Integer.toString(this.quantity)}};
       Datahandler dh = new Datahandler();
        try {
            return dh.performInsert(TableSpecifiers.STOCK.getTable(), stock);
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
