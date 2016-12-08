/**
* class: supplierInformationHandler (SupplierManager.java)
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
import Entitys.Medicine;
import Entitys.Supplier;
import Entitys.Phone;
import java.sql.SQLException;
import java.util.ArrayList;
import exceptions.InvalidFieldException;

/**
 *
 * @author diego
 */
public class SupplierInformationHandler extends Receptionist<Supplier> {
    private static final SupplierInformationHandler supplierInformationHandler = new SupplierInformationHandler();
    private SupplierDAO supplierDAO;
    
    /**
     * Constants to use with the createSupplier method
     */
    
    private static final int nameIndex = 0;
    private static final int phoneLadaIndex = 1;
    private static final int phoneNumberIndex = 2;
    
    
    
    private SupplierInformationHandler(){
        this.supplierDAO = SupplierDAO.GetInstance();
    }
    
     /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    
    public static SupplierInformationHandler GetInstance(){
        return supplierInformationHandler;
    }
    
    
    @Override
    public void register(Supplier supplier) {
        supplierDAO.add(supplier);
    }

    @Override
    public void remove(int id){
        Supplier supplier =  (Supplier)(supplierDAO.get(id));
        supplierDAO.delete(supplier);
    }
    
    @Override
    public void edit(Supplier supplier){
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
    public void registerSupplier(ArrayList<String> supplierData) throws InvalidFieldException{

            Supplier supplier = new Supplier(createSupplier(supplierData));
            supplierInformationHandler.register(supplier);
    }

    public void modifySupplier(ArrayList<String> newSupplierData , int id) throws InvalidFieldException{

            Supplier supplier =  (getSupplier(id));
            Supplier updatedSupplier = createSupplier(newSupplierData);
            updatedSupplier.setId_Supplier(supplier.getId_Supplier());
            edit(updatedSupplier);

    }
    
    public ArrayList<Supplier> getSupplierList(){
        ArrayList<Supplier> supplierList;
        supplierList = new ArrayList<Supplier> ((ArrayList<Supplier>) supplierDAO.getList());
        return supplierList; 
    }
    
    public void addMedicine(String supplierName,Medicine medicine){
        Supplier supplier = getSupplierData(supplierName); //aqui se llama a lo de get por nombre
        supplier.addMedicines(medicine);
        edit(supplier);
    }
    
    public void updateMedicine(String supplierName,Medicine medicine, int index){
        Supplier supplier = getSupplierData(supplierName); //aqui se llama a lo de get por nombre
        supplier.getMedicines().set(index-1,medicine);
        edit(supplier);
    }
     
}
