/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.SessionManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.views.EmployeeLoginView;

/**
 *
 * @author mannu
 */
public class EmployeeLoginViewHelper extends AbstractRegisterController {
    EmployeeLoginView employeeLoginView;
    IntroController introController;

    EmployeeLoginViewHelper(){
        setEmployeeLoginView(new EmployeeLoginView());
        setIntroControllerView(new IntroController());
        initializeView();
        
    }

    public EmployeeLoginView getEmployeeLoginView() {
        return employeeLoginView;
    }

    public void setEmployeeLoginView(EmployeeLoginView employeeLoginView) {
        this.employeeLoginView = employeeLoginView;
    }

    public IntroController getIntroController() {
        return introController;
    }

    public void setIntroControllerView(IntroController introController) {
        this.introController = introController;
    }
    
    
    
    @Override
    public void openWindow() {
        getEmployeeLoginView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getEmployeeLoginView() );
        getEmployeeLoginView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getEmployeeLoginView().getLogin_Bttn().addActionListener( actionEvent -> sendDataLoginEmployee() );
    }
    
    
    /*Posiblemente habra que separar este metodo en 2*/
    public void sendDataLoginEmployee(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        boolean isValidField =!isEmptyFields(data);
        boolean isValidUser = false;
        
        
        if(isValidField){
            SessionManager sessionManager = SessionManager.GetInstance();
            isValidUser = sessionManager.EmployeeUserAuthentification(data);
        }
        
        if(isValidUser){
            openIntroView();
        }
        
    }
        

    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        
        String employeeUser = getEmployeeLoginView().getField_UserEmployee().getText();
        data.add(employeeUser);
        
        String employeePassword = new String (getEmployeeLoginView().getField_PassEmployee().getPassword());
        data.add(employeePassword);
        
        return data;
    }
    
     private void openIntroView(){
        getIntroController().openWindow();
    }
    
}
