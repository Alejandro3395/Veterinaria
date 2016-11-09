/**
* class: PetDAO (PetDAO.java)
* @author: Diego Nicoli
* 
* date: October 27, 2016
* 
* This class represent the data section of the Pet class.
* 
* The main objective of this class is to make the comunication with the 
* database.
* 
*/
package Data.DAOs;


import Entitys.Pet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class PetDAO extends AbstractDAO<Pet> {
    private static final PetDAO PetDAO = new PetDAO();
    
    public PetDAO() {
    }
    
    
     public static PetDAO GetInstance() {
        return PetDAO;
    }
    
      @Override
    public void add(Pet Pet)  {
        saveEntity(Pet);
    }
    
    
    
    @Override
    public void delete(Pet entity) {
        deleteEntity(entity);
    }

    @Override
    public void update(Pet entity) {
        updateEntity(entity);
    }

     /**
     * This method recieves a pet object and checks if exists in the 
     * database.
     * @paramo:objectId
     * @return 
     */
    
     @Override
    public Object get(long objectId) {
        Pet Pet = null;
        
        try{
            openSession();
            
            Pet = (Pet) session.get(Pet.class,objectId);
        }finally{
            session.close();
        }
        return Pet;
    }

    
    /**
     * This method returns the collection of elements from the Pet type.
     * @return 
     */
   @Override
    public ArrayList<Pet> getList() {
        ArrayList<Pet> ProviderList = null;
        
        try{
            openSession();
            ProviderList = (ArrayList) session.createQuery("from Pet").list();
            
            
        }finally{
            session.close();
        }
        return ProviderList;
    }
}