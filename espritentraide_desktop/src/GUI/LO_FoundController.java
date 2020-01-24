/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.LostObject;
import Services.LostObjectService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Seif Bejaoui
 */
public class LO_FoundController implements Initializable {

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
    private JFXTextField search;
    @FXML
    private TableView<LostObject> table;
    @FXML
    private TableColumn<LostObject, String> user;
    @FXML
    private TableColumn<LostObject, String> title;
    @FXML
    private TableColumn<LostObject, String> description;
    @FXML
    private TableColumn<LostObject, String> type;
    @FXML
    private TableColumn<LostObject, Date> created_at;
    @FXML
    private JFXCheckBox checkTitle;
    @FXML
    private JFXCheckBox checkDesc;
    @FXML
    private JFXCheckBox checkUser;
    @FXML
    private Text msgerr;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LostObjectService LOservice = new LostObjectService();
        ArrayList arrayList = (ArrayList) LOservice.selectAllDemande();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        user.setCellValueFactory(new PropertyValueFactory<>("username"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        TableColumn action = new TableColumn("");
        action.setCellValueFactory(new PropertyValueFactory<>("btn6"));
        table.getColumns().add(action);
        FilteredList<LostObject> filteredData = new FilteredList(observableList, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!checkTitle.isSelected() && !checkDesc.isSelected() && !checkUser.isSelected()) {
                msgerr.setVisible(true);
            } else {
                msgerr.setVisible(false);
                if (newValue.equals("")) {
                    table.setItems(observableList);
                    user.setCellValueFactory(new PropertyValueFactory<>("username"));
                    title.setCellValueFactory(new PropertyValueFactory<>("title"));
                    description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    type.setCellValueFactory(new PropertyValueFactory<>("type"));
                    created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));

                } else {
                    if (checkTitle.isSelected()) {
                        ArrayList newarrayList = (ArrayList) LOservice.searchByTitle(newValue, "demande");
                        ObservableList newobservableList = FXCollections.observableArrayList(newarrayList);
                        table.setItems(newobservableList);
                        user.setCellValueFactory(new PropertyValueFactory<>("username"));
                        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                        description.setCellValueFactory(new PropertyValueFactory<>("description"));
                        type.setCellValueFactory(new PropertyValueFactory<>("type"));
                        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));

                    } else if (checkDesc.isSelected()) {
                        ArrayList newarrayList = (ArrayList) LOservice.searchByDescription(newValue, "demande");
                        ObservableList newobservableList = FXCollections.observableArrayList(newarrayList);
                        table.setItems(newobservableList);
                        user.setCellValueFactory(new PropertyValueFactory<>("username"));
                        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                        description.setCellValueFactory(new PropertyValueFactory<>("description"));
                        type.setCellValueFactory(new PropertyValueFactory<>("type"));
                        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
                    } else {
                        ArrayList newarrayList = (ArrayList) LOservice.searchByUser(newValue, "demande");
                        ObservableList newobservableList = FXCollections.observableArrayList(newarrayList);
                        table.setItems(newobservableList);
                        user.setCellValueFactory(new PropertyValueFactory<>("username"));
                        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                        description.setCellValueFactory(new PropertyValueFactory<>("description"));
                        type.setCellValueFactory(new PropertyValueFactory<>("type"));
                        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
                    }

                }

            }

        });
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
    private void checkTitle_action(ActionEvent event) {

        checkDesc.selectedProperty().set(false);
        checkUser.selectedProperty().set(false);
    }

    @FXML
    private void checkDesc_action(ActionEvent event) {
        checkTitle.selectedProperty().set(false);
        checkUser.selectedProperty().set(false);
    }

    @FXML
    private void checkUser_action(ActionEvent event) {
        checkDesc.selectedProperty().set(false);
        checkTitle.selectedProperty().set(false);
    }
    
      @FXML
    private void logo_action(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard.fxml"));
        logo.getScene().setRoot(root);
    }

}
