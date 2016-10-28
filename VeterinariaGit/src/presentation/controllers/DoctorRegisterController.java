/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Doctor;
import Entitys.UserDoctor;
import bussiness.DoctorManager;
import presentation.controllers.*;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.views.DoctorRegisterView;

/**
 *
 * @author Jorge
 */
public class DoctorRegisterController extends AbstractRegisterController {
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
        getDoctorRegisterView().getBtn_register().addActionListener(actionEvent -> registerDoctor());
    }
    
    private void registerDoctor(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        
        ArrayList<String> doctorData = new ArrayList<String>(data.subList(0, 9));
        ArrayList<String> userDoctorData = new ArrayList<String>(data.subList(9, data.size()));
        
        
        boolean isValidField =!isEmptyFields(data);
        
        if(isValidField){
            DoctorManager doctorManager = DoctorManager.GetInstance();
                 doctorManager.createEntity(doctorData,userDoctorData);
        }
    }

    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        
        String doctorName = getDoctorRegisterView().getField_doctorName().getText();
        data.add(doctorName);
        
        String doctorPostalCode = getDoctorRegisterView().getField_doctorAddressPostalCode().getText();
        data.add(doctorPostalCode);
        
        String doctorAddressStreet = getDoctorRegisterView().getField_doctorAddressStreet().getText();
        data.add(doctorAddressStreet);
        
        String doctorAddressColony = getDoctorRegisterView().getField_doctorAddressColony().getText();
        data.add(doctorAddressColony);
        
        String doctorAddressCross = getDoctorRegisterView().getField_doctorAddressCross().getText();
        data.add(doctorAddressCross);
        
        String doctorPhoneLada = getDoctorRegisterView().getField_doctorPhoneLada().getText(); 
        data.add(doctorPhoneLada);
        
        String doctorPhoneNumber = getDoctorRegisterView().getField_doctorPhoneNumber().getText();
        data.add(doctorPhoneNumber);
        
        String doctorRFC = getDoctorRegisterView().getField_doctorRFC().getText();
        data.add(doctorRFC);
        
        String doctorProfessionalCode = getDoctorRegisterView().getField_doctorProfessionalCode().getText();
        data.add(doctorProfessionalCode);
        
        String doctorUserName = getDoctorRegisterView().getField_doctorUserName().getText();
        data.add(doctorUserName);
        
        String doctorUserPassword = getDoctorRegisterView().getField_doctorUserPassword().getText();
        data.add(doctorUserPassword);
        
        String doctorUserEmail = getDoctorRegisterView().getField_doctorEmail().getText();
        data.add(doctorUserEmail);
        
        return data;
    }

    
}
