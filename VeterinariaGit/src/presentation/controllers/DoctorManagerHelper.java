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
import presentation.controllers.DoctorEliminationHelper;

/**
 *
 * @author Jorge
 */
public class DoctorManagerHelper extends AbstractViewController {
    private DoctorManagerView doctorManagerView;
    private DoctorRegisterController doctorRegisterController;
    private DoctorModificationHelper doctorModificationHelper;
    private DoctorEliminationHelper doctorEliminationHelper;
    private static int registerOption = 1;
    private static int modifyOption = 2;
    
    public DoctorManagerHelper(){
        setDoctorManagerView(new DoctorManagerView());
        setDoctorRegisterController(new DoctorRegisterController(this));
        setDoctorEliminationHelper(new DoctorEliminationHelper(this));
        setDoctorModificationHelper(new DoctorModificationHelper(this));
        
        loadDoctorRegister();
        initializeView();
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

    public DoctorEliminationHelper getDoctorEliminationHelper() {
        return doctorEliminationHelper;
    }

    public void setDoctorEliminationHelper(DoctorEliminationHelper doctorEliminationHelper) {
        this.doctorEliminationHelper = doctorEliminationHelper;
    } 

    public DoctorRegisterController getDoctorRegisterController() {
        return doctorRegisterController;
    }

    public void setDoctorRegisterController(DoctorRegisterController doctorRegisterController) {
        this.doctorRegisterController = doctorRegisterController;
    } 

    @Override
    public void openWindow() {
        
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
        getDoctorManagerView().getBtn_deleteDoctor().addActionListener(actionEvent -> openEliminationView());
    }
    
    public void loadDoctorRegister(){
        
        DefaultTableModel model = (DefaultTableModel) getDoctorManagerView().getTable_doctorTable().getModel();
        
        int rowCount = model.getRowCount();
        System.out.println(rowCount);
        
        if(rowCount !=0){model.setRowCount(0);}
        
        
        DoctorManager doctorManager = DoctorManager.GetInstance();
        
        ArrayList<Doctor> doctorList = doctorManager.getDoctorList() ;

        setTableContent(doctorList);
        
        
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            getDoctorModificationHelper().openWindow();
        }else{
            //porfavor elije un row
        }
    }
    
    private void openEliminationView(){
        
        if(isRowSelected()){
            getDoctorEliminationHelper().openWindow();
        }else{
            //notificar que porfavor se elija una row
        }
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
        //long id = index +1;
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
    
    public void updateTable(){
        loadDoctorRegister();
    }
    
    
    
    

    
}
