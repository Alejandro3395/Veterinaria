/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.ViewHelper;
import presentation.views.IntroView;

/**
 *
 * @author Jorge
 */
public class IntroViewHelper extends ViewHelper {
    private static IntroViewHelper introViewHelper ; 
    private IntroView introView;

    

    private IntroViewHelper() {
        setIntroView(new IntroView());

        initializeView();
    }
    
    public static IntroViewHelper getInstance(){
        if(introViewHelper == null) {
         introViewHelper = new IntroViewHelper();
        }
        return introViewHelper;
    }

    
    public IntroView getIntroView() {
        return introView;
    }

    public void setIntroView(IntroView introView) {
        this.introView = introView;
    }

    @Override
    public void loadView() {
        getIntroView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getIntroView() );
        getIntroView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getIntroView().getBtn_start().addActionListener(actionEvent -> openLoginMainView());
    }
    
    private void openLoginMainView(){
        LoginMainViewHelper loginMainViewHelper = LoginMainViewHelper.getInstance();
        loginMainViewHelper.loadView();
    }
  
}
