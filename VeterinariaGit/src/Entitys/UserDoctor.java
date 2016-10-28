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
 * @author Jorge
 */
public class UserDoctor  extends User implements Serializable{
    private int passwordMinSize = 8;
    
    public UserDoctor(String userName, String userPassword,String userEmail ) throws InvalidFieldException {
        super(userName, userPassword,userEmail);
        
        if(!isValidPassword(userPassword)){
           throw new InvalidFieldException("La contraseña proporcionada es menor del tamaño solicitado!"); 
        }
        
    }
    private boolean isValidPassword(String password){
        boolean result = true;
        if(password.length() < passwordMinSize){
            result = false;
        }
        return result;
    }

    public UserDoctor(UserDoctor userDoctor) {
        super(userDoctor.getUserName(),userDoctor.getUserPassword(),userDoctor.getUserEmail());
    }

    public UserDoctor() {
    }

    
    
    
}
