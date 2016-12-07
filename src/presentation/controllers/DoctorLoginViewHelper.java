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
import presentation.views.DoctorLoginView;

/**
 *
 * @author mannu
 */
public class DoctorLoginViewHelper extends DataViewHelper{
    private static DoctorLoginViewHelper doctorLoginViewHelper= null; 
    private DoctorLoginView doctorLoginView;

    public DoctorLoginViewHelper(){
        setDoctorLoginView(new DoctorLoginView());
        initializeView();
    }
    
     public static DoctorLoginViewHelper getInstance(){
        if( doctorLoginViewHelper== null) {
         doctorLoginViewHelper = new DoctorLoginViewHelper();
        }
        return doctorLoginViewHelper;
    }

    public void setDoctorLoginView(DoctorLoginView doctorLoginView) {
        this.doctorLoginView = doctorLoginView;
    }

    @Override
    protected void setEvents() {
        doctorLoginView.getLogin_Bttn().addActionListener(actionEvent -> validateDoctorUserAccess() );
        doctorLoginView.getBtn_Cancel().addActionListener(actionEvent -> closeWindow() );

    }
    
    @Override
    public void loadView() {
        doctorLoginView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( doctorLoginView );
        doctorLoginView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    
    private void closeWindow(){
        doctorLoginView.dispose();
        clearFields();
        LoginMainViewHelper.getInstance().loadView();
    }

    public void validateDoctorUserAccess(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        boolean isValidField =!isEmptyFields(data);
        boolean isValidUser = false;
        
        String message = "";
        if(isValidField){
            SessionManager sessionManager = SessionManager.GetInstance();
            isValidUser = sessionManager.doctorUserAuthentification(data);
            if(isValidUser){
                openMainMenuView();
            }else{
                message = "Usuario inexistente";
                getNotifier().showWarningMessage( message );
            }
        }else{
            message = "Datos invalidos";
            getNotifier().showWarningMessage( message );
        }
        
        
    }
    
    @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String doctorUser = doctorLoginView.getField_UserDoctor().getText();
        data.add(doctorUser);
        
        String doctorPassword = new String (doctorLoginView.getDoctorPassword().getPassword());
        data.add(doctorPassword);
        
        return data;
    }
    
   private void openMainMenuView(){
        doctorLoginView.dispose();
        clearFields();
        EmployeeMainMenuViewHelper mainMenuViewHelper = EmployeeMainMenuViewHelper.getInstance();
        mainMenuViewHelper.loadView();
    }

    @Override
    protected void clearFields() {
        String emptyString = "";
        doctorLoginView.getField_UserDoctor().setText(emptyString);
        doctorLoginView.getDoctorPassword().setText(emptyString);
    }
}