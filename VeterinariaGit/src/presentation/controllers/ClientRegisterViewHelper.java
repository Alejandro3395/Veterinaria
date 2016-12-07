/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.ClientManager;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.ClientRegisterView;

/**
 *
 * @author Mannuel
 */
public class ClientRegisterViewHelper extends DataViewHelper{
    private static ClientRegisterViewHelper clientRegisterViewHelper;
    private ClientRegisterView clientRegisterView;
    
    public ClientRegisterViewHelper(){
        setClientRegisterView(new ClientRegisterView());
        initializeView();
    }
    
    
    
    public static ClientRegisterViewHelper getInstance(){
        if( clientRegisterViewHelper== null) {
         clientRegisterViewHelper = new ClientRegisterViewHelper();
        }
        return clientRegisterViewHelper;
    }
    
    public void setClientRegisterView(ClientRegisterView clientRegisterView) {
        this.clientRegisterView = clientRegisterView;
    }
    
    @Override
    public void loadView() {
        clientRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( clientRegisterView );
        clientRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        clientRegisterView.getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
        clientRegisterView.getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
    }
    
    private void proceedWithRegistration(){
        
        ArrayList<String> clientData = new ArrayList<String>(obtainDataFromView());
              
        boolean isValidField =!isEmptyFields(clientData);
        
        String message="";
        
        if(isValidField){
            try{
                ClientManager clientManager = ClientManager.GetInstance();
                clientManager.registerClient(clientData);
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Client");
                updateManagerViewTable();
                clearFields();
                closeWindow();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
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
        clientRegisterView.dispose();
        clearFields();
        ClientManagerViewHelper.getInstance().loadView();
        
    }
    
    private void updateManagerViewTable(){
        ClientManagerViewHelper.getInstance().updateTable();
    }
    
    @Override
    protected ArrayList<String> obtainDataFromView() {
        
        ArrayList<String> data = new ArrayList<String>();
        
        String clientName = clientRegisterView.getField_clientName().getText();
        data.add(clientName);
        
        String clientPostalCode = clientRegisterView.getField_clientAddressPostalCode().getText() ;
        data.add(clientPostalCode);
        
        String clientAddressStreet = clientRegisterView.getField_clientAddressStreet().getText();
        data.add(clientAddressStreet);
        
        String clientAddressColony = clientRegisterView.getField_clientAddressColony().getText() ;
        data.add(clientAddressColony);
        
        String clientAddressCross = clientRegisterView.getField_clientAddressCrossing().getText() ;
        data.add(clientAddressCross);
        
        String clientPhoneLada = clientRegisterView.getField_clientPhoneLada().getText();
        data.add(clientPhoneLada);
        
        String clientPhoneNumber = clientRegisterView.getField_clientPhoneNumber().getText() ;
        data.add(clientPhoneNumber);
        
        String clientEmail = clientRegisterView.getField_clientEmail().getText() ;
        data.add(clientEmail);
        
        return data;
    }
    
     @Override
    protected void clearFields() {
        clientRegisterView.getField_clientName().setText("");
        
        clientRegisterView.getField_clientAddressPostalCode().setText("") ;
        
        clientRegisterView.getField_clientAddressStreet().setText("");
                
        clientRegisterView.getField_clientAddressColony().setText("");
        
        clientRegisterView.getField_clientAddressCrossing().setText("");
        
        clientRegisterView.getField_clientPhoneLada().setText("");
        
        clientRegisterView.getField_clientPhoneNumber().setText("");
        
        clientRegisterView.getField_clientEmail().setText("");
    }
}