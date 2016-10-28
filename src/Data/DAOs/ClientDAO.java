/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.DAOs;

import Entitys.Client;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mannu
 */
public class ClientDAO extends AbstractDAO<Client> {

     private static final ClientDAO clientDAO = new ClientDAO();

    public ClientDAO() {
    }
    
    
    public static ClientDAO GetInstance() {
        return clientDAO;
    }
    
  @Override
    public void add(Client client) {
        saveEntity(client);
    }

    @Override
    public void delete(Client client) {
        deleteEntity(client);
    }

    @Override
    public void update(Client client) {
         updateEntity(client);
    }

    @Override
    public Object get(int objectId) {
        Client client = null;
        
        try{
            openSession();
            
            client = (Client) session.get(Client.class,objectId);
        }finally{
            session.close();
        }
        return client;
    }

    @Override
    public ArrayList<Client> getList() {
        ArrayList<Client> clientList = null;
        
        try{
            openSession();
            clientList = (ArrayList) session.createQuery("from Client").list();
            
            
        }finally{
            session.close();
        }
        return clientList;
    }

}
