/**
* class: DoctorRegisterController (DoctorRegisterController.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the controller for the doctor entitys.
* The objective of the class is to listen the events that the view
* provides and pass the data to the manager class.
* 
*/

package presentation.controllers;

import Entitys.Doctor;
import Entitys.UserDoctor;
import bussiness.DoctorManager;
import exceptions.InvalidFieldException;
import presentation.controllers.*;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.DoctorRegisterView;


public class DoctorRegisterViewHelper extends DataViewHelper {
    private DoctorRegisterView doctorRegisterView;
    private static DoctorRegisterViewHelper doctorRegisterViewHelper = null;
    
    private static int doctorDataIndex = 0;
    private static int userDoctorDataIndex = 1;
    
    private DoctorRegisterViewHelper(){
        setDoctorRegisterView(new DoctorRegisterView());
        
        initializeView();
    }
    
    public static DoctorRegisterViewHelper getInstance(){
        if( doctorRegisterViewHelper== null) {
         doctorRegisterViewHelper = new DoctorRegisterViewHelper();
        }
        return doctorRegisterViewHelper;
    }

    public void setDoctorRegisterView(DoctorRegisterView doctorRegisterView) {
        this.doctorRegisterView = doctorRegisterView;
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        doctorRegisterView.getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
        doctorRegisterView.getBtn_cancel().addActionListener(actionEvent -> cancelRegistration());     
    }
    
    private void updateManagerViewTable(){
        DoctorManagerViewHelper.getInstance().updateTable();
    }
    
    @Override
    public void loadView() {
        doctorRegisterView.setVisible(true);
    }
    
    @Override
    protected void initializeView() {
        configureView( doctorRegisterView );
        doctorRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    
    private void cancelRegistration(){
        closeWindow();
    }
    
    private void closeWindow(){
        doctorRegisterView.dispose();
        clearFields();
        DoctorManagerViewHelper doctorManagerViewHelper = DoctorManagerViewHelper.getInstance();
        doctorManagerViewHelper.loadView();
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void proceedWithRegistration(){
        
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());

        ArrayList<ArrayList> parsedData = new ArrayList<ArrayList>(parseData(data));

        ArrayList<String> doctorData = new ArrayList<String>(parsedData.get(doctorDataIndex));
        ArrayList<String> userDoctorData = new ArrayList<String>(parsedData.get(userDoctorDataIndex));
        
        boolean isValidField =!isEmptyFields(data);
        
        String message="";
        
        if(isValidField){
            try{
                DoctorManager doctorManager = DoctorManager.GetInstance();
                doctorManager.registerDoctor(doctorData,userDoctorData);
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Doctor");
                updateManagerViewTable();
                clearFields();
                closeWindow();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
                getNotifier().showWarningMessage( message );
            }
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
        }
    }

    private ArrayList<ArrayList> parseData(ArrayList<String> data){
        ArrayList<String> doctorData = new ArrayList<String>(data.subList(0, 9));
        ArrayList<String> userDoctorData = new ArrayList<String>(data.subList(9, data.size()));
        
        ArrayList<ArrayList> parsedData = new ArrayList<ArrayList>();
        parsedData.add(doctorData);
        parsedData.add(userDoctorData);

        return parsedData;
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
        
        String doctorUserName = doctorRegisterView.getField_doctorUserName().getText();
        data.add(doctorUserName);
        
        String doctorUserPassword = doctorRegisterView.getField_doctorUserPassword().getText();
        data.add(doctorUserPassword);
        
        String doctorUserEmail = doctorRegisterView.getField_doctorEmail().getText();
        data.add(doctorUserEmail);
        
        return data;
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