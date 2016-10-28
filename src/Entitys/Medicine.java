/**
* class: Medicine (Medicine.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* A class to model the medicine entity.
* This class provides the interface for the manipulation of 
* object properties such as the dose and the administration
* way.
*/

package Entitys;

import exceptions.InvalidFieldException;
import java.io.Serializable;

public class Medicine extends Product implements Serializable{
  
    
    private String dose;
    private String administration;

    
    public Medicine(
            String dose, 
            String administration){
        this.dose = dose;
        this.administration = administration;
    }

    public Medicine( 
            String name,
            String supplier,
            int amount,
            double cost,
            String dose,
            String expiration_date,
            String administration
            ) throws InvalidFieldException{
        super(name,supplier, amount, cost,expiration_date);
        if(isValidAmount(amount)){
            this.dose = dose;
            this.administration = administration;
        }else{
            throw new InvalidFieldException("La cantidad de un nuevo medicamento no puede ser 0!");
        }
        
        
    }

    public boolean isValidAmount(int amount){
        boolean result = false;
        if(amount > 0){
            result = true;
        }
        return result;
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
