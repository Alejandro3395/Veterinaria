/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.CommonBehaviorViewHelper;
import presentation.views.DoctorLoginView;
import presentation.views.LoginMainView;

/**
 *
 * @author mannu
 */
public class LoginMainViewHelper extends CommonBehaviorViewHelper{
    private LoginMainView loginMainView;
    private static LoginMainViewHelper loginMainViewHelper;
    private DoctorLoginViewHelper doctorLoginViewHelper;
    private EmployeeLoginViewHelper employeeLoginViewHelper;
    
    public LoginMainViewHelper(){
        setLoginView(new LoginMainView());
        
        setDoctorLoginViewHelper(DoctorLoginViewHelper.getInstance());
        setEmployeeLoginViewHelper(EmployeeLoginViewHelper.getInstance());
        
        initializeView();
    }

    public static LoginMainViewHelper getInstance(){
        if(loginMainViewHelper == null) {
           loginMainViewHelper = new LoginMainViewHelper();
        }
        return loginMainViewHelper;
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
    
    @Override
    protected void setEvents() {
        getLoginView().getDoctorLoginBttn().addActionListener(actionEvent -> openDoctorLoginView());
        getLoginView().getEmployeeLoginBttn().addActionListener( actionEvent -> openEmployeeLoginView());
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
        doctorLoginViewHelper.loadView();
    }
    
    public void openEmployeeLoginView(){
        employeeLoginViewHelper.loadView();
    }
    
}
