/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.ViewHelper;
import presentation.views.DoctorMainMenuView;

/**
 *
 * @author Jorge
 */
public class DoctorMainMenuViewHelper extends ViewHelper {
    private static DoctorMainMenuViewHelper doctorMainMenuViewHelper = null;
    private DoctorMainMenuView doctorMainMenuView;

    
    public DoctorMainMenuViewHelper (){
        setDoctorMainMenuView(new DoctorMainMenuView());

        initializeView();
    }

     public static DoctorMainMenuViewHelper getInstance(){
        if( doctorMainMenuViewHelper== null) {
         doctorMainMenuViewHelper = new DoctorMainMenuViewHelper();
        }
        return doctorMainMenuViewHelper;
    }
    


    public void setDoctorMainMenuView(DoctorMainMenuView doctorMainMenuView) {
        this.doctorMainMenuView = doctorMainMenuView;
    }

    @Override
    public void loadView() {
        doctorMainMenuView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( doctorMainMenuView);
        doctorMainMenuView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        doctorMainMenuView.getBtn_consult().addActionListener(actionEvent -> openAppointmentView());
        doctorMainMenuView.getBtn_exit().addActionListener(actionEvent -> closeView());
       
    }
    
    private void closeView(){
        doctorMainMenuView.dispose();
        LoginMainViewHelper.getInstance().loadView();
    }
    
    
    private void openAppointmentView(){
        doctorMainMenuView.dispose();
        AppointmentManagerViewHelper.getInstance().loadView();
    }
}