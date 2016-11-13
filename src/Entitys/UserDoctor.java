/**
* class: UserDoctor (UsersDoctor.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* A class to model the user of the doctor entity.
* We understand that a doctor is a user in the system, then
* the properties we can find in this object are the ones that inherits from
* user.
*/
package Entitys;

import exceptions.InvalidFieldException;
import java.io.Serializable;

public class UserDoctor  extends User implements Serializable{
    private int passwordMinSize = 8;
    
    public UserDoctor(String userName, String userPassword,String userEmail ) throws InvalidFieldException {
        super(userName, userPassword,userEmail);
        
        if(!isValidPassword(userPassword)){
           throw new InvalidFieldException("La contraseña proporcionada es menor del tamaño solicitado!"); 
        }
        
        if(!isValidEmailAddress(userEmail)){
            throw new InvalidFieldException("El correo electronico introducido es incorrecto");    
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

    public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    
}