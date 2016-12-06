/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mannu
 */
public class SalesManager {
    
    private static SalesManager salesManager= null;
    private WarehouseManager warehouseManager;
    
    private SalesManager(){
        warehouseManager = WarehouseManager.getInstance();
    }
    
    public static SalesManager getInstance(){
        if(salesManager== null){
            salesManager = new SalesManager();
        }
        
        return salesManager;
    }
    
    public double calculateAmountToPay(List<String> purchases){
        String[] productData;
        double amountToPay= 0;
        int indexProductPrice = 1;
        for(String product : purchases){
             productData = product.split("  ");
             amountToPay = amountToPay + Double.parseDouble(productData[indexProductPrice]);
                   
            }
        return amountToPay;
    }
    
    
    public boolean addProductToPurchase(String nameMedicine){
        boolean isInExistence =false ;
        isInExistence = warehouseManager.isProductInExistence(nameMedicine);
        if(isInExistence){
            warehouseManager.decreaseProductAmount(nameMedicine);
            return isInExistence;
        }
        return isInExistence;
    }
    
    public void removeProductToPurchase(String nameMedicine){
        warehouseManager.increaseProductAmount(nameMedicine);
    }
    
    
}
