/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Client;
import Entitys.Pet;
import bussiness.ClientManager;
import bussiness.PetManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.TransitionalViewHelper;
import presentation.views.PetManagerView;

/**
 *
 * @author Jorge
 */
public class PetManagerViewHelper extends TransitionalViewHelper {
    private static PetManagerViewHelper petManagerViewHelper = null;
    private PetManagerView petManagerView;
    private PetRegisterViewHelper petRegisterViewHelper;
    private PetModificationViewHelper petModificationViewHelper;
    private int comoboSize;
    
    public PetManagerViewHelper(){
        setPetManagerView(new PetManagerView());
        setPetRegisterViewHelper(PetRegisterViewHelper.getInstance());
        setPetModificationViewHelper(PetModificationViewHelper.getInstance());
        
        initializeView();
    }
    
    public static PetManagerViewHelper getInstance(){
        if( petManagerViewHelper== null) {
         petManagerViewHelper = new PetManagerViewHelper();
        }
        return petManagerViewHelper;
    }

    public PetModificationViewHelper getPetModificationViewHelper() {
        return petModificationViewHelper;
    }

    public void setPetModificationViewHelper(PetModificationViewHelper petModificationViewHelper) {
        this.petModificationViewHelper = petModificationViewHelper;
    }
    
    public PetManagerView getPetManagerView() {
        return petManagerView;
    }

    public void setPetManagerView(PetManagerView petManagerView) {
        this.petManagerView = petManagerView;
    }


    public PetRegisterViewHelper getPetRegisterViewHelper() {
        return petRegisterViewHelper;
    }

    public void setPetRegisterViewHelper(PetRegisterViewHelper petRegisterViewHelper) {
        this.petRegisterViewHelper = petRegisterViewHelper;
    } 

    @Override
    public void openWindow() {
        getPetManagerView().setVisible(true);
        loadClientRegisterToCombo();
        loadPetRegisterToTable();

    }

    @Override
    protected void initializeView() {
        configureWindow( getPetManagerView() );
        getPetManagerView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getPetManagerView().getBtn_addPet().addActionListener(actionEvent -> openRegisterView());
        getPetManagerView().getBtn_modifyPet().addActionListener(actionEvent -> openModificationView());
        getPetManagerView().getBtn_deletePet().addActionListener(actionEvent -> openEliminationConfirmationView());
        getPetManagerView().getCombo_petOwner().addActionListener( actionEvent -> loadPetRegisterToTable() );
        getPetManagerView().getBtn_back().addActionListener(actionEvent -> closeWindow());
    }
    
    public void loadPetRegisterToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getPetManagerView().getTable_petTable().getModel();
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        if(!isEmptyList()){
            if(!hasDataChanged()){
                
                
                
                PetManager petManager = PetManager.GetInstance();
                String ownerName = getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
            
                List<Pet> petList = petManager.getPetList(ownerName) ;
                setTableContent(petList); 
            }
        } 
        
    }
    
    private boolean isEmptyCombo(){
        boolean result = false;
        
        //String comboItem = getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        int comboSize = getPetManagerView().getCombo_petOwner().getItemCount();
        if(comboSize<1){
            result = true;
        }
        return result;
    }
    
    private boolean isEmptyList(){
        boolean result = false;
        int listSize = getPetManagerView().getCombo_petOwner().getItemCount();
        
        if(listSize ==0){
            result = true;
        }
        return result;
    }
    
    private boolean hasDataChanged(){
        boolean result = false;
        
        if(comoboSize != getPetManagerView().getCombo_petOwner().getItemCount()){
            result = true;
        }
        return result;
    }
    
    private void loadClientRegisterToCombo(){
        
        ClientManager clientManager = ClientManager.GetInstance();
        ArrayList<Client> clientList = clientManager.getClientList();
        getPetManagerView().getCombo_petOwner().removeAllItems();
        
        comoboSize = clientList.size();
        
        for(int index = 0; index < clientList.size(); index++ ){
            Client client = clientList.get(index);
            getPetManagerView().getCombo_petOwner().addItem(client.getName().toString());
        }
        
        
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            getPetModificationViewHelper().openWindow();
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
        getPetManagerView().dispose();
    }
    
    private void proceedWithElimination(){
        
        int rowIndex = getPetManagerView().getTable_petTable().getSelectedRow();
        String petOwner = getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        ClientManager clientManager = ClientManager.GetInstance();
        Client client = clientManager.getClientData(petOwner);
        client.getPets().remove(rowIndex);
        
        clientManager.updateClient(client);
        
        getNotifier().showSuccessMessage("Eliminacion exitosa", "exito al eliminar el Pet");
        updateTable();
    }
    
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = getPetManagerView().getTable_petTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(getPetManagerView().getTable_petTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    private void setTableContent(List<Pet> petList){    
        for(int index =0; index < petList.size(); index++ ){
            Pet petData = petList.get(index) ;
            addPetToTable(petData,index);
        }
    }
    
    private void openRegisterView(){ 
        if(!isEmptyCombo()){
            PetRegisterViewHelper.getInstance().setMode(1);
            PetRegisterViewHelper.getInstance().openWindow();
        }else{
            getNotifier().showWarningMessage( "No es posible añadir mascota debido a que no hay clientes registrados" );
        }
        
    }
    
    private void addPetToTable(Pet pet, int index){
        
        DefaultTableModel model = (DefaultTableModel) getPetManagerView().getTable_petTable().getModel();
        
        long id = index + 1;
        String name = pet.getName();
        int petAge = pet.getAge();
        String petBreed = pet.getBreed();
            
        Object[] row = new Object[]{id,name,petAge,petBreed };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        System.out.println("llegue");
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadPetRegisterToTable();
    }
    
}