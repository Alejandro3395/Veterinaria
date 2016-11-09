/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import Data.DAOs.EmployeeDAO;
import Entitys.Address;
import Entitys.Employee;
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

        LoginMainViewHelper loginMainViewHelper = new LoginMainViewHelper();
        loginMainViewHelper.openWindow();
    }

    @Override
    public void run() {
        
    }
}
