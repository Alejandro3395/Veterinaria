/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;

/**
 *
 * @author Jorge
 */
public class UserDoctor  extends User implements Serializable{
    
    public UserDoctor(String userName, String userPassword,String userEmail ) {
        super(userName, userPassword,userEmail);
    }

    public UserDoctor(UserDoctor userDoctor) {
        super(userDoctor.getUserName(),userDoctor.getUserPassword(),userDoctor.getUserEmail());
    }

    public UserDoctor() {
    }

    
    
    
}
