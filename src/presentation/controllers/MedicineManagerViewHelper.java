/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import Entitys.Supplier;
import bussiness.MedicineInformationHandler;
import bussiness.SupplierInformationHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.MedicineManagerView;

/**
 *
 * @author mannu
 */
public class MedicineManagerViewHelper extends ViewHelper {
    private static MedicineManagerViewHelper medicineManagerViewHelper = null;
    private MedicineManagerView medicineManagerView;
    
    private int comboSize;
    
    private MedicineManagerViewHelper(){
        setMedicineManagerView(new MedicineManagerView());

        initializeView();
    }
    
    public static MedicineManagerViewHelper getInstance(){
        if( medicineManagerViewHelper== null) {
         medicineManagerViewHelper = new MedicineManagerViewHelper();
        }
        return medicineManagerViewHelper;
    }
    
    public MedicineManagerView getMedicineManagerView() {
        return medicineManagerView;
    }

    public void setMedicineManagerView(MedicineManagerView medicineManagerView) {
        this.medicineManagerView = medicineManagerView;
    }

    @Override
    public void loadView() {
        getMedicineManagerView().setVisible(true);
        loadSupplierRegisterToCombo();
        loadMedicineRecordsToTable();

    }

    @Override
    protected void initializeView() {
        configureView( getMedicineManagerView() );
        getMedicineManagerView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getMedicineManagerView().getBtn_addMedicine().addActionListener(actionEvent -> openRegisterView());
        getMedicineManagerView().getBtn_modifyMedicine().addActionListener(actionEvent -> openModificationView());
        getMedicineManagerView().getBtn_deleteMedicine().addActionListener(actionEvent -> displayConfirmationMessage());
        getMedicineManagerView().getCombo_medicineSupplier().addActionListener( actionEvent -> loadMedicineRecordsToTable() );
        getMedicineManagerView().getBtn_back().addActionListener(actionEvent -> closeView());
    }
    
    public void loadMedicineRecordsToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getMedicineManagerView().getTable_medicineTable().getModel();
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        if(!isEmptyList()){
            if(!hasDataChanged()){
                MedicineInformationHandler medicineInformationHandler = MedicineInformationHandler.GetInstance();
                String supplierName = getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
            
                List<Medicine> medicineList = medicineInformationHandler.getMedicinesBySupplierName(supplierName) ;
                setTableContent(medicineList); 
            }
        } 
        
    }
    
    private boolean isEmptyList(){
        boolean result = false;
        int listSize = getMedicineManagerView().getCombo_medicineSupplier().getItemCount();
        
        if(listSize ==0){
            result = true;
        }
        return result;
    }
    
    private boolean hasDataChanged(){
        boolean result = false;
        
        if(comboSize != getMedicineManagerView().getCombo_medicineSupplier().getItemCount()){
            result = true;
        }
        return result;
    }
    
    private void loadSupplierRegisterToCombo(){
        
        SupplierInformationHandler supplierManager = SupplierInformationHandler.GetInstance();
        ArrayList<Supplier> supplierList = supplierManager.getSupplierList();
        getMedicineManagerView().getCombo_medicineSupplier().removeAllItems();
        
        comboSize = supplierList.size();
        
        if(comboSize == 0){
            getNotifier().showWarningMessage("No existen proveedores registrados");
            closeView();
        }else{
          for(int index = 0; index < supplierList.size(); index++ ){
            Supplier supplier = supplierList.get(index);
            getMedicineManagerView().getCombo_medicineSupplier().addItem(supplier.getCompanyName().toString());
          }  
        }  
    }
    
   private void openModificationView(){
        if(isRowSelected()){
            getMedicineManagerView().dispose();
            ModifyMedicineInfoViewHelper modifyMedicineInfoViewHelper = ModifyMedicineInfoViewHelper.getInstance();
            modifyMedicineInfoViewHelper.loadView();
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void displayConfirmationMessage(){
        
        if(isRowSelected()){
            if(isDeletionConfirmed()){
                eliminateMedicine();
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void closeView(){
        getMedicineManagerView().dispose();
        RegisterSelectionViewHelper registerSelectionViewHelper = RegisterSelectionViewHelper.getInstance();
        registerSelectionViewHelper.loadView();
        
    }
    
    private void eliminateMedicine(){
        
        int rowIndex = getMedicineManagerView().getTable_medicineTable().getSelectedRow();
        String medicineSupplier = getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        
        SupplierInformationHandler supplierManager = SupplierInformationHandler.GetInstance();
        Supplier supplier = supplierManager.getSupplierData(medicineSupplier);
        supplier.getMedicines().remove(rowIndex);
        
        supplierManager.edit(supplier);
        
        getNotifier().showSuccessMessage("Eliminacion exitosa", "exito al eliminar el Medicine");
        updateTable();
    }
    
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = getMedicineManagerView().getTable_medicineTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(getMedicineManagerView().getTable_medicineTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    private void setTableContent(List<Medicine> medicineList){    
        for(int index =0; index < medicineList.size(); index++ ){
            Medicine medicineData = medicineList.get(index) ;
            addMedicineToTable(medicineData,index);
        }
    }
    
     private void openRegisterView(){ 
        
            getMedicineManagerView().dispose();
            MedicineRegisterViewHelper medicineRegisterViewHelper = MedicineRegisterViewHelper.getInstance();
            //medicineRegisterViewHelper.getInstance().setMode(1);
            medicineRegisterViewHelper.getInstance().loadView();
        
    }
    
    private void addMedicineToTable(Medicine medicine, int index){
        
        DefaultTableModel model = (DefaultTableModel) getMedicineManagerView().getTable_medicineTable().getModel();
        
        long id = index + 1;
        String medicineName = medicine.getName();
        
        double medicinePrice = medicine.getCost();
        
        int medicineQuantity = medicine.getAmount();
        
        Date medicineExpirationDate = medicine.getExpiration_date();
        
        String expirationDate = medicineExpirationDate.toString();
        
            
        Object[] row = new Object[]{id,medicineName,medicinePrice,medicineQuantity,expirationDate };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        System.out.println("llegue");
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadMedicineRecordsToTable();
    }
    
    
    
}
