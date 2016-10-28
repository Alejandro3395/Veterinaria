/**
* class: SupplierDAO (SupplierDAO.java)
* @author: Diego Nicoli
* 
* date: October 27, 2016
* 
* This class represent the data section of the Supplier class.
* 
* The main objective of this class is to make the comunication with the 
* database.
* 
*/
package Data.DAOs;


import Entitys.Supplier;
import java.sql.SQLException;
import java.util.ArrayList;


public class SupplierDAO extends AbstractDAO<Supplier> {
    private static final SupplierDAO SupplierDAO = new SupplierDAO();

    public SupplierDAO() {
    }
    
    
    public static SupplierDAO GetInstance() {
        return SupplierDAO;
    }
    
    @Override
    public void add(Supplier supplier)  {
        saveEntity(supplier);
    }

    @Override
    public void delete(Supplier entity) {
        deleteEntity(entity);
    }

    @Override
    public void update(Supplier entity) {
        updateEntity(entity);
    }

       /**
     * This method recieves a Supplier object and checks if exists in the 
     * database.
     * @paramo:objectId
     * @return 
     */
    
    @Override
    public Object get(int objectId) {
        Supplier Supplier = null;
        
        try{
            openSession();
            
            Supplier = (Supplier) session.get(Supplier.class,objectId);
        }finally{
            session.close();
        }
        return Supplier;
    }

    
    
    /**
     * This method returns the collection of elements from the Supplier type.
     * @return 
     */
    @Override
    public ArrayList<Supplier> getList() {
        ArrayList<Supplier> ProviderList = null;
        
        try{
            openSession();
            ProviderList = (ArrayList) session.createQuery("from supplier").list();
            
            
        }finally{
            session.close();
        }
        return ProviderList;
    }
}