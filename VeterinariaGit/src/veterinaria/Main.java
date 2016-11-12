/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import presentation.controllers.ClientManagerHelper;
import presentation.controllers.DoctorManagerHelper;
import presentation.controllers.EmployeeManagerHelper;
import presentation.controllers.IntroController;
import presentation.controllers.SupplierManagerHelper;

/**
 *
 * @author mannu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //createIntroView();
        /*
            DoctorManagerHelper doc = new DoctorManagerHelper();
            doc.openWindow();
            EmployeeManagerHelper em = new EmployeeManagerHelper();
            em.openWindow();
            ClientManagerHelper cl = new ClientManagerHelper();
            cl.openWindow();
        */
            SupplierManagerHelper sp = new SupplierManagerHelper();
            sp.openWindow();
           
    }
    
    public static void createIntroView(){
        IntroController introController = new IntroController();
        introController.openWindow();
    }
}
