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

/**
 *
 * @author mannu
 */
public class MedicineDAO extends AbstractDAO<Medicine> {
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
     * This method recieves a medicine object and checks if exists in the 
     * database.
     * @param objectId
     * @return 
     */
    @Override
    public Object get(int objectId) {
        Medicine medicine = null;
        
        try{
            openSession();
            
            medicine = (Medicine) session.get(Medicine.class,objectId);
        }finally{
            session.close();
        }
        return medicine;
    }
    
    /**
     * This method returns the collection of elements from the medicine type.
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
    
    
}
