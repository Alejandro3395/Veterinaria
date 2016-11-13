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
    
    
    private void addSupplier(Supplier supplier) {
        supplierDAO.add(supplier);
    }
    
    private void deleteSupplier(Supplier supplier){
        supplierDAO.delete(supplier);
    }
    
    private void updateSupplier(Supplier supplier){
        supplierDAO.update(supplier);
    }
    
    public Supplier getSupplier(int id){
        return (Supplier) supplierDAO.get(id);
    }
     
    public void eliminateSupplier(int id){
       deleteSupplier((Supplier)(supplierDAO.get(id)));
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
            ArrayList<String> supplierData = new ArrayList<>( getSupplierData(supplier) );
            
            if(isNewData(supplierData, newSupplierData)){
                Supplier updatedSupplier = updateData(supplier,newSupplierData);
                updateSupplier(updatedSupplier);
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
     
    
    private Supplier updateData(Supplier supplier, ArrayList<String> newSupplierData) throws InvalidFieldException{
        
        String newcompanyName = newSupplierData.get(nameIndex);
        String newsupplierPhoneLada = newSupplierData.get(phoneLadaIndex);
        String newsupplierPhoneNumber = newSupplierData.get(phoneNumberIndex);
        
        supplier.setCompanyName(newcompanyName);
        
        Phone newPhone = new Phone(newsupplierPhoneLada,newsupplierPhoneNumber);
        supplier.setPhone(newPhone);
       
        return supplier;
    }
        public void insertSupplier( Supplier supplier ){
        
        
        supplierManager.addSupplier(supplier);

    }
        
        private ArrayList<String> getSupplierData(Supplier supplier){
        
        ArrayList<String> data = new ArrayList<String>();
        
        data.add(supplier.getCompanyName().toString());
        data.add(supplier.getPhone().getLada().toString());
        data.add(supplier.getPhone().getNumber().toString());

        
        return data;
    }
    
    private boolean isNewData(ArrayList<String> supplierData, ArrayList<String> newSupplierData){
        boolean result = false;
        int diferences = 0;
        
        for(int index = 0; index < newSupplierData.size(); index++){
            
            if(!( supplierData.get(index).equals(newSupplierData.get(index) ) ) ){
                diferences++;
            }
        }
        
        if (diferences>0) { result = true;  }
        
        return result;
    }
    
    public ArrayList<Supplier> getSupplierList(){
        ArrayList<Supplier> supplierList;
        supplierList = new ArrayList<Supplier> (getSuppliers());
        return supplierList; 
    }
    
    
    public ArrayList<Supplier> getSuppliers(){
        return (ArrayList<Supplier>) supplierDAO.getList();
    }
     
     
     
     }
