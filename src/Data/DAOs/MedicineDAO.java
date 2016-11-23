/**
* class: MedicineDAO (MedicineDAO.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the data section of the medicine class.
* 
* The main objective of this class is to make the comunication with the 
* database.
* 
*/

package Data.DAOs;

import Entitys.Medicine;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author mannu
 */
public class MedicineDAO extends GeneralDAO<Medicine> {
    private static final MedicineDAO medicineDAO = new MedicineDAO();

    public MedicineDAO() {
    }
    
    
    public static MedicineDAO GetInstance() {
        return medicineDAO;
    }
    
    @Override
    public void add(Medicine medicine)  {
        saveEntity(medicine);
    }

    @Override
    public void delete(Medicine entity) {
        deleteEntity(entity);
    }

    @Override
    public void update(Medicine entity) {
        updateEntity(entity);
    }
    
    /**
     *Return the persistent instance of the given entity class with the given 
     * identifier, or null if there is no such persistent instance.
     * @param medicineId
     * @return null
     */
    @Override
    public Medicine get(long medicineId) {
        Medicine medicine = null;
        
        try{
            openSession();
            
            medicine = (Medicine) session.get(Medicine.class,medicineId);
        }finally{
            session.close();
        }
        return medicine;
    }
    
    /**
     * This method returns the collection of elements from the medicine type. Posiblemente hay que quitar
     * @return 
     */
    @Override
    public ArrayList<Medicine> getList() {
        ArrayList<Medicine> medicineList = null;
        
        try{
            openSession();
            medicineList = (ArrayList) session.createQuery("from Medicine").list();
            
            
        }finally{
            session.close();
        }
        return medicineList;
    }
    
    public List<Medicine> getMedicineDataList (){
        List<Medicine> medicineDataList= null;
         
         try{
         openSession();
         medicineDataList = session.createQuery(" FROM Medicine ").list();
         /*Iterator<Employee> it =  employeeDataList.iterator();
         while(it.hasNext()){
             Employee var = it.next();
             System.out.println("ID: "+ var.getId());
             
         } */     
            
         transaction.commit();
         }catch (HibernateException e) {
         if (transaction!=null) transaction.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
          return medicineDataList;
    }
    
    
}