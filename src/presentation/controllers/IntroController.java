/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.IntroView;

/**
 *
 * @author Jorge
 */
public class IntroController extends AbstractViewController {
    private static IntroController introController ; 
    private IntroView introView;
    private LoginMainViewHelper loginMainViewHelper;

    

    public IntroController() {
        setIntroView(new IntroView());
        setLoginMainViewHelper(LoginMainViewHelper.getInstance());

        initializeView();
    }
    
    public static IntroController getInstance(){
        if(introController == null) {
         introController = new IntroController();
        }
        return introController;
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
    public void openWindow() {
        getIntroView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getIntroView() );
        getIntroView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getIntroView().getBtn_start().addActionListener(actionEvent -> openMenu());
    }
    
    
    private void openMenu(){
        loginMainViewHelper.openWindow();
    }
    
    
    
    
    
}
