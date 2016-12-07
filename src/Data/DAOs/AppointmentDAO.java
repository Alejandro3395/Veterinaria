/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.DAOs;

import Entitys.Appointment;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jozapata
 */
public class AppointmentDAO extends GeneralDAO<Appointment>{

    private static final AppointmentDAO appointmentDAO = new AppointmentDAO();

    public AppointmentDAO() {
    }
    
    
    public static AppointmentDAO GetInstance() {
        return appointmentDAO;
    }
    
  @Override
    public void add(Appointment appointment) {
        saveEntity(appointment);
    }

    @Override
    public void delete(Appointment appointment) {
        deleteEntity(appointment);
    }

    @Override
    public void update(Appointment appointment) {
         updateEntity(appointment);
    }

    @Override
    public Object get(long objectId) {
        Appointment appointment = null;
        
        try{
            openSession();
            
            appointment = (Appointment) session.get(Appointment.class,objectId);
        }finally{
            session.close();
        }
        return appointment;
    }

    @Override
    public ArrayList<Appointment> getList() {
        ArrayList<Appointment> appointmentList = null;
        
        try{
            openSession();
            appointmentList = (ArrayList) session.createQuery("from Appointment").list();
            
            
        }finally{
            session.close();
        }
        return appointmentList;
    }

    
}
