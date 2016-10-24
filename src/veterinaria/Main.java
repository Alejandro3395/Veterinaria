/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import Data.DAOs.EmployeeDAO;
import Data.DAOs.MedicineDAO;
import Entitys.Address;
import Entitys.Employee;
import Entitys.Phone;
import presentation.controllers.IntroController;

/**
 *
 * @author mannu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EmployeeDAO employeeDAO= new EmployeeDAO();
        Phone phone = new Phone("969", "935-00-00");
        Address address = new Address(97320, "64","Francisco I Madero", "x 31 y 33");
        Employee employee1 = new Employee("Manuel Alo",address, phone,"BOCM950303HYNJTN01");
        Employee employee2 = new Employee("Jorge Zapata",address, phone,"COCO950NJTN01");
        
        
        employeeDAO.add(employee2);
        
        createIntroView();
        
    }
    
    public static void createIntroView(){
        IntroController introController = new IntroController();
        introController.openWindow();
    }
}
