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
public class Pet {
    
    //Parametros
    
    private int age;
    private String name;
    private String breed;
    
    
    //Constructor

    public Pet(int age, String name, String breed) {
        this.age = age;
        this.name = name;
        this.breed = breed;
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
    
    
    
    
    
    
}
