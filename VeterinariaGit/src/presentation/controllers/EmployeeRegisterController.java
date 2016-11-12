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
 *
 * @author Jorge
 */
public class EmployeeRegisterController  extends AbstractRegisterController{
    private EmployeeRegisterView employeeRegisterView;
    private EmployeeManagerHelper employeeManagerHelper;
    private static int employeeDataIndex = 0;
    private static int userEmployeeDataIndex = 1;
    
    
    public EmployeeRegisterController(EmployeeManagerHelper employeeManager){
        setEmployeeRegisterView(new EmployeeRegisterView());
        setEmployeeManagerHelper( employeeManager);
        
        initializeView();
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
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Doctor");
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
    
    private ArrayList<ArrayList> parseData(ArrayList<String> data){
        ArrayList<String> doctorData = new ArrayList<String>(data.subList(0, 8));
        ArrayList<String> userDoctorData = new ArrayList<String>(data.subList(8, data.size()));
        
        ArrayList<ArrayList> parsedData = new ArrayList<ArrayList>();
        parsedData.add(doctorData);
        parsedData.add(userDoctorData);

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
        getEmployeeManagerHelper().updateTable();
    }
    
}
