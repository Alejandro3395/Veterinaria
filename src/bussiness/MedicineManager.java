/**
* class: MedicineManager (MedicineManager.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the manager for the medicine entitys.
* The objective of the class is to recieve the data that the view
* collects and pass it to the entity class to insert it to the database.
* 
*/

package bussiness;

import Data.DAOs.MedicineDAO;
import Entitys.Medicine;
import Entitys.Supplier;
import exceptions.InvalidFieldException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineManager {
    private static final MedicineManager medicineManager = new MedicineManager();
    private MedicineDAO medicineDAO = new MedicineDAO();
    
    /**
     * Constants to use with the createMedicine method
     */
    private static final int nameIndex = 0;
    private static final int quantityIndex = 1;
    private static final int sellPriceIndex = 2;
    private static final int administrationWayIndex = 3;
    private static final int expirationDateIndex = 4;
    private static final int doseIndex = 5;
    
    
    private MedicineManager(){
        this.medicineDAO = MedicineDAO.GetInstance();
    }
    
    /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    public static MedicineManager GetInstance(){
        return medicineManager;
    }
    
    

    /**
     * The method recieves the data array from the view and parse it 
     * so that the Medicine entity can understand it, finally we create a 
     * new entity, the method assumes that the data is passed in the correct order.
     * 
     * @param data
     */
    public Medicine createMedicine(ArrayList<String> data) throws InvalidFieldException {

        String medicineName = data.get(nameIndex);
        int  medicineQuantity = Integer.valueOf(data.get(quantityIndex).toString());
        double medicineSelPrice = Double.parseDouble(data.get(sellPriceIndex).toString());
        
        String medicineAdministrationWay = data.get(administrationWayIndex).toString();
        String medicineExpirationDate = data.get(expirationDateIndex).toString();
        String medicineDose = data.get(doseIndex).toString();
                
        Medicine medicineData;
        
        medicineData = new Medicine(medicineName,medicineQuantity,medicineSelPrice,medicineDose,medicineExpirationDate,medicineAdministrationWay);

        return medicineData;
    }

     public void registerMedicine(ArrayList<String> medicineData,String medicineSupplier) throws InvalidFieldException{
            Medicine medicine = new Medicine(createMedicine(medicineData));

            SupplierManager supplierManager = SupplierManager.GetInstance();
            Supplier supplier = supplierManager.getSupplierData(medicineSupplier); //aqui se llama a lo de get por nombre
            supplier.addMedicines(medicine);
            supplierManager.edit(supplier);
    }


    public void modifyMedicine(ArrayList<String> newMedicineData , String medicineOwner,int index) throws InvalidFieldException{

            List<Medicine> medicineList =  getMedicinesBySupplierName(medicineOwner);
            Medicine medicine = medicineList.get(index);
            Medicine updatedMedicine = createMedicine(newMedicineData);
            medicine.setId(updatedMedicine.getId());

            SupplierManager supplierManager = SupplierManager.GetInstance();
            Supplier supplier = supplierManager.getSupplierData(medicineOwner); //aqui se llama a lo de get por nombre
            supplier.getMedicines().set(index,updatedMedicine);
            supplierManager.edit(supplier);
    }
    
    public List<Medicine> getMedicinesBySupplierName(String supplierName){
        
        SupplierManager supplierManager = SupplierManager.GetInstance();
        Supplier supplierData = supplierManager.getSupplierData(supplierName);
        
        List<Medicine> medicineList;
        medicineList = new ArrayList<Medicine>  (supplierData.getMedicines());
        
        return medicineList; 
    }
    
    public List<Medicine> getMedicines(){
        List<Medicine> listMedicine = new ArrayList<Medicine>();
        listMedicine = medicineDAO.getMedicineDataList();
        
        return listMedicine ;
    }
}