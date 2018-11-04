/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_dll;

import java.util.HashMap;

/**
 *
 * @author Tanya
 */
public enum TableSpecifiers {
    PERSON("tblperson"),
    ADDRESS("tbladdress"),
    CONTACT("tblcontact"),
    DEPARTMENT("tbldepartment"),
    USER("tbluser"),
    SEQURITY_QUESTIONS("tblsecurityquestions"),
    USER_QUESTIONS("tblusersecurityquestions"),
    PRODUCT("tblproduct"),
    CATEGORY("tblcategory"),
    REQUEST("tbluserrequest"),
    STOCK("tblstock"),
    ORDER("tblorder"),
    ORDER_ITEMS("tblorderitems"),
    MODEL("tblmodel");
    
    
    private String table;
    
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
    
    TableSpecifiers(String tableName){
        this.table = tableName;
    }
}
