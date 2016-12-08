/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Employee;
import bussiness.EmployeeInformationHandler;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.InformationViewHelper;
import presentation.views.EmployeeRegisterView;
import exceptions.InvalidFieldException;

/**
 *
 * @author Jorge
 */
public class ModifyEmployeeInfoViewHelper extends InformationViewHelper {
    
    private static ModifyEmployeeInfoViewHelper modifyEmployeeInfoViewHelper = null;
    private EmployeeRegisterView employeeRegisterView;
    
    public ModifyEmployeeInfoViewHelper(){
        setEmployeeRegisterView( new EmployeeRegisterView() );
        initializeView();
    }
    
    public static ModifyEmployeeInfoViewHelper getInstance(){
        if( modifyEmployeeInfoViewHelper== null) {
         modifyEmployeeInfoViewHelper = new ModifyEmployeeInfoViewHelper();
        }
        return modifyEmployeeInfoViewHelper;
    }
    
    public void setEmployeeRegisterView(EmployeeRegisterView employeeRegisterView) {
        this.employeeRegisterView = employeeRegisterView;
    }

    @Override
    public void loadView() {
        loadEmployeeData();
        employeeRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( employeeRegisterView );
        employeeRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        employeeRegisterView.getBtn_register().addActionListener(actionEvent -> modifyEmployee());
        employeeRegisterView.getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadEmployeeData(){
        int row = EmployeeManagerViewHelper.getInstance().getEmployeeManagerView().getTable_employeeTable().getSelectedRow();
        int id = Integer.valueOf( EmployeeManagerViewHelper.getInstance().getEmployeeManagerView().getTable_employeeTable().getValueAt(row, 0).toString() );
        
        EmployeeInformationHandler employeeManager = EmployeeInformationHandler.GetInstance();
        Employee employee =  employeeManager.getEmployee(id) ;
        
        setData(employee);
    }
    
    private void modifyEmployee(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int row = EmployeeManagerViewHelper.getInstance().getEmployeeManagerView().getTable_employeeTable().getSelectedRow();
        
        int id = Integer.valueOf( EmployeeManagerViewHelper.getInstance().getEmployeeManagerView().getTable_employeeTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            
            try{
                EmployeeInformationHandler employeeInformationHandler = EmployeeInformationHandler.GetInstance();
                employeeInformationHandler.modifyEmployee(data,id);
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Employee");
                updateManagerViewTable();
                closeView();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
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
        closeView();
    }
    
    private void closeView(){
        clearFields();
        employeeRegisterView.dispose();
        EmployeeManagerViewHelper.getInstance().loadView();
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
    
          
        return data;
    }
    
    private void setData(Employee employee){
        //setear datos de los campso
        
        String employeeName = employee.getName().toString();
        employeeRegisterView.getField_employeeName().setText(employeeName);
        
        String employeePostalCode = Integer.toString(employee.getAddress().getZipCode());
        employeeRegisterView.getField_employeeAddressPostalCode().setText(employeePostalCode);
        
        String employeeStreet = employee.getAddress().getStreet().toString();
        employeeRegisterView.getField_employeeAddressStreet().setText(employeeStreet);
        
        String employeeColony = employee.getAddress().getColony().toString();
        employeeRegisterView.getField_employeeAddressColony().setText(employeeColony);
        
        String employeeCross = employee.getAddress().getCrossovers().toString();
        employeeRegisterView.getField_employeeAddressCross().setText(employeeCross);
        
        String employeePhoneLada = employee.getPhone().getLada().toString();
        employeeRegisterView.getField_employeePhoneLada().setText(employeePhoneLada);
        
        String employeePhoneNumber = employee.getPhone().getNumber().toString();
        employeeRegisterView.getField_employeePhoneNumber().setText(employeePhoneNumber);
        
        String employeeRFC = employee.getRFC().toString();
        employeeRegisterView.getField_employeeRFC().setText(employeeRFC);
        
        
        String employeeUserName = employee.getUser().getUserName().toString();
        employeeRegisterView.getField_employeeUserName().setText(employeeUserName);
        employeeRegisterView.getField_employeeUserName().setEditable(false);
        
        String employeeUserPassword = employee.getUser().getUserPassword().toString();
        employeeRegisterView.getField_employeeUserPassword().setText(employeeUserPassword);
        employeeRegisterView.getField_employeeUserPassword().setEditable(false);
        
        String employeeUserEmail = employee.getUser().getUserEmail().toString();
        employeeRegisterView.getField_employeeEmail().setText(employeeUserEmail);
        employeeRegisterView.getField_employeeEmail().setEditable(false);  
        
    }
    
    private void resetFields(){
        loadEmployeeData();
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