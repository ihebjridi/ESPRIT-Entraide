package Services;

import Entities.Commentaire;
import Entities.evennement;
import Interfaces.Evennement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nebras
 */
public class EvennementServices implements Evennement {

    Connection connection = null;

    public EvennementServices() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void insert(evennement e) {
        try {
            String req = "INSERT INTO `event` (title,  date,  lieu, descritpion,created_at,published_at,canceled_at, enabled, cancled, id_user) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;
        
            preparedStatement = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, e.getTitle());
            preparedStatement.setDate(2, e.getDate());

            preparedStatement.setString(3, e.getLieu());
            preparedStatement.setString(4, e.getDescription());

            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setDate(6, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setDate(7, new java.sql.Date(System.currentTimeMillis()));

            preparedStatement.setBoolean(8, false
            );
            preparedStatement.setBoolean(9, false
            );
            preparedStatement.setInt(10, 7);

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int idEvennement) {
        String req = "DELETE FROM event WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, idEvennement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   public void update(evennement e) {
        String req = "UPDATE event SET title=?,date=?,lieu=?,descritpion=?,enabled=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, e.getTitle());
            preparedStatement.setDate(2, e.getDate());
            preparedStatement.setString(3, e.getLieu());
            preparedStatement.setString( 4,e.getDescription());
             preparedStatement.setBoolean(5,e.getEnabled());
            
            preparedStatement.setInt(6,e.getId());
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
  }  

    public void deleteParticipation(int idEvennement) {
        String req = "DELETE FROM event_user WHERE  id_event=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            
            preparedStatement.setInt(1,idEvennement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public ArrayList<evennement> selectFrontAll() {

        String req = "SELECT * FROM `event` WHERE enabled=1";
        PreparedStatement preparedStatement;
        ArrayList <evennement>  evennement = new ArrayList <evennement>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            
            while (resultSet.next()) {
               evennement e = new evennement(resultSet.getInt(1), resultSet.getString(2),resultSet.getDate(3),
                       resultSet.getString(4),resultSet.getString(5),resultSet.getDate(6),resultSet.getDate(7),resultSet.getDate(8),
                       resultSet.getBoolean(9),resultSet.getBoolean(10),resultSet.getInt(11));
               evennement.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evennement;
    }
    public ArrayList<evennement> selectBackAll() {

        String req = "SELECT * FROM `event` WHERE enabled=0";
        PreparedStatement preparedStatement;
        ArrayList <evennement>  evennement = new ArrayList <evennement>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            
            while (resultSet.next()) {
               evennement e = new evennement(resultSet.getInt(1), resultSet.getString(2),resultSet.getDate(3),
                       resultSet.getString(4),resultSet.getString(5),resultSet.getDate(6),resultSet.getDate(7),resultSet.getDate(8),
                       resultSet.getBoolean(9),resultSet.getBoolean(10),resultSet.getInt(11));
               evennement.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evennement;
    }
    public ArrayList<Commentaire> getComments(int idEvennement) {

        String req = "SELECT * FROM `commentaire` WHERE id_event="+idEvennement;
        
        ArrayList <Commentaire>  c = new ArrayList <Commentaire>();
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(req);
            //preparedStatement.setInt(1,idEvennement);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            
            while (resultSet.next()) {
               Commentaire e = new Commentaire(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),
                       resultSet.getInt(4));
               c.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    public void insertComment(Commentaire c) {
        try {
            String req = "INSERT INTO `commentaire` (text,id_user,id_event) VALUES (?,?,?)";

            PreparedStatement preparedStatement;
        
            preparedStatement = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, c.getText());
            preparedStatement.setInt(2, c.getId_user());

            preparedStatement.setInt(3, c.getId_event());
            

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void participate(int iduser,int idevent) {
        try {
            String req = "INSERT INTO `event_user` (id_user,id_event,rating) VALUES (?,?,0)";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, iduser);
            preparedStatement.setInt(2, idevent);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean getParticipation(int iduser,int idevent) {
        String req = "SELECT * FROM `event_user` WHERE id_event="+idevent+" AND id_user="+iduser;
        
        ArrayList <Commentaire>  c = new ArrayList <Commentaire>();
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(req);
            //preparedStatement.setInt(1,idEvennement);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            
            while (resultSet.next()) {
               Commentaire e = new Commentaire(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),
                       resultSet.getInt(4));
               c.add(e);

            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(c.isEmpty()){
                return true;
            }
        return false;
    }
 }
