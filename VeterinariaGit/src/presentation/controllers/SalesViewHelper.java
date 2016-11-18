/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.TransitionalViewHelper;
import presentation.views.SalesView;

/**
 *
 * @author mannu
 */
public class SalesViewHelper extends TransitionalViewHelper{
    private JComboBox comboBoxProducts ;
    private SalesView saleView;
    private DefaultComboBoxModel comboBoxModel;
    private DefaultTableModel Tablemodel;
    private  static SalesViewHelper salesViewHelper;
    
    
    public SalesViewHelper(){
        setSaleView(new SalesView());
        comboBoxModel= (DefaultComboBoxModel) getSaleView().getProduct_list().getModel();
        Tablemodel = (DefaultTableModel) getSaleView().getProductTable().getModel();
        loadProductsToComboBox();
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

    public void setSaleView(SalesView sellView) {
        this.saleView = sellView;
    }
    
    
    
    
    @Override
    public void openWindow() {
        saleView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getSaleView() );
        getSaleView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getSaleView().getAddProductBttn().addActionListener(actionEvent -> AddProductToTable());
        getSaleView().getDeleteProductBttn().addActionListener(actionEvent -> DeleteProductFromTable());
    }
    

    
    public void loadProductsToComboBox(){
        
        comboBoxModel.removeAllElements();
        MedicineManager medicineManager = MedicineManager.GetInstance();
        List<Medicine> productsList =  medicineManager.getMedicineList();
        
        for(Medicine medicine : productsList){
            
            String item = medicine.getName() +"  $"+medicine.getCost();
            comboBoxModel.addElement(item);
        }
        
        
        //Ponerlo quizas en un metodo aparte
        getSaleView().getProduct_list().setModel(comboBoxModel);
    }
    
    public void AddProductToTable(){
       String medicineSelected= getSelectedMedicine();
       String[] medicineDataRow = medicineSelected.split("\\$");
       String medicineName= medicineDataRow[0];
       double medicinePrice = Double.valueOf(medicineDataRow[1]);
        System.out.println(medicinePrice);
       Object[] rowMedicine = new Object[]{medicineName,medicinePrice};
       Tablemodel.addRow(rowMedicine);
       SetTotalCost();
       
    }
    
    public void DeleteProductFromTable(){
        
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
    
    public void SetTotalCost(){
       double totalCost=0;
       for(int i=0; i< Tablemodel.getRowCount();i++){
           totalCost = totalCost + (double) Tablemodel.getValueAt(i, 1);
       }
       
       getSaleView().getTotalSale_Field().setText(Double.toString(totalCost));
    }
            
    
     private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public String getSelectedMedicine(){
        String medicineSelected= (String)comboBoxModel.getSelectedItem();
        
        return medicineSelected;
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
    
}
