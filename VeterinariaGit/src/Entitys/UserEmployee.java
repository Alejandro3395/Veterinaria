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
 * @author mannu
 */
public class UserEmployee extends User implements Serializable{
    private int passwordMinSize = 8;
    
    public UserEmployee(String userName, String userPassword, String userEmail) throws InvalidFieldException {
        super(userName, userPassword, userEmail);
        if(!isValidPassword(userPassword)){
           throw new InvalidFieldException("La contraseña proporcionada es menor del tamaño solicitado!");
        }
        if(!isValidEmailAddress(userEmail)){
            throw new InvalidFieldException("El correo electronico introducido es incorrecto");    
        }
    }
    
    public UserEmployee(){};
    
    public UserEmployee(UserEmployee userEmployee) {
        super(userEmployee.getUserName(),userEmployee.getUserPassword(),userEmployee.getUserEmail());
    }
    
    private boolean isValidPassword(String password){
        boolean result = true;
        if(password.length() < passwordMinSize){
            result = false;
        }
        return result;
    }
    
    
    public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
}
