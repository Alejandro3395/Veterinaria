/**
* class: PetManager (PetManager.java)
* @author: Diego Nicoli
* 
* date: October 27, 2016
* 
* This class represent the manager for the Pet entitys.
* The objective of the class is to recieve the data that the view
* collects and pass it to the entity class to insert it to the database.
* 
*/
package bussiness;



import Entitys.Client;
import Entitys.Pet;
import exceptions.InvalidFieldException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PetHandler {
    
     
    /**
     * Constants to use with the createPet method
     */
    
    static final int  nameIndex = 0;
    static final int  breedIndex = 2;
    static final int  ageIndex = 1;
    static final int  ownerIndex = 3;
    
    private static final PetHandler petHandler = new PetHandler();
    
    public PetHandler(){
       
    }
    
     /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return     
     /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    
    
    public static PetHandler GetInstance(){
        return petHandler;
    }
  
         
        /**
     * The method recieves the data array from the view and parse it 
     * so that the pet entity can understand it, finally we create a 
     * new entity, the method assumes that the data is passed in the correct order.
     * 
     * @param data
     * @return data
     * @throws InvalidFieldException 
     */
    public Pet createPet(ArrayList<String> data) throws InvalidFieldException {
        
        String petName = data.get(nameIndex);
        int  petAge = Integer.valueOf(data.get(ageIndex));
        String petBreed = data.get(breedIndex);
                
        Pet petData;
        
        petData = new Pet(petName,petBreed,petAge);

        return petData;
    }

    public void registerPet(ArrayList<String> petData,String petOwner) throws InvalidFieldException{

            Pet pet = new Pet(createPet(petData));
            ClientHandler clientManager = ClientHandler.GetInstance();
            Client client = clientManager.getClientData(petOwner); //aqui se llama a lo de get por nombre
            client.addPets(pet);
            clientManager.edit(client);
    }


    public void modifyPet(ArrayList<String> newPetData , String petOwner,int index) throws InvalidFieldException{

            List<Pet> petList =  getPetList(petOwner);
            Pet pet = petList.get(index-1);
            Pet updatedPet = createPet(newPetData);
            pet.setId(updatedPet.getId());
            ClientHandler clientManager = ClientHandler.GetInstance();
            Client client = clientManager.getClientData(petOwner); //aqui se llama a lo de get por nombre
            client.getPets().set(index-1,updatedPet);
            clientManager.edit(client);
    }
    
    
    public List<Pet> getPetList(String ownerName){
        ClientHandler clientManager = ClientHandler.GetInstance();
        
        Client ownerData = clientManager.getClientData(ownerName);
        List<Pet> petList;
        petList = new ArrayList<Pet>  (ownerData.getPets());
        return petList; 
    }
     
    
}