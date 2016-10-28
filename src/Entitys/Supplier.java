 /**
* class: Supplier (Supplier.java)
* @author: Diego Nicoli
* 
* date: October 27, 2016
* 
* A class to model the Supplier entity.
* This file contains the access routines for a Supplier
* and provides an object to act as a container for the
* manipulation of a Supplier's data.
* 
*/
package Entitys;


public class Supplier {
    
    //Parametros
    
    private String companyName;
    private Phone phone;
    long id;
    
    
    //Constructor
    
    public Supplier(String companyName, Phone phone){
        
        this.companyName = companyName;
        this.phone = phone;
    
        
    }
    
    //Gets y sets

    public String getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    
    }