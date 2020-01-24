/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COV;

import Entities.Carpooling;
import Entities.LostObject;
import Entities.Session;
import Services.CarpoolingService;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Cov_AddAdsController implements Initializable {

    @FXML
    private JFXButton btn_all;
    @FXML
    private JFXButton btn_annonce;
    @FXML
    private JFXButton btn_demande;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       

    @FXML
    private void All_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }



    @FXML
    private void MyAds_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_MyAds.fxml"));
        btn_mesAds.getScene().setRoot(root);
    }

    @FXML
    private void MyDmd_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_MyDmd.fxml"));
        btn_mesDmd.getScene().setRoot(root);
    }

    @FXML
    private void AddAds_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_AddAds.fxml"));
        btn_AddAds.getScene().setRoot(root);
    }

    @FXML
    private void AddDmd_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_AddDmd.fxml"));
        btn_AddDmd.getScene().setRoot(root);
    }

    @FXML
    private void Close_action(MouseEvent event) {
    }

    @FXML
    private void AllAnnonce_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_Annonce.fxml"));
        btn_annonce.getScene().setRoot(root);
    }

    @FXML
    private void AllDemande_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_Demande.fxml"));
        btn_demande.getScene().setRoot(root);
    }

    @FXML
    private void BtnAdd_action(ActionEvent event) throws IOException {
        Session s = new Session();
        CarpoolingService COVservice = new CarpoolingService();
        Carpooling cov = new Carpooling(title.getText(), description.getText(), "annonce",s.getId());
        COVservice.InsertCarpooling(cov);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_MyAds.fxml"));
        add.getScene().setRoot(root);
    }
    
}
