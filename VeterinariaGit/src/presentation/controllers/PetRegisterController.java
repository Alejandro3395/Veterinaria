/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.AbstractViewController;
import presentation.views.PetRegisterView;

/**
 *
 * @author Jorge
 */
public class PetRegisterController extends AbstractRegisterController {
    private PetRegisterView petRegisterView;
    
    public PetRegisterController(){
        setPetRegisterView(new PetRegisterView());
        
        initializeView();
    }

    public PetRegisterView getPetRegisterView() {
        return petRegisterView;
    }

    public void setPetRegisterView(PetRegisterView petRegisterView) {
        this.petRegisterView = petRegisterView;
    }
    
    
    @Override
    public void openWindow() {
        getPetRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getPetRegisterView() );
        getPetRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getPetRegisterView().getBtn_register().addActionListener(actionEvent -> registerPet());
    }
    
    /*comentarios:
    necesitas tener ya listo el de clientes para cargar tu combobox de clientes con todos los clientes
    ya registrados en la base de datos.
    */
    
    private void registerPet(){
        
    }

    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        String petOwner = getPetRegisterView().getCombo_petOwner().getSelectedItem().toString();
        data.add(petOwner);

        //String petAge = getPetRegisterView().getSpinner_NOSECOMOSELLAMA().getValue().toString();
        
        return data;
    }
    
}
