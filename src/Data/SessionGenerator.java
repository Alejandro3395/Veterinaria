/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionGenerator {
    /*static: Para asegurar de que solo exista una instancia.
      final: para que la referencia no pueda ser cambiada una vez que se haya asignado*/
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException hibernateExcepcion) {
            System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + hibernateExcepcion);
            throw new ExceptionInInitializerError(hibernateExcepcion);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}