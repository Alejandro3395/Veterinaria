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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicineInformationHandler {
    private static final MedicineInformationHandler medicineInformationHandler = new MedicineInformationHandler();
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
    
    
    private MedicineInformationHandler(){
        this.medicineDAO = MedicineDAO.GetInstance();
    }
    
    /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    public static MedicineInformationHandler GetInstance(){
        return medicineInformationHandler;
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
        
        String expirationDate = data.get(expirationDateIndex).toString();
        Date medicineExpirationDate = getMedicineExpirationDate(expirationDate);
        
        //revisando las fechas
        
        String medicineDose = data.get(doseIndex).toString();
                
        Medicine medicineData;
        
        medicineData = new Medicine(medicineName,medicineQuantity,medicineSelPrice,medicineDose,medicineExpirationDate,medicineAdministrationWay);

        return medicineData;
    }

     public void registerMedicine(ArrayList<String> medicineData,String supplierName) throws InvalidFieldException{
            Medicine medicine = new Medicine(createMedicine(medicineData));

            SupplierInformationHandler supplierHandler = SupplierInformationHandler.GetInstance();
            supplierHandler.addMedicine(supplierName,medicine);
            
            
    }


    public void modifyMedicine(ArrayList<String> newMedicineData , String supplierName,int index) throws InvalidFieldException{

            List<Medicine> medicineList =  getMedicinesBySupplierName(supplierName);
            Medicine medicine = medicineList.get(index-1);
            
            Medicine updatedMedicine = createMedicine(newMedicineData);
            medicine.setId(updatedMedicine.getId());

            SupplierInformationHandler supplierHandler = SupplierInformationHandler.GetInstance();
            supplierHandler.updateMedicine(supplierName,medicine,index);
            
    }
    
    private Date getMedicineExpirationDate(String date) throws InvalidFieldException{
        
        Date expirationDate = null;
        
        try {
            expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException ex) {
            throw new InvalidFieldException("Datos erroneos en la Fecha");
        }
        
        
        return expirationDate;
    }
    
    public List<Medicine> getMedicinesBySupplierName(String supplierName){
        
        SupplierInformationHandler supplierManager = SupplierInformationHandler.GetInstance();
        Supplier supplierData = supplierManager.getSupplierData(supplierName);
        
        List<Medicine> medicineList;
        medicineList = new ArrayList<Medicine>  (supplierData.getMedicines());
        
        return medicineList; 
    }
    
    public Medicine getMedicineByName(String medicineName){
        return medicineDAO.getMedicineByName(medicineName);
    }
    
    public List<Medicine> getMedicines(){
        List<Medicine> listMedicine = new ArrayList<Medicine>();
        listMedicine = medicineDAO.getMedicineDataList();
        
        return listMedicine ;
    }
}