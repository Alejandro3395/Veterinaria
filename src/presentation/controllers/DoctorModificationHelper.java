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
import presentation.views.DoctorRegisterView;

/**
 *
 * @author Jorge
 */
public class DoctorModificationHelper extends AbstractRegisterController {
    private DoctorModificationView doctorModificationView;
    private DoctorRegisterView doctorRegisterView;
    private DoctorManagerHelper doctorManagerHelper;
    
    public DoctorModificationHelper(DoctorManagerHelper doctorManager){
        setDoctorModificationView( new DoctorModificationView());
        setDoctorRegisterView( new DoctorRegisterView() );
        setDoctorManagerHelper( doctorManager);
        
        initializeView();
    }

    public DoctorRegisterView getDoctorRegisterView() {
        return doctorRegisterView;
    }

    public void setDoctorRegisterView(DoctorRegisterView doctorRegisterView) {
        this.doctorRegisterView = doctorRegisterView;
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
        getDoctorRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        getDoctorRegisterView().getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadDoctorData(){
        int row = getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        int id = Integer.valueOf( getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );
        
        DoctorManager doctorManager = DoctorManager.GetInstance();
        Doctor doctor =  doctorManager.getDoctor(id) ;
        
        setData(doctor);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        
        int row = getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        
        int id = Integer.valueOf( getDoctorManagerHelper().getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        String successStatus = "SUCCESS";
        
        if( isValidField ){
            DoctorManager doctorManager = DoctorManager.GetInstance();
            message = doctorManager.modifyDoctor(data,id);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Doctor");
                updateManagerViewTable();
                closeWindow();
            }else{
                getNotifier().showWarningMessage( message );
            } 
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void updateManagerViewTable(){
        getDoctorManagerHelper().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        getDoctorRegisterView().dispose();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
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
          
        return data;
    }
    
    private void setData(Doctor doctor){
        //setear datos de los campso
        
        String doctorName = doctor.getName().toString();
        getDoctorRegisterView().getField_doctorName().setText(doctorName);
        
        String doctorPostalCode = Integer.toString(doctor.getAddress().getZipCode());
        getDoctorRegisterView().getField_doctorAddressPostalCode().setText(doctorPostalCode);
        
        String doctorStreet = doctor.getAddress().getStreet().toString();
        getDoctorRegisterView().getField_doctorAddressStreet().setText(doctorStreet);
        
        String doctorColony = doctor.getAddress().getColony().toString();
        getDoctorRegisterView().getField_doctorAddressColony().setText(doctorColony);
        
        String doctorCross = doctor.getAddress().getCrossovers().toString();
        getDoctorRegisterView().getField_doctorAddressCross().setText(doctorCross);
        
        String doctorPhoneLada = doctor.getPhone().getLada().toString();
        getDoctorRegisterView().getField_doctorPhoneLada().setText(doctorPhoneLada);
        
        String doctorPhoneNumber = doctor.getPhone().getNumber().toString();
        getDoctorRegisterView().getField_doctorPhoneNumber().setText(doctorPhoneNumber);
        
        String doctorRFC = doctor.getRFC().toString();
        getDoctorRegisterView().getField_doctorRFC().setText(doctorRFC);
        
        String doctorIdentityCard = doctor.getIdentityCard().toString();
        getDoctorRegisterView().getField_doctorProfessionalCode().setText(doctorIdentityCard);
        
        String doctorUserName = doctor.getUser().getUserName().toString();
        getDoctorRegisterView().getField_doctorUserName().setText(doctorUserName);
        getDoctorRegisterView().getField_doctorUserName().setEditable(false);
        
        String doctorUserPassword = doctor.getUser().getUserPassword().toString();
        getDoctorRegisterView().getField_doctorUserPassword().setText(doctorUserPassword);
        getDoctorRegisterView().getField_doctorUserPassword().setEditable(false);
        
        String doctorUserEmail = doctor.getUser().getUserEmail().toString();
        getDoctorRegisterView().getField_doctorEmail().setText(doctorUserEmail);
        getDoctorRegisterView().getField_doctorEmail().setEditable(false);  
        
    }
    
    private void resetFields(){
        loadDoctorData();
    }
}