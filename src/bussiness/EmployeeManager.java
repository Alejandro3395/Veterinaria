/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import Data.DAOs.EmployeeDAO;
import Entitys.Address;
import Entitys.Employee;
import Entitys.Phone;
import Entitys.UserEmployee;
import exceptions.InvalidFieldException;
import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeManager {
    private static final EmployeeManager employeeManager = new EmployeeManager();
    private EmployeeDAO employeeDAO;
    
    public EmployeeManager(){
        this.employeeDAO = EmployeeDAO.GetInstance();
    }
    
    public static EmployeeManager GetInstance(){
        return employeeManager;
    }
    
    
    private void saveEmployee(Employee employee)  {
        employeeDAO.add(employee);
    }
    

    
    public Employee createEmployee(ArrayList<String> data) throws InvalidFieldException {
        
        //  public Employee(String name, Address address, Phone phone, String RFC) {
        
        String employeeName = data.get(0);
        int  employeePostalCode = Integer.valueOf(data.get(1));
        String employeeAddressStreet = data.get(2);
        String employeeAddressColony = data.get(3);
        String employeeAddressCross = data.get(4);
        String employeePhoneLada = data.get(5);
        String employeePhoneNumber = data.get(6);
        String employeeRFC = data.get(7);
        
        
        
        
        /*
        validamos reglas de negocio de identityCard y postalcode
        */
        boolean isViolatingBussinessRule = false;
        
        //primera regla de negocio postal code 4 numeros

        //segunda regla de negocio la lada long 3
        if(employeePhoneLada.length() != 3){
            isViolatingBussinessRule = true;
        }
        
        //tercera regla de negocio numero de telefono long 10
        if(employeePhoneNumber.length() != 10){
            isViolatingBussinessRule = true;
        }
        
        if(employeeRFC.length() != 12){
            isViolatingBussinessRule = true;
        }
        
        
        Employee employeeData;
        
        Address employeeAddress = new Address(employeePostalCode,
                                              employeeAddressStreet ,
                                              employeeAddressColony ,
                                              employeeAddressCross);
        
        Phone employeePhone = new Phone(employeePhoneLada,employeePhoneNumber);
        
         employeeData = new Employee(employeeName,employeeAddress,employeePhone,employeeRFC);

        return employeeData;
    }
    
    public UserEmployee createUserEmployee(ArrayList<String> data){
        
        String employeeUserName = data.get(0);
        String employeeUserPassword = data.get(1);
        String employeeUserEmail = data.get(2);
        
        /*
        validamos reglas del negocio
        */
        
        
        
        UserEmployee userEmployee = new UserEmployee(employeeUserName, employeeUserPassword, employeeUserEmail);
        
        return userEmployee;
        
    }
    
    public void createEntity(ArrayList<String> employeeData, ArrayList<String> userEmployeeData) throws InvalidFieldException {
        
        Employee employee = new Employee(createEmployee(employeeData));
        UserEmployee user = new UserEmployee(createUserEmployee(userEmployeeData));
        insertEmployee(employee,user);
        
    }
    
    public void insertEmployee(Employee employee, UserEmployee userEmployee) {
        
        employee.setUser(userEmployee);
        employeeManager.saveEmployee(employee);

    }
}
