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
public class Phone implements Serializable{
    
    long id;
    private String lada;
    private String number;
    
    public Phone (){
        
    }

    public Phone(String lada, String number) {
        this.lada = lada;
        this.number = number;
    }

    public String getLada() {
        return lada;
    }

    public void setLada(String lada) {
        this.lada = lada;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}