/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.ReportManager;
import bussiness.SessionManager;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mannu
 */
public class ReportHandler {
   private static ReportHandler reportHandler = null; 
   private DateFormat hourdateFormat;
    
   private ReportHandler(){

       hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   }
   
     public static ReportHandler getInstance(){
        if( reportHandler== null) {
         reportHandler = new ReportHandler();
        }
        return reportHandler;
    }
    
    public void BuildSaleReport(double totalCost){
          try{
            // Se crea el documento
            Document documento = new Document();
            // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
            FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
            Image imagen = Image.getInstance("logo.jpeg"); 

            documento.open();
            PdfPTable table = new PdfPTable(2); 
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table.getDefaultCell().setPaddingLeft(60);
            BuildHeader(documento);
            
             
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
    
            imagen.setAbsolutePosition(110f, 150f);
           
            

            BuildBody(table,totalCost,documento);
              
            BuildFooter(documento);
            documento.add(imagen);
            documento.close();
             
        }catch(Exception e)
        {
            System.err.println("Ocurrio un error: " +e);
            System.exit(-1);
        }
          
         return;
    }
    
    private void BuildHeader( Document documento) throws DocumentException{
     
        Date date = new Date();
        Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph p = new Paragraph(hourdateFormat.format(date));
            p.add(new Chunk(glue));
            p.add("Le atendio: "+ SessionManager.getCurrentEmployeeName());
            documento.add(p);
            
    }
    
    private void BuildBody(PdfPTable table, double totalCost, Document documento) throws DocumentException{
        List<String> purchases = SalesViewHelper.getInstance().getPurchases();
        String[] productData;
        int productName = 0;
        int productPrice = 1;
         
         for(String product : purchases){
             productData = product.split("  ");
                
                
                    table.addCell(productData[productName]);
                    table.addCell(productData[productPrice]);
                    System.out.println(productData[productPrice]);
            }
           
            documento.add(table);
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total: " + String.valueOf(totalCost)));
            celdaFinal.setBorderWidthRight(0);
            celdaFinal.setBorderWidthLeft(0);
            celdaFinal.setBorderColorTop(BaseColor.DARK_GRAY);
            celdaFinal.setBorderColorBottom(BaseColor.DARK_GRAY);
            celdaFinal.setPaddingLeft(50);
            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(2);
            celdaFinal.setPaddingLeft(235);
            table.addCell(celdaFinal);
            documento.add(table);

    }
    
    private void BuildFooter(Document documento) throws DocumentException{
            
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            
            Paragraph p2= new Paragraph("GRACIAS POR SU PREFERENCIA");
            p2.setAlignment(Element.ALIGN_CENTER);
            Paragraph p3= new Paragraph("VUELVA PRONTO");
            p3.setAlignment(Element.ALIGN_CENTER);
            documento.add(p2);
            documento.add(p3);
    }
}
