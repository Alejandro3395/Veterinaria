/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import Data.DAOs.MedicineDAO;
import Entitys.Medicine;
import java.util.List;

/**
 *
 * @author mannu
 */
public class WarehouseManager {
    private static WarehouseManager warehouseManager= null;
    private MedicineDAO medicineDAO;
    private List<Medicine> medicines;
    
    private WarehouseManager(){
        medicineDAO = MedicineDAO.GetInstance();
        
    }
    
    public static WarehouseManager getInstance(){
        if(warehouseManager== null){
            warehouseManager = new WarehouseManager();
        }
        
        return warehouseManager;
    }
    
    public boolean isProductInExistence(String medicineName){
        boolean isInExistence= false;
        medicines = medicineDAO.getMedicineDataList();
        for(Medicine medicine : medicines){
            if(medicineName.equals(medicine.getName())){
                if(medicine.getAmount() > 0){
                    isInExistence = true;
                }
            }
        }
        return isInExistence;
    }
    
   
    
}
