/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.ViewHelper;
import presentation.views.DoctorLoginView;
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

    private void setLoginView(LoginMainView loginMainView) {
        this.loginMainView = loginMainView;
    }
    
    @Override
    protected void setEvents() {
        getLoginView().getDoctorLoginBttn().addActionListener(actionEvent -> openDoctorLoginView());
        getLoginView().getEmployeeLoginBttn().addActionListener( actionEvent -> openEmployeeLoginView());
    }

    public LoginMainView getLoginView() {
        return loginMainView;
    }
    
    @Override
    public void loadView() {
        getLoginView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getLoginView() );
        getLoginView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    public void openDoctorLoginView(){
        DoctorLoginViewHelper doctorLoginViewHelper = DoctorLoginViewHelper.getInstance();
        doctorLoginViewHelper.loadView();
    }
    
    public void openEmployeeLoginView(){
        EmployeeLoginViewHelper employeeLoginViewHelper = EmployeeLoginViewHelper.getInstance();
        employeeLoginViewHelper.loadView();
    }
    
}