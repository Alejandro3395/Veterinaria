/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

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
        createIntroView();
        
    }
    
    public static void createIntroView(){
        IntroController introController = new IntroController();
        introController.openWindow();
    }
}
