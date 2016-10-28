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
public class Client extends Person implements Serializable{
    private String clientEmail;
    
    public Client(String name, Address address, Phone phone, String clientEmail){    
        super(name, address, phone);
        this.clientEmail = clientEmail;
    }
    
    public Client(Client client){
        super(client.getName(), client.getAddress(), client.getPhone());
        this.clientEmail = client.getClientEmail();
    }
    
    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
    
}
