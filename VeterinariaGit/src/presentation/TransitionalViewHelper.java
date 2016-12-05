/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

/**
 *
 * @author jozapata
 */
public abstract class TransitionalViewHelper extends ViewHelper {
    
    protected abstract void closeActualView();
    
    protected abstract void openPreviousView();
    
}
