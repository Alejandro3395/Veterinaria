/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import exceptions.InvalidFieldException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class Client extends Person implements Serializable{
    private String clientEmail;
    private List<Pet> pets= new ArrayList<Pet>();
    
    public Client(String name, Address address, Phone phone, String clientEmail) throws InvalidFieldException{    
        super(name, address, phone);
        if(isValidEmailAddress(clientEmail)){
            this.clientEmail = clientEmail;
        }else{
            throw new InvalidFieldException("El correo electronico introducido es incorrecto");    
        }
    }
    
    public Client(){
    }
    
    public Client(Client client){
        super(client.getName(), client.getAddress(), client.getPhone());
        this.clientEmail = client.getClientEmail();
        this.pets = client.getPets();
    }
    
    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> Pets) {
        this.pets = Pets;
    }
    
    // Metodo auxiliar que permite agregar una nueva mascota
    public void addPets(Pet pet){
        this.pets.add(pet);
    }
    
    public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    
}
