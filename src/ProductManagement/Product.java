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
import java.sql.Date;
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
public class Product implements Datahandling{
    
    private String name;
    private String description;
    private Category category;
    private String status;
    private Model model;
    private double costPrice;
    private double salesPrice;
    private java.util.Date entryDate;

    public Product(String name, String description, Category category, String status, Model model, double costPrice, double salesPrice, java.util.Date entryDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.status = status;
        this.model = model;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.entryDate = entryDate;
    }

    public Product() {
    }

    public java.util.Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = (salesPrice<0)?0:salesPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = (costPrice<0)?0:costPrice;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = (model==null)?new Model():model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = (status.equals(""))?"N.A":status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = (category==null)?new Category():category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = (description.equals(""))?"N.A":description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name.equals(""))?"N.A":name;
    }

    @Override
    public String toString() {
        return name; 
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.category);
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.model);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.costPrice) ^ (Double.doubleToLongBits(this.costPrice) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.salesPrice) ^ (Double.doubleToLongBits(this.salesPrice) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.entryDate);
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
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.costPrice) != Double.doubleToLongBits(other.costPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.salesPrice) != Double.doubleToLongBits(other.salesPrice)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.entryDate, other.entryDate)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Product> select() {
        ArrayList<Product> prods = new ArrayList<Product>();
        Datahandler dh = new Datahandler();
        ResultSet rs;
        try {
            rs = dh.selectQuerySpec(Datahelper.selectProducts);
            while(rs.next()){
                prods.add(new Product(rs.getString("Name"),rs.getString("Description"),new Category(rs.getString("CatDescription")),rs.getString("Status"),
                        new Model(rs.getString("ModDescription")),rs.getDouble("CostPrice"),rs.getDouble("SalesPrice"),rs.getDate("EntryDate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prods;
    }

    public Product selectSpecProduct(){
        Product prod = new Product();
        Datahandler dh = new Datahandler();
        ResultSet rs;
        try {
            rs = dh.selectQuerySpec(Datahelper.specificProduct(this.name));
            while(rs.next()){
                prod = (new Product(rs.getString("Name"),rs.getString("Description"),new Category(rs.getString("CatDescription")),rs.getString("Status"),
                        new Model(rs.getString("ModDescription")),rs.getDouble("CostPrice"),rs.getDouble("SalesPrice"),rs.getDate("EntryDate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }
    
    @Override
    public synchronized int update() {
        String[][] prodVals = new String[][]{{"STRING","Description",this.description},{"INT","CategoryIDFK"," (SELECT `CategoryIDPK` FROM `tblcategory` WHERE `CatDescription` = '"+this.category.getDescription()+"')"},
                                            {"STRING","Status",this.status},{"INT","ModelIDFK"," (SELECT `ModelIDPK` FROM `tblmodel` WHERE `ModDescription` = '"+this.model.getDescription()+"')"},
                                            {"DOUBLE","CostPrice",Double.toString(this.costPrice)},{"DOUBLE","SalesPrice",Double.toString(this.salesPrice)},{"DATE","EntryDate",this.entryDate.toString()}};
        Datahandler dh = new Datahandler();
        try {
           return dh.performUpdate(TableSpecifiers.PRODUCT.getTable(), prodVals, "`Name` = '"+this.name+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int delete() {
        Datahandler dh = new Datahandler();
        try {
            return dh.performDelete(TableSpecifiers.PRODUCT.getTable(), "`Name` = '"+this.name+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int insert() {
        String[][] prodVals = new String[][]{{"STRING","Name",this.name},{"STRING","Description",this.description},{"INT","CategoryIDFK"," (SELECT `CategoryIDPK` FROM `tblcategory` WHERE `CatDescription` = '"+this.category.getDescription()+"')"},
                                            {"STRING","Status",this.status},{"INT","ModelIDFK"," (SELECT `ModelIDPK` FROM `tblmodel` WHERE `ModDescription` = '"+this.model.getDescription()+"')"},
                                            {"DOUBLE","CostPrice",Double.toString(this.costPrice)},{"DOUBLE","SalesPrice",Double.toString(this.salesPrice)},{"DATE","EntryDate",this.entryDate.toString()}};
        Datahandler dh = new Datahandler();
        try {
            return dh.performInsert(TableSpecifiers.PRODUCT.getTable(), prodVals);
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
