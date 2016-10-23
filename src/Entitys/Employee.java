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
   
   
   public Employee(String name, String RFC){
       super(name);
       this.RFC = RFC;
   }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }
    
   
   
   
}
