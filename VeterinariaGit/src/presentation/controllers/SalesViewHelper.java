/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineManager;
import bussiness.ReportManager;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
        comboBoxModel= (DefaultComboBoxModel) saleView.getProduct_list().getModel();
        Tablemodel = (DefaultTableModel) saleView.getProductTable().getModel();
        initializeView();
    }
    

    public static SalesViewHelper getInstance(){
        if( salesViewHelper== null) {
         salesViewHelper =  new SalesViewHelper();
        }
        return salesViewHelper;
    }
    
    
    @Override
    protected void setEvents() {
        saleView.getAddProductBttn().addActionListener(actionEvent -> InsertProductToTable());
        saleView.getDeleteProductBttn().addActionListener(actionEvent -> RemoveProductFromTable());
        saleView.getAceptSaleBttn().addActionListener(actionEvent -> BuildReport());
        saleView.getCancelSaleBttn().addActionListener(actionEvent -> closeView());
    }

    public void setSaleView(SalesView sellView) {
        this.saleView = sellView;
    }
    
    public void SetTotalCost(){
        DecimalFormat numDecimales = new DecimalFormat("0.00");
        for(int i=0; i< Tablemodel.getRowCount();i++){
            totalCost = totalCost + (double) Tablemodel.getValueAt(i, 1);
        }
        saleView.getTotalSale_Field().setText(numDecimales.format(totalCost));
    }
    
    public String getSelectedMedicine(){
        String medicineSelected= (String)comboBoxModel.getSelectedItem();    
        return medicineSelected;
    }
    
    @Override
    public void loadView() {
        setSaleView(new SalesView());
        saleView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( saleView );
        saleView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        loadProductsToComboBox();
        setEvents();
    }
   
    public void loadProductsToComboBox(){
        
        comboBoxModel.removeAllElements();
        MedicineManager medicineManager = MedicineManager.GetInstance();
        List<Medicine> productsList =  medicineManager.getMedicines();
        
        for(Medicine medicine : productsList){
            
            String item = medicine.getName() +"  $"+medicine.getCost();
            comboBoxModel.addElement(item);
        }
        
        
        //Ponerlo quizas en un metodo aparte
        saleView.getProduct_list().setModel(comboBoxModel);
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
            int indexRowSelected = saleView.getProductTable().getSelectedRow();
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
        
        int rows = saleView.getProductTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(saleView.getProductTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
     
     
     
     //Metodo puesto aqui para prueba 
     
     private void BuildReport(){
         int numMaxRows = 0;
         int numMaxColums = 0;
          try{
              
            
            PdfWriter.getInstance(reportManager.getDocument(), new FileOutputStream("tablas.pdf"));
            reportManager.getDocument().open();
             
            // Este codigo genera una tabla de 3 columnas
                            
            // Se rellena la tabla
            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
              System.out.println(Tablemodel.getRowCount());
            for(numMaxRows = 0; numMaxRows < Tablemodel.getRowCount(); numMaxRows++){
                
                for(numMaxColums = 0;numMaxColums < Tablemodel.getColumnCount(); numMaxColums++){
                    reportManager.getTable().addCell(String.valueOf( Tablemodel.getValueAt(numMaxRows, numMaxColums)));
                    
                }
                
            }

             
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
             
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total: " + String.valueOf(totalCost)));
             
            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(2);
            reportManager.getTable().addCell(celdaFinal);
             
            // Agregamos la tabla al documento            
            reportManager.getDocument().add(reportManager.getTable());
             
            reportManager.getDocument().close();
             
        }catch(Exception e)
        {
            System.err.println("Ocurrio un error: " +e);
            System.exit(-1);
        }
          
         return;
     }
     
     private void closeView(){
        saleView.dispose();
        MainMenuViewHelper mainMenuViewHelper = MainMenuViewHelper.getInstance();
        mainMenuViewHelper.loadView();
     }
}
