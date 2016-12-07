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
    private String type;
    
    
    //Constructor
    public Pet(){
        
    }
    
    public Pet( String name, String type,int age) {
        this.name = name;
        this.type = type;
        this.age = age;
        
        
    }

    public Pet(Pet pet){
       this.name = pet.getName().toString();
       this.type = pet.getType().toString();
       this.age = pet.getAge();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    
    
    
}
