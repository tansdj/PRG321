/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_dll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya
 */
public class Datahandler {

    Connection cn;

    public Datahandler() {//Connect once when class is instantiated.
        connect();
    }
    
    

    public final void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bc_stationary","root","");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Datahandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet selectQuery(String tableName) throws SQLException {
        ResultSet rs = null;
        try {
            Statement st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM `" + tableName + "`");
            
        } catch (SQLException ex) {
            Logger.getLogger(Datahandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet selectQuery(String tableName, String conditions) throws SQLException {
        ResultSet rs = null;
        try {
            Statement st = cn.createStatement();
            String query = "SELECT * FROM `" + tableName + "` WHERE " + conditions;
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Datahandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet selectQuerySpec(String query) throws SQLException{
      ResultSet rs = null;
        try {
            Statement st = cn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Datahandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return rs;
    }
    public int performInsert(String tablename, String[][] colValues) throws SQLException {
        String query = "INSERT INTO `" + tablename + "`(";
        try {
            Statement st = cn.createStatement();
            for (String[] item : colValues) {
                query += "`" + item[1] + "`,";
            }
            query = query.substring(0, query.length() - 1);
            query += ") VALUES(";
            for (String[] item : colValues) {
                String type = item[0].toString().toUpperCase();
                if (type.equals("STRING") || type.equals("DATE")) {
                    query += "'" + item[2] + "',";
                } else {
                    query += item[2] + ",";
                }
            }
            query = query.substring(0, query.length() - 1);
            query += ")";
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Datahandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int performUpdate(String tablename, String[][] colValues,String conditions) throws SQLException{
        String query = "UPDATE `" + tablename + "`SET ";
        try {
            Statement st = cn.createStatement();
            for (String[] item : colValues) {
                query += "`" + item[1] + "` = ";
                String type = item[0].toString().toUpperCase();
                if (type.equals("STRING") || type.equals("DATE")) {
                    query += "'" + item[2] + "',";
                } else {
                    query += item[2]+",";
                }
            }
            query = query.substring(0, query.length() - 1);
            if(conditions.length()>0){
                query += " WHERE "+conditions;
            }            
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Datahandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int performDelete(String tablename,String conditions) throws SQLException{
        String query = "DELETE FROM `" + tablename + "`";
        try {
            Statement st = cn.createStatement();
            if(conditions.length()>0){
                query += " WHERE "+conditions;
            }            
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Datahandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            cn.close();
        }
        return -1;
    }
    
}
