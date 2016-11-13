/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import Data.DAOs.ClientDAO;
import Data.DAOs.EmployeeDAO;
import Data.SessionGenerator;
import Entitys.Address;
import Entitys.Client;
import Entitys.Employee;
import Entitys.Pet;
import Entitys.Phone;
import Entitys.UserEmployee;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import java.util.List;
import presentation.controllers.IntroController;
import presentation.controllers.LoginMainViewHelper;
import presentation.views.LoginMainView;

/**
 *
 * @author mannu
 */
public class Main implements Runnable{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws InvalidFieldException   {
        createIntroView();
    }

    public static void createIntroView() throws InvalidFieldException {
        
        EmployeeDAO employeeDAO= new EmployeeDAO();
        ClientDAO clientDAO = new ClientDAO();
        Pet pet1 = new Pet("Peque", "French",9);
        Pet pet2 = new Pet("mora", "Frech", 1);
        
        
        Address address = new Address(97320, "64", "Francisco I Madero", "31 y 33");
        Phone phone= new Phone( "969", "1234567890");
        Client client = new Client("Manuel Alejandro", address, phone, "Mannuel_3395@hotmail.com");
        client.addPets(pet1);
        client.addPets(pet2);
        
        clientDAO.add(client);
        
        //UserEmployee user= new UserEmployee("Kinster", "Bojorquez3395","mannuel_3395@hotmail.com");
        
        //Employee employee = new Employee("Manuel Alejandro" ,address, phone, "BOCM950303",user );
        //employeeDAO.add(employee);
        
       // employee.setId(3);
        //employee.setName("Bojorquez Cetina");
        //employee.getUser().setUserName("Kinster3395");
        
        //employeeDAO.update(employee);

        //IntroController IntroController = new IntroController();
        //IntroController.openWindow();
    }

    @Override
    public void run() {
        
    }
}
