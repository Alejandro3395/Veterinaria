/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import Data.DAOs.DoctorDAO;
import Data.DAOs.EmployeeDAO;
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
    
    
    
    SessionManager(){
        this.doctorDAO = DoctorDAO.GetInstance();
        this.employeeDAO =  EmployeeDAO.GetInstance();
    }
    
    public static SessionManager GetInstance(){
        return sessionManager;
    }
    
    
    
    public Boolean ValidateUserDoctor(ArrayList<String> dataUserDoctor){
         List<UserDoctor> userDoctorData;
         Boolean isValid = false;
         
         userDoctorData = doctorDAO.getPassAndUser();
         for(UserDoctor data : userDoctorData){
             if( dataUserDoctor.get(0).equals( data.getUserName() ) && dataUserDoctor.get(1).equals(data.getUserPassword() ) ){
                 isValid= true;
             }   
         }
         return isValid;
    }
    
    public Boolean EmployeeUserAuthentification(ArrayList<String> dataUserEmployee){
         List<Employee> employeeData;
         UserEmployee userEmployee;
         Boolean isValid = false;
         
         employeeData = employeeDAO.getPassAndUser();
         for(Employee employee : employeeData){
             userEmployee = employee.getUser();
             if( dataUserEmployee.get(0).equals( userEmployee.getUserName() ) &&
                 dataUserEmployee.get(1).equals( userEmployee.getUserPassword()) ){
                 isValid= true;
             }   
         }
         return isValid;
    }
}
