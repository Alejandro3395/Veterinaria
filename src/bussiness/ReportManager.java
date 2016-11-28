/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;



/**
 *
 * @author mannu
 */
public class ReportManager {
    Document document ;
    PdfPTable table ;
    public ReportManager(){
         this.document = new Document();
         this.table = new PdfPTable(2);
    }

    public Document getDocument() {
        return document;
    }

    public PdfPTable getTable() {
        return table;
    }
       
    
    
    
           
    
    
    
    
       
}

