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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class UpdateEventController implements Initializable {
    @FXML
    private TextField title;
    @FXML
    private TextField lieux;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea description;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        description.setText(staticEvent.getDescription());
        lieux.setText(staticEvent.getLieu());
        title.setText(staticEvent.getTitle());
       // date.setValue(LocalDate);
        
    }
     @FXML
    private void submit (ActionEvent event) throws Exception {
        
        staticEvent.setTitle(title.getText());
        staticEvent.setLieu(lieux.getText());

        Date d = Date.valueOf(date.getValue());
        staticEvent.setDate(d);
        staticEvent.setDescription(description.getText());
        EvennementServices services = new EvennementServices();
        System.out.print(staticEvent);
        services.update(staticEvent);
        showMessageDialog(null, "Success");
        ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
    }
    @FXML
    private void back (ActionEvent event) throws Exception {
        ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
    }
    
}
