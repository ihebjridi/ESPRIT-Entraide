/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COL;

import Entities.Flatshare;
import Entities.Session;
import Services.FlatshareService;
import Services.LostObjectService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
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
 * @author ASUS PC
 */
public class COL_MyAdsController implements Initializable {

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
    private TableView<Flatshare> table;
    @FXML
    private TableColumn<Flatshare, Integer> user;
    @FXML
    private TableColumn<Flatshare, String> title;
    @FXML
    private TableColumn<Flatshare, String> description;
    @FXML
    private TableColumn<Flatshare, String> type;
    @FXML
    private TableColumn<Flatshare, Date> created_at;

    static int id_ads;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session s = new Session();
        FlatshareService FS = new FlatshareService();
        ArrayList arrayList = (ArrayList) FS.selectMyAnnonce(s.getId());
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
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
    private void click(MouseEvent event) {
        int id = table.getSelectionModel().getSelectedItem().getId();
        System.out.println(id);
        id_ads = id;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Gestio d'annonces");
        alert.setHeaderText("Que voulez vous faire ?");
        alert.setContentText("Précisez votre choix.");

        ButtonType Modifier = new ButtonType("Modifier");
        ButtonType Supprimer = new ButtonType("Supprimer");
        ButtonType Details = new ButtonType("Détails");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(Modifier, Supprimer, Details, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == Supprimer) {

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("confirmation");
            alert1.setHeaderText(null);
            alert1.setContentText("voulez vous supprimer cette demande");
            alert1.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    int idS = table.getSelectionModel().getSelectedItem().getId();
                    FlatshareService s = new FlatshareService();
                    s.delete(idS);
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI_COL/COL_MyAds.fxml"));
                        Scene scene = new Scene(root);
                        Stage x = (Stage) this.table.getScene().getWindow();
                        x.setScene(scene);
                        x.show();
                    } catch (IOException ex) {
                        Logger.getLogger(COL_MyAdsController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

        } else if (result.get() == Modifier) {
            try {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/GUI_COL/COL_UpdateMyAds.fxml"));
                COL_UpdateMyAdsController controller = loader.getController();

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
                Logger.getLogger(COL_MyAdsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (result.get() == Details) {
            // ... user chose "Three"
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

}
