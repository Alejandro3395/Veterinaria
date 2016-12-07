/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineHandler;
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
    private DefaultTableModel Tablemodel;
    private static SalesViewHelper salesViewHelper;
    private double totalCost;
    private SalesManager salesManager;
    
    
    private SalesViewHelper(){
        setSaleView(new SalesView());  
        comboBoxModel= (DefaultComboBoxModel) saleView.getProduct_list().getModel();
        Tablemodel = (DefaultTableModel) saleView.getProductTable().getModel();
        salesManager = SalesManager.getInstance();
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
        saleView.getCancelSaleBttn().addActionListener(actionEvent -> closeWindow());
    }

    private void closeWindow(){
        SalesManager.getInstance().CancelSale(getPurchases());
        saleView.dispose();
        clearTable();
        EmployeeMainMenuViewHelper mainMenuViewHelper = EmployeeMainMenuViewHelper.getInstance();
        mainMenuViewHelper.loadView();
    }
    
    //quitar de aqui
    public void setSaleView(SalesView sellView) {
        this.saleView = sellView;
    }

    
    public void SetTotalCost(){
        DecimalFormat numDecimales = new DecimalFormat("0.00");
        SalesManager salesManager = SalesManager.getInstance();
        totalCost = salesManager.calculateAmountToPay(getPurchases());
        saleView.getTotalSale_Field().setText(numDecimales.format(totalCost));
    }
    
    public String getSelectedMedicine(){
        String medicineSelected= (String)comboBoxModel.getSelectedItem();    
        return medicineSelected;
    }
    
    @Override
    public void loadView() {
        loadProducts();
        saleView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( saleView );
        saleView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
   
    public void loadProducts(){
        
        comboBoxModel.removeAllElements();
        MedicineHandler medicineManager = MedicineHandler.GetInstance();
        List<Medicine> productsList =  medicineManager.getMedicines();
        
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
       Tablemodel.addRow(rowMedicine);
       SetTotalCost();
       }
       
    }
    
    public void RemoveProductFromTable(){
        
        if(isRowSelected()){
            int indexRowSelected = saleView.getProductTable().getSelectedRow();
            if(isDeletionConfirmed()){
                salesManager.removeProductToPurchase((String) Tablemodel.getValueAt(indexRowSelected, 0));
                
                Tablemodel.removeRow(indexRowSelected);
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
        
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
    
    public List<String> getPurchases(){
        int numMaxRows = 0;
        String productAndPrice; 
        List purchases = new ArrayList<>();
        for(numMaxRows = 0; numMaxRows < Tablemodel.getRowCount(); numMaxRows++){ 
                productAndPrice ="";
                productAndPrice= productAndPrice.concat(String.valueOf(Tablemodel.getValueAt(numMaxRows, 0)));
                productAndPrice= productAndPrice.concat(String.valueOf(Tablemodel.getValueAt(numMaxRows, 1))); 
                purchases.add(productAndPrice);
        }

        
        return purchases;
    }
    
     //Metodo puesto aqui para prueba 
     // Checar nombres
     private void BuildReport(){
         getPurchases();
         ReportHandler rh = ReportHandler.getInstance();
        
         rh.BuildSaleReport(SalesManager.getInstance().calculateAmountToPay(getPurchases()));
         
         closeWindow();
     }
     
     private void clearTable(){
         Tablemodel.setRowCount(0);
     }
     
}
