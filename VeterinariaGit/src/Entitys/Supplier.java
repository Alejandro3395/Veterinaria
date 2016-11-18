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

import exceptions.InvalidFieldException;
import java.util.ArrayList;
import java.util.List;


public class Supplier {
    
    //Parametros
    
    private String companyName;
    private Phone phone;
    List<Medicine> medicines = new ArrayList<Medicine>();
    long id_Supplier;
    
    
    //Constructor
    
    public Supplier(String companyName, Phone phone){
        
        this.companyName = companyName;
        this.phone = phone;
    
        
    }
    
    public Supplier(){
        
    }
    
    public Supplier(Supplier supplier) throws InvalidFieldException {
        this.companyName = supplier.getCompanyName();
        this.phone =  new Phone(supplier.getPhone().getLada(),supplier.getPhone().getNumber());
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

    public long getId_Supplier() {
        return id_Supplier;
    }

    public void setId_Supplier(long id_Supplier) {
        this.id_Supplier = id_Supplier;
    }

    public void addMedicines(Medicine medicine){
        this.medicines.add(medicine);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
    
}