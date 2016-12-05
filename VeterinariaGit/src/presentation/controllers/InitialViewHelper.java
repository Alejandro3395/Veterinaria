/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.ViewHelper;
import presentation.views.InitialView;

/**
 *
 * @author Jorge
 */
public class InitialViewHelper extends ViewHelper {
    private static InitialViewHelper introViewHelper ; 
    private InitialView initialView;

    private InitialViewHelper() {
        setIntroView(new InitialView());

        initializeView();
    }
    
    public static InitialViewHelper getInstance(){
        if(introViewHelper == null) {
         introViewHelper = new InitialViewHelper();
        }
        return introViewHelper;
    }

    public void setIntroView(InitialView initialView) {
        this.initialView = initialView;
    }

    @Override
    public void loadView() {
        initialView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( initialView );
        initialView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        initialView.getBtn_start().addActionListener(actionEvent -> openLoginMainView());
    }
    
    private void openLoginMainView(){
        initialView.dispose();
        LoginMainViewHelper loginMainViewHelper = LoginMainViewHelper.getInstance();
        loginMainViewHelper.loadView();
    }
  
}
