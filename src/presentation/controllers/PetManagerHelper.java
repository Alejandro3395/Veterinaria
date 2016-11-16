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
import presentation.AbstractViewController;
import presentation.views.PetManagerView;

/**
 *
 * @author Jorge
 */
public class PetManagerHelper extends AbstractViewController {
    private static PetManagerHelper petManagerHelper = null;
    private PetManagerView petManagerView;
    private PetRegisterController petRegisterController;
    private PetModificationHelper petModificationHelper;
    private int comoboSize;
    
    public PetManagerHelper(){
        setPetManagerView(new PetManagerView());
        setPetRegisterController(PetRegisterController.getInstance());
        setPetModificationHelper(PetModificationHelper.getInstance());
        
        initializeView();
    }
    
    public static PetManagerHelper getInstance(){
        if( petManagerHelper== null) {
         petManagerHelper = new PetManagerHelper();
        }
        return petManagerHelper;
    }

    public PetModificationHelper getPetModificationHelper() {
        return petModificationHelper;
    }

    public void setPetModificationHelper(PetModificationHelper petModificationHelper) {
        this.petModificationHelper = petModificationHelper;
    }
    
    public PetManagerView getPetManagerView() {
        return petManagerView;
    }

    public void setPetManagerView(PetManagerView petManagerView) {
        this.petManagerView = petManagerView;
    }


    public PetRegisterController getPetRegisterController() {
        return petRegisterController;
    }

    public void setPetRegisterController(PetRegisterController petRegisterController) {
        this.petRegisterController = petRegisterController;
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
            getPetModificationHelper().openWindow();
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
        PetRegisterController.getInstance().setMode(1);
        PetRegisterController.getInstance().openWindow();
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