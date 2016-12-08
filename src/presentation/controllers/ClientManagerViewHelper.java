/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Client;
import bussiness.ClientInformationHandler;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import presentation.ViewHelper;
import presentation.views.ClientManagerView;

/**
 *
 * @author Jorge
 */
public class ClientManagerViewHelper extends ViewHelper {
    private static ClientManagerViewHelper clientManagerViewHelper = null;
    private ClientManagerView clientManagerView;
    
    public ClientManagerViewHelper(){
        setClientManagerView(new ClientManagerView());
        
        initializeView();
    }

    public static ClientManagerViewHelper getInstance(){
        if( clientManagerViewHelper== null) {
         clientManagerViewHelper = new ClientManagerViewHelper();
        }
        return clientManagerViewHelper;
    }
    
    public ClientManagerView getClientManagerView() {
        return clientManagerView;
    }

    public void setClientManagerView(ClientManagerView clientManagerView) {
        this.clientManagerView = clientManagerView;
    }

    @Override
    public void loadView() {
        loadClientRecordsToTable();
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
        getClientManagerView().getBtn_back().addActionListener(actionEvent -> closeView());
    }
    
    public void loadClientRecordsToTable(){
        
        DefaultTableModel model = (DefaultTableModel) getClientManagerView().getTable_clientTable().getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount !=0){model.setRowCount(0);}
        
        ClientInformationHandler clientInformationHandler = ClientInformationHandler.GetInstance();
        
        ArrayList<Client> clientList = clientInformationHandler.getClientList() ;
        setTableContent(clientList);
    }
    
    private void openModificationView(){
        if(isRowSelected()){
            clientManagerView.dispose();
            ModifyClientInfoViewHelper modifyClientInfoViewHelper = ModifyClientInfoViewHelper.getInstance();
            modifyClientInfoViewHelper.loadView();
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void openEliminationConfirmationView(){
        
        if(isRowSelected()){
            if(isDeletionConfirmed()){
                eliminateClient();
            }
        }else{
            getNotifier().showWarningMessage( "Porfavor elije un registro" );
        }
    }
    
    private void closeView(){
        getClientManagerView().dispose();
        RegisterSelectionViewHelper.getInstance().loadView();
    }
    
    private void eliminateClient(){
        int row = getClientManagerView().getTable_clientTable().getSelectedRow();
        
        int id = Integer.valueOf( getClientManagerView().getTable_clientTable().getValueAt(row, 0).toString() );

        ClientInformationHandler clientInformationHandler = ClientInformationHandler.GetInstance();
        clientInformationHandler.remove(id);
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
        clientManagerView.dispose();
        ClientRegisterViewHelper clientRegisterViewHelper = ClientRegisterViewHelper.getInstance();
        clientRegisterViewHelper.loadView();
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
        loadClientRecordsToTable();
    }
}