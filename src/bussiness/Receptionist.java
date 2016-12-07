/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

/**
 *
 * @author mannu
 */
public abstract class Receptionist<Entity> {
    public static String name;

    
    
    
    
    
    
    //-------------------
    // Registers new object into database
    public abstract void register(Entity entity);
    
    
    //-------------------
    // update object
    public abstract void edit(Entity entity);
    
    //--------------------
    // remove object from database
    public abstract void remove(int id);
    
    
}
