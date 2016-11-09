/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.ClientManager;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.AbstractViewController;
import presentation.views.ClientRegisterView;

/**
 *
 * @author Jorge
 */
public class ClientRegisterController extends AbstractRegisterController{
    private ClientRegisterView customerRegisterView;
    
    public ClientRegisterController(){
        setClientRegisterView(new ClientRegisterView());
        
        initializeView();
    }

    public ClientRegisterView getClientRegisterView() {
        return customerRegisterView;
    }

    public void setClientRegisterView(ClientRegisterView customerRegisterView) {
        this.customerRegisterView = customerRegisterView;
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
        getClientRegisterView().getBtn_register().addActionListener(actionEvent -> {
            try {
                registerClient();
            } catch (InvalidFieldException ex) {
                Logger.getLogger(ClientRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void registerClient() throws InvalidFieldException{
           ArrayList<String> clientData = new ArrayList<String>(obtainData());
        

        
        boolean isValidField =!isEmptyFields(clientData);
        
        if(isValidField){
            ClientManager clientManager = ClientManager.GetInstance();
                 clientManager.createEntity(clientData);
        }
        
    }

    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        
        String clientName = getClientRegisterView().getField_customerName().getText();
        data.add(clientName);
        
        String clientPostalCode = getClientRegisterView().getField_customerAddressPostalCode().getText() ;
        data.add(clientPostalCode);
        
        String clientAddressStreet = getClientRegisterView().getField_customerAddressStreet().getText();
        data.add(clientAddressStreet);
        
        String clientAddressColony = getClientRegisterView().getField_customerAddressColony().getText() ;
        data.add(clientAddressColony);
        
        String clientAddressCross = getClientRegisterView().getField_customerAddressCrossing().getText() ;
        data.add(clientAddressCross);
        
        String clientPhoneLada = getClientRegisterView().getField_customerPhoneLada().getText();
        data.add(clientPhoneLada);
        
        String clientPhoneNumber = getClientRegisterView().getField_customerPhoneNumber().getText() ;
        data.add(clientPhoneNumber);
        
        String clientEmail = getClientRegisterView().getField_customerEmail().getText() ;
        data.add(clientEmail);
        
        return data;
    }
    
}
