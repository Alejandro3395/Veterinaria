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
    public void add(Medicine medicine) throws SQLException {
        saveEntity(medicine);
    }

    @Override
    public void delete(Medicine entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Medicine entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(int objectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
