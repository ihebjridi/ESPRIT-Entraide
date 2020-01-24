/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.LostObject;
import Entities.Session;
import Services.LostObjectService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Seif Bejaoui
 */
public class LO_sendMailController implements Initializable {

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
    private JFXTextField sendTo;
    @FXML
    private JFXButton send;
    @FXML
    private JFXTextArea contenu;
    @FXML
    private Text msgerr1;
    @FXML
    private Text msgerr2;
    @FXML
    private JFXTextField subject;
    @FXML
    private Text msgerr3;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Session s = new Session();
        LostObjectService LOservice = new LostObjectService();
        LostObject ads = LOservice.getLostObject(s.getId_Lo());
        subject.setText("Objet-perdu <"+ads.getTitle()+">| Esprit Entr'aide");
        contenu.setText("L'objet "+ads.getTitle()+" de l'annonce "+ads.getId()+" ............");
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
    private void Btnsend_action(ActionEvent event) throws AddressException, MessagingException {
        boolean error = false;
        msgerr1.setVisible(false);
        msgerr2.setVisible(false);
        msgerr3.setVisible(false);
        if (sendTo.getText().length() < 2) {
            msgerr1.setVisible(true);
            error = true;
        }

        if (subject.getText().length() < 2) {
            error = true;
            msgerr2.setVisible(true);
        }
        
        if (contenu.getText().length() < 2) {
            error = true;
            msgerr3.setVisible(true);
        }
        if (!error) {
            System.out.println("sss");
            //send Mail
            String host = "smtp.gmail.com" ;
            String utilisateur = "seifbejaouii@gmail.com";
            String pass = "call me if you want my password";
            
            String SendTo = sendTo.getText();
            String from = "seifbejaouiii@gmail.com";
            String Subject = subject.getText();
            String textMessage = contenu.getText();
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
             java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, null);
            mailSession.setDebug(false);
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
            showMessageDialog(null, "votre email a été envoyé avec succès");
            sendTo.setText("");
        }
    }
    
    
      @FXML
    private void logo_action(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard.fxml"));
        logo.getScene().setRoot(root);
    }
}
