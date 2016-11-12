/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;

/**
 *
 * @author Jorge
 */
public class Supplier implements Serializable {
    
    //Parametros
    
    private String companyName;
    private Phone phone;
    long id;
    
    
    //Constructor
    
    public Supplier(String companyName, Phone phone){
        
        this.companyName = companyName;
        this.phone = phone;
    
        
    }
    
    public Supplier(){
        
    }

    public Supplier(Supplier supplier) {
        this.companyName = supplier.getCompanyName();
        this.phone =  new Phone(supplier.getPhone().getLada(),supplier.getPhone().getNumber());
    }
    
    //Gets y sets

    public String getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
