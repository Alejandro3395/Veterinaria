/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;


import Entitys.Doctor;
import Entitys.Medicine;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import presentation.controllers.SalesViewHelper;

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
            BuildHeaderSaleReport(documento);
  
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
            documento.add(new Chunk("\n"));
    
            imagen.setAbsolutePosition(110f, 150f);
           
             BuildBodySaleReport(table,totalCost,documento);
              
            BuildFooterSaleReport(documento);
            documento.add(imagen);
            documento.close();
             
        }catch(Exception e)
        {
            System.err.println("Ocurrio un error: " +e);
            System.exit(-1);
        }
    }
    
    public void BuildVeterinaryPrescription(String clientName,
                                            String petName,
                                            List<Medicine> medicines,
                                            String comments,
                                            Double totalCost){
        try{
            Document veterinaryPrescription = new Document(PageSize.A3.rotate());
            //Tal vez agregar la fecha al nombre de la receta
            FileOutputStream fileOutputStream = new FileOutputStream("prescription.pdf");
            PdfWriter.getInstance(veterinaryPrescription,fileOutputStream).setInitialLeading(20);
            veterinaryPrescription.open();
             
             //Encabezado
            BuildHeaderPrescription(veterinaryPrescription, clientName, petName);
            
            //Cuerpo
            veterinaryPrescription.add(new Chunk("\n"));
            veterinaryPrescription.add(new Chunk("\n"));
            veterinaryPrescription.add(new Chunk("\n"));
            
            PdfPTable table = new PdfPTable(3); 
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table.getDefaultCell().setPaddingLeft(60);
            
            BuildBodyPrescription(medicines , veterinaryPrescription,totalCost ,table );
            
            BuildFooterPrescription(veterinaryPrescription, comments);
            
             
            veterinaryPrescription.close();
        }catch(Exception e){
            System.err.println("Ocurrio un error: " +e);
            System.exit(-1);
        }
    }
    
    private void BuildHeaderPrescription(Document veterinaryPrescription, 
                                         String clientName, 
                                         String petName) throws DocumentException, IOException{
         BaseFont baseFont = BaseFont.createFont("Cookie.ttf",
                                                     BaseFont.WINANSI , 
                                                     BaseFont.EMBEDDED);
             Font font = new Font(baseFont);
             font.setStyle(Font.BOLDITALIC);
             font.setSize(35);
             Doctor doctor = SessionManager.getCurrentDoctor();
             Paragraph p1 = new Paragraph("Dr. "+doctor.getName(),font);
             p1.setAlignment(Element.ALIGN_CENTER);
             veterinaryPrescription.add(p1);
            // veterinaryPrescription.add(new Chunk("\n"));
         
             Font font2 = new Font();
             font2.setSize(20);
             
             Paragraph p2 = new Paragraph("CEDULA PROFESIONAL  " + doctor.getIdentityCard() ,font2);
             
             p2.setAlignment(Element.ALIGN_CENTER);
             
             veterinaryPrescription.add(p2);
             veterinaryPrescription.add(new Chunk("\n"));
            Paragraph p3 = new Paragraph("Nombre del cliente: "+ clientName,font2);
            veterinaryPrescription.add(p3);
            Paragraph p4 = new Paragraph("Nombre de la mascota: "+ petName,font2);
            veterinaryPrescription.add(p4);
    }
    
    private void BuildBodyPrescription(List<Medicine> medicines, 
                                       Document veterinaryPrescription,
                                       Double totalCost,
                                       PdfPTable table ) throws DocumentException{

         for(Medicine medicine : medicines){
             
                    table.addCell(medicine.getName());
                    table.addCell(medicine.getDose());
                    table.addCell(medicine.getAdministration());
                   
            }
           

            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total: " + totalCost)) ;
            celdaFinal.setBorderWidthRight(0);
            celdaFinal.setBorderWidthLeft(0);
            celdaFinal.setBorderColorTop(BaseColor.DARK_GRAY);
            celdaFinal.setBorderColorBottom(BaseColor.DARK_GRAY);
            celdaFinal.setPaddingLeft(50);
            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(3);
            celdaFinal.setPaddingLeft(235);
            table.addCell(celdaFinal);
            veterinaryPrescription.add(table);
    }
    
    private void BuildFooterPrescription(Document veterinaryPrescription,
                                         String comments ) throws DocumentException{
        Paragraph p = new Paragraph(comments);
        veterinaryPrescription.add(p);
    }
    
    
    private void BuildHeaderSaleReport( Document documento) throws DocumentException{
     
        Date date = new Date();
        Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph p = new Paragraph(hourdateFormat.format(date));
            p.add(new Chunk(glue));
            p.add("Le atendio: "+ Receptionist.name);
            documento.add(p);
            
    }
    
    
    
    private void BuildBodySaleReport(PdfPTable table, 
                                     double totalCost, 
                                     Document documento) throws DocumentException{
        
        List<String> purchases = SalesViewHelper.getInstance().getPurchases();
        String[] productData;
        int productName = 0;
        int productPrice = 1;
         for(String product : purchases){
             productData = product.split("  ");              
                    table.addCell(productData[productName]);
                    table.addCell(productData[productPrice]);
                   
            }
           

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
    
    private void BuildFooterSaleReport(Document documento) throws DocumentException{
            
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
