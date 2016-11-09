/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.DoctorLoginView;
import presentation.views.LoginMainView;

/**
 *
 * @author mannu
 */
public class LoginMainViewHelper extends AbstractViewController{
    private LoginMainView loginMainView;
    private DoctorLoginViewHelper doctorLoginViewHelper;
    private EmployeeLoginViewHelper employeeLoginViewHelper;
    
    public LoginMainViewHelper(){
        setLoginView(new LoginMainView());
        setDoctorLoginViewHelper(new DoctorLoginViewHelper());
        setEmployeeLoginViewHelper(new EmployeeLoginViewHelper());
        initializeView();
    }

    public DoctorLoginViewHelper getDoctorLoginViewHelper() {
        return doctorLoginViewHelper;
    }

    public void setDoctorLoginViewHelper(DoctorLoginViewHelper doctorLoginViewHelper) {
        this.doctorLoginViewHelper = doctorLoginViewHelper;
    }
    
    
    private void setLoginView(LoginMainView loginMainView) {
        this.loginMainView = loginMainView;
    }

    public LoginMainView getLoginView() {
        return loginMainView;
    }

    public EmployeeLoginViewHelper getEmployeeLoginViewHelper() {
        return employeeLoginViewHelper;
    }

    public void setEmployeeLoginViewHelper(EmployeeLoginViewHelper employeeLoginViewHelper) {
        this.employeeLoginViewHelper = employeeLoginViewHelper;
    }
    
    
    

    @Override
    public void openWindow() {
        getLoginView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getLoginView() );
        getLoginView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getLoginView().getDoctorLoginBttn().addActionListener(actionEvent -> openDoctorLoginView());
        getLoginView().getEmployeeLoginBttn().addActionListener( actionEvent -> openEmployeeLoginView());
    }

    public void openDoctorLoginView(){
        doctorLoginViewHelper.openWindow();
    }
    
    public void openEmployeeLoginView(){
        employeeLoginViewHelper.openWindow();
    }
    
}
