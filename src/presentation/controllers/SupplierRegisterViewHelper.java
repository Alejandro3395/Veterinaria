/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.SupplierManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.CommonBehaviorViewHelper;
import presentation.views.SupplierRegisterView;

/**
 *
 * @author Jorge
 */
public class SupplierRegisterViewHelper extends DataViewHelper{
    private SupplierRegisterView supplierRegisterView;
    private SupplierManagerViewHelper supplierManagerViewHelper;
    private static SupplierRegisterViewHelper supplierRegisterViewHelper;
    
    private static int supplierDataIndex = 0;
    private static int userSupplierDataIndex = 1;
    
    public SupplierRegisterViewHelper(){
        setSupplierRegisterView(new SupplierRegisterView());
        //setSupplierManagerViewHelper(  SupplierManagerViewHelper.getInstance() );
        
        initializeView();
    }
    
    public static SupplierRegisterViewHelper getInstance(){
        if( supplierRegisterViewHelper== null) {
         supplierRegisterViewHelper = new SupplierRegisterViewHelper();
        }
        return supplierRegisterViewHelper;
    }

    public SupplierManagerViewHelper getSupplierManagerViewHelper() {
        return supplierManagerViewHelper;
    }

    public void setSupplierManagerViewHelper(SupplierManagerViewHelper supplierManagerViewHelper) {
        this.supplierManagerViewHelper = supplierManagerViewHelper;
    }

    public SupplierRegisterView getSupplierRegisterView() {
        return supplierRegisterView;
    }

    public void setSupplierRegisterView(SupplierRegisterView supplierRegisterView) {
        this.supplierRegisterView = supplierRegisterView;
    }
    
    private void updateManagerViewTable(){
        SupplierManagerViewHelper.getInstance().updateTable();
    }
    
    @Override
    public void loadView() {
        getSupplierRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getSupplierRegisterView() );
        getSupplierRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getSupplierRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
        getSupplierRegisterView().getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
        
    }
    
    private void cancelRegistration(){
        closeWindow();
    }
    
    private void closeWindow(){
        getSupplierRegisterView().dispose();
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void proceedWithRegistration(){
        
        ArrayList<String> supplierData = new ArrayList<String>(obtainDataFromView());
        
        boolean isValidField =!isEmptyFields(supplierData);
        
        String message="";
        String successStatus="SUCCESS";
        
        if(isValidField){
            SupplierManager supplierManager = SupplierManager.GetInstance();
            message = supplierManager.registerSupplier(supplierData);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el proveedor");
                updateManagerViewTable();
                resetFields();
                closeWindow();
            }else{
                getNotifier().showWarningMessage( message );
            }
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
        }
    }
    
     /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String companyName = getSupplierRegisterView().getField_supplierName().getText();
        data.add(companyName);
        
        String supplierLada = getSupplierRegisterView().getField_supplierPhoneLada().getText();
        data.add(supplierLada);
        
        String supplierPhone = getSupplierRegisterView().getField_supplierPhoneNumber().getText();
        data.add(supplierPhone);
        
        return data;
    }

    private void resetFields(){
        getSupplierRegisterView().getField_supplierName().setText("");
        
        getSupplierRegisterView().getField_supplierPhoneLada().setText("");
       
        getSupplierRegisterView().getField_supplierPhoneNumber().setText("");
        
    }
}