/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll.Reports;

/**
 *
 * @author Tanya This interface is used to force all report classes to return
 * the appropriate name and header string for the report in question.
 */
public interface PdfReports {

    public String pdfName();

    public String header();
}
