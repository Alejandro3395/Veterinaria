/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.ClientManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.OperationalViewHelper;
import presentation.views.ClientRegisterView;

/**
 *
 * @author Mannuel
 */
public class ClientRegisterViewHelper extends OperationalViewHelper{
    private static ClientRegisterViewHelper clientRegisterViewHelper;
    private ClientRegisterView clientRegisterView;
    private ClientManagerViewHelper clientManagerViewHelper;
    private PetRegisterViewHelper petRegisterViewHelper;
    
    public ClientRegisterViewHelper(){
        setClientRegisterView(new ClientRegisterView());
        //setClientManagerHelper( clientManager  );
        
        initializeView();
    }
    
    public static ClientRegisterViewHelper getInstance(){
        if( clientRegisterViewHelper== null) {
         clientRegisterViewHelper = new ClientRegisterViewHelper();
        }
        return clientRegisterViewHelper;
    }

    public PetRegisterViewHelper getPetRegisterViewHelper() {
        return petRegisterViewHelper;
    }

    public void setPetRegisterViewHelper(PetRegisterViewHelper petRegisterViewHelper) {
        this.petRegisterViewHelper = petRegisterViewHelper;
    }
    
    public ClientRegisterView getClientRegisterView() {
        return clientRegisterView;
    }

    public void setClientRegisterView(ClientRegisterView clientRegisterView) {
        this.clientRegisterView = clientRegisterView;
    }

    public ClientManagerViewHelper getClientManagerHelper() {
        return clientManagerViewHelper;
    }

    public void setClientManagerHelper(ClientManagerViewHelper clientManagerViewHelper) {
        this.clientManagerViewHelper = clientManagerViewHelper;
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
                    PetRegisterViewHelper.getInstance().setOwner(clientData.get(0).toString());
                    PetRegisterViewHelper.getInstance().setMode(0);
                    PetRegisterViewHelper.getInstance().openWindow();
                resetFields();
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
        ClientManagerViewHelper.getInstance().updateTable();
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
    
     private void resetFields(){
        getClientRegisterView().getField_clientName().setText("");
        
        getClientRegisterView().getField_clientAddressPostalCode().setText("") ;
        
        getClientRegisterView().getField_clientAddressStreet().setText("");
                
        getClientRegisterView().getField_clientAddressColony().setText("");
        
        getClientRegisterView().getField_clientAddressCrossing().setText("");
        
        getClientRegisterView().getField_clientPhoneLada().setText("");
        
        getClientRegisterView().getField_clientPhoneNumber().setText("");
        
        getClientRegisterView().getField_clientEmail().setText("");
    }
}