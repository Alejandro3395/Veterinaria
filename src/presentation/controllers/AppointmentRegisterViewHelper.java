/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Client;
import Entitys.Pet;
import bussiness.AppointmentManager;
import bussiness.ClientManager;
import bussiness.PetManager;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.AppointmentRegisterView;

/**
 *
 * @author jozapata
 */
public class AppointmentRegisterViewHelper extends DataViewHelper{
    private static AppointmentRegisterViewHelper appointmentRegisterViewHelper;
    private AppointmentRegisterView appointmentRegisterView;
    
    private int clientComboSize;

    public AppointmentRegisterViewHelper() {
        setAppointmentRegisterView(new AppointmentRegisterView());
       
        initializeView();
    }   
            
    public static AppointmentRegisterViewHelper getInstance(){
        if(appointmentRegisterViewHelper == null) {
           appointmentRegisterViewHelper = new AppointmentRegisterViewHelper();
        }
        return appointmentRegisterViewHelper;
    }

    public void setAppointmentRegisterView(AppointmentRegisterView appointmentRegisterView) {
        this.appointmentRegisterView = appointmentRegisterView;
    }
    
    
    

    @Override
    protected void setEvents() {
        appointmentRegisterView.getBtn_register().addActionListener(actionEvent -> programAppointment());
        appointmentRegisterView.getCombo_client().addItemListener(aListener -> loadPetRegisterToCombo());
        appointmentRegisterView.getBtn_cancel().addActionListener( actionEvent -> closeWindow());        
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
        
        //System.out.println("date: "+date + " hour: "+hour);
        
        return data;
    }

    @Override
    protected void clearFields() {
        appointmentRegisterView.getCombo_client().setSelectedIndex(0);
        appointmentRegisterView.getCombo_pet().setSelectedIndex(0);
        appointmentRegisterView.getDateChooser().setCalendar(null);   
    }
    
    private void programAppointment(){
        ArrayList<String> appointmentData = new ArrayList<String>(obtainDataFromView());
        
        boolean isValidField = !isEmptyFields(appointmentData);
        String message = "";
        
        if(isValidField){
            try{
                AppointmentManager appointmentManager = AppointmentManager.GetInstance();
                appointmentManager.registerAppointment(appointmentData);
                getNotifier().showSuccessMessage("registro existoso", "Exito al registrar la cita");
                closeWindow();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
                getNotifier().showWarningMessage(message);
            }
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage(message);
        }
    }
    
    private void loadPetRegisterToCombo(){
        appointmentRegisterView.getCombo_pet().removeAllItems();
        
        if(!isEmptyList()){
            if(!hasDataChanged()){
                PetManager petManager = PetManager.GetInstance();
                String owner = appointmentRegisterView.getCombo_client().getSelectedItem().toString();                             
                List<Pet> petList = petManager.getPetList(owner);
                
                if(petList == null){
                    getNotifier().showWarningMessage("No existen mascotas para el cliente seleccionado");
                    closeWindow();
                }else{
                    for(int index = 0; index < petList.size(); index++){
                        Pet pet = petList.get(index);
                        appointmentRegisterView.getCombo_pet().addItem(pet.getName().toString());
                    }  
                }
                
                
            }
        }
        
    }
    
    private void loadClientRegisterToCombo(){
        ClientManager clientManager = ClientManager.GetInstance();
        ArrayList<Client> clientList = clientManager.getClientList();
        appointmentRegisterView.getCombo_client().removeAllItems();
        
        clientComboSize = clientList.size();
        
        if(clientComboSize  == 0){
            getNotifier().showWarningMessage("No existen clientes registrados");
            closeWindow();
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
    
    private void closeWindow(){
        appointmentRegisterView.dispose();
        clearFields();
        AppointmentManagerViewHelper.getInstance().loadView();
    }
}
