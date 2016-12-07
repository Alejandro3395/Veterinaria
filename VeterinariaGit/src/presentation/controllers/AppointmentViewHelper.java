/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Appointment;
import Entitys.Medicine;
import bussiness.AppointmentManager;
import bussiness.MedicineHandler;
import bussiness.ReportHandler;
import bussiness.SalesManager;
import bussiness.SessionManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.AppointmentView;
import presentation.views.SalesView;

/**
 *
 * @author mannu
 */
public class AppointmentViewHelper extends ViewHelper{
    private AppointmentView appointmentView;
    private DefaultComboBoxModel comboBoxModel;
    private DefaultTableModel Tablemodel;
    private static AppointmentViewHelper appointmentViewHelper;
    private double totalCost;
    private SalesManager salesManager;
    
    
    private AppointmentViewHelper(){
        setAppointmentView(new AppointmentView());  
        comboBoxModel= (DefaultComboBoxModel) appointmentView.getProduct_list().getModel();
        Tablemodel = (DefaultTableModel) appointmentView.getProductTable().getModel();
        salesManager = SalesManager.getInstance();
        initializeView();
    }

    public static AppointmentViewHelper getInstance(){
        if( appointmentViewHelper== null) {
         appointmentViewHelper =  new AppointmentViewHelper();
        }
        
        return appointmentViewHelper;
    }
    
    @Override
    protected void setEvents() {
        appointmentView.getAddProductBttn().addActionListener(actionEvent -> InsertProductToTable());
        appointmentView.getDeleteProductBttn().addActionListener(actionEvent -> RemoveProductFromTable());
        appointmentView.getAceptSaleBttn().addActionListener(actionEvent -> BuildReport());
        appointmentView.getCancelSaleBttn().addActionListener(actionEvent -> closeWindow());
    }

    private void closeWindow(){
        SalesManager.getInstance().CancelSale(getProducts());
        appointmentView.dispose();
        clearTable();
        DoctorMainMenuViewHelper doctorMainMenuViewHelper = DoctorMainMenuViewHelper.getInstance();
        doctorMainMenuViewHelper.loadView();
    }
    
    //quitar de aqui
    public void setAppointmentView(AppointmentView appointmentView) {
        this.appointmentView = appointmentView;
    }

    
    public void SetTotalCost(){
        DecimalFormat numDecimales = new DecimalFormat("0.00");
        SalesManager salesManager = SalesManager.getInstance();
        totalCost = salesManager.calculateAmountToPay(getProducts());
        appointmentView.getTotalSale_Field().setText(numDecimales.format(totalCost));
    }
    
    public String getSelectedMedicine(){
        String medicineSelected= (String)comboBoxModel.getSelectedItem();    
        return medicineSelected;
    }
    
    @Override
    public void loadView() {
        loadProducts();
        setDataToView();
        appointmentView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( appointmentView );
        appointmentView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
   
    public void loadProducts(){
        
        comboBoxModel.removeAllElements();
        MedicineHandler medicineManager = MedicineHandler.GetInstance();
        List<Medicine> productsList =  medicineManager.getMedicines();
        
        for(Medicine medicine : productsList){
            
            String item = medicine.getName() +"  $"+medicine.getCost();
            comboBoxModel.addElement(item);
        }

        appointmentView.getProduct_list().setModel(comboBoxModel);
    }
    
    public void InsertProductToTable(){
       String medicineSelected= getSelectedMedicine();
       String[] medicineDataRow = medicineSelected.split("\\$");
       String medicineName= medicineDataRow[0];
       double medicinePrice = Double.valueOf(medicineDataRow[1]);
       
       if(salesManager.addProductToPurchase(medicineName)){
       Object[] rowMedicine = new Object[]{medicineName,medicinePrice};
       Tablemodel.addRow(rowMedicine);
       SetTotalCost();
       }
       
    }
    
    public void RemoveProductFromTable(){
        
        if(isRowSelected()){
            int indexRowSelected = appointmentView.getProductTable().getSelectedRow();
            if(isDeletionConfirmed()){
                salesManager.removeProductToPurchase((String) Tablemodel.getValueAt(indexRowSelected, 0));
                
                Tablemodel.removeRow(indexRowSelected);
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
        
        SetTotalCost();
    }
        
    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
        
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = appointmentView.getProductTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(appointmentView.getProductTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    public List<String> getProducts(){
        int numMaxRows = 0;
        String medicineName; 
        List products = new ArrayList<>();
        for(numMaxRows = 0; numMaxRows < Tablemodel.getRowCount(); numMaxRows++){ 
                
            medicineName = String.valueOf(Tablemodel.getValueAt(numMaxRows, 0));
            Medicine medicine = MedicineHandler.GetInstance().getMedicineByName(medicineName);
            products.add(medicine);
        }

        
        return products;
    }
    
     //Metodo puesto aqui para prueba 
     // Checar nombres
     private void BuildReport(){
        getProducts();
        ReportHandler rh = ReportHandler.getInstance();
         
        //String comment = appointmentView.getField_comment().getText().toString();
        //System.out.println("comment: "+comment);
        //parametros clientName,petName,getProducts(),comment
        //rh.BuildSaleReport(SalesManager.getInstance().calculateAmountToPay(getProducts()));
         
        closeWindow();
     }
     
     private void setDataToView(){
         String doctorName = SessionManager.getCurrentDoctor().getName();
         appointmentView.getField_doctor().setText(doctorName);
         appointmentView.getField_doctor().setEditable(false);
         
         int row = AppointmentManagerViewHelper.getInstance().getAppointmentManagerView().getTable_appointmentTable().getSelectedRow();
        
         int id = Integer.valueOf( AppointmentManagerViewHelper.getInstance().getAppointmentManagerView().getTable_appointmentTable().getValueAt(row, 0).toString() );

         Appointment appointment = AppointmentManager.GetInstance().getAppointment(id);
         
         appointmentView.getField_client().setText(appointment.getClientName());
         appointmentView.getField_client().setEditable(false);
         
         appointmentView.getField_pet().setText(appointment.getPetName());
         appointmentView.getField_pet().setEditable(false);
         
         appointmentView.getField_comment().setText("");
     }
     
     private void clearTable(){
         Tablemodel.setRowCount(0);
     }
     
}
