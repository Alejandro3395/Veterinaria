/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.SupplierInformationHandler;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.InformationViewHelper;
import presentation.ViewHelper;
import presentation.views.SupplierRegisterView;

/**
 *
 * @author Jorge
 */
public class SupplierRegisterViewHelper extends InformationViewHelper{
    private SupplierRegisterView supplierRegisterView;
    private static SupplierRegisterViewHelper supplierRegisterViewHelper;
    
    private static int supplierDataIndex = 0;
    private static int userSupplierDataIndex = 1;
    
    private SupplierRegisterViewHelper(){
        setSupplierRegisterView(new SupplierRegisterView());
        initializeView();
    }
    
    public static SupplierRegisterViewHelper getInstance(){
        if( supplierRegisterViewHelper== null) {
         supplierRegisterViewHelper = new SupplierRegisterViewHelper();
        }
        return supplierRegisterViewHelper;
    }

    public void setSupplierRegisterView(SupplierRegisterView supplierRegisterView) {
        this.supplierRegisterView = supplierRegisterView;
    }
    
    
    @Override
    public void loadView() {
        supplierRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( supplierRegisterView );
        supplierRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        supplierRegisterView.getBtn_register().addActionListener(actionEvent -> registerSupplier());
        supplierRegisterView.getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
        
    }
    
    private void cancelRegistration(){
        closeView();
    }
    
    private void closeView(){
        clearFields();
        supplierRegisterView.dispose();
        SupplierManagerViewHelper.getInstance().loadView();
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void registerSupplier(){
        
        ArrayList<String> supplierData = new ArrayList<String>(obtainDataFromView());
        
        boolean isValidField =!isEmptyFields(supplierData);
        
        String message="";
        
        if(isValidField){
            try{
                SupplierInformationHandler supplierInformationHandler = SupplierInformationHandler.GetInstance();
                supplierInformationHandler.registerSupplier(supplierData);
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el proveedor");
                closeView();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
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
        
        String companyName = supplierRegisterView.getField_supplierName().getText();
        data.add(companyName);
        
        String supplierLada = supplierRegisterView.getField_supplierPhoneLada().getText();
        data.add(supplierLada);
        
        String supplierPhone = supplierRegisterView.getField_supplierPhoneNumber().getText();
        data.add(supplierPhone);
        
        return data;
    }

    @Override
    protected void clearFields() {
        supplierRegisterView.getField_supplierName().setText("");
        
        supplierRegisterView.getField_supplierPhoneLada().setText("");
       
        supplierRegisterView.getField_supplierPhoneNumber().setText("");
        
    }
}