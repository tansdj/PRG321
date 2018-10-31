/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import ProductManagement.*;
import com.itextpdf.text.Element;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author elcap
 */
public class Reporting<T> {
    
    private ArrayList<T> inputArray;
    private String docName;

    public Reporting(ArrayList<T> inputArray, String docName) {
        this.inputArray = inputArray;
        this.docName = docName;
    }
 
    public void generateReport() {
        FileOutputStream outputStream = null;
        try {
            Document reportDocument = new Document(PageSize.A4, 50, 50, 50, 50);
            outputStream = new FileOutputStream(docName);
            PdfWriter pdfWriter = PdfWriter.getInstance(reportDocument, outputStream);
            try {
                reportDocument.open();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate currentDate = LocalDate.now();
                String anchorText = String.format("This document was generated on the: " + dateFormatter.format(currentDate));
                Anchor anchorTarget = new Anchor(anchorText);
                Paragraph reportChapterTitle = new Paragraph("Automatically generated report", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
                Chapter reportChapter = new Chapter(reportChapterTitle, 1);
                reportChapter.setNumberDepth(0);
                Paragraph reportPara = new Paragraph("List of information requested");
                reportChapter.add(reportPara);
                reportChapter.add(anchorTarget);
                List reportList = new List(true);
                ListItem reportItem = null;
                for (Object arrayObject : inputArray) {
                    
                    if (arrayObject instanceof UserRequest) {
                        reportItem = new ListItem(((UserRequest) arrayObject).reportToString());
                        reportList.add(reportItem);
                    }
                    
                    if (arrayObject instanceof Stock) {
                        reportItem = new ListItem(((Stock) arrayObject).reportToString());
                        reportList.add(reportItem);
                    }
                }
                reportChapter.add(reportList);
                reportDocument.add(reportChapter);
                
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                reportDocument.close();
                pdfWriter.close();
            }
 
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (DocumentException ex) {
            System.out.println(ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void generatePurchaseOrder() {
        FileOutputStream outputStream = null;
        try {
            Document reportDocument = new Document(PageSize.A4, 50, 50, 50, 50);
            outputStream = new FileOutputStream(docName);
            PdfWriter pdfWriter = PdfWriter.getInstance(reportDocument, outputStream);
            try {
                reportDocument.open();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate currentDate = LocalDate.now();
                String anchorText = String.format("Purchase order send on: " + dateFormatter.format(currentDate));
                Anchor anchorTarget = new Anchor(anchorText);
                Paragraph reportChapterTitle = new Paragraph("Automatically generated purchase order", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
                Chapter reportChapter = new Chapter(reportChapterTitle, 1);
                reportChapter.setNumberDepth(0);
                Paragraph reportPara = new Paragraph("Product with amount of items to purchase:");
                reportChapter.add(reportPara);
                reportChapter.add(anchorTarget);
                List reportList = new List(true);
                ListItem reportItem = null;
                for (Object arrayObject : inputArray) {
                    
                    if (arrayObject instanceof Stock) {
                        reportItem = new ListItem(((Stock) arrayObject).purchaseOrderToString());
                        reportList.add(reportItem);
                    }
                }
                reportChapter.add(reportList);
                reportDocument.add(reportChapter);
                
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                reportDocument.close();
                pdfWriter.close();
            }
 
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (DocumentException ex) {
            System.out.println(ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
