/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

/**
 *
 * @author Jorge
 */
public class MedicineManager {
    private static final MedicineManager medicineManager = new MedicineManager();
    //private MedicineDAO medicineDAO;
    
    public MedicineManager(){
        //this.medicineDAO = MedicineDAO.GetInstance();
    }
    
    public static MedicineManager GetInstance(){
        return medicineManager;
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
        
        
        return result;
        
    }
    
}
