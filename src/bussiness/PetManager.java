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


import Data.DAOs.PetDAO;
import Entitys.Pet;
import java.sql.SQLException;
import java.util.ArrayList;



public class PetManager {
    
     
    /**
     * Constants to use with the createPet method
     */
    
    static final int  nameIndex = 0;
    static final int  breedIndex = 1;
    static final int  ageIndex = 2;
    static final int  ownerIndex = 3;
    
    private static final PetManager petManager = new PetManager();
    private PetDAO petDAO;
    
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
    
    
    private void savePet(Pet pet) {
        petDAO.add(pet);
    }
    
    
    
     /**
     * The method recieves the data array from the view and parse it 
     * so that the Pet entity can understand it, finally we create a 
     * new entity.
     * 
     * @paramdata
     * @return data
     * @throws InvalidFieldException 
     */
    
     public void  createPet(ArrayList<String> data){
         
         
         String petName = data.get(nameIndex);
         String petBreed = data.get(breedIndex);
         int  petAge = Integer.valueOf(data.get(ageIndex));         
         String petOwner = data.get(ownerIndex);
        
        
        Pet petData;
        
        
         petData = new Pet(petAge, petName, petBreed );
         
        insertPet( petData );
        
        
        
        
     }
     
         public void insertPet( Pet pet ){
              
            petManager.savePet(pet);

    }
     
     
    
}

     
     