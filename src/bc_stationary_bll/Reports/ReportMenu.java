/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll.Reports;

/**
 *
 * @author Tanya
 */
public enum ReportMenu {
    STOCK_REPORT(1),
    USER_REQUEST_REPORT(2),
    PURCHASE_ORDER(3),
    ORDER_DELIVERY_REPORT(4);
    
    public int reportOption;

    private ReportMenu(int reportOption) {
        this.reportOption = reportOption;
    }
    
}
