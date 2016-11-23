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
    protected void setEvents() {
         getDoctorLoginView().getLogin_Bttn().addActionListener(actionEvent -> validateDoctorUserAccess() );
    }
    
    @Override
    public void loadView() {
        getDoctorLoginView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getDoctorLoginView() );
        getDoctorLoginView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    public void validateDoctorUserAccess(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
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
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String doctorUser = getDoctorLoginView().getField_UserDoctor().getText();
        data.add(doctorUser);
        
        String doctorPassword = new String (getDoctorLoginView().getDoctorPassword().getPassword());
        data.add(doctorPassword);
        
        return data;
    }
    
    private void openIntroView(){
        mainMenuController.loadView();
    }
}
