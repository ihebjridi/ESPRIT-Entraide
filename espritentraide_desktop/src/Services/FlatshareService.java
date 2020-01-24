/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Flatshare;
import Entities.User;
import Interfaces.IFlatshare;
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
 * @author ASUS PC
 */
public class FlatshareService implements IFlatshare {

    Connection connection = null;

    public FlatshareService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void InsertCOL(Flatshare f) {
        String req = "INSERT INTO `flatshare` (`title`, `description`, `type`, `created_at`, `enabled`, `cancled`, `id_user`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, f.getTitle());
            preparedStatement.setString(2, f.getDescription());
            preparedStatement.setString(3, f.getType());
            preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setBoolean(5, false);
            preparedStatement.setBoolean(6, false);
            preparedStatement.setInt(7, f.getId_user());

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Flatshare> selectAll() {
        String req = "SELECT * FROM `flatshare` WHERE enabled=true AND cancled=false";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10)
                );
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Flatshare> selectAllDemande() {
        String req = "SELECT * FROM `flatshare` WHERE type='demande' AND enabled=true AND cancled=false";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Flatshare> selectAllAnnonce() {
        String req = "SELECT * FROM `flatshare` WHERE type='annonce' AND enabled=true AND cancled=false";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;

    }

    @Override
    public List<Flatshare> selectMyAnnonce(int id) {
        String req = "SELECT * FROM `flatshare` WHERE type='annonce' AND id_user=" + id;

        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Flatshare> selectMyDemande(int id) {
        String req = "SELECT * FROM `flatshare` WHERE type='demande' AND id_user=" + id;

        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public void delete(int idAds) {
        String req = "DELETE FROM `flatshare` WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.setInt(1, idAds);
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Flatshare f) {
        String req = "UPDATE `flatshare` SET title = ? , description = ? , type = ? , enabled = ? , cancled = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, f.getTitle());
            ps.setString(2, f.getDescription());
            ps.setString(3, f.getType());
            ps.setBoolean(4, false);
            ps.setBoolean(5, false);
            ps.setInt(6, f.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Flatshare> searchById(int idAds) {
        String req = "SELECT * FROM `flatshare` WHERE id=" + idAds;
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Flatshare> searchByTitle(String title) {
        String req = "SELECT * FROM `flatshare` WHERE title LIKE '%" + title + "%'";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Flatshare> searchByDescription(String description) {
        String req = "SELECT * FROM `flatshare` WHERE description LIKE '%" + description + "%'";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public void cancel(int idAds) {
        String req = "UPDATE `flatshare` SET enabled = ? , cancled = ?, canceled_at= ? WHERE id =" + idAds;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setBoolean(1, false);
            ps.setBoolean(2, true);
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void accept(int idAds) {
        String req = "UPDATE `flatshare` SET enabled = ? , cancled = ?, published_at= ?  WHERE id =" + idAds;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setBoolean(1, true);
            ps.setBoolean(2, false);
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Flatshare> admin_selectAll() {
        String req = "SELECT * FROM `flatshare` ";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Flatshare> admin_selectAll_Published() {
        String req = "SELECT * FROM `flatshare` WHERE enabled=true AND cancled=false ";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Flatshare> admin_selectAll_Attente() {
        String req = "SELECT * FROM `flatshare` WHERE enabled=false AND cancled=false ";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<Flatshare> admin_selectAll_Cancled() {
        String req = "SELECT * FROM `flatshare` WHERE enabled=false AND cancled=true ";
        PreparedStatement preparedStatement;
        List<Flatshare> Ads = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Flatshare fs = new Flatshare(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getDate(8),
                        resultSet.getBoolean(9),
                        resultSet.getBoolean(10));
                Ads.add(fs);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public void update1(int id, Flatshare p) {
        String req = "UPDATE Flatshare SET title=?,description=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, p.getTitle());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getUser(int id) {
        System.out.println(id);
        String users = "";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("select * from fos_user where id =? ");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                users = rs.getString("email");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FlatshareService.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(users);
        return users;
    }

}
