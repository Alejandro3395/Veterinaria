/**
* class: Doctor (Doctor.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* A class to model the doctor entity.
* This file contains the access routines for a doctor
* and provides an object to act as a container for the
* manipulation of a doctor's data.
* 
*/

package Entitys;

import exceptions.InvalidFieldException;
import java.io.Serializable;

public class Doctor extends Person implements Serializable{
    

    private String RFC;
    private String identityCard;
    private UserDoctor user;
    private int RFCSize= 12;
    private int identityCardSize = 8;
//          supplier(.......) 
    
    public Doctor(){    
    }
    
    public Doctor(String name, Address address, Phone phone, String RFC, String identityCard) throws InvalidFieldException {
        super(name, address, phone);
        
        if(isValidRFC(RFC)){
            
            if(isValidIdentityCard(identityCard)){
                this.RFC = RFC;
                this.identityCard = identityCard;
            }else{
                throw new InvalidFieldException("Datos erroneos en la Cedula Profesional");
            }
            
        }else{
            throw new InvalidFieldException("Datos erroneos en el RFC");
        } 
        
    }
    
    private boolean isValidRFC(String RFC){
        boolean result = true;
        if(RFC.length() != RFCSize){
            result = false;
        }
        return result;
    }
    
    private boolean isValidIdentityCard(String identityCard){
        boolean result = true;
        if(identityCard.length() != identityCardSize){
            result = false;
        }
        return result;
    }

    public Doctor(Doctor doctor) {
        super(doctor.getName(),doctor.getAddress(),doctor.getPhone());
        this.RFC = doctor.RFC;
        this.identityCard = doctor.identityCard;
        
    }

    public UserDoctor getUser() {
        return user;
    }

    public void setUser(UserDoctor user) {
        this.user = user;
    }
    
    
    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) throws InvalidFieldException {
        if( isValidRFC(RFC) ){
        this.RFC = RFC;            
        }else{
            throw new InvalidFieldException("Datos erroneos en el RFC");
        }
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) throws InvalidFieldException {
        if(isValidIdentityCard(identityCard)){
            this.identityCard = identityCard;   
        }else{
            throw new InvalidFieldException("Datos erroneos en la cedula profesional");
        }
    }
     
    
}
