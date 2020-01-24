/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COV;

import Entities.Carpooling;
import Entities.Session;
import Services.CarpoolingService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Cov_ModifierDemandeController implements Initializable {

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
    static int id_dmd;

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
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Cov_Demande.fxml"));
        btn_demande.getScene().setRoot(root);
    }

    @FXML
    private void BtnMod_action(ActionEvent event) {
        Session s = new Session();
        Carpooling f = new Carpooling(title.getText(), description.getText(), "demande",s.getId());
        CarpoolingService sv = new CarpoolingService();

        System.out.println("id demande: " + Cov_MyDmdController.id_dmd);
        sv.update1(Cov_MyDmdController.id_dmd, f);

        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_MyDmd.fxml"));
            Scene scene = new Scene(root);
            Stage a = new Stage();
            a.setScene(scene);
            a.show();

        } catch (IOException ex) {
            Logger.getLogger(Cov_MyAdsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
