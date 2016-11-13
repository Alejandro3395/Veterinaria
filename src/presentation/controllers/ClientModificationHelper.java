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
import presentation.AbstractRegisterController;
import presentation.views.ClientRegisterView;

/**
 *
 * @author Jorge
 */
public class ClientModificationHelper extends AbstractRegisterController {
    private ClientRegisterView clientRegisterView;
    private ClientManagerHelper clientManagerHelper;
    
    public ClientModificationHelper(ClientManagerHelper clientManager){
        setClientRegisterView( new ClientRegisterView() );
        setClientManagerHelper( clientManager);
        
        initializeView();
    }

    public ClientRegisterView getClientRegisterView() {
        return clientRegisterView;
    }

    public void setClientRegisterView(ClientRegisterView clientRegisterView) {
        this.clientRegisterView = clientRegisterView;
    }
    

    public ClientManagerHelper getClientManagerHelper() {
        return clientManagerHelper;
    }

    public void setClientManagerHelper(ClientManagerHelper clientManagerHelper) {
        this.clientManagerHelper = clientManagerHelper;
    }

    @Override
    public void openWindow() {
        loadClientData();
        getClientRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getClientRegisterView() );
        getClientRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getClientRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        getClientRegisterView().getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadClientData(){
        int row = getClientManagerHelper().getClientManagerView().getTable_clientTable().getSelectedRow();
        int id = Integer.valueOf( getClientManagerHelper().getClientManagerView().getTable_clientTable().getValueAt(row, 0).toString() );
        
        ClientManager clientManager = ClientManager.GetInstance();
        Client client =  clientManager.getClient(id) ;
        
        setData(client);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        
        int row = getClientManagerHelper().getClientManagerView().getTable_clientTable().getSelectedRow();
        
        int id = Integer.valueOf( getClientManagerHelper().getClientManagerView().getTable_clientTable().getValueAt(row, 0).toString() );
        
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
        getClientManagerHelper().updateTable();
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
    protected ArrayList<String> obtainData() {
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