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
    private static MainMenuController mainMenuController = null;
    private MainMenuView mainView;
    private RegisterSelectionController registerSelectionController;
    private SalesViewHelper salesViewHelper;
   
    
    
    
    public MainMenuController (){
        setMainView(new MainMenuView());
        setRegisterSelectionController(RegisterSelectionController.getInstance());
        setSalesViewHelper(SalesViewHelper.getInstance());
        
        initializeView();
    }

     public static MainMenuController getInstance(){
        if( mainMenuController== null) {
         mainMenuController = new MainMenuController();
        }
        return mainMenuController;
    }

    public void setSalesViewHelper(SalesViewHelper salesViewHelper) {
        this.salesViewHelper = salesViewHelper;
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
        getMainView().getBtn_sales().addActionListener(actionEvent -> openSalesView());
       
    }
    
    private void openSalesView(){
        salesViewHelper.openWindow();
    }
    
}
