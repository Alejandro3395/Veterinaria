package bussiness;

import Data.DAOs.ClientDAO;
import Entitys.Address;
import Entitys.Client;
import Entitys.Phone;
import exceptions.InvalidFieldException;
import java.util.ArrayList;

/**
* class: ClientManager (ClientManager.java)
* @author: Manuel Bojorquez
* 
* date: October 27, 2016
* 
* This class represent the manager for the client entitys.
* The objective of the class is to recieve the data that the view
* collects and pass it to the entity class to insert it to the database.
* 
*/

public class ClientManager {
    private static final ClientManager clientManager = new ClientManager();
    private ClientDAO clientDAO;
    
    /**
     * Constants to use with the createClient method
     */
    private static final int nameIndex = 0;
    private static final int postalCodeIndex = 1;
    private static final int adressStreetIndex = 2;
    private static final int addressColonyIndex = 3;
    private static final int addressCrossIndex = 4;
    private static final int phoneLadaIndex = 5;
    private static final int phoneNumberIndex = 6;
    private static final int emailIndex = 7;
    

    
    public ClientManager(){
        this.clientDAO = ClientDAO.GetInstance();
    }
    
    
    /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return clientManager
     */
    public static ClientManager GetInstance(){
        return clientManager;
    }
    
    
    public void addClient(Client client)  {
        clientDAO.add(client);
    }
    
    public void deleteClient(Client client){
        clientDAO.delete(client);
    }
    
    public void updateClient(Client client){
        clientDAO.update(client);
    }
    
    public Client getClient(int id){
        return (Client) clientDAO.get(id);
    }
    
    public Client getClientData(String clientName){
        return clientDAO.getClientByName(clientName);
    }
     
    public void eliminateClient(int id){
       deleteClient((Client)(clientDAO.get(id)));
    }
    
    /**
     * The method recieves the data array from the view and parse it 
     * so that the client entity can understand it, finally we create a 
     * new entity, the method assumes that the data is passed in the correct order.
     * 
     * @param data
     * @return data
     * @throws InvalidFieldException 
     */
    
    public Client createClient(ArrayList<String> data) throws InvalidFieldException {

        String clientName = data.get(nameIndex);
        int clientPostalCode = Integer.valueOf(data.get(postalCodeIndex)) ;
        String clientAddressStreet = data.get(adressStreetIndex);
        String clientAddressColony = data.get(addressColonyIndex);
        String clientAddressCross = data.get(addressCrossIndex);
        String clientPhoneLada = data.get(phoneLadaIndex);
        String clientPhoneNumber = data.get(phoneNumberIndex);
        String clientEmail = data.get(emailIndex);
        


        
        
        Client clientData;
        
        Address clientAddress = new Address(
                                            clientPostalCode,
                                            clientAddressStreet ,
                                            clientAddressColony ,
                                            clientAddressCross);
        
        Phone clientPhone = new Phone(clientPhoneLada,clientPhoneNumber);
        
         clientData = new Client(clientName,clientAddress,clientPhone, clientEmail);

        return clientData;
    }
    
    /**
     * The method recieves the data from the view and uses it to create the new 
     * entitys, an exception is thrown if there's an invalid data.
     * 
     * @param clientData
     * @param userClientData 
     */
    public String registerClient(ArrayList<String> clientData){
        String message ="";
        try{
            Client client = new Client(createClient(clientData));
            clientManager.addClient(client);
            message = "SUCCESS";
        }catch(InvalidFieldException exception ){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    public String modifyClient(ArrayList<String> newClientData , int id){
        String message = "";
 
        try{
            Client client =  (getClient(id));
            ArrayList<String> clientData = new ArrayList<>( getClientData(client) );
            
            if(isNewData(clientData, newClientData)){
                Client updatedClient = updateData(client,newClientData);
                updateClient(updatedClient);
                message = "SUCCESS";
            }else{
                System.out.println("datos sin cambio");
            }
        }catch(InvalidFieldException exception){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    private Client updateData(Client client, ArrayList<String> newClientData) throws InvalidFieldException{
        
        String newClientName = newClientData.get(nameIndex);
        int  newClientPostalCode = Integer.valueOf(newClientData.get(postalCodeIndex));
        String newClientAddressStreet = newClientData.get(adressStreetIndex);
        String newClientAddressColony = newClientData.get(addressColonyIndex);
        String newClientAddressCross = newClientData.get(addressCrossIndex);
        String newClientPhoneLada = newClientData.get(phoneLadaIndex);
        String newClientPhoneNumber = newClientData.get(phoneNumberIndex);
        String newClientEmail = newClientData.get(emailIndex);
        
        client.setName(newClientName);
        
        Address newAddress = new Address(newClientPostalCode,newClientAddressStreet,newClientAddressColony,newClientAddressCross);
        client.setAddress(newAddress);
        
        Phone newPhone = new Phone(newClientPhoneLada,newClientPhoneNumber);
        client.setPhone(newPhone);
        
        client.setClientEmail(newClientEmail);

        return client;
    }
    
    
    private ArrayList<String> getClientData(Client client){
        
        ArrayList<String> data = new ArrayList<String>();
        
        data.add(client.getName().toString());
        data.add( Integer.toString( client.getAddress().getZipCode() ) );
        data.add(client.getAddress().getStreet().toString());
        data.add(client.getAddress().getColony().toString());
        data.add(client.getAddress().getCrossovers().toString());
        data.add(client.getPhone().getLada().toString());
        data.add(client.getPhone().getNumber().toString());
        data.add(client.getClientEmail().toString());
        
        return data;
    }
    
    private boolean isNewData(ArrayList<String> clientData, ArrayList<String> newClientData){
        boolean result = false;
        int diferences = 0;
        
        for(int index = 0; index < newClientData.size(); index++){
            
            if(!( clientData.get(index).equals(newClientData.get(index) ) ) ){
                diferences++;
            }
        }
        
        if (diferences>0) { result = true;  }
        
        return result;
    }
    
    
    //Posiblemente se elimine uno
    public ArrayList<Client> getClientList(){
        ArrayList<Client> clientList;
        clientList = new ArrayList<Client> (getClients());
        return clientList; 
    }
    
    
    public ArrayList<Client> getClients(){
        return (ArrayList<Client>) clientDAO.getList();
    }     
    
    
    ///Revisar este metodo
    /**
     * The method recieves the data from the view and uses it to create the new 
     * entitys, an exception is thrown if there's an invalid data.
     * 
     * @param clientData
     */
 
    public void createEntity(ArrayList<String> clientData) throws InvalidFieldException {
        
        Client client = new Client(createClient(clientData));
        insertClient(client);
        
    }
    
    
    /**
     * The method recieves the client entity to insert the client into the DataBase.
     * @param client
     */
    public void insertClient(Client client) {
        
        clientManager.addClient(client);

    }
    
}
