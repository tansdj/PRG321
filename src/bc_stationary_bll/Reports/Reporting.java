/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll.Reports;
 
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
public class Reporting {
    
    private ArrayList<String> inputArray;
    private String docName;
    private String name;
    private String header;

    public Reporting(String name,ArrayList<String> inputArray, String docName,String header) {
        this.inputArray = inputArray;
        this.docName = docName;
        this.name = name;
        this.header = header;
    }
 
    public void generateReport() {
        FileOutputStream outputStream = null;
        try {
            Document reportDocument = new Document(PageSize.A4, 50, 50, 50, 50);
            
            outputStream = new FileOutputStream(docName);
            PdfWriter pdfWriter = PdfWriter.getInstance(reportDocument, outputStream);
            try {
                reportDocument.open();
                Paragraph emptyLine = new Paragraph("");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate currentDate = LocalDate.now();
                String anchorText = String.format("(This document was generated on: " + dateFormatter.format(currentDate)+")");
                Anchor anchorTarget = new Anchor(anchorText, FontFactory.getFont("Calibri",10,Font.ITALIC,BaseColor.DARK_GRAY));
                Paragraph reportChapterTitle = new Paragraph(String.format("%40S","BC Stationary Management System"), FontFactory.getFont("Century Gothic", 20, Font.BOLD, BaseColor.RED));
                Chapter reportChapter = new Chapter(reportChapterTitle, 1);
                reportChapter.setNumberDepth(0);
                Paragraph reportPara = new Paragraph(name,FontFactory.getFont("Calibri",16,Font.BOLD,BaseColor.BLACK));
                reportChapter.add(anchorTarget);
                reportChapter.add(reportPara);
                Paragraph reportHead = new Paragraph(header,FontFactory.getFont("Calibri",12,Font.UNDERLINE,BaseColor.BLACK));
                reportChapter.add(reportHead);
                reportChapter.add(emptyLine);
                List reportList = new List(true);
                ListItem reportItem = null;
                for (String arrayObject : inputArray) {
                        reportList.add(arrayObject);
                }
                reportChapter.add(emptyLine);
                reportChapter.add(reportList);
                reportChapter.add(emptyLine);
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
