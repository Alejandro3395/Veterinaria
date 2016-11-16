/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Address;
import Entitys.Doctor;
import Entitys.Phone;
import bussiness.DoctorManager;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.AbstractViewController;
import presentation.views.DoctorManagerView;
import presentation.controllers.DoctorRegisterController;

/**
 *
 * @author Jorge
 */
public class DoctorManagerHelper extends AbstractViewController {
    private static DoctorManagerHelper doctorManagerHelper = null;
    private DoctorManagerView doctorManagerView;
    private DoctorRegisterController doctorRegisterController;
    private DoctorModificationHelper doctorModificationHelper;
    
    public DoctorManagerHelper(){
        setDoctorManagerView(new DoctorManagerView());
        setDoctorRegisterController( DoctorRegisterController.getInstance() );
        setDoctorModificationHelper( DoctorModificationHelper.getInstance());        
        initializeView();
    }

    public static DoctorManagerHelper getInstance(){
        if( doctorManagerHelper== null) {
         doctorManagerHelper = new DoctorManagerHelper();
        }
        return doctorManagerHelper;
    }
    
    
    public DoctorModificationHelper getDoctorModificationHelper() {
        return doctorModificationHelper;
    }

    public void setDoctorModificationHelper(DoctorModificationHelper doctorModificationHelper) {
        this.doctorModificationHelper = doctorModificationHelper;
    }
    
    public DoctorManagerView getDoctorManagerView() {
        return doctorManagerView;
    }

    public void setDoctorManagerView(DoctorManagerView doctorManagerView) {
        this.doctorManagerView = doctorManagerView;
    }


    public DoctorRegisterController getDoctorRegisterController() {
        return doctorRegisterController;
    }

    public void setDoctorRegisterController(DoctorRegisterController doctorRegisterController) {
        this.doctorRegisterController = doctorRegisterController;
    } 

    @Override
    public void openWindow() {
        loadDoctorRegisterToTable();
        getDoctorManagerView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getDoctorManagerView() );
        getDoctorManagerView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getDoctorManagerView().getBtn_addDoctor().addActionListener(actionEvent -> openRegisterView());
        getDoctorManagerView().getBtn_modifyDoctor().addActionListener(actionEvent -> openModificationView());
        getDoctorManagerView().getBtn_deleteDoctor().addActionListener(actionEvent -> openEliminationConfirmationView());
        getDoctorManagerView().getBtn_back().addActionListener(actionEvent -> closeWindow());
    }
    
    public void loadDoctorRegisterToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getDoctorManagerView().getTable_doctorTable().getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        DoctorManager doctorManager = DoctorManager.GetInstance();
        
        ArrayList<Doctor> doctorList = doctorManager.getDoctorList() ;
        setTableContent(doctorList);
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            getDoctorModificationHelper().openWindow();
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void openEliminationConfirmationView(){
        
        if(isRowSelected()){
            if(isDeletionConfirmed()){
                proceedWithElimination();
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void closeWindow(){
        getDoctorManagerView().dispose();
    }
    
    private void proceedWithElimination(){
        int row = getDoctorManagerView().getTable_doctorTable().getSelectedRow();
        
        int id = Integer.valueOf( getDoctorManagerView().getTable_doctorTable().getValueAt(row, 0).toString() );

        DoctorManager doctorManager = DoctorManager.GetInstance();
        doctorManager.eliminateDoctor(id);
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
    
    private void setTableContent(ArrayList<Doctor> doctorList){    
        for(int index =0; index < doctorList.size(); index++ ){
            Doctor doctorData = doctorList.get(index) ;
            addDoctorToTable(doctorData);
        }
    }
    
    private void openRegisterView(){
        getDoctorRegisterController().openWindow();
    }
    
    private void addDoctorToTable(Doctor doctor){
        
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
        loadDoctorRegisterToTable();
    }
}