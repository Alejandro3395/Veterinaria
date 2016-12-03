/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineManager;
import bussiness.ReportManager;
import bussiness.SessionManager;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.SalesView;

/**
 *
 * @author mannu
 */
public class SalesViewHelper extends ViewHelper{
    private SalesView saleView;
    private DefaultComboBoxModel comboBoxModel;
    private DefaultTableModel Tablemodel;
    private  static SalesViewHelper salesViewHelper;
    private ReportManager reportManager = new ReportManager();
    private double totalCost=0;
    
    private SalesViewHelper(){
        setSaleView(new SalesView());
        comboBoxModel= (DefaultComboBoxModel) getSaleView().getProduct_list().getModel();
        Tablemodel = (DefaultTableModel) getSaleView().getProductTable().getModel();
        initializeView();
    }

    public static SalesViewHelper getInstance(){
        if( salesViewHelper== null) {
         salesViewHelper =  new SalesViewHelper();
        }
        return salesViewHelper;
    }
    
    public SalesView getSaleView() {
        return saleView;
    }
    
    @Override
    protected void setEvents() {
        getSaleView().getAddProductBttn().addActionListener(actionEvent -> InsertProductToTable());
        getSaleView().getDeleteProductBttn().addActionListener(actionEvent -> RemoveProductFromTable());
        getSaleView().getAceptSaleBttn().addActionListener(actionEvent -> BuildReport());
    }

    //quitar de aqui
    public void setSaleView(SalesView sellView) {
        this.saleView = sellView;
    }
    
    public void SetTotalCost(){
        totalCost=0;
        DecimalFormat numDecimales = new DecimalFormat("0.00");
        for(int i=0; i< Tablemodel.getRowCount();i++){
            totalCost = totalCost + (double) Tablemodel.getValueAt(i, 1);
        }
        getSaleView().getTotalSale_Field().setText(numDecimales.format(totalCost));
    }
    
    public String getSelectedMedicine(){
        String medicineSelected= (String)comboBoxModel.getSelectedItem();    
        return medicineSelected;
    }
    
    @Override
    public void loadView() {
        saleView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getSaleView() );
        getSaleView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        loadProducts();
        setEvents();
    }
   
    public void loadProducts(){
        
        comboBoxModel.removeAllElements();
        MedicineManager medicineManager = MedicineManager.GetInstance();
        List<Medicine> productsList =  medicineManager.getMedicines();
        
        for(Medicine medicine : productsList){
            
            String item = medicine.getName() +"  $"+medicine.getCost();
            comboBoxModel.addElement(item);
        }
        
        
        //Ponerlo quizas en un metodo aparte
        getSaleView().getProduct_list().setModel(comboBoxModel);
    }
    
    public void InsertProductToTable(){
       String medicineSelected= getSelectedMedicine();
       String[] medicineDataRow = medicineSelected.split("\\$");
       String medicineName= medicineDataRow[0];
       double medicinePrice = Double.valueOf(medicineDataRow[1]);
        System.out.println(medicinePrice);
       Object[] rowMedicine = new Object[]{medicineName,medicinePrice};
       Tablemodel.addRow(rowMedicine);
       SetTotalCost();
       
    }
    
    public void RemoveProductFromTable(){
        
        if(isRowSelected()){
            int indexRowSelected = getSaleView().getProductTable().getSelectedRow();
            if(isDeletionConfirmed()){
                
                Tablemodel.removeRow(indexRowSelected);
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
        System.out.println(Tablemodel.getRowCount());
        SetTotalCost();
    }
        
    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
        
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = getSaleView().getProductTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(getSaleView().getProductTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    public List<String> getPurchases(){
        int numMaxRows = 0;
        String productAndPrice; 
        List purchases = new ArrayList<>();
        for(numMaxRows = 0; numMaxRows < Tablemodel.getRowCount(); numMaxRows++){ 
                productAndPrice ="";
                productAndPrice= productAndPrice.concat(String.valueOf(Tablemodel.getValueAt(numMaxRows, 0)));
                productAndPrice= productAndPrice.concat(String.valueOf(Tablemodel.getValueAt(numMaxRows, 1))); 
                purchases.add(productAndPrice);
               // productAndPrice.split(" ");
        }

        
        return purchases;
    }
    
     //Metodo puesto aqui para prueba 
     
     private void BuildReport(){
         getPurchases();
         ReportHandler rh = ReportHandler.getInstance();
         rh.BuildSaleReport(totalCost);
     }
     
}
