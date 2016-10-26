/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.SupplierRegisterView;

/**
 *
 * @author Jorge
 */
public class SupplierRegisterController extends AbstractViewController{
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

    @Override
    protected void setEvents() {
        //getEmployeeRegisterView().getBtn_accept().addActionListener(actionEvent -> getValue());
    }
}
