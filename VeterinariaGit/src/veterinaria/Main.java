/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import Data.SessionGenerator;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import presentation.controllers.InitialViewHelper;


/**
 *
 * @author mannu
 */
public class Main implements Runnable{

    static Thread myThread;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
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
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MistAquaSkin"); // Setencia que aplica el skin Creme de Substance
       // SubstanceLookAndFeel.setCurrentWatermark( new SubstanceImageWatermark("c:\\logo.jpg"));
        //SubstanceLookAndFeel.setImageWatermarkOpacity(new Float(0.5));
        createInitialView();
    }

    public static void createInitialView() {
       InitialViewHelper.getInstance().loadView();
        
    }

    @Override
    public void run() {
        SessionGenerator.getSessionFactory();
        
    }
}
