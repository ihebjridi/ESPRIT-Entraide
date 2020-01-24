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
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Cov_MyAdsController implements Initializable {

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
    private JFXTextField search;
    @FXML
    private TableView<Carpooling> table;
    @FXML
    private TableColumn<Carpooling, String> user;
    @FXML
    private TableColumn<Carpooling, String> title;
    @FXML
    private TableColumn<Carpooling, String> description;
    @FXML
    private TableColumn<Carpooling, String> type;
    @FXML
    private TableColumn<Carpooling, Date> created_at;
    static int id_ads;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session s = new Session();
        CarpoolingService Cov = new CarpoolingService();
        ArrayList arrayList = (ArrayList) Cov.selectMyAnnonce(s.getId());
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
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
    private void Supp_Annonce(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous supprimer cette annonce");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                int id = table.getSelectionModel().getSelectedItem().getId();
                CarpoolingService s = new CarpoolingService();
                s.delete(id);
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/GUI_COV/Cov_MyAds.fxml"));
                    Scene scene = new Scene(root);
                    Stage x = (Stage) this.table.getScene().getWindow();
                    x.setScene(scene);
                    x.show();
                } catch (IOException ex) {
                    Logger.getLogger(Cov_MyAdsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    @FXML
    private void ModifierAnnonce(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/GUI_COV/Cov_ModifierAnnonce.fxml"));
            Cov_ModifierAnnonceController controller = loader.getController();

            System.out.println("TEST : " + table.getSelectionModel().getSelectedItem());

            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.initOwner(table.getScene().getWindow());
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Cov_MyAdsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Ads(MouseEvent event) {
        int id = table.getSelectionModel().getSelectedItem().getId();
        System.out.println(id);
        id_ads = id;
    }
    
}
