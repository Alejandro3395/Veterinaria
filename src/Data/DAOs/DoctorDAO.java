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
import Entitys.UserDoctor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Jorge
 */
public class DoctorDAO extends GeneralDAO<Doctor> {
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
    public Doctor get(long doctorId) {
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
    public List<Doctor> getList() {
        List<Doctor> doctorList = null;
        
        try{
            openSession();
            doctorList =  session.createQuery("from Doctor").list();
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
                e.printStackTrace(); 
        } finally{
            session.close();
        }
        return doctorList;
    }
    

    
     /* Method to get a list with all the employees*/
     public List <Doctor> getDoctorList(){
         List<Doctor> employeeDataList= null;
         
         try{
         openSession();
         employeeDataList = session.createQuery(" FROM Doctor ").list();

         transaction.commit();
         }catch (HibernateException e) {
             if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
      }finally {
         session.close(); 
      }
          return employeeDataList;
   }
    
}