/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.DoctorRegisterView;

/**
 *
 * @author Jorge
 */
public class DoctorRegisterController extends AbstractViewController {
    private DoctorRegisterView doctorRegisterView;
    
    
    public DoctorRegisterController(){
        setDoctorRegisterView(new DoctorRegisterView());
        
        initializeView();
    }

    public DoctorRegisterView getDoctorRegisterView() {
        return doctorRegisterView;
    }

    public void setDoctorRegisterView(DoctorRegisterView doctorRegisterView) {
        this.doctorRegisterView = doctorRegisterView;
    }
    
    
    
    @Override
    public void openWindow() {
        getDoctorRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getDoctorRegisterView() );
        getDoctorRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        //getEmployeeRegisterView().getBtn_accept().addActionListener(actionEvent -> getValue());
    }
    
}
