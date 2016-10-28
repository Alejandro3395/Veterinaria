/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.DAOs;

import Entitys.Doctor;
import Entitys.Medicine;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class DoctorDAO extends AbstractDAO<Doctor> {
    private static final DoctorDAO doctorDAO = new DoctorDAO();
    
    
    public DoctorDAO(){
    }
    
    public static DoctorDAO GetInstance(){
        return doctorDAO;
    }
    
    @Override
    public void add(Doctor entity) {
        saveEntity(entity);
    }

    @Override
    public void delete(Doctor entity) {
        deleteEntity(entity);
    }

    @Override
    public void update(Doctor entity) {
        updateEntity(entity);
    }

    @Override
    public Object get(int objectId) {
        Doctor doctor = null;
        
        try{
            openSession();
            
            doctor = (Doctor) session.get(Doctor.class,objectId);
        }finally{
            session.close();
        }
        return doctor;
    }

    @Override
    public ArrayList<?> getList() {
        ArrayList<Doctor> doctorList = null;
        
        try{
            openSession();
            doctorList = (ArrayList) session.createQuery("from Doctor").list();
            
        } finally{
            session.close();
        }
        return doctorList;
    }
    
}
