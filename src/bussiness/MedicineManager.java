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
    private MedicineDAO medicineDAO;
    
    /**
     * Constants to use with the createMedicine method
     */
    private static final int nameIndex = 0;
    private static final int quantityIndex = 1;
    private static final int sellPriceIndex = 2;
    //private static final int supplierIndex = 3;
    private static final int administrationWayIndex = 3;
    private static final int expirationDateIndex = 4;
    private static final int doseIndex = 5;
    
    
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

    public String registerMedicine(ArrayList<String> medicineData,String medicineSupplier){
        String message ="";
        try{
            Medicine medicine = new Medicine(createMedicine(medicineData));
            SupplierManager supplierManager = SupplierManager.GetInstance();
            Supplier supplier = supplierManager.getSupplierData(medicineSupplier); //aqui se llama a lo de get por nombre
            supplier.addMedicines(medicine);
            supplierManager.updateSupplier(supplier);
            message = "SUCCESS";
            
        }catch(InvalidFieldException exception ){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    
    public String modifyMedicine(ArrayList<String> newMedicineData , String medicineOwner,int index){
        String message = "";
        
        try{
            List<Medicine> medicineList =  getMedicineList(medicineOwner);
            Medicine medicine = medicineList.get(index);
            ArrayList<String> medicineData = new ArrayList<>( getMedicineData(medicine) );
            
            if(isNewData(medicineData, newMedicineData)){
                Medicine updatedMedicine = updateData(medicine,newMedicineData);
                SupplierManager supplierManager = SupplierManager.GetInstance();
                Supplier supplier = supplierManager.getSupplierData(medicineOwner); //aqui se llama a lo de get por nombre
                supplier.getMedicines().set(index,medicine);
                supplierManager.updateSupplier(supplier);
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
    
    private Medicine updateData(Medicine medicine, ArrayList<String> newMedicineData) throws InvalidFieldException{
        
        /*
        
        private static final int nameIndex = 0;
    private static final int quantityIndex = 1;
    private static final int sellPriceIndex = 2;
    //private static final int supplierIndex = 3;
    private static final int administrationWayIndex = 3;
    private static final int expirationDateIndex = 4;
    private static final int doseIndex = 5;
        */
        
        medicine.setName(newMedicineData.get(nameIndex));
        medicine.setAmount( Integer.valueOf(newMedicineData.get(quantityIndex)));
        medicine.setCost(Double.valueOf( newMedicineData.get(sellPriceIndex)));
        medicine.setAdministration(newMedicineData.get(administrationWayIndex));
        medicine.setExpiration_date(newMedicineData.get(expirationDateIndex));
        medicine.setDose(newMedicineData.get(doseIndex));
        
        
       
        return medicine;
    }
    
    private ArrayList<String> getMedicineData(Medicine medicine){
        
        ArrayList<String> data = new ArrayList<String>();
        
        data.add(medicine.getName().toString());
        data.add( Integer.toString( medicine.getAmount()) );
        data.add(Double.toString(medicine.getCost()));
        data.add(medicine.getAdministration());
        data.add(medicine.getExpiration_date());
        data.add(medicine.getDose());
        
        return data;
    }
    
    private boolean isNewData(ArrayList<String> medicineData, ArrayList<String> newMedicineData){
        boolean result = false;
        int diferences = 0;
        
        for(int index = 0; index < newMedicineData.size(); index++){
            
            if(!( medicineData.get(index).equals(newMedicineData.get(index) ) ) ){
                diferences++;
            }
        }
        
        if (diferences>0) { result = true;  }
        
        return result;
    }
    
    public List<Medicine> getMedicineList(String ownerName){
        
        SupplierManager supplierManager = SupplierManager.GetInstance();
        
        Supplier ownerData = supplierManager.getSupplierData(ownerName);
        List<Medicine> medicineList;
        medicineList = new ArrayList<Medicine>  (getMedicines(ownerData));
        return medicineList; 
    }
    
    public List<Medicine> getMedicines(Supplier ownerData){
        //System.out.println("medicines: "+ownerData.getMedicines().get(0));
        return ownerData.getMedicines();
    }
    
    public List<Medicine> getMedicineList(){
        List<Medicine> listMedicine = new ArrayList<Medicine>();
        listMedicine = medicineDAO.getMedicineDataList();
        
        return listMedicine ;
    }
}