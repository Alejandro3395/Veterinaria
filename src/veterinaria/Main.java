/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import Data.DAOs.ClientDAO;
import Data.DAOs.EmployeeDAO;
import Data.DAOs.SupplierDAO;
import Data.SessionGenerator;
import Entitys.Address;
import Entitys.Client;
import Entitys.Employee;
import Entitys.Medicine;
import Entitys.Pet;
import Entitys.Phone;
import Entitys.Supplier;
import Entitys.UserEmployee;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
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
        
        myThread = new Thread(new Main());
        myThread.start();
 
        
        JFrame.setDefaultLookAndFeelDecorated(true); //que nos permite dejar a Substance la decoracion ( por asi decirlo) 
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.theme.SubstanceLightAquaTheme"); // Setencia que aplica el skin Creme de Substance
        createIntroView();
    }

    public static void createIntroView() throws InvalidFieldException {
       InitialViewHelper.getInstance().loadView();
        
    }

    @Override
    public void run() {
        SessionGenerator.getSessionFactory();
        
    }
}
