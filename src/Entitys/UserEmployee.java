/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;

/**
 *
 * @author mannu
 */
public class UserEmployee extends User implements Serializable{

    public UserEmployee(String userName, String userPassword, String userEmail) {
        super(userName, userPassword, userEmail);
    }
    
    public UserEmployee(){};
    
    public UserEmployee(UserEmployee userEmployee) {
        super(userEmployee.getUserName(),userEmployee.getUserPassword(),userEmployee.getUserEmail());
    }
}
