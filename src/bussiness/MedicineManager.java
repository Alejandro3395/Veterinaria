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
import exceptions.InvalidFieldException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineManager {
    private static final MedicineManager medicineManager = new MedicineManager();
    private MedicineDAO medicineDAO;
    
    /**
     * Constants to use with the createMedicine method
     */
    private static final int nameIndex = 0;
    private static final int quantityIndex = 1;
    private static final int sellPriceIndex = 2;
    private static final int supplierIndex = 3;
    private static final int administrationWayIndex = 4;
    private static final int expirationDateIndex = 5;
    private static final int doseIndex = 6;
    
    
    public MedicineManager(){
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
    
    
    private void addMedicine(Medicine medicine) {
        medicineDAO.add(medicine);
    }
    
    
    

    /**
     * The method recieves the data array from the view and parse it 
     * so that the Medicine entity can understand it, finally we create a 
     * new entity, the method assumes that the data is passed in the correct order.
     * 
     * @param data
     */
    public void createMedicine(ArrayList<String> data) {
        
        String medicineName = data.get(nameIndex);
        int  medicineQuantity =  Integer.parseInt(data.get(quantityIndex).toString());
        double medicineSellPrice =  Double.parseDouble(data.get(sellPriceIndex).toString());
        
        String medicineSupplier = data.get(supplierIndex);
        String medicineAdministrationWay = data.get(administrationWayIndex);
        
        //obtener la fecha
        String medicineExpirationDate = data.get(expirationDateIndex);
        
        //obtener la dosis
        String medicineDose= data.get(doseIndex);
        
        try{
            Medicine medicine = new Medicine(medicineName,medicineSupplier,medicineQuantity,medicineSellPrice,medicineDose,medicineExpirationDate,medicineAdministrationWay);
            medicineManager.addMedicine(medicine);
        }catch(InvalidFieldException exception){
            System.out.println(exception.getMessage());
        }
        
        

    }
    
}