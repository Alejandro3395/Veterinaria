/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import Data.DAOs.AbstractDAO;
import Data.DAOs.MedicineDAO;
import Entitys.Medicine;
import java.sql.SQLException;

/**
 *
 * @author mannu
 */
public class Veterinaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException{
        MedicineDAO medicineDAO= new MedicineDAO();
        Medicine medicine = new Medicine("Paracetamol","BIPAL", 15, 25,"01-02-2016","1 cada 8 hrs","Oral");
        
        medicineDAO.add(medicine);

    } 
}
