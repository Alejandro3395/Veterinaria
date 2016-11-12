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
public class Address implements Serializable {
    
    private int zipCode;
    private String street;
    private String colony;
    private String crossovers;
    private static int zipCodeMinSize = 10000;
    private static int zipCodeMaxSize = 99999;

    public Address(int zipCode, String street, String colony, String crossovers) throws InvalidFieldException {
        
        if(isValidZipCode(zipCode)){
            this.zipCode = zipCode;
            this.street = street;
            this.colony = colony;
            this.crossovers = crossovers;
        }else{
            throw new InvalidFieldException("Codigo postal erroneo!");
        }       
    }
    
    private boolean isValidZipCode(int zipCode){
        boolean result = false;
        if( (zipCode > zipCodeMinSize) && (zipCode < zipCodeMaxSize) ){
            result = true;
        }
        System.out.println(result);
        return result;
    }

    public Address() {
    }
    

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) throws InvalidFieldException {
        if(isValidZipCode(zipCode)){
            this.zipCode = zipCode;            
        }else{
            throw new InvalidFieldException("Codigo postal erroneo!");
        }
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
