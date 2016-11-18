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
 public abstract class Person implements Serializable {
    
    //Parametros
    private long id;
    private Address address;
    private Phone phone;
    private String name;
    
    
    //Constructor
    public Person(String name, Address address, Phone phone){
        this.name = name;
        this.address= address;
        this.phone = phone;
    }
    
    public Person(){
        
    }
    
    //gets y sets

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
 
    
    
}
