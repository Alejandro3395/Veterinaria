/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.CustomerRegisterView;

/**
 *
 * @author Jorge
 */
public class CustomerRegisterController extends AbstractViewController{
    private CustomerRegisterView customerRegisterView;
    
    public CustomerRegisterController(){
        setCustomerRegisterView(new CustomerRegisterView());
        
        initializeView();
    }

    public CustomerRegisterView getCustomerRegisterView() {
        return customerRegisterView;
    }

    public void setCustomerRegisterView(CustomerRegisterView customerRegisterView) {
        this.customerRegisterView = customerRegisterView;
    }
    
    @Override
    public void openWindow() {
        getCustomerRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getCustomerRegisterView() );
        getCustomerRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getCustomerRegisterView().getBtn_register().addActionListener(actionEvent -> registerCustomer());
    }
    
    private void registerCustomer(){
        
    }
    
}
