/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.SupplierManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.AbstractViewController;
import presentation.views.SupplierRegisterView;

/**
 *
 * @author Jorge
 */
public class SupplierRegisterController extends AbstractRegisterController{
    private SupplierRegisterView supplierRegisterView;
    private SupplierManagerHelper supplierManagerHelper;
    
    private static int supplierDataIndex = 0;
    private static int userSupplierDataIndex = 1;
    
    public SupplierRegisterController(SupplierManagerHelper supplierManager){
        setSupplierRegisterView(new SupplierRegisterView());
        setSupplierManagerHelper( supplierManager  );
        
        initializeView();
    }

    public SupplierManagerHelper getSupplierManagerHelper() {
        return supplierManagerHelper;
    }

    public void setSupplierManagerHelper(SupplierManagerHelper supplierManagerHelper) {
        this.supplierManagerHelper = supplierManagerHelper;
    }

    public SupplierRegisterView getSupplierRegisterView() {
        return supplierRegisterView;
    }

    public void setSupplierRegisterView(SupplierRegisterView supplierRegisterView) {
        this.supplierRegisterView = supplierRegisterView;
    }
    
    private void updateManagerViewTable(){
        getSupplierManagerHelper().updateTable();
    }
    
    @Override
    public void openWindow() {
        getSupplierRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getSupplierRegisterView() );
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
        
        ArrayList<String> supplierData = new ArrayList<String>(obtainData());
        
        boolean isValidField =!isEmptyFields(supplierData);
        
        String message="";
        String successStatus="SUCCESS";
        
        if(isValidField){
            SupplierManager supplierManager = SupplierManager.GetInstance();
            message = supplierManager.registerSupplier(supplierData);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el proveedor");
                updateManagerViewTable();
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

}