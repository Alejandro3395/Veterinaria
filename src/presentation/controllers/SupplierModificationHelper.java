/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Supplier;
import bussiness.SupplierManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.views.SupplierRegisterView;
/**
 *
 * @author Jorge
 */
public class SupplierModificationHelper extends AbstractRegisterController {
    private SupplierRegisterView supplierRegisterView;
    private SupplierManagerHelper supplierManagerHelper;
    
    public SupplierModificationHelper(SupplierManagerHelper supplierManager){
        setSupplierRegisterView( new SupplierRegisterView() );
        setSupplierManagerHelper( supplierManager);
        
        initializeView();
    }

    public SupplierRegisterView getSupplierRegisterView() {
        return supplierRegisterView;
    }

    public void setSupplierRegisterView(SupplierRegisterView supplierRegisterView) {
        this.supplierRegisterView = supplierRegisterView;
    }

    public SupplierManagerHelper getSupplierManagerHelper() {
        return supplierManagerHelper;
    }

    public void setSupplierManagerHelper(SupplierManagerHelper supplierManagerHelper) {
        this.supplierManagerHelper = supplierManagerHelper;
    }

    @Override
    public void openWindow() {
        loadSupplierData();
        getSupplierRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getSupplierRegisterView() );
        getSupplierRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getSupplierRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        getSupplierRegisterView().getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadSupplierData(){
        int row = getSupplierManagerHelper().getSupplierManagerView().getTable_supplierTable().getSelectedRow();
        int id = Integer.valueOf( getSupplierManagerHelper().getSupplierManagerView().getTable_supplierTable().getValueAt(row, 0).toString() );
        
        SupplierManager supplierManager = SupplierManager.GetInstance();
        Supplier supplier =  supplierManager.getSupplier(id) ;
        
        setData(supplier);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        
        int row = getSupplierManagerHelper().getSupplierManagerView().getTable_supplierTable().getSelectedRow();
        
        int id = Integer.valueOf( getSupplierManagerHelper().getSupplierManagerView().getTable_supplierTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        String successStatus = "SUCCESS";
        
        if( isValidField ){
            SupplierManager supplierManager = SupplierManager.GetInstance();
            message = supplierManager.modifySupplier(data,id);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Supplier");
                updateManagerViewTable();
                closeWindow();
            }else{
                getNotifier().showWarningMessage( message );
            } 
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void updateManagerViewTable(){
        getSupplierManagerHelper().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        getSupplierRegisterView().dispose();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        
        String companyName = getSupplierRegisterView().getField_supplierName().getText();
        data.add(companyName);
        
        String supplierLada = getSupplierRegisterView().getField_supplierPhoneLada().getText();
        data.add(supplierLada);
        
        String supplierPhone = getSupplierRegisterView().getField_supplierPhoneNumber().getText();
        data.add(supplierPhone);
        
        return data;
    }
    
    private void setData(Supplier supplier){
        //setear datos de los campso
        
        String supplierName = supplier.getCompanyName().toString();
        getSupplierRegisterView().getField_supplierName().setText(supplierName);
        
        String supplierPhoneLada = supplier.getPhone().getLada().toString();
        getSupplierRegisterView().getField_supplierPhoneLada().setText(supplierPhoneLada);
        
        String supplierPhoneNumber = supplier.getPhone().getNumber().toString();
        getSupplierRegisterView().getField_supplierPhoneNumber().setText(supplierPhoneNumber);
        
    }
    
    private void resetFields(){
        loadSupplierData();
    }
    
}