/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
