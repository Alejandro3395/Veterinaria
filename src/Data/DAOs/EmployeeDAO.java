/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.DAOs;

import Entitys.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author mannu
 */
public class EmployeeDAO extends AbstractDAO<Employee> {
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public EmployeeDAO() {
    }
    
    
    public static EmployeeDAO GetInstance() {
        return employeeDAO;
    }
    
  @Override
    public void add(Employee employee) {
        saveEntity(employee);
    }

    @Override
    public void delete(Employee employee) {
        deleteEntity(employee);
    }

    @Override
    public void update(Employee employee) {
         updateEntity(employee);
    }

    /**
     *Return the persistent instance of the given entity class with the given 
     * identifier, or null if there is no such persistent instance.
     * @param employeeId
     * @return null
     */
    @Override
    public Employee get(long employeeId) {
        Employee employee = null;
        
        try{
            openSession();
            
            employee = (Employee) session.get(Employee.class,employeeId);
        }finally{
            session.close();
        }
        return employee;
    }

    @Override
    public ArrayList<Employee> getList() {
        ArrayList<Employee> employeeList = null;
        
        try{
            openSession();
            employeeList = (ArrayList) session.createQuery("from Employee").list();
            
            
        }finally{
            session.close();
        }
        return employeeList;
    }

     public String getName(){
        String name = null;
        
        try{
            openSession();
            name= (String) session.createQuery("SELECT u.name FROM Employee as u WHERE id=1").uniqueResult();
        }finally{
            session.close();
        }
        return name;
    }
     
     
     /* Method to get a list with all the employees*/
     public List <Employee> getPassAndUser(){
         List<Employee> employeeDataList= null;
         
         try{
         openSession();
         employeeDataList = session.createQuery(" FROM Employee ").list();
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
