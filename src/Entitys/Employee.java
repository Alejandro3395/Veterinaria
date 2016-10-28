/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

/**
 *
 * @author diego
 */
public class Employee extends Person{
    
   private  String RFC;
   private UserEmployee user;
   
   
   public Employee(String name, Address address, Phone phone, String RFC){
       super(name, address, phone);
       this.RFC = RFC;
   }

    public UserEmployee getUser() {
        return user;
    }

    public void setUser(UserEmployee user) {
        this.user = user;
    }

    public Employee(Employee employee) {
        super(employee.getName(), employee.getAddress(), employee.getPhone());
        RFC= employee.getRFC();
        
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }
   
   
   
}
