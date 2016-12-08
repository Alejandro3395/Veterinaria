/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Appointment;
import Entitys.Client;
import Entitys.Pet;
import bussiness.AppointmentInformationHandler;
import bussiness.ClientInformationHandler;
import bussiness.PetInformationHandler;
import exceptions.InvalidFieldException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.WindowConstants;
import presentation.InformationViewHelper;
import presentation.views.AppointmentRegisterView;

/**
 *
 * @author jozapata
 */
public class ModifyAppointmentInfoView extends InformationViewHelper{
    private static ModifyAppointmentInfoView modifyAppointmentInfoView;
    private AppointmentRegisterView appointmentRegisterView;
    
    private int clientComboSize;

    public ModifyAppointmentInfoView() {
        setAppointmentRegisterView(new AppointmentRegisterView());
       
        initializeView();
    }   
            
    public static ModifyAppointmentInfoView getInstance(){
        if(modifyAppointmentInfoView == null) {
           modifyAppointmentInfoView = new ModifyAppointmentInfoView();
        }
        return modifyAppointmentInfoView;
    }

    public void setAppointmentRegisterView(AppointmentRegisterView appointmentRegisterView) {
        this.appointmentRegisterView = appointmentRegisterView;
    }
    
    
    

    @Override
    protected void setEvents() {
        appointmentRegisterView.getBtn_register().addActionListener(actionEvent -> programAppointment());
        appointmentRegisterView.getCombo_client().addItemListener(aListener -> loadPetRegisterToCombo());
        appointmentRegisterView.getBtn_cancel().addActionListener( actionEvent -> closeView());        
    }

    
    @Override
    public void loadView() {
        appointmentRegisterView.setVisible(true);
        loadClientRegisterToCombo();
        loadPetRegisterToCombo();
    }

    @Override
    protected void initializeView() {
        configureView( appointmentRegisterView );
        appointmentRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    
    private void loadAppointmentData(){
        int row = AppointmentManagerViewHelper.getInstance().getAppointmentManagerView().getTable_appointmentTable().getSelectedRow();
        int id = Integer.valueOf( AppointmentManagerViewHelper.getInstance().getAppointmentManagerView().getTable_appointmentTable().getValueAt(row, 0).toString() );
        
        AppointmentInformationHandler appointmentInformationHandler = AppointmentInformationHandler.GetInstance();
        Appointment appointment  =  appointmentInformationHandler.getAppointment(id) ;
        
        setDataToView(appointment);
    }

    @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String appointmentClient = appointmentRegisterView.getCombo_client().getSelectedItem().toString();
        data.add(appointmentClient);
        
        String appointmentPet = appointmentRegisterView.getCombo_pet().getSelectedItem().toString();
        data.add(appointmentPet);
        
        Calendar calendar = Calendar.getInstance();
        
        String date = "";
        String Day = Integer.toString(appointmentRegisterView.getDateChooser().getCalendar().get(calendar.DATE));
        String Month = Integer.toString( (appointmentRegisterView.getDateChooser().getCalendar().get(calendar.MONTH)) + 1 );
        String Year = Integer.toString(appointmentRegisterView.getDateChooser().getCalendar().get(calendar.YEAR));
        date = Year  + "-" + Month + "-" +  Day; 
        data.add(date);
        
        String hour = "";
        String minutes = Integer.toString(appointmentRegisterView.getDateChooser().getCalendar().get(calendar.MINUTE));
        String seconds = Integer.toString(appointmentRegisterView.getDateChooser().getCalendar().get(calendar.SECOND));
        hour = minutes + ":" + seconds;
        data.add(hour);

        
        return data;
    }
    private void setDataToView(Appointment appointment){
        //setear datos de los campso
        appointmentRegisterView.getCombo_client().setEditable(false);
        
        String pet = appointment.getPetName();
        
        int comboElements = appointmentRegisterView.getCombo_pet().getItemCount();
        int targetPetIndex = 0;
        
        for(int i = 0; i < comboElements; i++){
            if(pet.equals(appointmentRegisterView.getCombo_pet().getItemAt(i).toString())){
                targetPetIndex = i;
            }
        }
        
        appointmentRegisterView.getCombo_pet().setSelectedIndex(targetPetIndex);
        
        
        String date = appointment.getDate() +" "+ appointment.getHour();
        Date appointmentDate = null;
        try {
            appointmentDate = new SimpleDateFormat("yyyy-MM-dd hh:ss").parse(date);
        } catch (ParseException ex) {
            //throw new InvalidFieldException("Datos erroneos en la Fecha");
        }
        appointmentRegisterView.getDateChooser().setDate(appointmentDate);
        
    }
    private void resetFields(){
        loadAppointmentData();
    }

