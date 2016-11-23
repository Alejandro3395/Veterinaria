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
import javax.swing.table.DefaultTableModel;
import presentation.CommonBehaviorViewHelper;
import presentation.views.EmployeeManagerView;

/**
 *
 * @author Jorge
 */
public class EmployeeManagerViewHelper extends CommonBehaviorViewHelper {
    private static EmployeeManagerViewHelper employeeManagerViewHelper = null;
    private EmployeeManagerView employeeManagerView;
    private EmployeeRegisterViewHelper employeeRegisterViewHelper;
    private EmployeeModificationViewHelper employeeModificationViewHelper;
    
    private EmployeeManagerViewHelper(){
        setEmployeeManagerView(new EmployeeManagerView());
        setEmployeeRegisterViewHelper( EmployeeRegisterViewHelper.getInstance());
        
        setEmployeeModificationViewHelper(EmployeeModificationViewHelper.getInstance());
        
        initializeView();
    }

    public static EmployeeManagerViewHelper getInstance(){
        if( employeeManagerViewHelper== null) {
         employeeManagerViewHelper = new EmployeeManagerViewHelper();
        }
        return employeeManagerViewHelper;
    }
    
    public EmployeeManagerView getEmployeeManagerView() {
        return employeeManagerView;
    }

    public void setEmployeeManagerView(EmployeeManagerView employeeManagerView) {
        this.employeeManagerView = employeeManagerView;
    }

    public EmployeeRegisterViewHelper getEmployeeRegisterViewHelper() {
        return employeeRegisterViewHelper;
    }

    public void setEmployeeRegisterViewHelper(EmployeeRegisterViewHelper employeeRegisterViewHelper) {
        this.employeeRegisterViewHelper = employeeRegisterViewHelper;
    }

    public EmployeeModificationViewHelper getEmployeeModificationViewHelper() {
        return employeeModificationViewHelper;
    }

    public void setEmployeeModificationViewHelper(EmployeeModificationViewHelper employeeModificationViewHelper) {
        this.employeeModificationViewHelper = employeeModificationViewHelper;
    }

    

    @Override
    public void loadView() {
        loadEmployeeRegisterToTable();
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
        getEmployeeManagerView().getBtn_back().addActionListener(actionEvent -> closeWindow());
    }
    
    public void loadEmployeeRegisterToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getEmployeeManagerView().getTable_employeeTable().getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        EmployeeManager employeeManager = EmployeeManager.GetInstance();
        
        ArrayList<Employee> employeeList = employeeManager.getEmployees() ;
        setTableContent(employeeList);
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            getEmployeeModificationViewHelper().loadView();
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void openEliminationConfirmationView(){
        
        if(isRowSelected()){
            if(isDeletionConfirmed()){
                proceedWithElimination();
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void closeWindow(){
        getEmployeeManagerView().dispose();
    }
    
    private void proceedWithElimination(){
        int row = getEmployeeManagerView().getTable_employeeTable().getSelectedRow();
        
        int id = Integer.valueOf( getEmployeeManagerView().getTable_employeeTable().getValueAt(row, 0).toString() );

        EmployeeManager employeeManager = EmployeeManager.GetInstance();
        employeeManager.deleteEmployee(id);
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
        for(int index =0; index < employeeList.size(); index++ ){
            Employee employeeData = employeeList.get(index) ;
            addEmployeeToTable(employeeData);
        }
    }
    
    private void openRegisterView(){
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
        System.out.println("llegue");
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadEmployeeRegisterToTable();
    }
}