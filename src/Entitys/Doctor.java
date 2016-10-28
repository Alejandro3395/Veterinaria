/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;

/**
 *
 * @author diego
 */
public class Doctor extends Person implements Serializable{
    

    private String RFC;
    private String identityCard;
    private UserDoctor user;

    public Doctor(String name, Address address, Phone phone, String RFC, String identityCard) {
        super(name, address, phone);
        this.RFC = RFC;
        this.identityCard = identityCard;
    }

    public Doctor(Doctor doctor) {
        super(doctor.getName(),doctor.getAddress(),doctor.getPhone());
        this.RFC = doctor.RFC;
        this.identityCard = doctor.identityCard;
        
    }

    public Doctor(String name, Address address, Phone phone) {
        super(name, address, phone);
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
