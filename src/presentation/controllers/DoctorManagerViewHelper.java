/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Address;
import Entitys.Doctor;
import Entitys.Phone;
import bussiness.DoctorInformationHandler;
import bussiness.SessionManager;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.DoctorManagerView;
import presentation.controllers.DoctorRegisterViewHelper;

/**
 *
 * @author Jorge
 */
public class DoctorManagerViewHelper extends ViewHelper {
    private static DoctorManagerViewHelper doctorManagerViewHelper = null;
    private DoctorManagerView doctorManagerView;
    
    private DoctorManagerViewHelper(){
        setDoctorManagerView(new DoctorManagerView());
        initializeView();
    }

    public static DoctorManagerViewHelper getInstance(){
        if( doctorManagerViewHelper== null) {
         doctorManagerViewHelper = new DoctorManagerViewHelper();
        }
        return doctorManagerViewHelper;
    }
     
    
    public DoctorManagerView getDoctorManagerView() {
        return doctorManagerView;
    }

    public void setDoctorManagerView(DoctorManagerView doctorManagerView) {
        this.doctorManagerView = doctorManagerView;
    }
    
    private void setTableContent(ArrayList<Doctor> doctorList){
        for(int index =0; index < doctorList.size(); index++ ){
            Doctor doctorData = doctorList.get(index) ;
            insertDoctorToTable(doctorData);  
        }
    }
    
    @Override
    protected void setEvents() {
        getDoctorManagerView().getBtn_addDoctor().addActionListener(actionEvent -> openRegisterView());
        getDoctorManagerView().getBtn_modifyDoctor().addActionListener(actionEvent -> openModificationView());
        getDoctorManagerView().getBtn_deleteDoctor().addActionListener(actionEvent -> displayConfirmationMessage());
        getDoctorManagerView().getBtn_back().addActionListener(actionEvent -> closeView());
    }
    
    @Override
    public void loadView() {
        loadDoctorRecordsToTable();
        getDoctorManagerView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getDoctorManagerView() );
        getDoctorManagerView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */ 
    private void loadDoctorRecordsToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getDoctorManagerView().getTable_doctorTable().getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        DoctorInformationHandler doctorInformationHandler = DoctorInformationHandler.GetInstance();
        
        ArrayList<Doctor> doctorList = doctorInformationHandler.getDoctors() ;
        setTableContent(doctorList);
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            doctorManagerView.dispose();
            ModifyDoctorInfoViewHelper modifyDoctorInfoViewHelper = ModifyDoctorInfoViewHelper.getInstance();
            modifyDoctorInfoViewHelper.loadView();
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void displayConfirmationMessage(){
        
        if(isRowSelected()){
            if(isDeletionConfirmed()){
                eliminateDoctor();
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void closeView(){
        getDoctorManagerView().dispose();
        RegisterSelectionViewHelper.getInstance().loadView();
    }
    
    private void eliminateDoctor(){
        int row = getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        
        int id = Integer.valueOf( getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );

        DoctorInformationHandler doctorInformationHandler = DoctorInformationHandler.GetInstance();
        doctorInformationHandler.remove(id);
        getNotifier().showSuccessMessage("Eliminacion exitosa", "exito al eliminar el Doctor");
        updateTable();
    }
    
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = getDoctorManagerView().getTable_doctorTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(getDoctorManagerView().getTable_doctorTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    private void openRegisterView(){
        doctorManagerView.dispose();
        DoctorRegisterViewHelper doctorRegisterViewHelper = DoctorRegisterViewHelper.getInstance();
        doctorRegisterViewHelper.loadView();
    }
    
    private void insertDoctorToTable(Doctor doctor){
        
        DefaultTableModel model = (DefaultTableModel) getDoctorManagerView().getTable_doctorTable().getModel();
        
        long id = doctor.getId();
        String name = doctor.getName();
        String street =  doctor.getAddress().getStreet();
        int postal = doctor.getAddress().getZipCode();
        String colony = doctor.getAddress().getColony();
        String cross = doctor.getAddress().getCrossovers();
        String lada = doctor.getPhone().getLada();
        String number = doctor.getPhone().getNumber();
            
        Object[] row = new Object[]{id,name,street,postal,colony,cross,lada,number };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        System.out.println("llegue");
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadDoctorRecordsToTable();
    }
} 