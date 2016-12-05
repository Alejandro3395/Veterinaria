/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.ViewHelper;
import presentation.views.DoctorLoginView;
import presentation.views.EmployeeLoginView;
import presentation.views.LoginMainView;

/**
 *
 * @author mannu
 */
public class LoginMainViewHelper extends ViewHelper{
    private LoginMainView loginMainView;
    private static LoginMainViewHelper loginMainViewHelper;

    public LoginMainViewHelper(){
        setLoginView(new LoginMainView());
       
        initializeView();
    }

    public static LoginMainViewHelper getInstance(){
        if(loginMainViewHelper == null) {
           loginMainViewHelper = new LoginMainViewHelper();
        }
        return loginMainViewHelper;
    }

    public void setLoginView(LoginMainView loginMainView) {
        this.loginMainView = loginMainView;
    }
    
    @Override
    protected void setEvents() {
        loginMainView.getDoctorLoginBttn().addActionListener(actionEvent -> openDoctorLoginView());
        loginMainView.getEmployeeLoginBttn().addActionListener( actionEvent -> openEmployeeLoginView());
    }
    
    @Override
    public void loadView() {
        loginMainView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( loginMainView );
        loginMainView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    public void openDoctorLoginView(){
        loginMainView.dispose();
        DoctorLoginViewHelper doctorLoginViewHelper = DoctorLoginViewHelper.getInstance();
        doctorLoginViewHelper.loadView();
    }
    
    public void openEmployeeLoginView(){
        loginMainView.dispose();
        EmployeeLoginViewHelper employeeLoginViewHelper = EmployeeLoginViewHelper.getInstance();
        employeeLoginViewHelper.loadView();
        
    }
    
    
    
}