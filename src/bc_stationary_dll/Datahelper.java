/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_dll;

import java.util.Date;

/**
 *
 * @author Tanya
 */
public class Datahelper {
    public static String selectPerson = "SELECT * FROM `tblperson` INNER JOIN `tbladdress` ON `AddressIDFK`=`AddressIDPK`\n" +
                                 "INNER JOIN `tblcontact` ON `ContactIDPK` = `ContactIDFK` INNER JOIN `tblDepartment` ON `DepIDFK` = `DepartmentIDPK`";
    public static String specificPerson(String id){
        return "SELECT * FROM `tblperson` INNER JOIN `tbladdress` ON `AddressIDFK`=`AddressIDPK`\n" +
                    "INNER JOIN `tblcontact` ON `ContactIDPK` = `ContactIDFK` INNER JOIN `tblDepartment` ON `DepIDFK` = `DepartmentIDPK` WHERE `IDNumber` = '"+id+"'";
    }
    public static String selectUser = "SELECT * FROM `tblUser` INNER JOIN `tblPerson` ON `PersonIDFK` = `PersonIDPK`";
    
    public static String pendingUser = "SELECT * FROM `tblUser` INNER JOIN `tblPerson` ON `PersonIDFK` = `PersonIDPK`  AND `Status`= 'Pending'";
    
    public static String specificUser(String username){
        return "SELECT * FROM `tblUser` INNER JOIN `tblPerson` ON `PersonIDFK` = `PersonIDPK` WHERE `Username` = '"+username+"'";
    }
    
    public static String selectUQuestions = "SELECT * FROM `tblusersecurityquestions` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` "
                                            + "INNER JOIN `tblsecurityquestions` ON `QuestionIDFK` = `QuestionIDPK`";
    public static String specificUQuestions(String username){
        return "SELECT * FROM `tblusersecurityquestions` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` "
                    + "                        INNER JOIN `tblsecurityquestions` ON `QuestionIDFK` = `QuestionIDPK` WHERE `Username` = '"+username+"'";
    }
    
    public static String selectProducts = "SELECT * FROM `tblproduct` INNER JOIN `tblmodel` ON `ModelIDFK` = `ModelIDPK` INNER JOIN `tblcategory` ON `CategoryIDFK` = `CategoryIDPK`";
    
    public static String specificProduct(String productName){
        return "SELECT * FROM `tblproduct` INNER JOIN `tblmodel` ON `ModelIDFK` = `ModelIDPK` INNER JOIN `tblcategory` ON `CategoryIDFK` = `CategoryIDPK` WHERE `Name` = '"+productName+"'";
    }
    
    public static String selectStock = "SELECT * FROM `tblstock` INNER JOIN `tblproduct` ON `ProductIDFK` = `ProductIDPK` INNER JOIN `tblmodel` ON `ModelIDFK` = `ModelIDPK` INNER JOIN `tblcategory` ON `CategoryIDFK` = `CategoryIDPK`";
    
    public static String specificStock(String productName){
        return "SELECT * FROM `tblstock` INNER JOIN `tblproduct` ON `ProductIDFK` = `ProductIDPK`  INNER JOIN `tblmodel` ON `ModelIDFK` = `ModelIDPK` INNER JOIN `tblcategory` ON `CategoryIDFK` = `CategoryIDPK` WHERE `Name` = '"+productName+"'";
    }
    
    public static String selectRequest = "SELECT * FROM `tbluserrequest` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` INNER JOIN `tblproduct` ON `ProductIDFK` = `ProductIDPK`";
    
    public static String selectUnprocessedRequests = "SELECT * FROM `tbluserrequest` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` INNER JOIN `tblproduct` ON `ProductIDFK` = `ProductIDPK` WHERE `ReqStatus` = 'Unprocessed'";
    
    public static String selectUnprocessed_BackOrderRequests = "SELECT * FROM `tbluserrequest` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` INNER JOIN `tblproduct` ON `ProductIDFK` = `ProductIDPK` WHERE `ReqStatus` = 'Unprocessed' OR `ReqStatus` = 'Back Ordered'";
    
    public static String selectSpecUserRequest(String username){
        return "SELECT * FROM `tbluserrequest` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` INNER JOIN `tblproduct` ON `ProductIDFK` = `ProductIDPK` WHERE `Username` = '"+username+"'";
    }
    
    public static String selectOrderItems(int orderId){
        return "SELECT * FROM `tblorderitems` INNER JOIN `tblproduct` ON `ProductIDFK` = `ProductIDFK` WHERE `OrderIDFK` = "+orderId;
    }
    
    public static String selectOrders = "SELECT * FROM `tblorder` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK`";
    
    public static String specificUserOrders(String username,Date startDate,Date endDate){
        return "SELECT * FROM `tblorder` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` WHERE `Username` = '"+username+"' AND `OrderDate`>='"+startDate.toString()+"' AND `ReceivedDate`<='"+endDate.toString()+"')";
    }
    
    public static String specificUserOpenOrder(String username){
        return "SELECT * FROM `tblorder` INNER JOIN `tbluser` ON `UserIDFK` = `UserIDPK` WHERE `Username` = '"+username+"' AND `OrderDate`>`ReceivedDate` ORDER BY `OrderIDPK` DESC LIMIT 1";
    }
    
}
