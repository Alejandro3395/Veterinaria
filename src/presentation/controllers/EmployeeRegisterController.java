/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.EmployeeManager;
import exceptions.InvalidFieldException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.AbstractViewController;
import presentation.views.EmployeeRegisterView;

/**
* class: EmployeeRegisterController (EmployeeRegisterController.java)
* @author: Manuel Bojorquez
* 
* date: October 27, 2016
* 
* This class represent the controller for the client entitys.
* The objective of the class is to listen the events that the view
* provides and pass the data to the manager class.
* 
*/
public class EmployeeRegisterController  extends AbstractRegisterController{
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

    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getEmployeeRegisterView().getBtn_register().addActionListener(actionEvent -> {
            try {
                registerEmployee();
            } catch (InvalidFieldException ex) {
                Logger.getLogger(EmployeeRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void registerEmployee() throws InvalidFieldException {
        
          ArrayList<String> data = new ArrayList<String>(obtainData());
        
        ArrayList<String> employeeData = new ArrayList<String>(data.subList(0, 8));
        ArrayList<String> userEmployeeData = new ArrayList<String>(data.subList(8, data.size()));
        
        
        boolean isValidField =!isEmptyFields(data);
        
        if(isValidField){
            EmployeeManager employeeManager = EmployeeManager.GetInstance();
                 employeeManager.createEntity(employeeData,userEmployeeData);
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
        
        String employeeName = getEmployeeRegisterView().getField_employeeName().getText();
        data.add(employeeName);
        
        String employeePostalCode = getEmployeeRegisterView().getField_employeeAddressPostalCode().getText();
        data.add(employeePostalCode);
        
        String employeeAddressStreet = getEmployeeRegisterView().getField_employeeAddressStreet().getText();
        data.add(employeeAddressStreet);
        
        String employeeAddressColony = getEmployeeRegisterView().getField_employeeAddressColony().getText();
        data.add(employeeAddressColony);
        
        String employeeAddressCross = getEmployeeRegisterView().getField_employeeAddressCross().getText();
        data.add(employeeAddressCross);
        
        String employeePhoneLada = getEmployeeRegisterView().getField_employeePhoneLada().getText(); 
        data.add(employeePhoneLada);
        
        String employeePhoneNumber = getEmployeeRegisterView().getField_employeePhoneNumber().getText();
        data.add(employeePhoneNumber);
        
        String employeeRFC = getEmployeeRegisterView().getField_employeeRFC().getText();
        data.add(employeeRFC);
        
        /* Se obtienen los datos del usuario tipo cliente*/
        
        String employeeUserName = getEmployeeRegisterView().getField_employeeUserName().getText();
        data.add(employeeUserName);
        
        String employeeUserPassword = getEmployeeRegisterView().getField_employeePassword().getText();
        data.add(employeeUserPassword);
        
        String employeeUserEmail = getEmployeeRegisterView().getField_employeeEmail().getText();
        data.add(employeeUserEmail);
        
        return data;
    }
    
}
