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
public class Pet implements Serializable{
    
    //Parametros
    private long id;
    private int age;
    private String name;
    private String breed;
    
    
    //Constructor

    public Pet( String name, String breed,int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        
        
    }

    
    
    
    //Gets y Sets
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    
    
    
}
