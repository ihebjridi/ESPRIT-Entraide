/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.LostObject;
import Entities.Session;
import Services.LostObjectService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Seif Bejaoui
 */
public class LO_AddAdsController implements Initializable {

    @FXML
    private JFXButton btn_all;
    @FXML
    private JFXButton btn_perdu;
    @FXML
    private JFXButton btn_trouve;
    @FXML
    private JFXButton btn_mesAds;
    @FXML
    private JFXButton btn_mesDmd;
    @FXML
    private JFXButton btn_AddAds;
    @FXML
    private JFXButton btn_AddDmd;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXButton add;
    @FXML
    private JFXTextArea description;
    @FXML
    private Text msgerr1;
    @FXML
    private Text msgerr2;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void All_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllPerdu_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_Perdu.fxml"));
        btn_perdu.getScene().setRoot(root);
    }

    @FXML
    private void AllFound_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_Found.fxml"));
        btn_trouve.getScene().setRoot(root);
    }

    @FXML
    private void MyAds_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_MyAds.fxml"));
        btn_mesAds.getScene().setRoot(root);
    }

    @FXML
    private void MyDmd_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_MyDmd.fxml"));
        btn_mesDmd.getScene().setRoot(root);
    }

    @FXML
    private void AddAds_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_AddAds.fxml"));
        btn_AddAds.getScene().setRoot(root);
    }

    @FXML
    private void AddDmd_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_AddDmd.fxml"));
        btn_AddDmd.getScene().setRoot(root);
    }

    @FXML
    private void BtnAdd_action(ActionEvent event) throws IOException {
        boolean error = false;
        msgerr1.setVisible(false);
        msgerr2.setVisible(false);
        if (title.getText().length() < 2) {
            msgerr1.setVisible(true);
            error = true;
        }

        if (description.getText().length() < 2) {
            error = true;
            msgerr2.setVisible(true);
        }
        if (!error) {
            Session s = new Session();
            LostObjectService LOservice = new LostObjectService();
            LostObject LO = new LostObject(title.getText(), description.getText(), "annonce", s.getId());
            LOservice.InsetLostObject(LO);
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_MyAds.fxml"));
            add.getScene().setRoot(root);
        }
    }

     @FXML
    private void logo_action(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard.fxml"));
        logo.getScene().setRoot(root);
    }
}
