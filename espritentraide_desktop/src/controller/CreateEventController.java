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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class CreateEventController extends Application implements Initializable {
    @FXML
    private TextField title;
    @FXML
    private TextField lieux;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea description;
    
    
    @FXML
    private void submit (ActionEvent event) throws Exception {
        Date d = Date.valueOf(date.getValue());
        java.sql.Date dnow = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if(dnow.before(d)){
            evennement e = new evennement();
            e.setTitle(title.getText());
            e.setLieu(lieux.getText());



            e.setDate(d);
            e.setDescription(description.getText());
            EvennementServices services = new EvennementServices();
            services.insert(e);

            //send Mail
            String host = "smtp.gmail.com" ;
            String utilisateur = "nebras.boulares@esprit.tn";
            String pass = "94051426";
            String SendTo = "nebrasboulaares9@gmail.com";
            String from = "nebras.boulares@esprit.tn";
            String Subject = "Information à propos l'ajout d'un event";
            String textMessage = "Votre event a été créer avec succée ";
            boolean seesionDebug = false;
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
             java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(seesionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(SendTo)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(Subject);

            msg.setText(textMessage);

            Transport transport=mailSession.getTransport("smtp");
            transport.connect(host, utilisateur, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
            showMessageDialog(null, "Success");
            ((Node) event.getSource()).getScene().getWindow().hide(); 
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Lister.fxml"));
            Stage stage = new Stage();
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
        }else{
            showMessageDialog(null, "Date invalide");
        }
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CreateEvent.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("EspritEntraide");
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }
    
}
