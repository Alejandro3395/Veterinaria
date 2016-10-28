/**
* class: supplierManager (SupplierManager.java)
* @author: Diego Nicoli
* 
* date: October 27, 2016
* 
* This class represent the manager for the Supplier entitys.
* The objective of the class is to recieve the data that the view
* collects and pass it to the entity class to insert it to the database.
* 
*/
package bussiness;

import Data.DAOs.SupplierDAO;
import Entitys.Supplier;
import Entitys.Phone;
import java.sql.SQLException;
import java.util.ArrayList;
import Exception.InvalidFieldException;

/**
 *
 * @author diego
 */
public class SupplierManager {
    
    
    /**
     * Constants to use with the createSupplier method
     */
    
    final static int companyNameIndex = 0;
    final static int phoneIndex = 1;
    final static int ladaIndex = 2;
    final static int idIndex = 3;

    private static final SupplierManager supplierManager = new SupplierManager();
    private SupplierDAO supplierDAO;
    
    
    
    public SupplierManager(){
        this.supplierDAO = SupplierDAO.GetInstance();
    }
    
     /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    
    public static SupplierManager GetInstance(){
        return supplierManager;
    }
    
    
    private void saveSupplier(Supplier supplier) {
        supplierDAO.add(supplier);
    }
    
    
     /**
     * The method recieves the data array from the view and parse it 
     * so that the Supplier entity can understand it, finally we create a 
     * new entity, the method assumes that the data is passed in the correct order.
     * 
     * @paramdata
     * @return data
     * @throws InvalidFieldException 
     */
    
     public void  createSupplier(ArrayList<String> data){
         
         
         String companyName = data.get(companyNameIndex);
         String supplierPhoneLada = data.get(phoneIndex);
         String supplierPhoneNumber = data.get(ladaIndex);
         String supplierID= data.get(idIndex);
         
         try{
             
             Supplier supplierData;
        
        
        Phone supplierPhone = new Phone(supplierPhoneLada,supplierPhoneNumber);
        
         supplierData = new Supplier(companyName,supplierPhone);
         
        insertSupplier( supplierData );
             
         } catch(InvalidFieldException exception){
             System.out.println(exception.getMessage());
         }
        
      
        
    }
     
     
        public void insertSupplier( Supplier supplier ){
        
        
        supplierManager.saveSupplier(supplier);

    }
     
     
     
     }
    

