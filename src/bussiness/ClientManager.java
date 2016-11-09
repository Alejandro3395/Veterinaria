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
    private static final int RFCIndex = 7;
    

    
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
    
    
    private void saveClient(Client client)  {
        clientDAO.add(client);
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

        String clientName = data.get(0);
        int clientPostalCode = Integer.valueOf(data.get(1)) ;
        String clientAddressStreet = data.get(2);
        String clientAddressColony = data.get(3);
        String clientAddressCross = data.get(4);
        String clientPhoneLada = data.get(5);
        String clientPhoneNumber = data.get(6);
        String clientEmail = data.get(7);
        


        
        
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
        
        clientManager.saveClient(client);

    }
    
}
