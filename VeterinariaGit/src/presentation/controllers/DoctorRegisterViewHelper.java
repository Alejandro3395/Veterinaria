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
import presentation.controllers.*;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.OperationalViewHelper;
import presentation.views.DoctorRegisterView;


public class DoctorRegisterViewHelper extends OperationalViewHelper {
    private DoctorRegisterView doctorRegisterView;
    private DoctorManagerViewHelper doctorManagerViewHelper;
    private static DoctorRegisterViewHelper doctorRegisterViewHelper = null;
    
    private static int doctorDataIndex = 0;
    private static int userDoctorDataIndex = 1;
    
    public DoctorRegisterViewHelper(){
        setDoctorRegisterView(new DoctorRegisterView());
        
        initializeView();
    }
    
    public static DoctorRegisterViewHelper getInstance(){
        if( doctorRegisterViewHelper== null) {
         doctorRegisterViewHelper = new DoctorRegisterViewHelper();
        }
        return doctorRegisterViewHelper;
    }

    public DoctorManagerViewHelper getDoctorManagerViewHelper() {
        return doctorManagerViewHelper;
    }

    public void setDoctorManagerViewHelper(DoctorManagerViewHelper doctorManagerViewHelper) {
        this.doctorManagerViewHelper = doctorManagerViewHelper;
    }

    public DoctorRegisterView getDoctorRegisterView() {
        return doctorRegisterView;
    }

    public void setDoctorRegisterView(DoctorRegisterView doctorRegisterView) {
        this.doctorRegisterView = doctorRegisterView;
    }
    
    private void updateManagerViewTable(){
        DoctorManagerViewHelper.getInstance().updateTable();
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
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getDoctorRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
        getDoctorRegisterView().getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
        
    }
    
    private void cancelRegistration(){
        closeWindow();
    }
    
    private void closeWindow(){
        getDoctorRegisterView().dispose();
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void proceedWithRegistration(){
        
        ArrayList<String> data = new ArrayList<String>(obtainData());

        ArrayList<ArrayList> parsedData = new ArrayList<ArrayList>(parseData(data));

        ArrayList<String> doctorData = new ArrayList<String>(parsedData.get(doctorDataIndex));
        ArrayList<String> userDoctorData = new ArrayList<String>(parsedData.get(userDoctorDataIndex));
        
        boolean isValidField =!isEmptyFields(data);
        
        String message="";
        String successStatus="SUCCESS";
        
        if(isValidField){
            DoctorManager doctorManager = DoctorManager.GetInstance();
            message = doctorManager.registerDoctor(doctorData,userDoctorData);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Doctor");
                updateManagerViewTable();
                resetFields();
                closeWindow();
            }else{
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
    
    private void resetFields(){
        getDoctorRegisterView().getField_doctorName().setText("");
        
        getDoctorRegisterView().getField_doctorAddressPostalCode().setText("");
        
        getDoctorRegisterView().getField_doctorAddressStreet().setText("");
        
        getDoctorRegisterView().getField_doctorAddressColony().setText("");
        
        getDoctorRegisterView().getField_doctorAddressCross().setText("");
        
        getDoctorRegisterView().getField_doctorPhoneLada().setText(""); 
        
        getDoctorRegisterView().getField_doctorPhoneNumber().setText("");
        
        getDoctorRegisterView().getField_doctorRFC().setText("");
        
        getDoctorRegisterView().getField_doctorProfessionalCode().setText("");
        
        getDoctorRegisterView().getField_doctorUserName().setText("");
        
        getDoctorRegisterView().getField_doctorUserPassword().setText("");
        
        getDoctorRegisterView().getField_doctorEmail().setText("");
        
    }

    
}