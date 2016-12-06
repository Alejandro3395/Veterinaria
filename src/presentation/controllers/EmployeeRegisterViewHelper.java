/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.EmployeeHandler;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.ViewHelper;
import presentation.views.EmployeeRegisterView;

/**
* class: EmployeeRegisterViewHelper (EmployeeRegisterViewHelper.java)
* @author: Manuel Bojorquez
* 
* date: October 27, 2016
* 
* This class represent the controller for the client entitys.
* The objective of the class is to listen the events that the view
* provides and pass the data to the manager class.
* 
*/
public class EmployeeRegisterViewHelper  extends DataViewHelper{
    private static EmployeeRegisterViewHelper employeeRegisterViewHelper = null;
    private static int employeeDataIndex = 0;
    private static int userEmployeeDataIndex = 1;
    private EmployeeRegisterView employeeRegisterView;
    
    
    public EmployeeRegisterViewHelper(){
        setEmployeeRegisterView(new EmployeeRegisterView());
        
        initializeView();
    }

    public static EmployeeRegisterViewHelper getInstance(){
        if( employeeRegisterViewHelper== null) {
         employeeRegisterViewHelper = new EmployeeRegisterViewHelper();
        }
        return employeeRegisterViewHelper;
    }

    public void setEmployeeRegisterView(EmployeeRegisterView employeeRegisterView) {
        this.employeeRegisterView = employeeRegisterView;
    }
 
    @Override
    public void loadView() {
        employeeRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( employeeRegisterView );
        employeeRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        employeeRegisterView.getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
    }
    
    private void proceedWithRegistration(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());

        ArrayList<ArrayList> parsedData = new ArrayList<ArrayList>(parseData(data));

        ArrayList<String> employeeData = new ArrayList<String>(parsedData.get(employeeDataIndex));
        ArrayList<String> userEmployeeData = new ArrayList<String>(parsedData.get(userEmployeeDataIndex));
        
        boolean isValidField =!isEmptyFields(data);
        
        String message="";
        
        if(isValidField){
            
            try{
               EmployeeHandler employeeHandler = EmployeeHandler.GetInstance();
               employeeHandler.registerEmployee(employeeData,userEmployeeData);
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Empleado");
                updateManagerViewTable();
                clearFields();
                closeWindow();
            }catch(InvalidFieldException exception){
                message = exception.getMessage() ;
                getNotifier().showWarningMessage( message );
            }
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
        }
    }
    
    private ArrayList<ArrayList> parseData(ArrayList<String> data){
        ArrayList<String> employeeData = new ArrayList<String>(data.subList(0, 8));
        ArrayList<String> userEmployeeData = new ArrayList<String>(data.subList(8, data.size()));
        
        ArrayList<ArrayList> parsedData = new ArrayList<ArrayList>();
        parsedData.add(employeeData);
        parsedData.add(userEmployeeData);

        return parsedData;
    }
    
    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String employeeName = employeeRegisterView.getField_employeeName().getText();
        data.add(employeeName);
        
        String employeePostalCode = employeeRegisterView.getField_employeeAddressPostalCode().getText();
        data.add(employeePostalCode);
        
        String employeeAddressStreet = employeeRegisterView.getField_employeeAddressStreet().getText();
        data.add(employeeAddressStreet);
        
        String employeeAddressColony = employeeRegisterView.getField_employeeAddressColony().getText();
        data.add(employeeAddressColony);
        
        String employeeAddressCross = employeeRegisterView.getField_employeeAddressCross().getText();
        data.add(employeeAddressCross);
        
        String employeePhoneLada = employeeRegisterView.getField_employeePhoneLada().getText(); 
        data.add(employeePhoneLada);
        
        String employeePhoneNumber = employeeRegisterView.getField_employeePhoneNumber().getText();
        data.add(employeePhoneNumber);
        
        String employeeRFC = employeeRegisterView.getField_employeeRFC().getText();
        data.add(employeeRFC);
        
        /* Se obtienen los datos del usuario tipo cliente*/
        
        String employeeUserName = employeeRegisterView.getField_employeeUserName().getText();
        data.add(employeeUserName);
        
        String employeeUserPassword = employeeRegisterView.getField_employeeUserPassword().getText();
        data.add(employeeUserPassword);
        
        String employeeUserEmail = employeeRegisterView.getField_employeeEmail().getText();
        data.add(employeeUserEmail);
        
        return data;
    }

    private void closeWindow() {
        employeeRegisterView.dispose();
        clearFields();
        EmployeeManagerViewHelper.getInstance().loadView();
        
    }

    private void updateManagerViewTable() {
        EmployeeManagerViewHelper.getInstance().updateTable();
    }
    
   @Override
    protected void clearFields() {
        employeeRegisterView.getField_employeeName().setText("");
        
        employeeRegisterView.getField_employeeAddressPostalCode().setText("");
        
        employeeRegisterView.getField_employeeAddressStreet().setText("");
        
        employeeRegisterView.getField_employeeAddressColony().setText("");
        
        employeeRegisterView.getField_employeeAddressCross().setText("");
        
        employeeRegisterView.getField_employeePhoneLada().setText(""); 
        
        employeeRegisterView.getField_employeePhoneNumber().setText("");
        
        employeeRegisterView.getField_employeeRFC().setText("");

        employeeRegisterView.getField_employeeUserName().setText("");
        
        employeeRegisterView.getField_employeeUserPassword().setText("");
        
        employeeRegisterView.getField_employeeEmail().setText("");
    }
}