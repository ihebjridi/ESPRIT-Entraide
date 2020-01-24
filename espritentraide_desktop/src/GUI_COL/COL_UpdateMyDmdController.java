/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COL;

import Entities.Flatshare;
import Entities.Session;
import Services.FlatshareService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
 * @author ASUS PC
 */
public class COL_UpdateMyDmdController implements Initializable {

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
    private JFXButton update;
    @FXML
    private JFXTextArea description;

    static int id_pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void All_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void MyAds_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_MyAds.fxml"));
        btn_mesAds.getScene().setRoot(root);
    }

    @FXML
    private void MyDmd_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_MyDmd.fxml"));
        btn_mesDmd.getScene().setRoot(root);
    }

    @FXML
    private void AddAds_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_AddAnnonce.fxml"));
        btn_AddAds.getScene().setRoot(root);
    }

    @FXML
    private void AddDmd_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_AddDmd.fxml"));
        btn_AddDmd.getScene().setRoot(root);
    }

    @FXML
    private void Close_action(MouseEvent event) {
    }

    @FXML
    private void AllAnnonces_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_Annonces.fxml"));
        btn_annonce.getScene().setRoot(root);
    }

    @FXML
    private void AllDemandes_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_Demandes.fxml"));
        btn_demande.getScene().setRoot(root);
    }

    @FXML
    private void BtnUpdate_action(ActionEvent event) throws IOException {
        Session s = new Session();
        FlatshareService FS = new FlatshareService();
        Flatshare f = new Flatshare(title.getText(), description.getText(), "demande", s.getId());
        FS.update1(COL_MyDmdController.id_dmd, f);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_MyDmd.fxml"));
        update.getScene().setRoot(root);
    }

}
