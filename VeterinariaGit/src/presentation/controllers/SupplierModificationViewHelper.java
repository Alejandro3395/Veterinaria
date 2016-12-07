/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Supplier;
import bussiness.SupplierHandler;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.SupplierRegisterView;
/**
 *
 * @author Jorge
 */
public class SupplierModificationViewHelper extends DataViewHelper {
    private static SupplierModificationViewHelper supplierModificationViewHelper;
    private SupplierRegisterView supplierRegisterView;
    
    private SupplierModificationViewHelper(){
        setSupplierRegisterView( new SupplierRegisterView() );
        
        initializeView();
    }

    public static SupplierModificationViewHelper getInstance(){
        if( supplierModificationViewHelper == null) {
            supplierModificationViewHelper = new SupplierModificationViewHelper();
        }
        return supplierModificationViewHelper;
    }

    public void setSupplierRegisterView(SupplierRegisterView supplierRegisterView) {
        this.supplierRegisterView = supplierRegisterView;
    }

    @Override
    public void loadView() {
        loadSupplierData();
        supplierRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( supplierRegisterView );
        supplierRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        supplierRegisterView.getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        supplierRegisterView.getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadSupplierData(){
        int row = SupplierManagerViewHelper.getInstance().getSupplierManagerView().getTable_supplierTable().getSelectedRow();
        int id = Integer.valueOf( SupplierManagerViewHelper.getInstance().getSupplierManagerView().getTable_supplierTable().getValueAt(row, 0).toString() );
        
        SupplierHandler supplierManager = SupplierHandler.GetInstance();
        Supplier supplier =  supplierManager.getSupplier(id) ;
        
        setData(supplier);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int row = SupplierManagerViewHelper.getInstance().getSupplierManagerView().getTable_supplierTable().getSelectedRow();
        
        int id = Integer.valueOf( SupplierManagerViewHelper.getInstance().getSupplierManagerView().getTable_supplierTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            try{
                SupplierHandler supplierManager = SupplierHandler.GetInstance();
                supplierManager.modifySupplier(data,id);
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Supplier");
                updateManagerViewTable();
                closeWindow();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
                getNotifier().showWarningMessage( message );
            }
            
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void updateManagerViewTable(){
        SupplierManagerViewHelper.getInstance().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        supplierRegisterView.dispose();
        clearFields();
        SupplierManagerViewHelper.getInstance().loadView();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String companyName = supplierRegisterView.getField_supplierName().getText();
        data.add(companyName);
        
        String supplierLada = supplierRegisterView.getField_supplierPhoneLada().getText();
        data.add(supplierLada);
        
        String supplierPhone = supplierRegisterView.getField_supplierPhoneNumber().getText();
        data.add(supplierPhone);
        
        return data;
    }
    
    private void setData(Supplier supplier){
        //setear datos de los campso
        
        String supplierName = supplier.getCompanyName().toString();
        supplierRegisterView.getField_supplierName().setText(supplierName);
        
        String supplierPhoneLada = supplier.getPhone().getLada().toString();
        supplierRegisterView.getField_supplierPhoneLada().setText(supplierPhoneLada);
        
        String supplierPhoneNumber = supplier.getPhone().getNumber().toString();
        supplierRegisterView.getField_supplierPhoneNumber().setText(supplierPhoneNumber);
        
    }
    
    private void resetFields(){
        loadSupplierData();
    }
    
    @Override
    protected void clearFields() {
        supplierRegisterView.getField_supplierName().setText("");
        
        supplierRegisterView.getField_supplierPhoneLada().setText("");
       
        supplierRegisterView.getField_supplierPhoneNumber().setText("");
        
    }
    
}