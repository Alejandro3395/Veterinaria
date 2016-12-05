/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

 /**
* class: SessionGenerator (SessionGenerator.java)
* @author: Manuel Bojorquez
* 
* date: October 25, 2016
* 
* The objective of the class is to initialize a SessionFactory object.
* @param sessionFactory object responsible for managing sessions connecting to the database
* static: To ensure that there is only one instance. 
* final: To that the reference can not be changed once assigned
*/

public class SessionGenerator {
    private static final SessionFactory sessionFactory;

    
    
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException hibernateExcepcion) {
            System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + hibernateExcepcion);
            throw new ExceptionInInitializerError(hibernateExcepcion);
        }
    }

    /**
     * Method responsible of provides a session to connect with the Data Base
     * @return sessionFactory 
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}