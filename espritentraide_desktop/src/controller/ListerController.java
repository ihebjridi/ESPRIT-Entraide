/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.EvennementServices;


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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

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
public class ListerController extends Application implements Initializable {

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
        ArrayList<evennement> listEvents = service.selectFrontAll();
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
    

    @Override
    public void start(Stage stage) throws Exception {
        
    
        Parent root = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("EspritEntraide");
        stage.show();
        
    }
    @FXML
    private void update (ActionEvent event) throws Exception {
        if(staticEvent.getId()==0){
           showMessageDialog(null, "Please choose the event that you want to update"); 
        }
        else if(staticEvent.getId_user()!=7){
            showMessageDialog(null, "This Event is not yours");
        }else{
            
            ((Node) event.getSource()).getScene().getWindow().hide(); // dans une seule fenetre ;
            Parent parent = FXMLLoader.load(getClass().getResource("/view/UpdateEvent.fxml"));
            Stage stage = new Stage();
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
        }
        
    }
     @FXML
    private void create (ActionEvent event) throws Exception {   
        ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent = FXMLLoader.load(getClass().getResource("/view/CreateEvent.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
         
    }
    @FXML
    private void delete (ActionEvent event) throws Exception {
        if(staticEvent.getId()==0){
           showMessageDialog(null, "Please choose the event that you want to delete"); 
        }else if(staticEvent.getId_user()!=7){
            showMessageDialog(null, "This Event is not yours");
        }else{
            showMessageDialog(null, "Success");
            EvennementServices services = new EvennementServices();
            services.deleteParticipation(staticEvent.getId());
            services.delete(staticEvent.getId());
            
            ((Node) event.getSource()).getScene().getWindow().hide(); 
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
            Stage stage = new Stage();
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
        
        }
        
    }
    @FXML
    private void details (ActionEvent event) throws Exception {
        if(staticEvent.getId()==0){
           showMessageDialog(null, "Please choose the event that you want to show"); 
        }else{
            ((Node) event.getSource()).getScene().getWindow().hide(); 
            Parent parent = FXMLLoader.load(getClass().getResource("/view/EventDetails.fxml"));
            Stage stage = new Stage();
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
        
        }
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
