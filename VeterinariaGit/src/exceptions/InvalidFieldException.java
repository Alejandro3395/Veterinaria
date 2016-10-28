/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Jorge
 */
public class InvalidFieldException extends Exception {
    private String message;
    
    public InvalidFieldException (String message){
        this.message = message;
    }
    
    @Override
    public String getMessage(){
        return message;
    }
    
}
