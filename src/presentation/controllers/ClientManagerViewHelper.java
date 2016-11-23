/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Client;
import bussiness.ClientManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.CommonBehaviorViewHelper;
import presentation.views.ClientManagerView;

/**
 *
 * @author Jorge
 */
public class ClientManagerViewHelper extends CommonBehaviorViewHelper {
    private static ClientManagerViewHelper clientManagerViewHelper = null;
    private ClientManagerView clientManagerView;
    private ClientRegisterViewHelper clientRegisterViewHelper;
    private ClientModificationViewHelper clientModificationHelper;
    
    public ClientManagerViewHelper(){
        setClientManagerView(new ClientManagerView());
        setClientRegisterViewHelper( ClientRegisterViewHelper.getInstance());
        setClientModificationHelper( ClientModificationViewHelper.getInstance());
        
        initializeView();
    }

    public static ClientManagerViewHelper getInstance(){
        if( clientManagerViewHelper== null) {
         clientManagerViewHelper = new ClientManagerViewHelper();
        }
        return clientManagerViewHelper;
    }
    
    public ClientModificationViewHelper getClientModificationHelper() {
        return clientModificationHelper;
    }

    public void setClientModificationHelper(ClientModificationViewHelper clientModificationHelper) {
        this.clientModificationHelper = clientModificationHelper;
    }
    
    public ClientManagerView getClientManagerView() {
        return clientManagerView;
    }

    public void setClientManagerView(ClientManagerView clientManagerView) {
        this.clientManagerView = clientManagerView;
    }


    public ClientRegisterViewHelper getClientRegisterViewHelper() {
        return clientRegisterViewHelper;
    }

    public void setClientRegisterViewHelper(ClientRegisterViewHelper clientRegisterViewHelper) {
        this.clientRegisterViewHelper = clientRegisterViewHelper;
    } 

    @Override
    public void loadView() {
        loadClientRegisterToTable();
        getClientManagerView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getClientManagerView() );
        getClientManagerView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getClientManagerView().getBtn_addClient().addActionListener(actionEvent -> openRegisterView());
        getClientManagerView().getBtn_modifyClient().addActionListener(actionEvent -> openModificationView());
        getClientManagerView().getBtn_deleteClient().addActionListener(actionEvent -> openEliminationConfirmationView());
        getClientManagerView().getBtn_back().addActionListener(actionEvent -> closeWindow());
    }
    
    public void loadClientRegisterToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getClientManagerView().getTable_clientTable().getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        ClientManager clientManager = ClientManager.GetInstance();
        
        ArrayList<Client> clientList = clientManager.getClientList() ;
        setTableContent(clientList);
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            getClientModificationHelper().loadView();
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
        getClientManagerView().dispose();
    }
    
    private void proceedWithElimination(){
        int row = getClientManagerView().getTable_clientTable().getSelectedRow();
        
        int id = Integer.valueOf( getClientManagerView().getTable_clientTable().getValueAt(row, 0).toString() );

        ClientManager clientManager = ClientManager.GetInstance();
        clientManager.deleteClient(id);
        getNotifier().showSuccessMessage("Eliminacion exitosa", "exito al eliminar el Client");
        updateTable();
    }
    
    private boolean isRowSelected(){
        boolean result = false;
        
        int rows = getClientManagerView().getTable_clientTable().getRowCount();
        
        for(int i =0; i < rows; i++ ){
            if(getClientManagerView().getTable_clientTable().isRowSelected(i)){
                result = true;
            }
        }
        return result;
    }
    
    private void setTableContent(ArrayList<Client> clientList){    
        for(int index =0; index < clientList.size(); index++ ){
            Client clientData = clientList.get(index) ;
            addClientToTable(clientData);
        }
    }
    
    private void openRegisterView(){
        getClientRegisterViewHelper().loadView();
    }
    
    private void addClientToTable(Client client){
        
        DefaultTableModel model = (DefaultTableModel) getClientManagerView().getTable_clientTable().getModel();
        
        long id = client.getId();
        String name = client.getName();
        String street =  client.getAddress().getStreet();
        int postal = client.getAddress().getZipCode();
        String colony = client.getAddress().getColony();
        String cross = client.getAddress().getCrossovers();
        String lada = client.getPhone().getLada();
        String number = client.getPhone().getNumber();
            
        Object[] row = new Object[]{id,name,street,postal,colony,cross,lada,number };
        model.addRow(row); 
    }
    
    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminarlo?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    
    public void updateTable(){
        loadClientRegisterToTable();
    }
}