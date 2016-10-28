/**
* class: SupplierRegisterController (SupplierRegisterController.java)
* @author: Diego Nicoli
* 
* date: October 27, 2016
* 
* This class represent the controller for the Supplier entitys.
* The objective of the class is to listen the events that the view
* provides and pass the data to the manager class.
* 
*/
package presentation.controllers;


import bussiness.SupplierManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.AbstractViewController;
import presentation.views.SupplierRegisterView;


public class SupplierRegisterController extends AbstractRegisterController{
    private SupplierRegisterView supplierRegisterView;
    
    
    public SupplierRegisterController(){
        setSupplierRegisterView(new SupplierRegisterView());
        
        initializeView();
    }

    public SupplierRegisterView getSupplierRegisterView() {
        return supplierRegisterView;
    }

    public void setSupplierRegisterView(SupplierRegisterView supplierRegisterView) {
        this.supplierRegisterView = supplierRegisterView;
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
        getSupplierRegisterView().getBtn_register().addActionListener(actionEvent -> registerSupplier());
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void registerSupplier(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
          
        
        boolean isValidField =!isEmptyFields(data);
        
        if(isValidField){
            SupplierManager supplierManager = SupplierManager.GetInstance();
                 supplierManager.createSupplier(data);
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
