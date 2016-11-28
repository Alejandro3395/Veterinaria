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
import presentation.DataViewHelper;
import presentation.views.ClientRegisterView;

/**
 *
 * @author Jorge
 */
public class ClientModificationViewHelper extends DataViewHelper {
    private static ClientModificationViewHelper clientModificationViewHelper = null;
    private ClientRegisterView clientRegisterView;
    
    public ClientModificationViewHelper(){
        setClientRegisterView( new ClientRegisterView() );
        //setClientManagerViewHelper( clientManager);
        
        initializeView();
    }
    
    public static ClientModificationViewHelper getInstance(){
        if( clientModificationViewHelper== null) {
         clientModificationViewHelper = new ClientModificationViewHelper();
        }
        return clientModificationViewHelper;
    }

    public ClientRegisterView getClientRegisterView() {
        return clientRegisterView;
    }

    public void setClientRegisterView(ClientRegisterView clientRegisterView) {
        this.clientRegisterView = clientRegisterView;
    }
    

    @Override
    public void loadView() {
        loadClientData();
        getClientRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getClientRegisterView() );
        getClientRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getClientRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        getClientRegisterView().getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadClientData(){
        int row = ClientManagerViewHelper.getInstance().getClientManagerView().getTable_clientTable().getSelectedRow();
        int id = Integer.valueOf( ClientManagerViewHelper.getInstance().getClientManagerView().getTable_clientTable().getValueAt(row, 0).toString() );
        
        ClientManager clientManager = ClientManager.GetInstance();
        Client client =  clientManager.getClient(id) ;
        
        setData(client);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int row = ClientManagerViewHelper.getInstance().getClientManagerView().getTable_clientTable().getSelectedRow();
        
        int id = Integer.valueOf( ClientManagerViewHelper.getInstance().getClientManagerView().getTable_clientTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        String successStatus = "SUCCESS";
        
        if( isValidField ){
            ClientManager clientManager = ClientManager.GetInstance();
            message = clientManager.modifyClient(data,id);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Client");
                updateManagerViewTable();
                closeWindow();
            }else{
                getNotifier().showWarningMessage( message );
            } 
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void updateManagerViewTable(){
        ClientManagerViewHelper.getInstance().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        getClientRegisterView().dispose();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
     @Override
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String clientName = getClientRegisterView().getField_clientName().getText();
        data.add(clientName);
        
        String clientPostalCode = getClientRegisterView().getField_clientAddressPostalCode().getText() ;
        data.add(clientPostalCode);
        
        String clientAddressStreet = getClientRegisterView().getField_clientAddressStreet().getText();
        data.add(clientAddressStreet);
        
        String clientAddressColony = getClientRegisterView().getField_clientAddressColony().getText() ;
        data.add(clientAddressColony);
        
        String clientAddressCross = getClientRegisterView().getField_clientAddressCrossing().getText() ;
        data.add(clientAddressCross);
        
        String clientPhoneLada = getClientRegisterView().getField_clientPhoneLada().getText();
        data.add(clientPhoneLada);
        
        String clientPhoneNumber = getClientRegisterView().getField_clientPhoneNumber().getText() ;
        data.add(clientPhoneNumber);
        
        String clientEmail = getClientRegisterView().getField_clientEmail().getText() ;
        data.add(clientEmail);
        
        return data;
    }
    private void setData(Client client){
        //setear datos de los campso
        
        String clientName = client.getName().toString();
        getClientRegisterView().getField_clientName().setText(clientName);
        
        String clientPostalCode = Integer.toString(client.getAddress().getZipCode());
        getClientRegisterView().getField_clientAddressPostalCode().setText(clientPostalCode);
        
        String clientStreet = client.getAddress().getStreet().toString();
        getClientRegisterView().getField_clientAddressStreet().setText(clientStreet);
        
        String clientColony = client.getAddress().getColony().toString();
        getClientRegisterView().getField_clientAddressColony().setText(clientColony);
        
        String clientCross = client.getAddress().getCrossovers().toString();
        getClientRegisterView().getField_clientAddressCrossing().setText(clientCross);
        
        String clientPhoneLada = client.getPhone().getLada().toString();
        getClientRegisterView().getField_clientPhoneLada().setText(clientPhoneLada);
        
        String clientPhoneNumber = client.getPhone().getNumber().toString();
        getClientRegisterView().getField_clientPhoneNumber().setText(clientPhoneNumber);
        
        String clientEmail = client.getClientEmail().toString();
        getClientRegisterView().getField_clientEmail().setText(clientEmail);
        getClientRegisterView().getField_clientEmail().setEditable(false);  
        
    }
    
    private void resetFields(){
        loadClientData();
    }
}