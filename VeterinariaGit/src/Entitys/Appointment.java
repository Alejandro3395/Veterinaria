/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import exceptions.InvalidFieldException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jozapata
 */
public class Appointment implements Serializable {
    //private Client client;
    //private Pet pet;
    private long id;
    private String clientName;
    private String petName;
    private Date date;
    private String hour;
    private String status;
    
    List<Medicine> medicines = new ArrayList<Medicine>();
    private Doctor doctor = null;
    
    public Appointment(){
    }

    public Appointment(String clientName, String petName, Date date, String hour,String status) throws InvalidFieldException{
        if(isValidDate(date)){
            this.clientName = clientName;
            this.petName = petName;
            this.date = date;
            this.hour = hour;
            this.status = status;
        }else{
            throw new InvalidFieldException("Datos incorrrectos en la fecha");
        }
        
    }

    public Appointment(Appointment newAppointment) throws InvalidFieldException {
        if(isValidDate(newAppointment.getDate())){
            this.clientName = newAppointment.getClientName();
            this.petName = newAppointment.getPetName();
            this.date = newAppointment.getDate();
            this.hour = newAppointment.getHour();
            this.status = newAppointment.getStatus();
        }else{
            throw new InvalidFieldException("Datos incorrrectos en la fecha");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    
    
    private boolean isValidDate(Date date){
        boolean result = true;
        Date todaysDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.format(todaysDate);
        
        if(date.before(todaysDate)){
            result = false;
        }
        return result;
    }
    
    
}
