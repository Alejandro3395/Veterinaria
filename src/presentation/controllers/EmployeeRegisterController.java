/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.EmployeeRegisterView;

/**
 *
 * @author Jorge
 */
public class EmployeeRegisterController  extends AbstractViewController{
    private EmployeeRegisterView employeeRegisterView;
    
    public EmployeeRegisterController(){
        setEmployeeRegisterView(new EmployeeRegisterView());
        
        initializeView();
    }

    public EmployeeRegisterView getEmployeeRegisterView() {
        return employeeRegisterView;
    }

    public void setEmployeeRegisterView(EmployeeRegisterView employeeRegisterView) {
        this.employeeRegisterView = employeeRegisterView;
    }
    
    
    
    @Override
    public void openWindow() {
        getEmployeeRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getEmployeeRegisterView() );
        getEmployeeRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        //getEmployeeRegisterView().getBtn_accept().addActionListener(actionEvent -> getValue());
    }
}
