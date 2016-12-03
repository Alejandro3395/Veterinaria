/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Supplier;
import bussiness.SupplierManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.SupplierManagerView;

/**
 *
 * @author Jorge
 */
public class SupplierManagerViewHelper extends ViewHelper {
    private static SupplierManagerViewHelper supplierManagerViewHelper = null;
    private SupplierManagerView supplierManagerView;

    private SupplierManagerViewHelper(){
        setSupplierManagerView(new SupplierManagerView());
        
        initializeView();
    }
    
    public static SupplierManagerViewHelper getInstance(){
        if( supplierManagerViewHelper == null) {
         supplierManagerViewHelper = new SupplierManagerViewHelper();
        }
        return supplierManagerViewHelper;
    }

    public SupplierManagerView getSupplierManagerView() {
        return supplierManagerView;
    }

    public void setSupplierManagerView(SupplierManagerView supplierManagerView) {
        this.supplierManagerView = supplierManagerView;
    }


    @Override
    public void loadView() {
        getSupplierManagerView().setVisible(true);
        loadSupplierRegisterToTable();

    }

    @Override
    protected void initializeView() {
        configureView( getSupplierManagerView() );
        getSupplierManagerView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getSupplierManagerView().getBtn_addSupplier().addActionListener(actionEvent -> openRegisterView());
        getSupplierManagerView().getBtn_modifySupplier().addActionListener(actionEvent -> openModificationView());
        getSupplierManagerView().getBtn_deleteSupplier().addActionListener(actionEvent -> displayConfirmationMessage());
        getSupplierManagerView().getBtn_back().addActionListener(actionEvent -> closeWindow());
    }
    
    public void loadSupplierRegisterToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getSupplierManagerView().getTable_supplierTable().getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        SupplierManager supplierManager = SupplierManager.GetInstance();
        
        ArrayList<Supplier> supplierList = supplierManager.getSupplierList() ;
        setTableContent(supplierList);
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            SupplierModificationViewHelper supplierModificationViewHelper = SupplierModificationViewHelper.getInstance();
            supplierModificationViewHelper.loadView();
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
        getSupplierManagerView().dispose();
    }
    
    private void proceedWithElimination(){
        int row = getSupplierManagerView().getTable_supplierTable().getSelectedRow();
        
        int id = Integer.valueOf( getSupplierManagerView().getTable_supplierTable().getValueAt(row, 0).toString() );

        SupplierManager supplierManager = SupplierManager.GetInstance();
        supplierManager.deleteSupplier(id);
        getNotifier().showSuccessMessage("Eliminacion exitosa", "exito al eliminar el Supplier");
        updateTable();
    }
    
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = getSupplierManagerView().getTable_supplierTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(getSupplierManagerView().getTable_supplierTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    private void setTableContent(ArrayList<Supplier> supplierList){    
        for(int index =0; index < supplierList.size(); index++ ){
            Supplier supplierData = supplierList.get(index) ;
            addSupplierToTable(supplierData);
        }
    }
    
    private void openRegisterView(){
        SupplierRegisterViewHelper supplierRegisterViewHelper = SupplierRegisterViewHelper.getInstance();
        supplierRegisterViewHelper.loadView();
    }
    
    private void addSupplierToTable(Supplier supplier){
        
        DefaultTableModel model = (DefaultTableModel) getSupplierManagerView().getTable_supplierTable().getModel();
        
        long id = supplier.getId_Supplier();
        String name = supplier.getCompanyName();
        String lada = supplier.getPhone().getLada();
        String number = supplier.getPhone().getNumber();
            
        Object[] row = new Object[]{id,name,lada,number };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        System.out.println("llegue");
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadSupplierRegisterToTable();
    }
    
}