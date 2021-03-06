/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Client;
import bussiness.ClientInformationHandler;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.InformationViewHelper;
import presentation.views.ClientRegisterView;

/**
 *
 * @author Jorge
 */
public class ModifyClientInfoViewHelper extends InformationViewHelper {
    private static ModifyClientInfoViewHelper modifyClientInfoViewHelper = null;
    private ClientRegisterView clientRegisterView;
    
    public ModifyClientInfoViewHelper(){
        setClientRegisterView( new ClientRegisterView() );
        
        initializeView();
    }
    
    public static ModifyClientInfoViewHelper getInstance(){
        if( modifyClientInfoViewHelper== null) {
         modifyClientInfoViewHelper = new ModifyClientInfoViewHelper();
        }
        return modifyClientInfoViewHelper;
    }

    public void setClientRegisterView(ClientRegisterView clientRegisterView) {
        this.clientRegisterView = clientRegisterView;
    }
    

    @Override
    public void loadView() {
        loadClientData();
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
        clientRegisterView.getBtn_register().addActionListener(actionEvent -> modifyClient());
        clientRegisterView.getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadClientData(){
        int row = ClientManagerViewHelper.getInstance().getClientManagerView().getTable_clientTable().getSelectedRow();
        int id = Integer.valueOf( ClientManagerViewHelper.getInstance().getClientManagerView().getTable_clientTable().getValueAt(row, 0).toString() );
        
        ClientInformationHandler clientInformationHandler = ClientInformationHandler.GetInstance();
        Client client =  clientInformationHandler.getClient(id) ;
        
        setData(client);
    }
    
    private void modifyClient(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int row = ClientManagerViewHelper.getInstance().getClientManagerView().getTable_clientTable().getSelectedRow();
        
        int id = Integer.valueOf( ClientManagerViewHelper.getInstance().getClientManagerView().getTable_clientTable().getValueAt(row, 0).toString() );
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            try{
                ClientInformationHandler clientInformationHandler = ClientInformationHandler.GetInstance();
                clientInformationHandler.modifyClient(data,id);
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Client");
                closeView();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
                getNotifier().showWarningMessage( message );
            }
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void cancelModification(){
        closeView();
    }
    
    private void closeView(){
        clearFields();
        clientRegisterView.dispose();
        ClientManagerViewHelper.getInstance().loadView();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
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
    private void setData(Client client){
        //setear datos de los campso
        
        String clientName = client.getName().toString();
        clientRegisterView.getField_clientName().setText(clientName);
        
        String clientPostalCode = Integer.toString(client.getAddress().getZipCode());
        clientRegisterView.getField_clientAddressPostalCode().setText(clientPostalCode);
        
        String clientStreet = client.getAddress().getStreet().toString();
        clientRegisterView.getField_clientAddressStreet().setText(clientStreet);
        
        String clientColony = client.getAddress().getColony().toString();
        clientRegisterView.getField_clientAddressColony().setText(clientColony);
        
        String clientCross = client.getAddress().getCrossovers().toString();
        clientRegisterView.getField_clientAddressCrossing().setText(clientCross);
        
        String clientPhoneLada = client.getPhone().getLada().toString();
        clientRegisterView.getField_clientPhoneLada().setText(clientPhoneLada);
        
        String clientPhoneNumber = client.getPhone().getNumber().toString();
        clientRegisterView.getField_clientPhoneNumber().setText(clientPhoneNumber);
        
        String clientEmail = client.getClientEmail().toString();
        clientRegisterView.getField_clientEmail().setText(clientEmail);
        clientRegisterView.getField_clientEmail().setEditable(false);  
        
    }
    
    private void resetFields(){
        loadClientData();
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