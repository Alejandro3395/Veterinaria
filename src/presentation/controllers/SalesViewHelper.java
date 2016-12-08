/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineInformationHandler;
import bussiness.ReportHandler;
import bussiness.SalesManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private DefaultTableModel tablemodel;
    private static SalesViewHelper salesViewHelper;
    private double totalCost;
    private SalesManager salesManager;
    
      private SalesViewHelper(){
        saleView  = new SalesView();  
        comboBoxModel= (DefaultComboBoxModel) saleView.getProduct_list().getModel();
        tablemodel = (DefaultTableModel) saleView.getProductTable().getModel();
        salesManager = SalesManager.getInstance();
        initializeView();
    }
     
    
    public static SalesViewHelper getInstance(){
        if( salesViewHelper== null) {
         salesViewHelper =  new SalesViewHelper();
        }
        return salesViewHelper;
    }
    
    public String getSelectedMedicine(){
        String medicineSelected = (String)comboBoxModel.getSelectedItem();    
        return medicineSelected;
    }
    
    public void loadProducts(){
        
        comboBoxModel.removeAllElements();
        MedicineInformationHandler medicineHandler = MedicineInformationHandler.GetInstance();
        List<Medicine> productsList =  medicineHandler.getMedicines();
        
        for(Medicine medicine : productsList){
            
            String item = medicine.getName() +"  $"+medicine.getCost();
            comboBoxModel.addElement(item);
        }

        saleView.getProduct_list().setModel(comboBoxModel);
    }
    
    public void InsertProductToTable(){
       String medicineSelected= getSelectedMedicine();
       String[] medicineDataRow = medicineSelected.split("\\$");
       String medicineName= medicineDataRow[0];
       double medicinePrice = Double.valueOf(medicineDataRow[1]);
       
       if(salesManager.addProductToPurchase(medicineName)){
            Object[] rowMedicine = new Object[]{medicineName,medicinePrice};
            tablemodel.addRow(rowMedicine);
            SetTotalCost();
       }else{
           getNotifier().showWarningMessage("Se han agotado las unidades para el producto elegido");
       }
       
    }
    
    public void RemoveProductFromTable(){
        
        if(isRowSelected()){
            int indexRowSelected = saleView.getProductTable().getSelectedRow();
            if(isDeletionConfirmed()){
                salesManager.removeProductToPurchase((String) tablemodel.getValueAt(indexRowSelected, 0));
                
                tablemodel.removeRow(indexRowSelected);
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
        
        SetTotalCost();
    }

    public List<Medicine> getPurchasesInformation(){
        int numMaxRows = 0;
        String medicineName; 
        List products = new ArrayList<>();
        for(numMaxRows = 0; numMaxRows < tablemodel.getRowCount(); numMaxRows++){  
                
            medicineName = String.valueOf(tablemodel.getValueAt(numMaxRows, 0));
            Medicine medicine = MedicineInformationHandler.GetInstance().getMedicineByName(medicineName);
            products.add(medicine);
        }
        return products;
    }
    
    @Override
    public void loadView() {
        loadProducts();
        saleView.setVisible(true);
    }
    
    @Override
    protected void setEvents() {
        saleView.getAddProductBttn().addActionListener(actionEvent -> InsertProductToTable());
        saleView.getDeleteProductBttn().addActionListener(actionEvent -> RemoveProductFromTable());
        saleView.getAceptSaleBttn().addActionListener(actionEvent -> CompleteSale());
        saleView.getCancelSaleBttn().addActionListener(actionEvent -> closeView());
    }

    @Override
    protected void initializeView() {
        configureView( saleView );
        saleView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
   
    
    
    
     //Metodo puesto aqui para prueba 
     // Checar nombres
     private void BuildReport(){
         ReportHandler reportInfoHandler = ReportHandler.getInstance();
        
         reportInfoHandler.BuildSaleReport(totalCost);
         
         closeView();
     }
     
     private void clearTable(){
         tablemodel.setRowCount(0);
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
     
     private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
     
     private void closeView(){
        SalesManager.getInstance().CancelSale(getPurchasesInformation());
        saleView.dispose();
        clearTable();
        EmployeeMainMenuViewHelper mainMenuViewHelper = EmployeeMainMenuViewHelper.getInstance();
        mainMenuViewHelper.loadView();
    }
     
    private void SetTotalCost(){    
        SalesManager salesManager = SalesManager.getInstance();
        totalCost = Double.valueOf(salesManager.calculateAmountToPay(getPurchasesInformation()));
        DecimalFormat numDecimales = new DecimalFormat("0.00");
        System.out.println("costo: "+ totalCost);
        saleView.getTotalSale_Field().setText(numDecimales.format(totalCost));
    }
    
    private boolean isPurchaseTableEmpty(){
        boolean isEmpty = false;
        int numProductsInTable = tablemodel.getRowCount();
        if( numProductsInTable == 0){
            isEmpty= true;
        }
        return isEmpty;
    }
    
    private void CompleteSale(){
        if(isPurchaseTableEmpty()){
            getNotifier().showWarningMessage( "Porfavor agregue minimo un producto a la lista para realizar la venta" );
        }else{
            BuildReport();
        }
        
    }
    
  
}
