/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_COV;

import Services.CarpoolingService;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Admin_Cov_HomeController implements Initializable {

    @FXML
    private JFXButton btn_all;
    @FXML
    private JFXButton btn_attente;
    @FXML
    private JFXButton btn_publie;
    @FXML
    private JFXButton btn_annule;
    @FXML
    private JFXTextField search;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> user;
    @FXML
    private TableColumn<?, ?> title;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> created_at;
    @FXML
    private JFXButton btn_stat;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarpoolingService cov = new CarpoolingService();
        ArrayList arrayList = (ArrayList) cov.admin_selectAll();
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
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Admin_Cov_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAttente_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Admin_Cov_Attente.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllPublie_action(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Admin_Cov_Publish.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAnnule_action(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Admin_Cov_Cancel.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void Close_action(MouseEvent event) {
    }

     @FXML
    private void Stat_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI_COV/Admin_Cov_Stat.fxml"));
        btn_stat.getScene().setRoot(root);
    }
    
}
