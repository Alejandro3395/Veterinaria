/**
* class: DoctorDAO (DoctorDAO.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the data section of the doctor class.
* 
* The main objective of this class is to make the comunication with the 
* database.
* 
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
    
    
     /**
     *Return the persistent instance of the given entity class with the given 
     * identifier, or null if there is no such persistent instance.
     * @param doctorId
     * @return null
     */
    @Override
    public Doctor get(int doctorId) {
        Doctor doctor = null;
        
        try{
            openSession();
            
            doctor = (Doctor) session.get(Doctor.class,doctorId);
        }finally{
            session.close();
        }
        return doctor;
    }
    
    /**
     * This method returns the collection of elements from the doctor type.
     * @return 
     */
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