/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Doctor;
import bussiness.DoctorManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.views.DoctorModificationView;

/**
 *
 * @author Jorge
 */
public class DoctorModificationHelper extends AbstractRegisterController {
    private DoctorModificationView doctorModificationView;
    private DoctorManagerHelper doctorManagerHelper;
    private static int modifyOption = 2;
    
    public DoctorModificationHelper(DoctorManagerHelper doctorManager){
        setDoctorModificationView( new DoctorModificationView());
        setDoctorManagerHelper( doctorManager);
        
        initializeView();
    }

    public DoctorModificationView getDoctorModificationView() {
        return doctorModificationView;
    }

    public void setDoctorModificationView(DoctorModificationView doctorModificationView) {
        this.doctorModificationView = doctorModificationView;
    }

    

    public DoctorManagerHelper getDoctorManagerHelper() {
        return doctorManagerHelper;
    }

    public void setDoctorManagerHelper(DoctorManagerHelper doctorManagerHelper) {
        this.doctorManagerHelper = doctorManagerHelper;
    }

    @Override
    public void openWindow() {
        loadDoctorData();
        getDoctorModificationView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getDoctorModificationView() );
        getDoctorModificationView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getDoctorModificationView().getBtn_modify().addActionListener(actionEvent -> proceedWithModification());
        getDoctorModificationView().getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadDoctorData(){
        int row = getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        int id = Integer.valueOf( getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );

        
        DoctorManager doctorManager = DoctorManager.GetInstance();
        Doctor doctor = new Doctor( doctorManager.getDoctor(id) );
        
        setData(doctor);
        

    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        
        int row = getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        
        int id = Integer.valueOf( getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            DoctorManager doctorManager = DoctorManager.GetInstance();
            doctorManager.modifyDoctor(data,id);
            getDoctorManagerHelper().updateTable(modifyOption, id);
        }else{
             message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
        }
    }
    
    private void cancelModification(){
        getDoctorModificationView().dispose();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        
        String doctorName = getDoctorModificationView().getField_doctorName().getText();
        data.add(doctorName);
        
        String doctorPostalCode = getDoctorModificationView().getField_doctorAddressPostalCode().getText();
        data.add(doctorPostalCode);
        
        String doctorAddressStreet = getDoctorModificationView().getField_doctorAddressStreet().getText();
        data.add(doctorAddressStreet);
        
        String doctorAddressColony = getDoctorModificationView().getField_doctorAddressColony().getText();
        data.add(doctorAddressColony);
        
        String doctorAddressCross = getDoctorModificationView().getField_doctorAddressCross().getText();
        data.add(doctorAddressCross);
        
        String doctorPhoneLada = getDoctorModificationView().getField_doctorPhoneLada().getText(); 
        data.add(doctorPhoneLada);
        
        String doctorPhoneNumber = getDoctorModificationView().getField_doctorPhoneNumber().getText();
        data.add(doctorPhoneNumber);
        
        String doctorRFC = getDoctorModificationView().getField_doctorRFC().getText();
        data.add(doctorRFC);
        
        String doctorProfessionalCode = getDoctorModificationView().getField_doctorProfessionalCode().getText();
        data.add(doctorProfessionalCode);
          
        return data;
    }
    
    private void setData(Doctor doctor){
        //setear datos de los campso
        
        String doctorName = doctor.getName().toString();
        getDoctorModificationView().getField_doctorName().setText(doctorName);
        
        String doctorPostalCode = Integer.toString(doctor.getAddress().getZipCode());
        getDoctorModificationView().getField_doctorAddressPostalCode().setText(doctorPostalCode);
        
        String doctorStreet = doctor.getAddress().getStreet().toString();
        getDoctorModificationView().getField_doctorAddressStreet().setText(doctorStreet);
        
        String doctorColony = doctor.getAddress().getColony().toString();
        getDoctorModificationView().getField_doctorAddressColony().setText(doctorColony);
        
        String doctorCross = doctor.getAddress().getCrossovers().toString();
        getDoctorModificationView().getField_doctorAddressCross().setText(doctorCross);
        
        String doctorPhoneLada = doctor.getPhone().getLada().toString();
        getDoctorModificationView().getField_doctorPhoneLada().setText(doctorPhoneLada);
        
        String doctorPhoneNumber = doctor.getPhone().getNumber().toString();
        getDoctorModificationView().getField_doctorPhoneNumber().setText(doctorPhoneNumber);
        
        String doctorRFC = doctor.getRFC().toString();
        getDoctorModificationView().getField_doctorRFC().setText(doctorRFC);
        
        String doctorIdentityCard = doctor.getIdentityCard().toString();
        getDoctorModificationView().getField_doctorProfessionalCode().setText(doctorIdentityCard);
        
        
    }
    
}
