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
    
    public Medicine createMedicine(
            String name,
            int quantity,
            double sellPrice,
            String supplier,
            String administrationWay,
            String expirationDate,
            String dose  
    ) {
        Medicine medicine = new Medicine(name,supplier,quantity,sellPrice,dose,expirationDate,administrationWay);
        System.out.println("Vamos bien\n");
        medicineManager.saveMedicine(medicine);
        System.out.println("exito!");
        return medicine;

    }
    
}
