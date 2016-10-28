/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import exceptions.InvalidFieldException;
import java.io.Serializable;

/**
 *
 * @author diego
 */
public class Doctor extends Person implements Serializable{
    

    private String RFC;
    private String identityCard;
    private UserDoctor user;
    private int RFCSize= 12;
    private int identityCardSize = 8;

    public Doctor(String name, Address address, Phone phone, String RFC, String identityCard) throws InvalidFieldException {
        super(name, address, phone);
        
        if(isValidRFC(RFC) && isValidIdentityCard(identityCard)){
            this.RFC = RFC;
            this.identityCard = identityCard;
        }else{
            throw new InvalidFieldException("Datos erroneos...Cedula o RFC");
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

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
     
    
}
