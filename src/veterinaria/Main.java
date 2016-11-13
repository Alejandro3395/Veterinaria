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
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
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
        JFrame.setDefaultLookAndFeelDecorated(true); //que nos permite dejar a Substance la decoracion ( por asi decirlo) 
SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MagmaSkin"); // Setencia que aplica el skin Creme de Substance
        createIntroView();
    }

    public static void createIntroView() throws InvalidFieldException {
        
        EmployeeDAO employeeDAO= new EmployeeDAO();
        ClientDAO clientDAO = new ClientDAO();
        Pet pet1 = new Pet("Peque", "French",9);
        Pet pet2 = new Pet("mora", "Frech", 1);
        
        
        Address address = new Address(97320, "64", "Francisco I Madero", "31 y 33");
        Phone phone= new Phone( "969", "1234567890");
        Client client = new Client(" Alejandro Bojorquez", address, phone, "Mannuel_3395@hotmail.com");
        client.getPets().add(pet1);
        client.getPets().add(pet2);
        //Se agrega el cliente
        clientDAO.add(client);
        //se obtiene el cliente agregado
        Client client2= (Client) clientDAO.get(1);
        

        
        Pet pet4 = new Pet("Camil", "Frech", 8);
        
        client2.getPets().add(pet4);
        clientDAO.update(client2);
        
        System.out.println(((Client)clientDAO.get(1)).getPets().size());
        
        Client client3 = (Client) clientDAO.get(1);
        client3.getPets().remove(client3.getPets().get(1));
        clientDAO.update(client3);
        
        
        //UserEmployee user= new UserEmployee("Kinster", "Bojorquez3395","mannuel_3395@hotmail.com");
        
        //Employee employee = new Employee("Manuel Alejandro" ,address, phone, "BOCM950303",user );
        //employeeDAO.add(employee);
        
       // employee.setId(3);
        //employee.setName("Bojorquez Cetina");
        //employee.getUser().setUserName("Kinster3395");
        
        //employeeDAO.update(employee);

        /*IntroController IntroController = new IntroController();
        IntroController.openWindow();*/
    }

    @Override
    public void run() {
        
    }
}
