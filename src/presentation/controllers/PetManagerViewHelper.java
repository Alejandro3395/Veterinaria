/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Client;
import Entitys.Pet;
import bussiness.ClientInformationHandler;
import bussiness.PetInformationHandler;
import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.PetManagerView;

/**
 *
 * @author Jorge
 */
public class PetManagerViewHelper extends ViewHelper {
    private static PetManagerViewHelper petManagerViewHelper = null;
    private PetManagerView petManagerView;
    
    private int comboSize;
    
    public PetManagerViewHelper(){
        setPetManagerView(new PetManagerView());

        initializeView();
    }
    
    public static PetManagerViewHelper getInstance(){
        if( petManagerViewHelper== null) {
         petManagerViewHelper = new PetManagerViewHelper();
        }
        return petManagerViewHelper;
    }
    
    public PetManagerView getPetManagerView() {
        return petManagerView;
    }

    public void setPetManagerView(PetManagerView petManagerView) {
        this.petManagerView = petManagerView;
    }

    @Override
    public void loadView() {
        getPetManagerView().setVisible(true);
        loadClientRegisterToCombo();
        loadPetRecordsToTable();
        
    }

    @Override
    protected void initializeView() {
        configureView( getPetManagerView() );
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
        getPetManagerView().getCombo_petOwner().addActionListener( actionEvent -> loadPetRecordsToTable() );
        getPetManagerView().getBtn_back().addActionListener(actionEvent -> closeView());
    }
    
    public void loadPetRecordsToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getPetManagerView().getTable_petTable().getModel();
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        if(!isEmptyList()){
            if(!hasDataChanged()){
                
                
                
                PetInformationHandler petInformationHandler = PetInformationHandler.GetInstance();
                String ownerName = getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
            
                List<Pet> petList = petInformationHandler.getPetList(ownerName) ;
                setTableContent(petList); 
            }
        } 
        
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
        
        if(comboSize != getPetManagerView().getCombo_petOwner().getItemCount()){
            result = true;
        }
        return result;
    }
    
    private void loadClientRegisterToCombo(){
        
        ClientInformationHandler clientManager = ClientInformationHandler.GetInstance();
        ArrayList<Client> clientList = clientManager.getClientList();
        getPetManagerView().getCombo_petOwner().removeAllItems();
        
        comboSize = clientList.size();
        
        if(comboSize == 0){
          getNotifier().showWarningMessage("No existen Clientes registrados");
        }else{
          for(int index = 0; index < clientList.size(); index++ ){
            Client client = clientList.get(index);
            getPetManagerView().getCombo_petOwner().addItem(client.getName().toString());
          }  
        }
        
        
        
        
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            getPetManagerView().dispose();
            ModifyPetInfoViewHelper modifyPetInfoViewHelper = ModifyPetInfoViewHelper.getInstance();
            modifyPetInfoViewHelper.loadView();
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void openEliminationConfirmationView(){
        
        if(isRowSelected()){
            if(isDeletionConfirmed()){
                eliminatePet();
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void closeView(){
        getPetManagerView().dispose();
        RegisterSelectionViewHelper.getInstance().loadView();
    }
    
    private void eliminatePet(){
        
        int rowIndex = getPetManagerView().getTable_petTable().getSelectedRow();
        String petOwner = getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        ClientInformationHandler clientManager = ClientInformationHandler.GetInstance();
        Client client = clientManager.getClientData(petOwner);
        client.getPets().remove(rowIndex);
        
        clientManager.edit(client);
        
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
            getPetManagerView().dispose();
            PetRegisterViewHelper petRegisterViewHelper = PetRegisterViewHelper.getInstance();
            //petRegisterViewHelper.getInstance().setMode(1);
            petRegisterViewHelper.getInstance().loadView();
        
    }
    
    private void addPetToTable(Pet pet, int index){
        
        DefaultTableModel model = (DefaultTableModel) getPetManagerView().getTable_petTable().getModel();
        
        long id = index + 1;
        String name = pet.getName();
        int petAge = pet.getAge();
        String petType = pet.getType();
        
        Object[] row = new Object[]{id,name,petAge,petType };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        System.out.println("llegue");
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadPetRecordsToTable();
    }
    
}