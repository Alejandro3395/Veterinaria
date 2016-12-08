package bussiness;

import Data.DAOs.ClientDAO;
import Entitys.Address;
import Entitys.Client;
import Entitys.Pet;
import Entitys.Phone;
import exceptions.InvalidFieldException;
import java.util.ArrayList;

/**
* class: ClientInformationHandler (ClientInformationHandler.java)
* @author: Manuel Bojorquez
* 
* date: October 27, 2016
* 
* This class represent the manager for the client entitys.
* The objective of the class is to recieve the data that the view
* collects and pass it to the entity class to insert it to the database.
* 
*/

public class ClientInformationHandler extends  Receptionist<Client> {
    private static final ClientInformationHandler clientInformationHandler = new ClientInformationHandler();
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
    

    
    public ClientInformationHandler(){
        this.clientDAO = ClientDAO.GetInstance();
    }
    
    
    /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return clientInformationHandler
     */
    public static ClientInformationHandler GetInstance(){
        return clientInformationHandler;
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
    public void registerClient(ArrayList<String> clientData) throws InvalidFieldException{
            Client client = new Client(createClient(clientData));
            register(client);
    }

    public void modifyClient(ArrayList<String> newClientData , int id) throws InvalidFieldException{
      Client client =  (getClient(id));
      Client updatedClient = createClient(newClientData);
      updatedClient.setId(client.getId());
      edit(updatedClient);
    }
    
    public void addPets(String clientName,Pet pet){
        Client client = getClientData(clientName); //aqui se llama a lo de get por nombre
        client.addPets(pet);
        edit(client);
    }
    
    public ArrayList<Client> getClientList(){
        ArrayList<Client> clientList;
        clientList = new ArrayList<Client> ( (ArrayList<Client>) clientDAO.getList() );
        return clientList; 
    }
    
    public void updatePet(String clientName,Pet pet,int index){
        Client client = getClientData(clientName); //aqui se llama a lo de get por nombre
        client.getPets().set(index-1,pet);
        edit(client);
    }

    @Override
    public void register(Client client) {
        clientDAO.add(client);
    }

    @Override
    public void edit(Client client) {
        clientDAO.update(client);
    }

    @Override
    public void remove(int id) {
        Client client =  (Client)(clientDAO.get(id));
        clientDAO.delete(client);
    }
    
}
