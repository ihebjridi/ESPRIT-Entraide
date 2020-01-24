/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Session;
import java.sql.Connection;
import java.sql.Statement;
import Utils.DataSource;
import Entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Seif Bejaoui
 */
public class UserService {

    public Connection cnx = DataSource.getInstance().getConnection();
    public Statement st;

    public UserService() {
        try {
            st = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AjouterUser(User u) throws SQLException {
        String req = "insert into fos_user(username, username_canonical, email, email_canonical,password, enabled,roles) values(?,?,?,?,?,1,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, u.getUsername());
        pre.setString(2, u.getUsername().toLowerCase());
        pre.setString(3, u.getEmail());
        pre.setString(4, u.getEmail().toLowerCase());
        pre.setString(5, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13)));
        pre.setString(7, "a:0:{}");
        pre.executeUpdate();
    }

    public Boolean verif_username(String username) {

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery("select * from fos_user where username ='" + username + "'");

            while (rs.next()) {

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean Signin(String username, String email, String pwd) throws SQLException {
        String req = "INSERT INTO `fos_user` (`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `password`, `roles`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            String generatedColumns[] = {"ID"};
            preparedStatement = cnx.prepareStatement(req,generatedColumns);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username.toLowerCase());
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, email.toLowerCase());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, BCrypt.hashpw(pwd, BCrypt.gensalt(13)));
            preparedStatement.setString(7, "a:0:{}");
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    Session.setId((int) id);
                    Session.setUsername(username);
                    Session.setEmail(email);
                    Session.setRole("a:0:{}");
                }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean SigninByfb(String username, String userid) throws SQLException {
        if (!Exist(username, userid)) {
            String req = "INSERT INTO `fos_user` (`username`, `username_canonical`, `isFb`, `idFb`, `enabled`, `roles`) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement;

            try {
                String generatedColumns[] = {"ID"};
                preparedStatement = cnx.prepareStatement(req, generatedColumns);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, username.toLowerCase());
                preparedStatement.setBoolean(3, true);
                preparedStatement.setString(4, userid);
                preparedStatement.setInt(5, 1);
                preparedStatement.setString(6, "a:0:{}");
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    Session.setId((int) id);
                    Session.setUsername(username);
                    Session.setEmail("null");
                    Session.setRole("a:0:{}");
                }

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LostObjectService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public Boolean login(String userName, String Pwd) throws SQLException {
        String req = "SELECT * FROM fos_user WHERE username=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, userName);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            if (BCrypt.checkpw(Pwd, rs.getString("password"))) {
                Session.setId(rs.getInt("id"));
                Session.setUsername(rs.getString("username"));
                Session.setEmail(rs.getString("email"));
                Session.setRole(rs.getString("roles"));
                return true;
            }
        }
        return false;
    }

    public Boolean loginByfb(String username, String userid) throws SQLException {
        String req = "SELECT * FROM fos_user WHERE username=? and isFb=? and idFb=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, username);
        pre.setBoolean(2, true);
        pre.setString(3, userid);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            Session.setId(rs.getInt("id"));
            Session.setUsername(rs.getString("username"));
            Session.setEmail(rs.getString("email"));
            Session.setRole(rs.getString("roles"));
            return true;

        }
        return false;
    }

    public Boolean Exist(String username, String userid) throws SQLException {
        String req = "SELECT * FROM fos_user WHERE username=? and isFb=? and idFb=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, username);
        pre.setBoolean(2, true);
        pre.setString(3, userid);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            System.out.println("Exist" + rs.getString("id"));
            return true;

        }
        return false;
    }

}
