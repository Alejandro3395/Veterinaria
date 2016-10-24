/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

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
        boolean isValidField =!isEmptyFields(data);
        
        if(isValidField){
            
            
            
        }
    }

    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        
        data.add(getDoctorRegisterView().getField_doctorName().getText());
        
        String doctorPhone = "";
        String phoneLada = getDoctorRegisterView().getField_doctorPhoneLada().getText(); 
        String phoneNumber = getDoctorRegisterView().getField_doctorPhoneNumber().getText();
        doctorPhone = phoneLada +"-" +phoneNumber;
        data.add(doctorPhone);
        
        String doctorAddress ="";
        String addressStreet = getDoctorRegisterView().getField_doctorAddressStreet().getText();
        String addressCross = getDoctorRegisterView().getField_doctorAddressCross().getText();
        String addressColony = getDoctorRegisterView().getField_doctorAddressColony().getText();
        doctorAddress = addressStreet + " " + addressCross + " " + addressColony;
        data.add(doctorAddress);
        
        String doctorPostalCode = getDoctorRegisterView().getField_doctorAddressPostalCode().getText();
        data.add(doctorPostalCode);
        
        String doctorEmail = getDoctorRegisterView().getField_doctorEmail().getText();
        data.add(doctorEmail);
        
        String doctorRFC = getDoctorRegisterView().getField_doctorRFC().getText();
        data.add(doctorRFC);
        
        String doctorProfessionalCode = getDoctorRegisterView().getField_doctorProfessionalCode().getText();
        data.add(doctorProfessionalCode);
        
        String doctorUserName = getDoctorRegisterView().getField_doctorUserName().getText();
        data.add(doctorUserName);
        
        String doctorUserPassword = getDoctorRegisterView().getField_doctorUserPassword().getText();
        data.add(doctorUserPassword);
        
        return data;
    }

    
}
