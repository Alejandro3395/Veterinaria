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
import presentation.views.DoctorLoginView;

/**
 *
 * @author mannu
 */
public class DoctorLoginViewHelper extends AbstractRegisterController{
    private DoctorLoginView doctorLoginView;
    private IntroController introcontroller ;

    DoctorLoginViewHelper(){
        setDoctorLoginView(new DoctorLoginView());
        setIntroController (new IntroController());
        initializeView();
    }
    
    public DoctorLoginView getDoctorLoginView() {
        return doctorLoginView;
    }

    public void setDoctorLoginView(DoctorLoginView doctorLoginView) {
        this.doctorLoginView = doctorLoginView;
    }

    public IntroController getIntroController() {
        return introcontroller;
    }

    public void setIntroController(IntroController introcontroller) {
        this.introcontroller = introcontroller;
    }
    
    
    
    
    @Override
    public void openWindow() {
        getDoctorLoginView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getDoctorLoginView() );
        getDoctorLoginView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
         getDoctorLoginView().getLogin_Bttn().addActionListener(actionEvent -> sendDataLoginDoctor() );
    }

    /*Crear un session manager que verifique las credenciales y haga el cambio de  ventana */
    public void sendDataLoginDoctor(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        boolean isValidField =!isEmptyFields(data);
        boolean isValidUser = false;
        
        
        if(isValidField){
            SessionManager sessionManager = SessionManager.GetInstance();
            isValidUser = sessionManager.ValidateUserDoctor(data);
        }
        
        if(isValidUser){
            openIntroView();
        }
    }
    
    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        
        String doctorUser = getDoctorLoginView().getField_UserDoctor().getText();
        data.add(doctorUser);
        
        String doctorPassword = new String (getDoctorLoginView().getDoctorPassword().getPassword());
        data.add(doctorPassword);
        
        return data;
    }
    
    private void openIntroView(){
        getIntroController().openWindow();
    }
    
    
    
}
