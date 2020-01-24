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
import static controller.ListerController.staticEvent;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Seif Bejaoui
 */
public class Admin_LO_HomeController implements Initializable {

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
    private JFXButton btn_stat;
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
        ArrayList arrayList;
        arrayList = (ArrayList) LOservice.admin_selectAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        user.setCellValueFactory(new PropertyValueFactory<>("username"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));

        TableColumn action1 = new TableColumn("");
        TableColumn action2 = new TableColumn("");
        action1.setCellValueFactory(new PropertyValueFactory<>("btn1"));
        action2.setCellValueFactory(new PropertyValueFactory<>("btn2"));
        table.getColumns().add(action1);
        table.getColumns().add(action2);
  
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
                        ArrayList newarrayList = (ArrayList) LOservice.searchAdminByTitle(newValue,"all");
                        ObservableList newobservableList = FXCollections.observableArrayList(newarrayList);
                        table.setItems(newobservableList);
                        user.setCellValueFactory(new PropertyValueFactory<>("username"));
                        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                        description.setCellValueFactory(new PropertyValueFactory<>("description"));
                        type.setCellValueFactory(new PropertyValueFactory<>("type"));
                        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));

                    } else if (checkDesc.isSelected()) {
                        ArrayList newarrayList = (ArrayList) LOservice.searchAdminByDescription(newValue,"all");
                        ObservableList newobservableList = FXCollections.observableArrayList(newarrayList);
                        table.setItems(newobservableList);
                        user.setCellValueFactory(new PropertyValueFactory<>("username"));
                        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                        description.setCellValueFactory(new PropertyValueFactory<>("description"));
                        type.setCellValueFactory(new PropertyValueFactory<>("type"));
                        created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
                    } else {
                        ArrayList newarrayList = (ArrayList) LOservice.searchAdminByUser(newValue,"all");
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
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Home.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAttente_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Attente.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllPublie_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Publish.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void AllAnnule_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Cancel.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void Stat_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Admin_LO_Stat.fxml"));
        btn_stat.getScene().setRoot(root);
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
