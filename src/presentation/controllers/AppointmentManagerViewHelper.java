/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Appointment;
import bussiness.AppointmentManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.AppointmentManagerView;

/**
 *
 * @author jozapata
 */
public class AppointmentManagerViewHelper extends ViewHelper {
    private static AppointmentManagerViewHelper appointmentManagerViewHelper = null;
    private AppointmentManagerView appointmentManagerView;
    
    
    private AppointmentManagerViewHelper(){
        setAppointmentManagerView(new AppointmentManagerView());
        initializeView();
    }

    public static AppointmentManagerViewHelper getInstance(){
        if( appointmentManagerViewHelper== null) {
         appointmentManagerViewHelper = new AppointmentManagerViewHelper();
        }
        return appointmentManagerViewHelper;
    }
     
    
    public AppointmentManagerView getAppointmentManagerView() {
        return appointmentManagerView;
    }

    public void setAppointmentManagerView(AppointmentManagerView appointmentManagerView) {
        this.appointmentManagerView = appointmentManagerView;
    }
    
    private void setTableContent(ArrayList<Appointment> appointmentList){    
        for(int index =0; index < appointmentList.size(); index++ ){
            Appointment appointmentData = appointmentList.get(index) ;
            insertAppoitmentToTable(appointmentData);
        }
    }
    
    @Override
    protected void setEvents() {
        getAppointmentManagerView().getBtn_addAppointment().addActionListener(actionEvent -> openRegisterView());
        getAppointmentManagerView().getBtn_modifyAppointment().addActionListener(actionEvent -> openModificationView());
        getAppointmentManagerView().getBtn_deleteAppointment().addActionListener(actionEvent -> displayConfirmationMessage());
        getAppointmentManagerView().getBtn_startAppointment().addActionListener(actionEvent -> startAppointment());
        getAppointmentManagerView().getBtn_back().addActionListener(actionEvent -> closeWindow());
    }
    
    @Override
    public void loadView() {
        loadAppointmentRegisterToTable();
        getAppointmentManagerView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getAppointmentManagerView() );
        getAppointmentManagerView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */ 
    private void loadAppointmentRegisterToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getAppointmentManagerView().getTable_appointmentTable().getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        AppointmentManager appointmentManager = AppointmentManager.GetInstance();
        
        ArrayList<Appointment> appointmentList = appointmentManager.getAppointments() ;
        setTableContent(appointmentList);
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            if(isAppointmentAvailable()){
                appointmentManagerView.dispose();
                AppointmentModificationViewHelper appointmentModificationViewHelper = AppointmentModificationViewHelper.getInstance();
                appointmentModificationViewHelper.loadView();
            }else{
                getNotifier().showWarningMessage( "La cita que elegiste no esta disponible" );
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void displayConfirmationMessage(){
        
        if(isRowSelected()){
            if(isDeletionConfirmed()){
                proceedWithElimination();
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void closeWindow(){
        getAppointmentManagerView().dispose();
        DoctorMainMenuViewHelper.getInstance().loadView();
    }
    
    private void proceedWithElimination(){
        if(isAppointmentAvailable()){
            int row = getAppointmentManagerView().getTable_appointmentTable().getSelectedRow();
        
            int id = Integer.valueOf( getAppointmentManagerView().getTable_appointmentTable().getValueAt(row, 0).toString() );

            AppointmentManager appointmentManager = AppointmentManager.GetInstance();
            appointmentManager.cancelAppointment(id);
            getNotifier().showSuccessMessage("Eliminacion exitosa", "exito al eliminar la cita");
            updateTable();
        }else{
            getNotifier().showWarningMessage( "La cita que elegiste no esta disponible" );        
        }
        
    }
    
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = getAppointmentManagerView().getTable_appointmentTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(getAppointmentManagerView().getTable_appointmentTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    private boolean isAppointmentAvailable(){
        boolean result = true;
        String FINISHEDSTATUS = "TERMINADA";
        String CANCELEDSTATUS = "CANCELADA";
        
        int row = getAppointmentManagerView().getTable_appointmentTable().getSelectedRow();
        String status = String.valueOf(getAppointmentManagerView().getTable_appointmentTable().getValueAt(row, 5).toString() );
        
        if((status.equals(FINISHEDSTATUS)) || (status.equals(CANCELEDSTATUS))){
            result = false;
        }
        
        return result;
        
    }
    
    private void openRegisterView(){
        appointmentManagerView.dispose();
        AppointmentRegisterViewHelper appointmentRegisterViewHelper = AppointmentRegisterViewHelper.getInstance();
        appointmentRegisterViewHelper.loadView();
    }
    
    private void startAppointment(){
        if(isRowSelected()){
            
            if(isAppointmentAvailable()){
                appointmentManagerView.dispose();
                int row = getAppointmentManagerView().getTable_appointmentTable().getSelectedRow();
                int id = Integer.valueOf( getAppointmentManagerView().getTable_appointmentTable().getValueAt(row, 0).toString() );
                
                AppointmentManager.GetInstance().startAppointment(id);
                AppointmentViewHelper appointmentHelper = AppointmentViewHelper.getInstance();
                appointmentHelper.loadView();
            }else{
                getNotifier().showWarningMessage( "La cita que elegiste no esta disponible" );
            }
            
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
        
    }
    
    private void insertAppoitmentToTable(Appointment appointment){
        
        DefaultTableModel model = (DefaultTableModel) getAppointmentManagerView().getTable_appointmentTable().getModel();
        
        long id = appointment.getId();
        String client = appointment.getClientName();
        String pet =  appointment.getPetName();
        String date = appointment.getDate().toString();
        String hour = appointment.getHour();
        String status = appointment.getStatus();
            
        Object[] row = new Object[]{id,client,pet,date,hour,status };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        //System.out.println("llegue");
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadAppointmentRegisterToTable();
    }
}
