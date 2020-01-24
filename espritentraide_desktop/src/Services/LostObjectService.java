/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.LostObject;
import Entities.Session;
import Entities.User;
import Interfaces.ILostObject;
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

/**
 *
 * @author Seif Bejaoui
 */
public class LostObjectService implements ILostObject {

    Connection connection = null;

    public LostObjectService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void InsetLostObject(LostObject p) {
        String req = "INSERT INTO `lost_object` (`title`, `description`, `type`, `created_at`, `enabled`, `cancled`, `id_user`) VALUES (?,?,?,?,?,?,?)";
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
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUserById(int id) {

        String req = "SELECT * FROM fos_user WHERE id=" + id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4));

                return resultSet.getString(2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<LostObject> selectAll() {
        String req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false";
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));

                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> admin_selectAll() {
        String req = "SELECT * FROM `lost_object` ";
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> admin_selectAll_trier() {
        String req = "SELECT * FROM `lost_object` order by created_at ";
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> admin_selectAll_publish() {
        String req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false ";
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> admin_selectAll_Attente() {
        String req = "SELECT * FROM `lost_object` WHERE enabled=false AND cancled=false ";
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> admin_selectAll_Cancled() {
        String req = "SELECT * FROM `lost_object` WHERE enabled=false AND cancled=true ";
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> selectAllDemande() {
        String req = "SELECT * FROM `lost_object` WHERE type='demande' AND enabled=true AND cancled=false";
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> selectAllAnnonce() {
        String req = "SELECT * FROM `lost_object` WHERE type='annonce' AND enabled=true AND cancled=false";
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> selectMyDemande(int id) {
        String req = "SELECT * FROM `lost_object` WHERE type='demande' AND id_user=" + id;
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> selectMyAnnonce(int id) {
        String req = "SELECT * FROM `lost_object` WHERE type='annonce' AND id_user=" + id;
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public void delete(int idAds) {

        String req = "DELETE FROM `lost_object` WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.setInt(1, idAds);
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(LostObject p) {
        String req = "UPDATE `lost_object` SET title = ? , description = ? , enabled = ? , cancled = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getDescription());
            ps.setBoolean(3, false);
            ps.setBoolean(4, false);
            ps.setInt(5, p.getId());
// execute insert SQL stetement
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<LostObject> searchById(int idAds) {
        String req = "SELECT * FROM `lost_object` WHERE id=" + idAds;
        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> searchByTitle(String title, String type) {
        String req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and title LIKE '%" + title + "%'";
        if (type == "annonce") {
            req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and type='annonce' and title LIKE '%" + title + "%'";

        } else if (type == "demande") {
            req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and type='demande' and title LIKE '%" + title + "%'";
        }

        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> searchAdminByTitle(String title, String type) {
        String req = "SELECT * FROM `lost_object` where title LIKE '%" + title + "%'";

        if (type == "attente") {
            req = "SELECT * FROM `lost_object` WHERE enabled=false AND cancled=false  and title LIKE '%" + title + "%'";

        } else if (type == "cancel") {
            req = "SELECT * FROM `lost_object` WHERE enabled=false AND cancled=true and title LIKE '%" + title + "%'";

        } else if (type == "publish") {
            req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false  and title LIKE '%" + title + "%'";
        }

        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> searchMineByTitle(String title, String type) {
        Session s = new Session();
        String req = "SELECT * FROM `lost_object` WHERE id_user=" + s.getId() + " and title LIKE '%" + title + "%'";
        if (type == "annonce") {
            req = "SELECT * FROM `lost_object` WHERE id_user=" + s.getId() + " and type='annonce' and title LIKE '%" + title + "%'";

        } else if (type == "demande") {
            req = "SELECT * FROM `lost_object` WHERE id_user=" + s.getId() + " and type='demande' and title LIKE '%" + title + "%'";
        }

        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> searchByDescription(String description, String type) {
        String req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and description LIKE '%" + description + "%'";
        if (type == "annonce") {
            req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and type='annonce' and description LIKE '%" + description + "%'";

        } else if (type == "demande") {
            req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and type='demande' and description LIKE '%" + description + "%'";
        }

        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> searchAdminByDescription(String description, String type) {
        String req = "SELECT * FROM `lost_object` WHERE description LIKE '%" + description + "%'";
        if (type == "attente") {
            req = "SELECT * FROM `lost_object` WHERE enabled=false AND cancled=false and description LIKE '%" + description + "%'";

        } else if (type == "cancel") {
            req = "SELECT * FROM `lost_object` WHERE enabled=false AND cancled=true and description LIKE '%" + description + "%'";
        } else if (type == "publish") {
            req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and description LIKE '%" + description + "%'";
        }

        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    @Override
    public List<LostObject> searchMineByDescription(String description, String type) {
        Session s = new Session();
        String req = "SELECT * FROM `lost_object` WHERE id_user=" + s.getId() + " and description LIKE '%" + description + "%'";
        if (type == "annonce") {
            req = "SELECT * FROM `lost_object` WHERE id_user=" + s.getId() + " and type='annonce' and description LIKE '%" + description + "%'";

        } else if (type == "demande") {
            req = "SELECT * FROM `lost_object` WHERE id_user=" + s.getId() + " and type='demande' and description LIKE '%" + description + "%'";
        }

        PreparedStatement preparedStatement;
        List<LostObject> Ads = new ArrayList<LostObject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }

    public List<User> searchUserbyname(String username) {
        String req = "SELECT * FROM `fos_user` WHERE username LIKE '%" + username + "%'";
        PreparedStatement preparedStatement;
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                User _user = new User(resultSet.getInt(1));
                users.add(_user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public List<LostObject> searchByUser(String user, String type) {
        List<User> users = searchUserbyname(user);
        List<LostObject> Ads = new ArrayList<LostObject>();
        users.stream().forEach(u -> {
            String req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and id_user = " + u.getId();
            if (type == "annonce") {
                req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and type='annonce' and id_user = " + u.getId();

            } else if (type == "demande") {
                req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and type='demande' and id_user = " + u.getId();
            }

            PreparedStatement preparedStatement;
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(req);
                while (resultSet.next()) {
                    LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                    Ads.add(LO);
                }

            } catch (SQLException ex) {
                Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return Ads;
    }

    @Override
    public List<LostObject> searchAdminByUser(String user, String type) {
        List<User> users = searchUserbyname(user);
        List<LostObject> Ads = new ArrayList<LostObject>();
        users.stream().forEach(u -> {
            String req = "SELECT * FROM `lost_object` WHERE  id_user = " + u.getId();
            if (type == "attente") {
                req = "SELECT * FROM `lost_object` WHERE enabled=false AND cancled=false and id_user = " + u.getId();

            } else if (type == "cancel") {
                req = "SELECT * FROM `lost_object` WHERE enabled=false AND cancled=true and id_user = " + u.getId();

            } else if (type == "publish") {
                req = "SELECT * FROM `lost_object` WHERE enabled=true AND cancled=false and id_user = " + u.getId();
            }

            PreparedStatement preparedStatement;
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(req);
                while (resultSet.next()) {
                    LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));
                    Ads.add(LO);
                }

            } catch (SQLException ex) {
                Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return Ads;
    }

    @Override
    public void cancel(int idAds) {
        String req = "UPDATE `lost_object` SET enabled = ? , cancled = ?, canceled_at= ? WHERE id =" + idAds;
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
    public void accpet(int idAds) {
        String req = "UPDATE `lost_object` SET enabled = ? , cancled = ?, published_at= ?  WHERE id =" + idAds;
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
    public LostObject getLostObject(int id) {
        String req = "SELECT * FROM lost_object WHERE id=" + id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                LostObject LO = new LostObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getBoolean(8), resultSet.getBoolean(9), getUserById(resultSet.getInt(10)));

                return LO;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
