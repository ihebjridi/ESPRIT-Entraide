/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Carpooling;
import Interfaces.ICarpooling;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class CarpoolingService implements ICarpooling {
    Connection connection = null;

    public CarpoolingService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void InsertCarpooling(Carpooling p) {
        String req = "INSERT INTO `carpooling` (`title`, `description`, `type`, `created_at`, `enabled`, `cancled`, `id_user`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, p.getTitle());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setString(3, p.getType());
            preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setBoolean(5, false);
            preparedStatement.setBoolean(6, false);
            preparedStatement.setInt(7, p.getId_user());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CarpoolingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Carpooling> selectAll() {
       String req = "SELECT * FROM `carpooling` WHERE enabled=true AND cancled=false";
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Carpooling> selectAllDemande() {
        String req = "SELECT * FROM `carpooling` WHERE type='demande' AND enabled=true AND cancled=false";
        List<Carpooling> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
        
    }

    @Override
    public List<Carpooling> selectAllAnnonce() {
       String req = "SELECT * FROM `carpooling` WHERE type='annonce' AND enabled=true AND cancled=false";
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Carpooling> selectMyAnnonce(int id) {
        String req = "SELECT * FROM `carpooling` WHERE type='annonce' AND id_user=" + id;
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Carpooling> selectMyDemande(int id) {
String req = "SELECT * FROM `carpooling` WHERE type='demande' AND id_user=" + id;
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public void delete(int idAds) {
String req = "DELETE FROM `carpooling` WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.setInt(1, idAds);
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Carpooling p) {
String req = "UPDATE `carpooling` SET title = ? , description = ? , type = ? , enabled = ? , cancled = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getType());
            ps.setBoolean(4, false);
            ps.setBoolean(5, false);
            ps.setInt(6, p.getId());
// execute insert SQL stetement
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Carpooling> searchById(int idAds) {
        String req = "SELECT * FROM `carpooling` WHERE id=" + idAds;
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;

    }

    @Override
    public List<Carpooling> searchByTitle(String title) {
        String req = "SELECT * FROM `carpooling` WHERE title LIKE '%" + title + "%'";
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Carpooling> searchByDescription(String description) {
         String req = "SELECT * FROM `carpooling` WHERE description LIKE '%" + description + "%'";
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;

    }

    @Override
    public void cancel(int idAds) {
        String req = "UPDATE `carpooling` SET enabled = ? , cancled = ?, canceled_at= ? WHERE id =" + idAds;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setBoolean(1, false);
            ps.setBoolean(2, true);
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
// execute insert SQL stetement
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void accept(int idAds) {
         String req = "UPDATE `carpooling` SET enabled = ? , cancled = ?, published_at= ?  WHERE id =" + idAds;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setBoolean(1, true);
            ps.setBoolean(2, false);
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
// execute insert SQL stetement
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Carpooling> admin_selectAll() {
        String req = "SELECT * FROM `carpooling` ";
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Carpooling> admin_selectAll_publish() {
        String req = "SELECT * FROM `carpooling` WHERE enabled=true AND cancled=false ";
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Carpooling> admin_selectAll_Attente() {
        String req = "SELECT * FROM `carpooling` WHERE enabled=false AND cancled=false ";
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Carpooling> admin_selectAll_Cancled() {
        String req = "SELECT * FROM `carpooling` WHERE enabled=false AND cancled=true ";
        PreparedStatement preparedStatement;
        List<Carpooling> Ads = new ArrayList<Carpooling>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Carpooling cov = new Carpooling(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), resultSet.getInt(10));
                Ads.add(cov);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CarpoolingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }
  @Override
    public void update1(int id, Carpooling p) {
        String req = "UPDATE carpooling SET title=?,description=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, p.getTitle());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CarpoolingService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    
    
}
