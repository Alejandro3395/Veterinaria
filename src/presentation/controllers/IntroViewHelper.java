/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.CommonBehaviorViewHelper;
import presentation.views.IntroView;

/**
 *
 * @author Jorge
 */
public class IntroViewHelper extends CommonBehaviorViewHelper {
    private static IntroViewHelper introViewHelper ; 
    private IntroView introView;
    private LoginMainViewHelper loginMainViewHelper;

    

    public IntroViewHelper() {
        setIntroView(new IntroView());
        setLoginMainViewHelper(LoginMainViewHelper.getInstance());

        initializeView();
    }
    
    public static IntroViewHelper getInstance(){
        if(introViewHelper == null) {
         introViewHelper = new IntroViewHelper();
        }
        return introViewHelper;
    }

    public LoginMainViewHelper getLoginMainViewHelper() {
        return loginMainViewHelper;
    }

    public void setLoginMainViewHelper(LoginMainViewHelper loginMainViewHelper) {
        this.loginMainViewHelper = loginMainViewHelper;
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
        getIntroView().getBtn_start().addActionListener(actionEvent -> openMenu());
    }
    
    
    private void openMenu(){
        loginMainViewHelper.loadView();
    }
    
    
    
    
    
}
