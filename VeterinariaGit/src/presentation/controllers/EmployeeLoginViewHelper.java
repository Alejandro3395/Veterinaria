/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.SessionManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.EmployeeLoginView;

/**
 *
 * @author mannu
 */
public class EmployeeLoginViewHelper extends DataViewHelper {
    private EmployeeLoginView employeeLoginView;
    private static EmployeeLoginViewHelper employeeLoginViewHelper;

    public EmployeeLoginViewHelper(){
        setEmployeeLoginView(new EmployeeLoginView());
        initializeView();
    }
    
     public static EmployeeLoginViewHelper getInstance(){
        if(employeeLoginViewHelper == null) {
         employeeLoginViewHelper = new EmployeeLoginViewHelper();
        }
        return employeeLoginViewHelper;
    }

    public void setEmployeeLoginView(EmployeeLoginView employeeLoginView) {
        this.employeeLoginView = employeeLoginView;
     //   initializeView();
    }

    @Override
    public void loadView() {
        initializeView();
        employeeLoginView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( employeeLoginView );
        employeeLoginView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        employeeLoginView.getLogin_Bttn().addActionListener( actionEvent -> validateEmployeeUserAccess() );
    }
   
    /*Posiblemente habra que separar este metodo en 2*/
    public void validateEmployeeUserAccess(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        boolean isValidField =!isEmptyFields(data);
        boolean isValidUser = false;
        SessionManager sessionManager = SessionManager.GetInstance();
        
        System.out.println("DAta: " + data.get(0) +" "+data.get(1));
        
        
        if(isValidField){  
            isValidUser = sessionManager.employeeUserAuthentification(data);
        }
        
        if(isValidUser){
            openMainMenuView();
        }
        
    }
        
    //Se obtiene la informacion de la vista
    @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String employeeUser = employeeLoginView.getField_UserEmployee().getText();
        data.add(employeeUser);
        
        String employeePassword = new String (employeeLoginView.getField_PassEmployee().getPassword());
        data.add(employeePassword);
        
        return data;
    }
    
     private void openMainMenuView(){
        employeeLoginView.dispose();
        clearFields();
        MainMenuViewHelper mainMenuViewHelper = MainMenuViewHelper.getInstance();
        mainMenuViewHelper.loadView();
    }

     
    @Override
    protected void clearFields() {
        String emptyString = "";
        employeeLoginView.getField_UserEmployee().setText(emptyString);
        employeeLoginView.getField_PassEmployee().setText(emptyString);
    }
     
    
    
    
    
}
