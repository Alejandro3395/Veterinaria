/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Employee;
import bussiness.EmployeeManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.EmployeeRegisterView;

/**
 *
 * @author Jorge
 */
public class EmployeeModificationViewHelper extends DataViewHelper {
    
    private static EmployeeModificationViewHelper employeeModificationViewHelper = null;
    private EmployeeRegisterView employeeRegisterView;
    private EmployeeManagerViewHelper employeeManagerViewHelper;
    
    public EmployeeModificationViewHelper(){
        setEmployeeRegisterView( new EmployeeRegisterView() );
        initializeView();
    }
    
    public static EmployeeModificationViewHelper getInstance(){
        if( employeeModificationViewHelper== null) {
         employeeModificationViewHelper = new EmployeeModificationViewHelper();
        }
        return employeeModificationViewHelper;
    }

    public EmployeeRegisterView getEmployeeRegisterView() {
        return employeeRegisterView;
    }

    public void setEmployeeRegisterView(EmployeeRegisterView employeeRegisterView) {
        this.employeeRegisterView = employeeRegisterView;
    }
    
    public EmployeeManagerViewHelper getEmployeeManagerViewHelper() {
        return employeeManagerViewHelper;
    }

    public void setEmployeeManagerViewHelper(EmployeeManagerViewHelper employeeManagerViewHelper) {
        this.employeeManagerViewHelper = employeeManagerViewHelper;
    }

    @Override
    public void loadView() {
        loadEmployeeData();
        getEmployeeRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getEmployeeRegisterView() );
        getEmployeeRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getEmployeeRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        getEmployeeRegisterView().getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadEmployeeData(){
        int row = EmployeeManagerViewHelper.getInstance().getEmployeeManagerView().getTable_employeeTable().getSelectedRow();
        int id = Integer.valueOf( EmployeeManagerViewHelper.getInstance().getEmployeeManagerView().getTable_employeeTable().getValueAt(row, 0).toString() );
        
        EmployeeManager employeeManager = EmployeeManager.GetInstance();
        Employee employee =  employeeManager.getEmployee(id) ;
        
        setData(employee);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int row = EmployeeManagerViewHelper.getInstance().getEmployeeManagerView().getTable_employeeTable().getSelectedRow();
        
        int id = Integer.valueOf( EmployeeManagerViewHelper.getInstance().getEmployeeManagerView().getTable_employeeTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        String successStatus = "SUCCESS";
        
        if( isValidField ){
            EmployeeManager employeeManager = EmployeeManager.GetInstance();
            message = employeeManager.modifyEmployee(data,id);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Employee");
                updateManagerViewTable();
                closeWindow();
            }else{
                getNotifier().showWarningMessage( message );
            } 
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void updateManagerViewTable(){
        EmployeeManagerViewHelper.getInstance().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        getEmployeeRegisterView().dispose();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainDataFromView() {
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
    
          
        return data;
    }
    
    private void setData(Employee employee){
        //setear datos de los campso
        
        String employeeName = employee.getName().toString();
        getEmployeeRegisterView().getField_employeeName().setText(employeeName);
        
        String employeePostalCode = Integer.toString(employee.getAddress().getZipCode());
        getEmployeeRegisterView().getField_employeeAddressPostalCode().setText(employeePostalCode);
        
        String employeeStreet = employee.getAddress().getStreet().toString();
        getEmployeeRegisterView().getField_employeeAddressStreet().setText(employeeStreet);
        
        String employeeColony = employee.getAddress().getColony().toString();
        getEmployeeRegisterView().getField_employeeAddressColony().setText(employeeColony);
        
        String employeeCross = employee.getAddress().getCrossovers().toString();
        getEmployeeRegisterView().getField_employeeAddressCross().setText(employeeCross);
        
        String employeePhoneLada = employee.getPhone().getLada().toString();
        getEmployeeRegisterView().getField_employeePhoneLada().setText(employeePhoneLada);
        
        String employeePhoneNumber = employee.getPhone().getNumber().toString();
        getEmployeeRegisterView().getField_employeePhoneNumber().setText(employeePhoneNumber);
        
        String employeeRFC = employee.getRFC().toString();
        getEmployeeRegisterView().getField_employeeRFC().setText(employeeRFC);
        
        
        String employeeUserName = employee.getUser().getUserName().toString();
        getEmployeeRegisterView().getField_employeeUserName().setText(employeeUserName);
        getEmployeeRegisterView().getField_employeeUserName().setEditable(false);
        
        String employeeUserPassword = employee.getUser().getUserPassword().toString();
        getEmployeeRegisterView().getField_employeeUserPassword().setText(employeeUserPassword);
        getEmployeeRegisterView().getField_employeeUserPassword().setEditable(false);
        
        String employeeUserEmail = employee.getUser().getUserEmail().toString();
        getEmployeeRegisterView().getField_employeeEmail().setText(employeeUserEmail);
        getEmployeeRegisterView().getField_employeeEmail().setEditable(false);  
        
    }
    
    private void resetFields(){
        loadEmployeeData();
    }
}