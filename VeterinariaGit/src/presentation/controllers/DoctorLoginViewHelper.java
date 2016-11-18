/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.SessionManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.OperationalViewHelper;
import presentation.views.DoctorLoginView;

/**
 *
 * @author mannu
 */
public class DoctorLoginViewHelper extends OperationalViewHelper{
    private static DoctorLoginViewHelper doctorLoginViewHelper= null; 
    private DoctorLoginView doctorLoginView;
    private MainMenuViewHelper mainMenuController ;

    public DoctorLoginViewHelper(){
        setDoctorLoginView(new DoctorLoginView());
        setMainMenuController (MainMenuViewHelper.getInstance());
        initializeView();
    }
    
     public static DoctorLoginViewHelper getInstance(){
        if( doctorLoginViewHelper== null) {
         doctorLoginViewHelper = new DoctorLoginViewHelper();
        }
        return doctorLoginViewHelper;
    }
    
    
    public DoctorLoginView getDoctorLoginView() {
        return doctorLoginView;
    }

    public void setDoctorLoginView(DoctorLoginView doctorLoginView) {
        this.doctorLoginView = doctorLoginView;
    }

    public MainMenuViewHelper getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuViewHelper mainMenuController) {
        this.mainMenuController = mainMenuController;
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
            isValidUser = sessionManager.doctorUserAuthentification(data);
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
        mainMenuController.openWindow();
    }
    
    
    
}
