/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import Data.DAOs.ClientDAO;
import Data.DAOs.EmployeeDAO;
import Entitys.Address;
import Entitys.Client;
import Entitys.Pet;
import Entitys.Phone;
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
        
        createIntroView();
        /*
            DoctorManagerHelper doc = new DoctorManagerHelper();
            doc.openWindow();
            EmployeeManagerHelper em = new EmployeeManagerHelper();
            em.openWindow();
            ClientManagerHelper cl = new ClientManagerHelper();
            cl.openWindow();
            SupplierManagerHelper sp = new SupplierManagerHelper();
            sp.openWindow();
            
        */
        
//
//        ClientDAO clientDAO = new ClientDAO();
//        Pet pet1 = new Pet("coco", "French",9);
//        Pet pet2 = new Pet("loco", "Frech", 1);
//        
//        
//        Address address = new Address(97320, "64", "Francisco I Madero", "31 y 33");
//        Phone phone= new Phone( "969", "1234567890");
//        Client client = new Client(" Jorge Zapata", address, phone, "Mannuel_3395@hotmail.com");
//        client.getPets().add(pet1);
//        client.getPets().add(pet2);
//        //Se agrega el cliente
//        clientDAO.add(client);
//        //se obtiene el cliente agregado
//        Client client2= (Client) clientDAO.get(2);
//        
//
//        
//        Pet pet4 = new Pet("Camil", "Frech", 8);
//        
//        client2.getPets().add(pet4);
//        clientDAO.update(client2);
//        
//        System.out.println(((Client)clientDAO.get(2)).getPets().size());
//        
//        Client client3 = (Client) clientDAO.get(2);
//        client3.getPets().remove(client3.getPets().get(1));
//        clientDAO.update(client3);
//        
//        
//           
    }
    
    public static void createIntroView(){
        IntroController introController = new IntroController();
        introController.openWindow();
    }
}
