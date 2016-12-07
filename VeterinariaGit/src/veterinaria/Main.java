/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import Data.DAOs.AppointmentDAO;
import Data.DAOs.ClientDAO;
import Data.DAOs.EmployeeDAO;
import Data.DAOs.SupplierDAO;
import Data.SessionGenerator;
import Entitys.Address;
import Entitys.Appointment;
import Entitys.Client;
import Entitys.Employee;
import Entitys.Medicine;
import Entitys.Pet;
import Entitys.Phone;
import Entitys.Supplier;
import Entitys.UserEmployee;
import exceptions.InvalidFieldException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import presentation.controllers.InitialViewHelper;
import presentation.controllers.LoginMainViewHelper;
import presentation.views.LoginMainView;

/**
 *
 * @author mannu
 */
public class Main implements Runnable{

    static Thread myThread;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws InvalidFieldException   {
        
        //myThread = new Thread(new Main());
        //myThread.start();
        
        /*
        EmployeeDAO employeeDAO= new EmployeeDAO();
        Address address = new Address(97320, "64", "Francisco I Madero", "31 y 33");
        Phone phone= new Phone( "969", "1234567890");
        UserEmployee user= new UserEmployee("Kinster", "Bojorquez3395","mannuel_3395@hotmail.com");
        
        Employee employee = new Employee("Manuel Alejandro" ,address, phone, "123456789012",user );
        employeeDAO.add(employee);
        */
        
        /*
        String client = "Alex";
        String pet = "misifus";
        
        String date ="2016-12-10";
        Date appointmentDate = null;
        
        try {
            appointmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException ex) {
            throw new InvalidFieldException("Datos erroneos en la Fecha");
        }
                
        String hour = "06:39";
        String status = "PENDING";
        
        
        
        Appointment ap = new Appointment(client,pet,appointmentDate,hour,status);
        
        AppointmentDAO.GetInstance().add(ap);
        */
        
        
        JFrame.setDefaultLookAndFeelDecorated(true); //que nos permite dejar a Substance la decoracion ( por asi decirlo) 
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.theme.SubstanceLightAquaTheme"); // Setencia que aplica el skin Creme de Substance
        createInitialView();
    }

    public static void createInitialView() throws InvalidFieldException {
       InitialViewHelper.getInstance().loadView();
        
    }

    @Override
    public void run() {
        SessionGenerator.getSessionFactory();
        
    }
}
