/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Doctor;
import bussiness.DoctorInformationHandler;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.InformationViewHelper;
import presentation.views.DoctorRegisterView;

/**
 *
 * @author Jorge
 */
public class ModifyDoctorInfoViewHelper extends InformationViewHelper {
    private DoctorRegisterView doctorRegisterView;
    private static ModifyDoctorInfoViewHelper modifyDoctorInfoViewHelper = null;

    
    private ModifyDoctorInfoViewHelper(){
        
        setDoctorRegisterView( new DoctorRegisterView() ); 
        initializeView();
    }

    public static ModifyDoctorInfoViewHelper getInstance(){
        if( modifyDoctorInfoViewHelper== null) {
         modifyDoctorInfoViewHelper =  new ModifyDoctorInfoViewHelper();
        }
        return modifyDoctorInfoViewHelper;
    }
    
    public void setDoctorRegisterView(DoctorRegisterView doctorRegisterView) {
        this.doctorRegisterView = doctorRegisterView;
    }

    @Override
    protected void setEvents() {
        doctorRegisterView.getBtn_register().addActionListener(actionEvent -> modifyDoctor());
        doctorRegisterView.getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }

    @Override
    public void loadView() {
        loadDoctorData();
        doctorRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( doctorRegisterView );
        doctorRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    private void loadDoctorData(){
        int row = DoctorManagerViewHelper.getInstance().getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        int id = Integer.valueOf( DoctorManagerViewHelper.getInstance().getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );
        
        DoctorInformationHandler doctorInformationHandler = DoctorInformationHandler.GetInstance();
        Doctor doctor =  doctorInformationHandler.getDoctor(id) ;
        
        setDataToView(doctor);
    }
    
    private void modifyDoctor(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int row = DoctorManagerViewHelper.getInstance().getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        
        int id = Integer.valueOf( DoctorManagerViewHelper.getInstance().getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            try{
               DoctorInformationHandler doctorHandler = DoctorInformationHandler.GetInstance();
               doctorHandler.modifyDoctor(data,id);
               getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Doctor");
               closeView();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
                getNotifier().showWarningMessage( message );
            }
               
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void cancelModification(){
        closeView();
    }
    
    private void closeView(){
        clearFields();
        doctorRegisterView.dispose();
        DoctorManagerViewHelper.getInstance().loadView();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String doctorName = doctorRegisterView.getField_doctorName().getText();
        data.add(doctorName);
        
        String doctorPostalCode = doctorRegisterView.getField_doctorAddressPostalCode().getText();
        data.add(doctorPostalCode);
        
        String doctorAddressStreet = doctorRegisterView.getField_doctorAddressStreet().getText();
        data.add(doctorAddressStreet);
        
        String doctorAddressColony = doctorRegisterView.getField_doctorAddressColony().getText();
        data.add(doctorAddressColony);
        
        String doctorAddressCross = doctorRegisterView.getField_doctorAddressCross().getText();
        data.add(doctorAddressCross);
        
        String doctorPhoneLada = doctorRegisterView.getField_doctorPhoneLada().getText(); 
        data.add(doctorPhoneLada);
        
        String doctorPhoneNumber = doctorRegisterView.getField_doctorPhoneNumber().getText();
        data.add(doctorPhoneNumber);
        
        String doctorRFC = doctorRegisterView.getField_doctorRFC().getText();
        data.add(doctorRFC);
        
        String doctorProfessionalCode = doctorRegisterView.getField_doctorProfessionalCode().getText();
        data.add(doctorProfessionalCode);
          
        return data;
    }
    
    private void setDataToView(Doctor doctor){
        //setear datos de los campso
        
        String doctorName = doctor.getName().toString();
        doctorRegisterView.getField_doctorName().setText(doctorName);
        
        String doctorPostalCode = Integer.toString(doctor.getAddress().getZipCode());
        doctorRegisterView.getField_doctorAddressPostalCode().setText(doctorPostalCode);
        
        String doctorStreet = doctor.getAddress().getStreet().toString();
        doctorRegisterView.getField_doctorAddressStreet().setText(doctorStreet);
        
        String doctorColony = doctor.getAddress().getColony().toString();
        doctorRegisterView.getField_doctorAddressColony().setText(doctorColony);
        
        String doctorCross = doctor.getAddress().getCrossovers().toString();
        doctorRegisterView.getField_doctorAddressCross().setText(doctorCross);
        
        String doctorPhoneLada = doctor.getPhone().getLada().toString();
        doctorRegisterView.getField_doctorPhoneLada().setText(doctorPhoneLada);
        
        String doctorPhoneNumber = doctor.getPhone().getNumber().toString();
        doctorRegisterView.getField_doctorPhoneNumber().setText(doctorPhoneNumber);
        
        String doctorRFC = doctor.getRFC().toString();
        doctorRegisterView.getField_doctorRFC().setText(doctorRFC);
        
        String doctorIdentityCard = doctor.getIdentityCard().toString();
        doctorRegisterView.getField_doctorProfessionalCode().setText(doctorIdentityCard);
        
        String doctorUserName = doctor.getUser().getUserName().toString();
        doctorRegisterView.getField_doctorUserName().setText(doctorUserName);
        doctorRegisterView.getField_doctorUserName().setEditable(false);
        
        String doctorUserPassword = doctor.getUser().getUserPassword().toString();
        doctorRegisterView.getField_doctorUserPassword().setText(doctorUserPassword);
        doctorRegisterView.getField_doctorUserPassword().setEditable(false);
        
        String doctorUserEmail = doctor.getUser().getUserEmail().toString();
        doctorRegisterView.getField_doctorEmail().setText(doctorUserEmail);
        doctorRegisterView.getField_doctorEmail().setEditable(false);  
        
    }
    private void resetFields(){
        loadDoctorData();
    }
    
    @Override
    protected void clearFields() {
        doctorRegisterView.getField_doctorName().setText("");
        
        doctorRegisterView.getField_doctorAddressPostalCode().setText("");
        
        doctorRegisterView.getField_doctorAddressStreet().setText("");
        
        doctorRegisterView.getField_doctorAddressColony().setText("");
        
        doctorRegisterView.getField_doctorAddressCross().setText("");
        
        doctorRegisterView.getField_doctorPhoneLada().setText(""); 
        
        doctorRegisterView.getField_doctorPhoneNumber().setText("");
        
        doctorRegisterView.getField_doctorRFC().setText("");
        
        doctorRegisterView.getField_doctorProfessionalCode().setText("");
        
        doctorRegisterView.getField_doctorUserName().setText("");
        
        doctorRegisterView.getField_doctorUserPassword().setText("");
        
        doctorRegisterView.getField_doctorEmail().setText("");
    }
}