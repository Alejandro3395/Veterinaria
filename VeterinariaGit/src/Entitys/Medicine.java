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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Medicine extends Product implements Serializable{
  
    
    private String dose;
    private String administration;

    public Medicine(){ 
    }
    
    public Medicine(
            String dose, 
            String administration){
        this.dose = dose;
        this.administration = administration;
    }

    public Medicine( 
            String name,
            int amount,
            double cost,
            String dose,
            Date expiration_date,
            String administration
            ) throws InvalidFieldException{
        
        super(name, amount, cost,expiration_date);
        if(isValidAmount(amount)){
            if(isValidDate(expiration_date)){
               String split[] = dose.split(" ");
               
               if(isValidDoseAmount(split[0])){
                    if(isValidDosePeriod(split[3])){
                        this.dose = dose;
                        this.administration = administration;
                   }else{
                       throw new InvalidFieldException("El periodo de reaplicacion debe ser mayor que 0");
                   }
               }else{
                   throw new InvalidFieldException("La cantidad de la dosis debe ser mayor que 0");
               }
            }else{
                throw new InvalidFieldException("La fecha de caducidad no puede ser menor  que la actual");
            }
            
        }else{
            throw new InvalidFieldException("La cantidad de un nuevo medicamento no puede ser 0!");
        }
    }

    public Medicine(Medicine newMedicine) throws InvalidFieldException {
        super(newMedicine.getName(), newMedicine.getAmount(), newMedicine.getCost(),newMedicine.getExpiration_date());

        String newMedicineDose = newMedicine.getDose();
        String newMedicineAdministrationWay = newMedicine.getAdministration();
        
        if(isValidAmount(newMedicine.getAmount())){
            if(isValidDate(newMedicine.getExpiration_date())){
                
               String split[] = newMedicineDose.split(" ");
               
               if(isValidDoseAmount(split[0])){
                    if(isValidDosePeriod(split[3])){
                        this.dose = newMedicineDose;
                        this.administration = newMedicineAdministrationWay;
                   }else{
                       throw new InvalidFieldException("El periodo de reaplicacion debe ser mayor que 0");
                   }
               }else{
                   throw new InvalidFieldException("La cantidad de la dosis debe ser mayor que 0");
               }
            }else{
                throw new InvalidFieldException("La fecha de caducidad no puede ser menor  que la actual");                
            }            
        }else{
            throw new InvalidFieldException("La cantidad de un nuevo medicamento no puede ser 0!");
        }
    }

    private boolean isValidAmount(int amount){
        boolean result = false;
        if(amount > 0){
            result = true;
        }
        return result;
    }
    
    private boolean isValidDate(Date expirationDate){
        boolean result = true;
        Date todaysDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.format(todaysDate);
        
        if(expirationDate.before(todaysDate)){
            result = false;
        }
        return result;
    }
    
    private boolean isValidDoseAmount(String doseAmount){
        boolean result = false;
        
        int amount = Integer.valueOf(doseAmount);
        
        if(amount > 0){
            result = true;
        }
        
        return result;
    }
    
    private boolean isValidDosePeriod(String dosePeriod){
        boolean result = false;
        
        int period = Integer.valueOf(dosePeriod);
        
        if(period > 0){
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
