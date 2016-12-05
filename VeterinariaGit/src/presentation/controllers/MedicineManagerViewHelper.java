/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import Entitys.Supplier;
import bussiness.MedicineManager;
import bussiness.SupplierManager;
import java.util.ArrayList;
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
    
    private int comoboSize;
    
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
        loadMedicineRegisterToTable();

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
        getMedicineManagerView().getCombo_medicineSupplier().addActionListener( actionEvent -> loadMedicineRegisterToTable() );
        getMedicineManagerView().getBtn_back().addActionListener(actionEvent -> closeWindow());
    }
    
    public void loadMedicineRegisterToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getMedicineManagerView().getTable_medicineTable().getModel();
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        if(!isEmptyList()){
            if(!hasDataChanged()){
                MedicineManager medicineManager = MedicineManager.GetInstance();
                String supplierName = getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
            
                List<Medicine> medicineList = medicineManager.getMedicinesBySupplierName(supplierName) ;
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
    
    private boolean isEmptyCombo(){
        boolean result = false;
        
        //String comboItem = getMedicineManagerView().getCombo_petOwner().getSelectedItem().toString();
        int comboSize = getMedicineManagerView().getCombo_medicineSupplier().getItemCount();
        if(comboSize<1){
            result = true;
        }
        return result;
    }
    
    private boolean hasDataChanged(){
        boolean result = false;
        
        if(comoboSize != getMedicineManagerView().getCombo_medicineSupplier().getItemCount()){
            result = true;
        }
        return result;
    }
    
    private void loadSupplierRegisterToCombo(){
        
        SupplierManager supplierManager = SupplierManager.GetInstance();
        ArrayList<Supplier> supplierList = supplierManager.getSupplierList();
        getMedicineManagerView().getCombo_medicineSupplier().removeAllItems();
        
        comoboSize = supplierList.size();
        
        for(int index = 0; index < supplierList.size(); index++ ){
            Supplier supplier = supplierList.get(index);
            getMedicineManagerView().getCombo_medicineSupplier().addItem(supplier.getCompanyName().toString());
        }
        
        
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            getMedicineManagerView().dispose();
            MedicineModificationViewHelper medicineModificationViewHelper = MedicineModificationViewHelper.getInstance();
            medicineModificationViewHelper.loadView();
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
        getMedicineManagerView().dispose();
        RegisterSelectionViewHelper.getInstance().loadView();
    }
    
    private void proceedWithElimination(){
        
        int rowIndex = getMedicineManagerView().getTable_medicineTable().getSelectedRow();
        String medicineSupplier = getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        
        SupplierManager supplierManager = SupplierManager.GetInstance();
        Supplier supplier = supplierManager.getSupplierData(medicineSupplier);
        supplier.getMedicines().remove(rowIndex);
        
        supplierManager.updateSupplier(supplier);
        
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
        if(!isEmptyCombo()){
            getMedicineManagerView().dispose();
            MedicineRegisterViewHelper medicineRegisterViewHelper = MedicineRegisterViewHelper.getInstance();
            //medicineRegisterViewHelper.getInstance().setMode(1);
            medicineRegisterViewHelper.getInstance().loadView();
        }else{
            getNotifier().showWarningMessage( "No es posible añadir mascota debido a que no hay clientes registrados" );
        }
    }
    
    private void addMedicineToTable(Medicine medicine, int index){
        
        DefaultTableModel model = (DefaultTableModel) getMedicineManagerView().getTable_medicineTable().getModel();
        
        long id = index + 1;
        String medicineName = medicine.getName();
        
        double medicinePrice = medicine.getCost();
        
        int medicineQuantity = medicine.getAmount();
        
        String medicineExpirationDate = medicine.getExpiration_date();
            
        Object[] row = new Object[]{id,medicineName,medicinePrice,medicineQuantity,medicineExpirationDate };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        System.out.println("llegue");
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadMedicineRegisterToTable();
    }
    
    
    
}
