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
import exceptions.InvalidFieldException;

/**
 *
 * @author diego
 */
public class SupplierManager {
    private static final SupplierManager supplierManager = new SupplierManager();
    private SupplierDAO supplierDAO;
    
    /**
     * Constants to use with the createSupplier method
     */
    
    private static final int nameIndex = 0;
    private static final int phoneLadaIndex = 1;
    private static final int phoneNumberIndex = 2;
    
    
    
    private SupplierManager(){
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
    
    
    private void addSupplier(Supplier supplier) {
        supplierDAO.add(supplier);
    }

    public void deleteSupplier(int id){
        Supplier supplier =  (Supplier)(supplierDAO.get(id));
        supplierDAO.delete(supplier);
    }
    
    public void updateSupplier(Supplier supplier){
        supplierDAO.update(supplier);
    }
    
    public Supplier getSupplier(int id){
        return (Supplier) supplierDAO.get(id);
    }
     
    
    public Supplier getSupplierData(String supplierName){
        return supplierDAO.getSupplierByName(supplierName);
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
    
     public Supplier createSupplier(ArrayList<String> data) throws InvalidFieldException {
        String companyName = data.get(nameIndex);
        String supplierPhoneLada = data.get(phoneLadaIndex);
        String supplierPhoneNumber = data.get(phoneNumberIndex);
        
        Supplier supplierData;
        
        Phone supplierPhone = new Phone(supplierPhoneLada,supplierPhoneNumber);
        
        supplierData = new Supplier(companyName,supplierPhone);

        return supplierData;
    }
     
     
     /**
     * The method recieves the data from the view and uses it to create the new 
     * entitys, an exception is thrown if there's an invalid data.
     * 
     * @param supplierData
     * @param userSupplierData 
     */
    public String registerSupplier(ArrayList<String> supplierData){
        String message ="";
        try{
            Supplier supplier = new Supplier(createSupplier(supplierData));
            supplierManager.addSupplier(supplier);
            message = "SUCCESS";
        }catch(InvalidFieldException exception ){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    public String modifySupplier(ArrayList<String> newSupplierData , int id){
        String message = "";
 
        try{
            Supplier supplier =  (getSupplier(id));   
            Supplier updatedSupplier = createSupplier(newSupplierData);
            updatedSupplier.setId_Supplier(supplier.getId_Supplier());
            updateSupplier(updatedSupplier);
            message = "SUCCESS";
        }catch(InvalidFieldException exception){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    public ArrayList<Supplier> getSupplierList(){
        ArrayList<Supplier> supplierList;
        supplierList = new ArrayList<Supplier> ((ArrayList<Supplier>) supplierDAO.getList());
        return supplierList; 
    } 
     
}
