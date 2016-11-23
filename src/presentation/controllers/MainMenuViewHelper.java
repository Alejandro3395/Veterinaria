/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.CommonBehaviorViewHelper;
import presentation.views.MainMenuView;

/**
 *
 * @author Jorge
 */
public class MainMenuViewHelper extends CommonBehaviorViewHelper {
    private static MainMenuViewHelper mainMenuViewHelper = null;
    private MainMenuView mainView;
    private RegisterSelectionViewHelper registerSelectionViewHelper;
    private SalesViewHelper salesViewHelper;
   
    
    
    
    public MainMenuViewHelper (){
        setMainView(new MainMenuView());
        setRegisterSelectionController(RegisterSelectionViewHelper.getInstance());
        setSalesViewHelper(SalesViewHelper.getInstance());
        
        initializeView();
    }

     public static MainMenuViewHelper getInstance(){
        if( mainMenuViewHelper== null) {
         mainMenuViewHelper = new MainMenuViewHelper();
        }
        return mainMenuViewHelper;
    }

    public void setSalesViewHelper(SalesViewHelper salesViewHelper) {
        this.salesViewHelper = salesViewHelper;
    }
    
     
     
    private void openRegisterSelection(){
        getRegisterSelectionController().loadView();
    }
    
    public RegisterSelectionViewHelper getRegisterSelectionController() {
        return registerSelectionViewHelper;
    }

    public void setRegisterSelectionController(RegisterSelectionViewHelper registerSelectionViewHelper) {
        this.registerSelectionViewHelper = registerSelectionViewHelper;
    }
    
    
    
    public MainMenuView getMainView() {
        return mainView;
    }

    public void setMainView(MainMenuView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadView() {
        getMainView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getMainView());
        getMainView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getMainView().getBtn_register().addActionListener(actionEvent -> openRegisterSelection());
        getMainView().getBtn_sales().addActionListener(actionEvent -> openSalesView());
       
    }
    
    private void openSalesView(){
        salesViewHelper.loadView();
    }
    
}
