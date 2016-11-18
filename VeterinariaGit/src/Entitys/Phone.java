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
public class Phone implements Serializable{
    
    long id;
    private String lada;
    private String number;
    private int ladaSize = 3;
    private int numberSize =10;
    public Phone (){
        
    }

    public Phone(String lada, String number) throws InvalidFieldException {
        
        if(isValidLada(lada)){
            if (isValidNumber(number)){
                this.lada = lada;
                this.number = number;
            }else{
                throw new InvalidFieldException("DAtos invalidos Numero");
            }
        }else{
            throw new InvalidFieldException("Datos invalidos en el LADA!");
        } 
        
    }
    
    private boolean isValidLada(String lada){
        boolean result = true;
        if(lada.length() != ladaSize){
            result = false;
        }
        return result;
    }
    
    private boolean isValidNumber(String number){
        boolean result = true;
        if(number.length() != numberSize){
            result = false;
        }
        return result;
    }

    public String getLada() {
        return lada;
    }

    public void setLada(String lada) throws InvalidFieldException {
        if(isValidLada(lada)){
            this.lada = lada;
        }else{
            throw new InvalidFieldException("Datos invalidos en el LADA!");
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) throws InvalidFieldException {
        if (isValidNumber(number)){
            this.number = number;
        }else{
            throw new InvalidFieldException("DAtos invalidos Numero");
        }
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}