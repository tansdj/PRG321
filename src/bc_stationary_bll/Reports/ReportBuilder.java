/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll.Reports;

import ProductManagement.OrderItems;
import ProductManagement.Stock;
import ProductManagement.UserRequest;
import java.util.ArrayList;

/**
 *
 * @author Tanya Factory pattern is used to instantiate the correct type of
 * report based on the input received. It creates a list of strings from the
 * list of objects and sends it to the Reporting class in order to generate a
 * report.
 */
//Example: Reporting r = new ReportBuilder(ReportMenu.STOCK_REPORT.reportOption,stockList);
//r.generateReport();
public class ReportBuilder<T> {

    public int reportOption;
    ArrayList<T> reportItems;

    public ReportBuilder(int reportOption, ArrayList<T> reportItems) {
        this.reportOption = reportOption;
        this.reportItems = reportItems;
    }

    public ReportBuilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reporting createReport() {
        Reporting report = null;
        ArrayList<String> reportStrings = new ArrayList<String>();
        switch (reportOption) {
            case 1:
                ArrayList<Stock> stock_list = (ArrayList<Stock>) reportItems;
                for (Stock s : stock_list) {
                    reportStrings.add(s.reportToString());
                }
                StockReport sr = new StockReport();
                report = new Reporting("Stock Report:", reportStrings, sr.pdfName(), sr.header());
                break;
            case 2:
                ArrayList<UserRequest> request_list = (ArrayList<UserRequest>) reportItems;
                for (UserRequest ur : request_list) {
                    reportStrings.add(ur.reportToString());
                }
                UserRequestReport urr = new UserRequestReport();
                report = new Reporting("User Requests:", reportStrings, urr.pdfName(), urr.header());
                break;
            case 3:
                ArrayList<Stock> po_list = (ArrayList<Stock>) reportItems;
                for (Stock po : po_list) {
                    reportStrings.add(po.purchaseOrderToString());
                }
                PurchaseOrderReport por = new PurchaseOrderReport();
                report = new Reporting("Purchase Order:", reportStrings, por.pdfName(), por.header());
                break;
            case 4: ArrayList<OrderItems> oi_list =  (ArrayList<OrderItems>)reportItems;
                    for(OrderItems oi:oi_list){
                        reportStrings.add(oi.orderItemsToString());
                    }
                    OrderDeliveryReport odr = new OrderDeliveryReport();
                    report = new Reporting("Order Items:",reportStrings,odr.pdfName(),odr.header());
                break;    
        }
        return report;
    }
}
