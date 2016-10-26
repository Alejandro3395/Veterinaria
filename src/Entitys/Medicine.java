/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;
/**
 *
 * 
 * @author mannu
 */
public class Medicine extends Product implements Serializable{
  
    
    private String dose;
    private String administration;


    public Medicine(){
    
    }

    public Medicine( 
            String name,
            String supplier,
            int amount,
            double cost,
            String dose,
            String expiration_date,
            String administration
            ){
        
        super(name,supplier, amount, cost,expiration_date);
        this.dose = dose;
        this.administration = administration;
        
    }

    
    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }
    
    
    
}
