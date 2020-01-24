/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.EvennementServices;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import static controller.ListerController.staticEvent;
import Entities.Commentaire;
import Entities.evennement;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class EventDetailsController implements Initializable {
    
    public static ObservableList comment ;
    @FXML
    private Label lTitle;
    @FXML
    private Label lLieux;
    @FXML
    private Label lDateC;
    @FXML
    private Label lDate;
    @FXML
    private Label lDescription;
    @FXML
    private ListView comments;
    @FXML
    private TextField tText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comment= FXCollections.observableArrayList();
        lTitle.setText(staticEvent.getTitle());
        lLieux.setText(staticEvent.getLieu());

        lDateC.setText(staticEvent.getCreated_at().toString());
        lDate.setText(staticEvent.getDate().toString());
        lDescription.setText(staticEvent.getDescription());
        EvennementServices services = new EvennementServices();
        List l=services.getComments(staticEvent.getId());
        for(Object c:l){
            if (c instanceof Commentaire){
                comment.add(((Commentaire) c).getText());
            }
        }
        comments.setItems(comment);
        
    }    
    @FXML
    private void send (ActionEvent event) throws Exception {
        Commentaire c = new Commentaire();
        c.setId_event(staticEvent.getId());
        c.setId_user(7);
        c.setText(tText.getText());
        EvennementServices services = new EvennementServices();
        services.insertComment(c);
        showMessageDialog(null, "Success");
        ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent = FXMLLoader.load(getClass().getResource("/view/EventDetails.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
    }
    @FXML
    private void share (ActionEvent event) throws Exception {
        showMessageDialog(null, "Event shared with success");
        String token ="EAACEdEose0cBAC1ijxiAPfloqXG3AogHJDs4voflviZC29li4X61J06w8BvVkOZAMzDmB8FMXjhZBlozk8oQs7XSIY4BwYxYoqkiEgmuuptETZBYHSosfPk70ZBLCpJymNZCfUJMJlSNv0GGK0qTrilxj5vASFiCDkqTxczizZBsrBV4hF7QkaA7aZBFQkut3ZC9Kss3oKMM6WDwUvk67cLAi";
        FacebookClient fb=new DefaultFacebookClient(token);
        FacebookType r=fb.publish("me/feed", FacebookType.class, Parameter.with("message", "L'event "+staticEvent.getTitle()+" aura lieu le "+staticEvent.getDate()+" à "+staticEvent.getLieu()));
        System.out.println("fb.com"+r.getId());
        
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
     @FXML
    private void agenda (ActionEvent event) throws Exception {
        ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        EventAgendaController e=new EventAgendaController();
        e.start(stage);
    }
    @FXML
    private void participate (ActionEvent event) throws Exception {
        EvennementServices services = new EvennementServices();
        if(services.getParticipation(7, staticEvent.getId())){
            showMessageDialog(null, "You have participated with success");
            services.participate(7, staticEvent.getId());
        }else
            showMessageDialog(null, "You have already participated to this event");
            
    }
}
