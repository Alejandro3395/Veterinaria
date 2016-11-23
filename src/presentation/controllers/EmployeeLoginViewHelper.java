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
    private MainMenuViewHelper mainMenuController ;

    public EmployeeLoginViewHelper(){
        setEmployeeLoginView(new EmployeeLoginView());
        setMainMenuController(MainMenuViewHelper.getInstance());
        initializeView();
        
    }
    
     public static EmployeeLoginViewHelper getInstance(){
        if(employeeLoginViewHelper == null) {
         employeeLoginViewHelper = new EmployeeLoginViewHelper();
        }
        return employeeLoginViewHelper;
    }

    public EmployeeLoginView getEmployeeLoginView() {
        return employeeLoginView;
    }

    public void setEmployeeLoginView(EmployeeLoginView employeeLoginView) {
        this.employeeLoginView = employeeLoginView;
    }

    public MainMenuViewHelper getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuViewHelper mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    @Override
    public void loadView() {
        getEmployeeLoginView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getEmployeeLoginView() );
        getEmployeeLoginView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getEmployeeLoginView().getLogin_Bttn().addActionListener( actionEvent -> validateEmployeeUserAccess() );
    }
   
    /*Posiblemente habra que separar este metodo en 2*/
    public void validateEmployeeUserAccess(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        boolean isValidField =!isEmptyFields(data);
        boolean isValidUser = false;
        SessionManager sessionManager = SessionManager.GetInstance();
        
        
        if(isValidField){  
            isValidUser = sessionManager.employeeUserAuthentification(data);
        }
        
        if(isValidUser){
            openIntroView();
        }
        
    }
        
    //Se obtiene la informacion de la vista
    @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String employeeUser = getEmployeeLoginView().getField_UserEmployee().getText();
        data.add(employeeUser);
        
        String employeePassword = new String (getEmployeeLoginView().getField_PassEmployee().getPassword());
        data.add(employeePassword);
        
        return data;
    }
    
     private void openIntroView(){
        mainMenuController.loadView();
    }
    
}
