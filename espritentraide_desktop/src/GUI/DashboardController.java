/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Seif Bejaoui
 */
public class DashboardController implements Initializable {

    @FXML
    private JFXButton btn_lo;
    @FXML
    private JFXButton btn_cov;
    @FXML
    private JFXButton btn_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Close_action(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void LostObject_action(ActionEvent event) throws IOException {
        Session session = new Session();
        String role = session.getRole();
        System.out.println("role: " + role);
        System.out.println("role: " + role.length());
        if (role.length() == 6) {
            System.out.println("simple user");
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_Home.fxml"));
            btn_lo.getScene().setRoot(root);
        } else {
            System.out.println("admin");
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Home.fxml"));
            btn_lo.getScene().setRoot(root);
        }

    }

    @FXML
    private void Col_action(ActionEvent event) throws IOException {
        Session session = new Session();
        String role = session.getRole();
        System.out.println("role: " + role);
        System.out.println("role: " + role.length());
        if (role.length() == 6) {
            System.out.println("simple user");
            Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_Home.fxml"));
            btn_lo.getScene().setRoot(root);
        } else {
            System.out.println("admin");
            Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Home.fxml"));
            btn_lo.getScene().setRoot(root);
        }
    }

    @FXML
    private void evenementAction(ActionEvent event) throws IOException {
        Session session = new Session();
        String role = session.getRole();
        System.out.println("role: " + role);
        System.out.println("role: " + role.length());
        if (role.length() == 6) {
            System.out.println("simple user");
            Parent root = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
            btn_lo.getScene().setRoot(root);
        } else {
            System.out.println("admin");
            Parent root = FXMLLoader.load(getClass().getResource("/view/ValidationEvent.fxml"));
            btn_lo.getScene().setRoot(root);
        }
        
    }

    @FXML
    private void Cov_Action(ActionEvent event) throws IOException {
         Session session = new Session();
        String role = session.getRole();
        System.out.println("role: " + role);
        System.out.println("role: " + role.length());
        if (role.length() == 6) {
            System.out.println("simple user");
             Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_Home.fxml"));
        btn_lo.getScene().setRoot(root);
        } else {
            System.out.println("admin");
             Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Admin_Cov_Home.fxml"));
        btn_lo.getScene().setRoot(root);
        }
    }
}
