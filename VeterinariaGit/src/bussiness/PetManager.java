/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import Data.DAOs.PetDAO;
import Entitys.Client;
import Entitys.Pet;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class PetManager {
    private static final PetManager petManager = new PetManager();
    private PetDAO petDAO;
    
    /**
     * Constants to use with the createPet method
     */
    private static final int nameIndex = 0;
    private static final int ageIndex = 1;
    private static final int breedIndex = 2;
    
    
    
    public PetManager(){
        this.petDAO = PetDAO.GetInstance();
    }
    
    /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    public static PetManager GetInstance(){
        return petManager;
    }
    
    
    private void addPet(Pet pet) {
        petDAO.add(pet);
    }
    
    private void deletePet(Pet pet){
        petDAO.delete(pet);
    }
    
    private void updatePet(Pet pet){
        petDAO.update(pet);
    }
    
    public Pet getPet(int id){
        return (Pet) petDAO.get(id);
    }
     
    public void eliminatePet(int id){
       deletePet((Pet)(petDAO.get(id)));
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

    public String registerPet(ArrayList<String> petData,String petOwner){
        String message ="";
        try{
            Pet pet = new Pet(createPet(petData));
            ClientManager clientManager = ClientManager.GetInstance();
            Client client = clientManager.getClientData(petOwner); //aqui se llama a lo de get por nombre
            client.addPets(pet);
            clientManager.updateClient(client);
            message = "SUCCESS";
            
        }catch(InvalidFieldException exception ){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    
    public String modifyPet(ArrayList<String> newPetData , String petOwner,int index){
        String message = "";
        
        try{
            List<Pet> petList =  getPetList(petOwner);
            Pet pet = petList.get(index);
            ArrayList<String> petData = new ArrayList<>( getPetData(pet) );
            
            if(isNewData(petData, newPetData)){
                Pet updatedPet = updateData(pet,newPetData);
                ClientManager clientManager = ClientManager.GetInstance();
                Client client = clientManager.getClientData(petOwner); //aqui se llama a lo de get por nombre
                client.getPets().set(index,pet);
                clientManager.updateClient(client);
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
    
    private Pet updateData(Pet pet, ArrayList<String> newPetData) throws InvalidFieldException{
        
        String petName = newPetData.get(nameIndex);
        int  petAge = Integer.valueOf(newPetData.get(ageIndex));
        String petBreed = newPetData.get(breedIndex);
        
        pet.setName(petName);
        
        pet.setAge(petAge);
        
        pet.setBreed(petBreed);
       
        return pet;
    }
    
    private ArrayList<String> getPetData(Pet pet){
        
        ArrayList<String> data = new ArrayList<String>();
        
        data.add(pet.getName().toString());
        data.add( Integer.toString( pet.getAge()) );
        data.add(pet.getBreed().toString());
        
        return data;
    }
    
    private boolean isNewData(ArrayList<String> petData, ArrayList<String> newPetData){
        boolean result = false;
        int diferences = 0;
        
        for(int index = 0; index < newPetData.size(); index++){
            
            if(!( petData.get(index).equals(newPetData.get(index) ) ) ){
                diferences++;
            }
        }
        
        if (diferences>0) { result = true;  }
        
        return result;
    }
    
    public List<Pet> getPetList(String ownerName){
        
        //Client ownerData = getOwnerData(ownerName);
        ClientManager clientManager = ClientManager.GetInstance();
        
        Client ownerData = clientManager.getClientData(ownerName);
        List<Pet> petList;
        petList = new ArrayList<Pet>  (getPets(ownerData));
        return petList; 
    }
    
    public List<Pet> getPets(Client ownerData){
        //System.out.println("pets: "+ownerData.getPets().get(0));
        return ownerData.getPets();
    }
    
}
