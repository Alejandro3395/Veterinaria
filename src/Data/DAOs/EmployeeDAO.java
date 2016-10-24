/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.DAOs;

import Entitys.Employee;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public void add(Employee employee)  {
        saveEntity(employee);
    }

    @Override
    public void delete(Employee entity) {
        deleteEntity(entity);
    }

    @Override
    public void update(Employee entity) {
        updateEntity(entity);
    }

    @Override
    public Object get(int objectId) {
        Employee employee = null;
        
        try{
            openSession();
            
            employee = (Employee) session.get(Employee.class,objectId);
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
            employeeList = (ArrayList) session.createQuery("from Employees").list();
            
            
        }finally{
            session.close();
        }
        return employeeList;
    }
    
}
