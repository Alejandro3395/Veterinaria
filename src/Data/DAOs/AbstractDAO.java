/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.DAOs;
import Data.SessionGenerator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

/**
* class: AbstractDao (AbstractDAO.java)
* @author: Manuel Bojorquez
* 
* date: October 27, 2016
* 
* 
* 
*/
public abstract class AbstractDAO<Entity> {
    protected Session session;
    protected Transaction transaction;

    protected void openSession() throws HibernateException {
        session = SessionGenerator.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    protected void exceptionManagement(HibernateException hibernateException) throws HibernateException {
        transaction.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos", hibernateException);
    }


    public abstract void add(Entity entity) throws SQLException;

    public abstract void delete(Entity entity);

    public abstract void update(Entity entity);

    public abstract Object get(long objectId);

    public abstract ArrayList<?> getList();


    protected void saveEntity(Entity entity) {
        try {
            openSession();

            session.save(entity);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    protected void deleteEntity(Entity entity) {
        try {
            openSession();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }

    protected void updateEntity(Entity entity) {
        try {
            openSession();

            session.update(entity);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            exceptionManagement(hibernateException);
            throw hibernateException;
        } finally {
            session.close();
        }
    }


}
