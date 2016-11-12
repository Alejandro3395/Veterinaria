/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.DAOs;

import Entitys.Supplier;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
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
        long id = (long) objectId;
        try{
            openSession();
            
            Supplier = (Supplier) session.get(Supplier.class,id);
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
            ProviderList = (ArrayList) session.createQuery("from Supplier").list();
            
            
        }finally{
            session.close();
        }
        return ProviderList;
    }
    
}
