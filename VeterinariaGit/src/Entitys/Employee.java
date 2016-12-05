/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import exceptions.InvalidFieldException;

/**
 *
 * @author diego
 */
public class Employee extends Person{
    
   private  String RFC;
   private UserEmployee user;
   private int RFCSize = 12;
   
   public Employee(){
       
   }
   
   public Employee(String name, Address address, Phone phone, String RFC) throws InvalidFieldException{
       super(name, address, phone);
       if(isValidRFC(RFC)){
            this.RFC = RFC;
        }else{
            throw new InvalidFieldException("Datos erroneos en el RFC");
        }
   }
   //Quitar despues de las pruebas
   public Employee(String name, Address address, Phone phone, String RFC, UserEmployee user){
       super(name, address, phone);
       this.user = user;
       this.RFC = RFC;
       
     
   }

   public Employee(Employee employee) {
        super(employee.getName(), employee.getAddress(), employee.getPhone());
        RFC= employee.getRFC();
        
    }

    private boolean isValidRFC(String RFC){
        boolean result = true;
        if(RFC.length() != RFCSize){
            result = false;
        }
        return result;
    }
           
    public UserEmployee getUser() {
        return user;
    }

    public void setUser(UserEmployee user) {
        this.user = user;
    }

    
    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) throws InvalidFieldException {
        if( isValidRFC(RFC) ){
            this.RFC = RFC;            
        }else{
            throw new InvalidFieldException("Datos erroneos en el RFC");
        }
    }
   
   
   
}
