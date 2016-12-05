/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.ViewHelper;
import presentation.views.LoginMainView;
import presentation.views.MainMenuView;
import presentation.views.SalesView;

/**
 *
 * @author Jorge
 */
public class MainMenuViewHelper extends ViewHelper {
    private static MainMenuViewHelper mainMenuViewHelper = null;
    private MainMenuView mainView;

    
    private MainMenuViewHelper (){
        setMainView(new MainMenuView());

        initializeView();
    }

     public static MainMenuViewHelper getInstance(){
        if( mainMenuViewHelper== null) {
         mainMenuViewHelper = new MainMenuViewHelper();
        }
        return mainMenuViewHelper;
    }

    public void setMainView(MainMenuView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadView() {
        mainView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( mainView);
        mainView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        mainView.getBtn_register().addActionListener(actionEvent -> openRegisterSelection());
        mainView.getBtn_sales().addActionListener(actionEvent -> openSalesView());
        mainView.getBtn_exit().addActionListener(actionEvent -> closeView());
    }
    
    private void openSalesView(){
        mainView.dispose();
        SalesViewHelper salesViewHelper = SalesViewHelper.getInstance();
        salesViewHelper.loadView();
    }
    
    private void openRegisterSelection(){
        mainView.dispose();
        RegisterSelectionViewHelper registerSelectionViewHelper = RegisterSelectionViewHelper.getInstance();
        registerSelectionViewHelper.loadView();
    }
    
    private void closeView(){
        mainView.dispose();
        LoginMainViewHelper.getInstance().loadView();
    }
    
    
}