/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.ClientManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.views.ClientRegisterView;

/**
 *
 * @author Jorge
 */
public class ClientRegisterController extends AbstractRegisterController{
    private ClientRegisterView clientRegisterView;
    private ClientManagerHelper clientManagerHelper;
    
    public ClientRegisterController(ClientManagerHelper clientManager){
        setClientRegisterView(new ClientRegisterView());
        setClientManagerHelper( clientManager  );
        
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
        getClientRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
        getClientRegisterView().getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
    }
    
    private void proceedWithRegistration(){
        ArrayList<String> clientData = new ArrayList<String>(obtainData());
              
        boolean isValidField =!isEmptyFields(clientData);
        
        String message="";
        String successStatus="SUCCESS";
        
        if(isValidField){
            ClientManager clientManager = ClientManager.GetInstance();
            message = clientManager.registerClient(clientData);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Client");
                updateManagerViewTable();
                closeWindow();
            }else{
                getNotifier().showWarningMessage( message );
            }
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
        }
    }
    
    private void cancelRegistration(){
        closeWindow();
    }
    
    private void closeWindow(){
        getClientRegisterView().dispose();
    }
    
    private void updateManagerViewTable(){
        getClientManagerHelper().updateTable();
    }
    
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
}
