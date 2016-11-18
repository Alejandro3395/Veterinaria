/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import Data.DAOs.DoctorDAO;
import Data.DAOs.EmployeeDAO;
import Entitys.Doctor;
import Entitys.Employee;
import Entitys.UserDoctor;
import Entitys.UserEmployee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mannu
 */
public class SessionManager {
    private static final SessionManager sessionManager = new SessionManager();
    private DoctorDAO doctorDAO;
    private EmployeeDAO employeeDAO;
    private static int nameIndex = 0;
    private static int passwordIndex = 1;
    
    
    
    SessionManager(){
        this.doctorDAO = DoctorDAO.GetInstance();
        this.employeeDAO =  EmployeeDAO.GetInstance();
    }
    
    public static SessionManager GetInstance(){
        return sessionManager;
    }
    
    
    
    public Boolean doctorUserAuthentification(ArrayList<String> dataUserDoctor){
         List<Doctor> doctorData;
         Boolean isValid = false;
         UserDoctor userDoctor;
         doctorData = doctorDAO.getDoctorList();
         
         
         for(Doctor doctor : doctorData){
             userDoctor = doctor.getUser();
             if( dataUserDoctor.get(nameIndex).equals( userDoctor.getUserName() ) &&
                 dataUserDoctor.get(passwordIndex).equals( userDoctor.getUserPassword()) ){
                 isValid= true;
             }   
         }
         
         return isValid;
    }
    
    public Boolean employeeUserAuthentification(ArrayList<String> dataUserEmployee){
         List<Employee> employeeData;
         UserEmployee userEmployee;
         Boolean isValid = false;
         
         employeeData = employeeDAO.getEmployeeList();
         for(Employee employee : employeeData){
             userEmployee = employee.getUser();
             if( dataUserEmployee.get(nameIndex).equals( userEmployee.getUserName() ) &&
                 dataUserEmployee.get(passwordIndex).equals( userEmployee.getUserPassword()) ){
                 isValid= true;
             }   
         }
         return isValid;
    }
}