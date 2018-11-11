/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll.Reports;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Tanya
 */
public class UserRequestReport implements PdfReports {

    @Override
    public String pdfName() {
        LocalDate ld = LocalDate.now();
        Date d = Date.valueOf(ld);
        return d.toString() + "_" + "UserRequests.pdf";
    }

    @Override
    public String header() {
        return String.format("%1S%10S%10S%10S%15S%23S%20S", "User:", "Prod:", "Qty:", "Date:", "Priority:", "Status:", "C.Date:");
    }

}
