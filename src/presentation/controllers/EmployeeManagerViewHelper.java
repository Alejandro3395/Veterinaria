/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Employee;
import bussiness.EmployeeInformationHandler;
import bussiness.Receptionist;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.EmployeeManagerView;

/**
 *
 * @author Jorge
 */
public class EmployeeManagerViewHelper extends ViewHelper {
    private static EmployeeManagerViewHelper employeeManagerviewHelper = null;
    private EmployeeManagerView employeeManagerView;
    
    private EmployeeManagerViewHelper(){
        setEmployeeManagerView(new EmployeeManagerView());

        initializeView();
    }

    public static EmployeeManagerViewHelper getInstance(){
        if( employeeManagerviewHelper== null) {
         employeeManagerviewHelper = new EmployeeManagerViewHelper();
        }
        return employeeManagerviewHelper;
    }
    
    public EmployeeManagerView getEmployeeManagerView() {
        return employeeManagerView;
    }

    public void setEmployeeManagerView(EmployeeManagerView employeeManagerView) {
        this.employeeManagerView = employeeManagerView;
    }

    

    @Override
    public void loadView() {
        loadEmployeeRecordsToTable();
        getEmployeeManagerView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getEmployeeManagerView() );
        getEmployeeManagerView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getEmployeeManagerView().getBtn_addEmployee().addActionListener(actionEvent -> openRegisterView());
        getEmployeeManagerView().getBtn_modifyEmployee().addActionListener(actionEvent -> openModificationView());
        getEmployeeManagerView().getBtn_deleteEmployee().addActionListener(actionEvent -> openEliminationConfirmationView());
        getEmployeeManagerView().getBtn_back().addActionListener(actionEvent -> closeView());
    }
    
    public void loadEmployeeRecordsToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getEmployeeManagerView().getTable_employeeTable().getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        EmployeeInformationHandler employeeInformationHandler = EmployeeInformationHandler.GetInstance();
        
        ArrayList<Employee> employeeList = employeeInformationHandler.getEmployees() ;
        setTableContent(employeeList);
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            employeeManagerView.dispose();
            ModifyEmployeeInfoViewHelper modifyEmployeeInfoViewHelper = ModifyEmployeeInfoViewHelper.getInstance();
            modifyEmployeeInfoViewHelper.loadView();
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void openEliminationConfirmationView(){
        
        if(isRowSelected()){
            if(isDeletionConfirmed()){
                eliminateEmployee();
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void closeView(){
        getEmployeeManagerView().dispose();
        RegisterSelectionViewHelper.getInstance().loadView();
    }
    
    private void eliminateEmployee(){
        int row = getEmployeeManagerView().getTable_employeeTable().getSelectedRow();
        
        int id = Integer.valueOf( getEmployeeManagerView().getTable_employeeTable().getValueAt(row, 0).toString() );

        EmployeeInformationHandler employeeInformationHandler = EmployeeInformationHandler.GetInstance();
        employeeInformationHandler.remove(id);
        getNotifier().showSuccessMessage("Eliminacion exitosa", "exito al eliminar el Employee");
        updateTable();
    }
    
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = getEmployeeManagerView().getTable_employeeTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(getEmployeeManagerView().getTable_employeeTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    private void setTableContent(ArrayList<Employee> employeeList){    
        
       String actualEmployee =  Receptionist.name;
        
        for(int index =0; index < employeeList.size(); index++ ){
            Employee employeeData = employeeList.get(index) ;
            
            if( !(employeeData.getName().equals(actualEmployee)) ){
                addEmployeeToTable(employeeData);
            }
            
            
        }
    }
    
    private void openRegisterView(){
        employeeManagerView.dispose();
        EmployeeRegisterViewHelper employeeRegisterViewHelper = EmployeeRegisterViewHelper.getInstance();
        employeeRegisterViewHelper.loadView();
    }
    
    private void addEmployeeToTable(Employee employee){
        
        DefaultTableModel model = (DefaultTableModel) getEmployeeManagerView().getTable_employeeTable().getModel();
        
        long id = employee.getId();
        String name = employee.getName();
        String street =  employee.getAddress().getStreet();
        int postal = employee.getAddress().getZipCode();
        String colony = employee.getAddress().getColony();
        String cross = employee.getAddress().getCrossovers();
        String lada = employee.getPhone().getLada();
        String number = employee.getPhone().getNumber();
            
        Object[] row = new Object[]{id,name,street,postal,colony,cross,lada,number };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadEmployeeRecordsToTable();
    }
}
