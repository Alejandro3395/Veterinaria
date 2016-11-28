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
    
    public void deleteClient(int id){
        Client client =  (Client)(clientDAO.get(id));
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
            Client updatedClient = createClient(newClientData);
            updatedClient.setId(client.getId());
            updateClient(updatedClient);
            message = "SUCCESS";
        }catch(InvalidFieldException exception){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    public ArrayList<Client> getClientList(){
        ArrayList<Client> clientList;
        clientList = new ArrayList<Client> ( (ArrayList<Client>) clientDAO.getList() );
        return clientList; 
    }
    
}
