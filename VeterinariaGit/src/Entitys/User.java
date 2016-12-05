/**
* class: User (User.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* A class to model the user entity.
* The system requires a registered user to work, then 
* we need the username and the password properties wich are in
* this class.
*/

package Entitys;

import java.io.Serializable;

public class User implements Serializable{
    private long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    
    public User(String userName, String userPassword,String userEmail){
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public User() {
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    
}