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
    
    /**
     * Constants to use with the createDoctor method
     */
    private static final int nameIndex = 0;
    private static final int postalCodeIndex = 1;
    private static final int adressStreetIndex = 2;
    private static final int addressColonyIndex = 3;
    private static final int addressCrossIndex = 4;
    private static final int phoneLadaIndex = 5;
    private static final int phoneNumberIndex = 6;
    private static final int RFCIndex = 7;
    
    /**
     * Constants to use with the createUserDoctor method
     */
    private static final int userNameIndex = 0;
    private static final int userPasswordIndex = 1;
    private static final int userEmailIndex = 2;
    
    private EmployeeManager(){
        this.employeeDAO = EmployeeDAO.GetInstance();
    }
    
    /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    public static EmployeeManager GetInstance(){
        return employeeManager;
    }
    
    public Employee getEmployee(int id){
        return (Employee) employeeDAO.get(id);
    }
    
    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employeeList;
        employeeList = new ArrayList<Employee> ( (ArrayList<Employee>) employeeDAO.getList() );
        return employeeList; 
    }
    
    /**
     * The method recieves the doctor and the userDoctor entitys to set the user to the doctor 
     * and then insert the doctor into the DataBase.
     * @param doctor
     * @param userDoctor 
     */
    private void setEmployeeUser(Employee employee, UserEmployee userEmployee){
        employee.setUser(userEmployee);
        employeeManager.addEmployee(employee);
    }
    
    private void addEmployee(Employee employee)  {
        employeeDAO.add(employee);
    }
    
    private void updateEmployee(Employee employee){
        employeeDAO.update(employee);
    }
    
    private Employee createEmployee(ArrayList<String> data) throws InvalidFieldException {
        
        //  public Employee(String name, Address address, Phone phone, String RFC) {
        
        String employeeName = data.get(nameIndex);
        int  employeePostalCode = Integer.valueOf(data.get(postalCodeIndex));
        String employeeAddressStreet = data.get(adressStreetIndex);
        String employeeAddressColony = data.get(addressColonyIndex);
        String employeeAddressCross = data.get(addressCrossIndex);
        String employeePhoneLada = data.get(phoneLadaIndex);
        String employeePhoneNumber = data.get(phoneNumberIndex);
        String employeeRFC = data.get(RFCIndex);
        

        
        Employee employeeData;
        
        Address employeeAddress = new Address(employeePostalCode,
                                              employeeAddressStreet ,
                                              employeeAddressColony ,
                                              employeeAddressCross);
        
        Phone employeePhone = new Phone(employeePhoneLada,employeePhoneNumber);
        
         employeeData = new Employee(employeeName,employeeAddress,employeePhone,employeeRFC);

        return employeeData;
    }
    
    private UserEmployee createUserEmployee(ArrayList<String> data) throws InvalidFieldException{
        
        String employeeUserName = data.get(userNameIndex);
        String employeeUserPassword = data.get(userPasswordIndex);
        String employeeUserEmail = data.get(userEmailIndex);
        
        UserEmployee userEmployee = new UserEmployee(employeeUserName, employeeUserPassword, employeeUserEmail);
        
        return userEmployee;
        
    }
    
    public void deleteEmployee(int id){
        Employee employee =  (Employee)(employeeDAO.get(id));
        employeeDAO.delete(employee);
    }
    
    public String registerEmployee(ArrayList<String> employeeData, ArrayList<String> userEmployeeData){
        String message ="";
        
        try{
            Employee employee = new Employee(createEmployee(employeeData));
            UserEmployee user = new UserEmployee(createUserEmployee(userEmployeeData));
            setEmployeeUser(employee,user);
            message = "SUCCESS";
        }catch(InvalidFieldException exception ){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    public String modifyEmployee(ArrayList<String> newEmployeeData , int id){
        String message = "";
 
        try{
            Employee employee =  (getEmployee(id));
            Employee updatedEmployee = createEmployee(newEmployeeData);
            updatedEmployee.setId(employee.getId());
            updatedEmployee.setUser(employee.getUser());
            updateEmployee(updatedEmployee);
                message = "SUCCESS";
        }catch(InvalidFieldException exception){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
}
