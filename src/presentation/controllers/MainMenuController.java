/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.MainMenuView;

/**
 *
 * @author Jorge
 */
public class MainMenuController extends AbstractViewController {
    private MainMenuView mainView;
    private RegisterSelectionController registerSelectionController;
    
    
    public MainMenuController (){
        setMainView(new MainMenuView());
        setRegisterSelectionController(new RegisterSelectionController());
        initializeView();
    }

    
    
    private void openRegisterSelection(){
        getRegisterSelectionController().openWindow();
    }
    
    public RegisterSelectionController getRegisterSelectionController() {
        return registerSelectionController;
    }

    public void setRegisterSelectionController(RegisterSelectionController registerSelectionController) {
        this.registerSelectionController = registerSelectionController;
    }
    
    
    
    public MainMenuView getMainView() {
        return mainView;
    }

    public void setMainView(MainMenuView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void openWindow() {
        getMainView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getMainView());
        getMainView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getMainView().getBtn_register().addActionListener(actionEvent -> openRegisterSelection());
    }
    
}
