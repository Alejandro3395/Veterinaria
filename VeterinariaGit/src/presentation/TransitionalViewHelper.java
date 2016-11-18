/**
* class: AbstractViewController (AbstractViewController.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the controller for the view classes.
* 
* The main idea in making the class abstract is to create a father class type 
* because all controllers require three main methods:
* 1. configure window
* 2. set events
* 3. initialize view
* 
*/

package presentation;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public abstract class TransitionalViewHelper {
    private Notifier notifier = new Notifier();


    public abstract void openWindow();

    protected void configureWindow(JFrame window) {
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
    }

    protected abstract void initializeView();

    protected abstract void setEvents();
 
    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }
    

}