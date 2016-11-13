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
    
    public EmployeeManager(){
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
    
    
    private void addEmployee(Employee employee)  {
        employeeDAO.add(employee);
    }
 
    
    private void deleteEmployee(Employee employee){
        employeeDAO.delete(employee);
    }
    
    private void updateDoctor(Employee employee){
        employeeDAO.update(employee);
    }
    
    public Employee getEmployee(int id){
        return (Employee) employeeDAO.get(id);
    }
     
    public void eliminateEmployee(int id){
       deleteEmployee((Employee)(employeeDAO.get(id)));
    }
    
    
    
    

    
    public Employee createEmployee(ArrayList<String> data) throws InvalidFieldException {
        
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
    
    public UserEmployee createUserEmployee(ArrayList<String> data) throws InvalidFieldException{
        
        String employeeUserName = data.get(userNameIndex);
        String employeeUserPassword = data.get(userPasswordIndex);
        String employeeUserEmail = data.get(userEmailIndex);
        
        UserEmployee userEmployee = new UserEmployee(employeeUserName, employeeUserPassword, employeeUserEmail);
        
        return userEmployee;
        
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
            ArrayList<String> employeeData = new ArrayList<>( getEmployeeData(employee) );
            
            if(isNewData(employeeData, newEmployeeData)){
                Employee updatedEmployee = updateData(employee,newEmployeeData);
                updateDoctor(updatedEmployee);
                message = "SUCCESS";
            }else{
                System.out.println("datos sin cambio");
            }
        }catch(InvalidFieldException exception){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    private Employee updateData(Employee employee, ArrayList<String> newEmployeeData) throws InvalidFieldException{
        
        String newEmployeeName = newEmployeeData.get(nameIndex);
        int  newEmployeePostalCode = Integer.valueOf(newEmployeeData.get(postalCodeIndex));
        String newEmployeeAddressStreet = newEmployeeData.get(adressStreetIndex);
        String newEmployeeAddressColony = newEmployeeData.get(addressColonyIndex);
        String newEmployeeAddressCross = newEmployeeData.get(addressCrossIndex);
        String newEmployeePhoneLada = newEmployeeData.get(phoneLadaIndex);
        String newEmployeePhoneNumber = newEmployeeData.get(phoneNumberIndex);
        String newEmployeeRFC = newEmployeeData.get(RFCIndex);
        
        employee.setName(newEmployeeName);
        
        Address newAddress = new Address(newEmployeePostalCode,newEmployeeAddressStreet,newEmployeeAddressColony,newEmployeeAddressCross);
        employee.setAddress(newAddress);
        
        Phone newPhone = new Phone(newEmployeePhoneLada,newEmployeePhoneNumber);
        employee.setPhone(newPhone);
        
        employee.setRFC(newEmployeeRFC);
       
        return employee;
    }
    
    private ArrayList<String> getEmployeeData(Employee employee){
        
        ArrayList<String> data = new ArrayList<String>();
        
        data.add(employee.getName().toString());
        data.add( Integer.toString( employee.getAddress().getZipCode() ) );
        data.add(employee.getAddress().getStreet().toString());
        data.add(employee.getAddress().getColony().toString());
        data.add(employee.getAddress().getCrossovers().toString());
        data.add(employee.getPhone().getLada().toString());
        data.add(employee.getPhone().getNumber().toString());
        data.add(employee.getRFC().toString());
        
        return data;
    }
    
    
    private boolean isNewData(ArrayList<String> employeeData, ArrayList<String> newEmployeeData){
        boolean result = false;
        int diferences = 0;
        
        for(int index = 0; index < newEmployeeData.size(); index++){
            
            if(!( employeeData.get(index).equals(newEmployeeData.get(index) ) ) ){
                diferences++;
            }
        }
        
        if (diferences>0) { result = true;  }
        
        return result;
    }
    
    public ArrayList<Employee> getEmployeeList(){
        ArrayList<Employee> employeeList;
        employeeList = new ArrayList<Employee> (getEmployees());
        return employeeList; 
    }
    
    /**
     * The method recieves the doctor and the userDoctor entitys to set the user to the doctor 
     * and then insert the doctor into the DataBase.
     * @param doctor
     * @param userDoctor 
     */
    public void setEmployeeUser(Employee employee, UserEmployee userEmployee){
        employee.setUser(userEmployee);
        employeeManager.addEmployee(employee);
    }
    
    public ArrayList<Employee> getEmployees(){
        return (ArrayList<Employee>) employeeDAO.getList();
    }
    
    public void createEntity(ArrayList<String> employeeData, ArrayList<String> userEmployeeData) throws InvalidFieldException {
        
        Employee employee = new Employee(createEmployee(employeeData));
        UserEmployee user = new UserEmployee(createUserEmployee(userEmployeeData));
        insertEmployee(employee,user);
        
    }
    
    public void insertEmployee(Employee employee, UserEmployee userEmployee) {
        
        employee.setUser(userEmployee);
        employeeManager.addEmployee(employee);

    }
}
