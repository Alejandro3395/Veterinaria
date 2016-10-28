/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import Data.DAOs.MedicineDAO;
import Entitys.Medicine;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class MedicineManager {
    private static final MedicineManager medicineManager = new MedicineManager();
    private MedicineDAO medicineDAO;
    
    public MedicineManager(){
        this.medicineDAO = MedicineDAO.GetInstance();
    }
    
    public static MedicineManager GetInstance(){
        return medicineManager;
    }
    
    
    private void saveMedicine(Medicine medicine) {
        medicineDAO.add(medicine);
    }
    
    public boolean verifyMedicine(
            String name,
            int quantity,
            double sellPrice,
            String supplier,
            String administrationWay,
            String expirationDate,
            String dose   ){
        boolean result = false;
        
        ArrayList<Medicine> medicineList = new ArrayList<Medicine>(medicineDAO.getList());
        
        if(medicineList.contains(new Medicine(name,supplier,quantity,sellPrice,dose,expirationDate,administrationWay))){
            result = true;
        }
        System.out.println(result);
        return result;
        
    }
    
    public Medicine createMedicine(ArrayList<String> data) {
        
        String medicineName = data.get(0);
        int  medicineQuantity =  Integer.parseInt(data.get(1).toString());
        double medicineSellPrice =  Double.parseDouble(data.get(2).toString());
        
        String medicineSupplier = data.get(3);
        String medicineAdministrationWay = data.get(4);
        
        //obtener la fecha
        String medicineExpirationDate = data.get(5);
        
        //obtener la dosis
        String medicineDose= data.get(6);
        
        Medicine medicine = new Medicine(medicineName,medicineSupplier,medicineQuantity,medicineSellPrice,medicineDose,medicineExpirationDate,medicineAdministrationWay);
        medicineManager.saveMedicine(medicine);
        return medicine;

    }
    
}
