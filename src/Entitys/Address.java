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
public class Address implements Serializable {
    
    
    private int zipCode;
    private String street;
    private String colony;
    private String crossovers;

    public Address(){
        
    }
    
    public Address(int zipCode, String street, String colony, String crossovers) {
        this.zipCode = zipCode;
        this.street = street;
        this.colony = colony;
        this.crossovers = crossovers;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getCrossovers() {
        return crossovers;
    }

    public void setCrossovers(String crossovers) {
        this.crossovers = crossovers;
    }
    
    
    
}
