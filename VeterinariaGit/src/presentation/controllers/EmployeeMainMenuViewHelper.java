/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.ViewHelper;
import presentation.views.EmployeeMainMenuView;

/**
 *
 * @author Jorge
 */
public class EmployeeMainMenuViewHelper extends ViewHelper {
    private static EmployeeMainMenuViewHelper employeeMainMenuViewHelper = null;
    private EmployeeMainMenuView employeeMainView;

    
    public EmployeeMainMenuViewHelper (){
        setEmployeeMainMenuView(new EmployeeMainMenuView());

        initializeView();
    }

     public static EmployeeMainMenuViewHelper getInstance(){
        if( employeeMainMenuViewHelper== null) {
         employeeMainMenuViewHelper = new EmployeeMainMenuViewHelper();
        }
        return employeeMainMenuViewHelper;
    }
    


    public void setEmployeeMainMenuView(EmployeeMainMenuView employeeMainView) {
        this.employeeMainView = employeeMainView;
    }

    @Override
    public void loadView() {
        employeeMainView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( employeeMainView);
        employeeMainView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        employeeMainView.getBtn_register().addActionListener(actionEvent -> openRegisterSelection());
        employeeMainView.getBtn_sales().addActionListener(actionEvent -> openSalesView());
        employeeMainView.getBtn_exit().addActionListener(actionEvent -> closeView());
       
    }
    
     private void openSalesView(){
        employeeMainView.dispose();
        SalesViewHelper salesViewHelper = SalesViewHelper.getInstance();
        
        salesViewHelper.loadView();
    }
    
    private void openRegisterSelection(){
        employeeMainView.dispose();
        RegisterSelectionViewHelper registerSelectionViewHelper = RegisterSelectionViewHelper.getInstance();
        registerSelectionViewHelper.loadView();
    }
    
    private void closeView(){
        employeeMainView.dispose();
        LoginMainViewHelper.getInstance().loadView();
    }
    
    
}