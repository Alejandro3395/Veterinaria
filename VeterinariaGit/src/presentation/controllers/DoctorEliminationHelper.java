/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Doctor;
import bussiness.DoctorManager;
import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.DoctorEliminationView;

/**
 *
 * @author Jorge
 */
public class DoctorEliminationHelper extends AbstractViewController {
    private DoctorEliminationView doctorEliminationView;
    private DoctorManagerHelper doctorManagerHelper;
    
    public DoctorEliminationHelper(DoctorManagerHelper doctorManager){
        setDoctorEliminationView( new DoctorEliminationView());
        setDoctorManagerHelper( doctorManager);
        
        initializeView();
    }

    public DoctorEliminationView getDoctorEliminationView() {
        return doctorEliminationView;
    }

    public void setDoctorEliminationView(DoctorEliminationView doctorEliminationView) {
        this.doctorEliminationView = doctorEliminationView;
    }

    public DoctorManagerHelper getDoctorManagerHelper() {
        return doctorManagerHelper;
    }

    public void setDoctorManagerHelper(DoctorManagerHelper doctorManagerHelper) {
        this.doctorManagerHelper = doctorManagerHelper;
    }
    
    


    @Override
    public void openWindow() {
       getDoctorEliminationView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getDoctorEliminationView() );
        getDoctorEliminationView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getDoctorEliminationView().getBtn_accept().addActionListener(actionEvent -> proceedWithElimination());
        getDoctorEliminationView().getBtn_cancel().addActionListener(actionEvent -> cancelElimination());
    }
    
    private void proceedWithElimination(){
        int row = getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        
        int id = Integer.valueOf( getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );

        DoctorManager doctorManager = DoctorManager.GetInstance();
        doctorManager.eliminateDoctor(id);
        
        //Doctor doctor = doctorManager.getDoctor(id);
        
        updateTable();
    }
    
    private void cancelElimination(){
        getDoctorEliminationView().dispose();
    }
    
    private void updateTable(){
        closeWindow();
        
    }
    
    
    private void closeWindow(){
        getDoctorManagerHelper().updateTable();

    }
}
