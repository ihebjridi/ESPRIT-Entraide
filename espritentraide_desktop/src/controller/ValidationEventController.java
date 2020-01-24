/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.EvennementServices;
import static controller.ListerController.staticEvent;
import Entities.evennement;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class ValidationEventController extends Application  implements Initializable {

    public static evennement staticEvent;
    @FXML
    private TableView<evennement> listEvent;
    @FXML
    private TableColumn<evennement, LocalDate> date;
    @FXML
    private TableColumn<evennement, String> lieux;
    @FXML
    private TableColumn<evennement, Integer> description;
    @FXML
    private TableColumn<evennement, String> titre;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        staticEvent=new evennement();
        EvennementServices service = new EvennementServices();
        ArrayList<evennement> listEvents = service.selectBackAll();
        ObservableList observableList = FXCollections.observableArrayList(listEvents);
        listEvent.setItems(observableList);
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieux.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
         
        titre.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        listEvent.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)){
               staticEvent=listEvent.getSelectionModel().getSelectedItem();
               
            }
        });
    }    
     @FXML
    private void valider (ActionEvent event) throws Exception {
         if(staticEvent.getId()==0){
           showMessageDialog(null, "Please choose the event you want to validate"); 
        }else{
             
            EvennementServices service = new EvennementServices();
            staticEvent.setEnabled(true);
            service.update(staticEvent);
            showMessageDialog(null, "Success");
            ((Node) event.getSource()).getScene().getWindow().hide(); 
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ValidationEvent.fxml"));
            Stage stage = new Stage();
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
         }
    }
    @FXML
    private void deny (ActionEvent event) throws Exception {
         if(staticEvent.getId()==0){
           showMessageDialog(null, "Please choose the event you want to deny"); 
        }else{
             
            EvennementServices services = new EvennementServices();
            services.deleteParticipation(staticEvent.getId());
            services.delete(staticEvent.getId());
            showMessageDialog(null, "Success");
            ((Node) event.getSource()).getScene().getWindow().hide(); 
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ValidationEvent.fxml"));
            Stage stage = new Stage();
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
         }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ValidationEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("EspritEntraide Back");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
