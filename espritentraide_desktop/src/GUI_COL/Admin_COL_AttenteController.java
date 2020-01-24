/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COL;

import Entities.Flatshare;
import Services.FlatshareService;
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
import javafx.scene.control.ButtonBar;
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
public class Admin_COL_AttenteController implements Initializable {

    @FXML
    private JFXButton btn_all;
    @FXML
    private JFXButton btn_attente;
    @FXML
    private JFXButton btn_publie;
    @FXML
    private JFXButton btn_annule;
    @FXML
    private JFXButton btn_stat;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FlatshareService FS = new FlatshareService();
        ArrayList arrayList = (ArrayList) FS.admin_selectAll_Attente();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        // TODO
    }

    @FXML
    private void All_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAttente_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Attente.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllPublie_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Published.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAnnule_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Canceled.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void Close_action(MouseEvent event) {
    }

    @FXML
    private void Stat_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Stat.fxml"));
        btn_stat.getScene().setRoot(root);
    }

    @FXML
    private void aorr(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Gestion d'annonces et demandes");
        alert.setHeaderText("Que voulez vous faire ?");
        alert.setContentText("Précisez votre choix.");

        ButtonType Accepter = new ButtonType("Accepter");
        ButtonType Refuser = new ButtonType("Refuser");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(Accepter, Refuser, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == Accepter) {

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("confirmation");
            alert1.setHeaderText("Accept");
            alert1.setContentText("voulez vous accepter cette publication ?");
            alert1.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    int idS = table.getSelectionModel().getSelectedItem().getId();
                    FlatshareService FS = new FlatshareService();
                    FS.accept(idS);
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Published.fxml"));
                        Scene scene = new Scene(root);
                        Stage x = (Stage) this.table.getScene().getWindow();
                        x.setScene(scene);
                        x.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Admin_COL_PublishedController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

        } else if (result.get() == Refuser) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("confirmation");
            alert1.setHeaderText("Refus");
            alert1.setContentText("voulez vous refuser cette publication ?");
            alert1.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    int idS = table.getSelectionModel().getSelectedItem().getId();
                    FlatshareService FS = new FlatshareService();
                    FS.cancel(idS);
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI_COL/Admin_COL_Canceled.fxml"));
                        Scene scene = new Scene(root);
                        Stage x = (Stage) this.table.getScene().getWindow();
                        x.setScene(scene);
                        x.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Admin_COL_CanceledController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

}
