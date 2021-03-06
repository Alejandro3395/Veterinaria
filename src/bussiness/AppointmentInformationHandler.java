/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import Data.DAOs.AppointmentDAO;
import Entitys.Appointment;
import Entitys.Client;
import Entitys.Pet;
import exceptions.InvalidFieldException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jozapata
 */
public class AppointmentInformationHandler extends Receptionist<Appointment> {
    private static final AppointmentInformationHandler appointmentInformationHandler = new AppointmentInformationHandler();
    private AppointmentDAO appointmentDAO;
    
    private Appointment actualAppointment = null;
    
    private static final int clientIndex = 0;
    private static final int petIndex = 1;
    private static final int dateIndex = 2;
    private static final int hourIndex = 3;
    
    private AppointmentInformationHandler(){
        this.appointmentDAO = AppointmentDAO.GetInstance();
    }
    
    public static AppointmentInformationHandler GetInstance(){
        return appointmentInformationHandler;
    }
    
    public Appointment getAppointment(int id){
        return (Appointment) appointmentDAO.get(id);
    }
    
    public ArrayList<Appointment> getAppointments(){
        ArrayList<Appointment> doctorList;
        doctorList = new ArrayList<Appointment> ((ArrayList<Appointment>) appointmentDAO.getList());
        return doctorList; 
    }

    @Override
    public void register(Appointment appointment) {
        appointmentDAO.add(appointment);
    }

    @Override
    public void edit(Appointment appointment) {
        appointmentDAO.update(appointment);
    }

    @Override
    public void remove(int id) {
        Appointment appointment =  (Appointment)(appointmentDAO.get(id));
        appointmentDAO.delete(appointment);
    }

    public Appointment getActualAppointment() {
        return actualAppointment;
    }
    
    public void registerAppointment(ArrayList<String> appointmentData) throws InvalidFieldException{
        Appointment appointment = new Appointment(createAppointment(appointmentData));
        register(appointment);
    }
    
    public void modifyAppointment(ArrayList<String> newAppointmentData,int id) throws InvalidFieldException{
        Appointment appointment = (getAppointment(id));
        Appointment updatedAppointment = createAppointment(newAppointmentData);
        updatedAppointment.setId(appointment.getId());
        edit(updatedAppointment);
    }
    
    private Appointment createAppointment(ArrayList<String> appointmentData) throws InvalidFieldException{
        
        String appointmentClient = appointmentData.get(clientIndex);
        String appointmentPet = appointmentData.get(petIndex);
        String date = appointmentData.get(dateIndex);
        String appointmentHour = appointmentData.get(hourIndex);
        
        Date appointmentDate = getDate(date);
        
        Appointment appointment = new Appointment(appointmentClient,appointmentPet,appointmentDate,appointmentHour,"PENDIENTE");
        return appointment;
    }
    
    public void startAppointment(int id){
        
        Appointment appointment = getAppointment(id);
        appointment.setStatus("INICIADA");
        edit(appointment);
        
        actualAppointment = appointment;
    }
    
    public void finishAppointment(){
        actualAppointment.setStatus("TERMINADA");
        edit(actualAppointment);
    }
    
    public void cancelAppointment(){
        actualAppointment.setStatus("CANCELADA");
        edit(actualAppointment);
    }
    
    public void cancelAppointment(int id){
        
        Appointment appointment = getAppointment(id);
        appointment.setStatus("CANCELADA");
        edit(appointment);
    }
    
    private Date getDate(String date) throws InvalidFieldException{
        
        Date appointmentDate = null;
        
        try {
            appointmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException ex) {
            throw new InvalidFieldException("Datos erroneos en la Fecha");
        }
        
        
        return appointmentDate;
    }
    
}
