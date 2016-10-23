/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

/**
 *
 * @author diego
 */
public class Person {
    
    //Parametros
    private Address address;
    private Phone phone;
    private String name;
    
    
    //Constructor
    public Person(String name){
        this.name = name;
    }
    
    //gets y sets

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    
    
}
