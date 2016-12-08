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



public class PetInformationHandler {
    
     
    /**
     * Constants to use with the createPet method
     */
    
    static final int  nameIndex = 0;
    static final int  breedIndex = 2;
    static final int  ageIndex = 1;
    static final int  ownerIndex = 3;
    
    private static final PetInformationHandler petInformationHandler = new PetInformationHandler();
    
    public PetInformationHandler(){
       
    }
    
     /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return     
     /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return     
     /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return     
     /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    
    
    public static PetInformationHandler GetInstance(){
        return petInformationHandler;
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

    public void registerPet(ArrayList<String> petData,String clientName) throws InvalidFieldException{

        Pet pet = new Pet(createPet(petData));
        ClientInformationHandler clientHandler = ClientInformationHandler.GetInstance();
        clientHandler.addPets(clientName,pet);
            
    }


    public void modifyPet(ArrayList<String> newPetData , String clientName,int index) throws InvalidFieldException{

        List<Pet> petList =  getPetList(clientName);
        Pet pet = petList.get(index-1);
        Pet updatedPet = createPet(newPetData);
        pet.setId(updatedPet.getId());
        ClientInformationHandler clientHandler = ClientInformationHandler.GetInstance();
        clientHandler.updatePet(clientName,pet,index);

    }
    
    
    public List<Pet> getPetList(String ownerName){
        ClientInformationHandler clientManager = ClientInformationHandler.GetInstance();
        
        Client ownerData = clientManager.getClientData(ownerName);
        List<Pet> petList;
        petList = new ArrayList<Pet>  (ownerData.getPets());
        return petList; 
    }
     
    
}