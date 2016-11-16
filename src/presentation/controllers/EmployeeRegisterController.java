/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.EmployeeManager;
import java.util.ArrayList;
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
    private static EmployeeRegisterController employeeRegisterController = null;

    private EmployeeManagerHelper employeeManagerHelper;
    private static int employeeDataIndex = 0;
    private static int userEmployeeDataIndex = 1;
    private EmployeeRegisterView employeeRegisterView;
    
    
    public EmployeeRegisterController(){
        setEmployeeRegisterView(new EmployeeRegisterView());
        
        initializeView();
    }

    public static EmployeeRegisterController getInstance(){
        if( employeeRegisterController== null) {
         employeeRegisterController = new EmployeeRegisterController();
        }
        return employeeRegisterController;
    }
    
    public EmployeeManagerHelper getEmployeeManagerHelper() {
        return employeeManagerHelper;
    }

    public void setEmployeeManagerHelper(EmployeeManagerHelper employeeManagerHelper) {
        this.employeeManagerHelper = employeeManagerHelper;
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
        getEmployeeRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
    }
    
    private void proceedWithRegistration(){
        ArrayList<String> data = new ArrayList<String>(obtainData());

        ArrayList<ArrayList> parsedData = new ArrayList<ArrayList>(parseData(data));

        ArrayList<String> employeeData = new ArrayList<String>(parsedData.get(employeeDataIndex));
        ArrayList<String> userEmployeeData = new ArrayList<String>(parsedData.get(userEmployeeDataIndex));
        
        boolean isValidField =!isEmptyFields(data);
        
        String message="";
        String successStatus="SUCCESS";
        
        if(isValidField){
            EmployeeManager employeeManager = EmployeeManager.GetInstance();
            message = employeeManager.registerEmployee(employeeData,userEmployeeData);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Empleado");
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
        
        String employeeUserPassword = getEmployeeRegisterView().getField_employeeUserPassword().getText();
        data.add(employeeUserPassword);
        
        String employeeUserEmail = getEmployeeRegisterView().getField_employeeEmail().getText();
        data.add(employeeUserEmail);
        
        return data;
    }

    private void closeWindow() {
        getEmployeeRegisterView().dispose();
    }

    private void updateManagerViewTable() {
        EmployeeManagerHelper.getInstance().updateTable();
    }
    
    private void resetFields(){
        getEmployeeRegisterView().getField_employeeName().setText("");
        
        getEmployeeRegisterView().getField_employeeAddressPostalCode().setText("");
        
        getEmployeeRegisterView().getField_employeeAddressStreet().setText("");
        
        getEmployeeRegisterView().getField_employeeAddressColony().setText("");
        
        getEmployeeRegisterView().getField_employeeAddressCross().setText("");
        
        getEmployeeRegisterView().getField_employeePhoneLada().setText(""); 
        
        getEmployeeRegisterView().getField_employeePhoneNumber().setText("");
        
        getEmployeeRegisterView().getField_employeeRFC().setText("");

        getEmployeeRegisterView().getField_employeeUserName().setText("");
        
        getEmployeeRegisterView().getField_employeeUserPassword().setText("");
        
        getEmployeeRegisterView().getField_employeeEmail().setText("");
    }
}
    