    @Override
    protected void clearFields() {
        appointmentRegisterView.getCombo_client().setSelectedIndex(0);
        appointmentRegisterView.getCombo_pet().setSelectedIndex(0);
        appointmentRegisterView.getDateChooser().setCalendar(null);   
    }
    
    private void programAppointment(){
        ArrayList<String> appointmentData = new ArrayList<String>(obtainDataFromView());
        
        int row = AppointmentManagerViewHelper.getInstance().getAppointmentManagerView().getTable_appointmentTable().getSelectedRow();
        int id = Integer.valueOf( AppointmentManagerViewHelper.getInstance().getAppointmentManagerView().getTable_appointmentTable().getValueAt(row, 0).toString() );
        
        
        boolean isValidField = !isEmptyFields(appointmentData);
        String message = "";
        
        if(isValidField){
            try{
                AppointmentInformationHandler appointmentInformationHandler = AppointmentInformationHandler.GetInstance();
                appointmentInformationHandler.modifyAppointment(appointmentData,id);
                getNotifier().showSuccessMessage("Modificacion existosa", "Exito al modificar la cita");
                closeView();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
                getNotifier().showWarningMessage(message);
            }
        }else{
            message = "Rellene todos los campos";
            resetFields();
            getNotifier().showWarningMessage(message);
        }
    }
    
    
    
    private void loadPetRegisterToCombo(){
        appointmentRegisterView.getCombo_pet().removeAllItems();
        
        if(!isEmptyList()){
            if(!hasDataChanged()){
                PetInformationHandler petManager = PetInformationHandler.GetInstance();
                String owner = appointmentRegisterView.getCombo_client().getSelectedItem().toString();                             
                List<Pet> petList = petManager.getPetList(owner);
                
                for(int index = 0; index < petList.size(); index++){
                    Pet pet = petList.get(index);
                    appointmentRegisterView.getCombo_pet().addItem(pet.getName().toString());
                }
            }
        }
        
    }
    
    private void loadClientRegisterToCombo(){
        ClientInformationHandler clientManager = ClientInformationHandler.GetInstance();
        ArrayList<Client> clientList = clientManager.getClientList();
        appointmentRegisterView.getCombo_client().removeAllItems();
        
        clientComboSize = clientList.size();
        
        if(clientComboSize  == 0){
            getNotifier().showWarningMessage("No existen clientes registrados");
            closeView();
        }else{
            for(int index = 0; index < clientList.size(); index++ ){
                Client client = clientList.get(index);
                appointmentRegisterView.getCombo_client().addItem(client.getName().toString());
            }  
        }
    }
    
    
    private boolean isEmptyList(){
        boolean result = false;
        int listSize = appointmentRegisterView.getCombo_client().getItemCount();
        
        if(listSize ==0){
            result = true;
        }
        return result;
    }
    
    private boolean hasDataChanged(){
        boolean result = false;
        
        if(clientComboSize != appointmentRegisterView.getCombo_client().getItemCount()){
            result = true;
        }
        return result;
    }
    
    private void closeView(){
        clearFields();
        appointmentRegisterView.dispose();
        AppointmentManagerViewHelper.getInstance().loadView();
    }
}
