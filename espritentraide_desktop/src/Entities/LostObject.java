/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Interfaces.ILostObject;
import Services.LostObjectService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Seif Bejaoui
 */
public class LostObject {

    private int id;
    private String title;
    private String description;
    private String type;
    private Date created_at;
    private Date published_at;
    private Date canceled_at;
    private Boolean enabled;
    private Boolean cancled;
    private int id_user;
    private User user;
    private String username;
    //accept
    private JFXButton btn1;
    //cancel
    private JFXButton btn2;
    //modifier
    private JFXButton btn3;
    //delete my ads
    private JFXButton btn4;
    //delete my dmd
    private JFXButton btn5;
    //sendmail
    private JFXButton btn6;

    public LostObject() {
    }

    public LostObject(int id, String title, String description, String type, Date created_at, Date published_at, Date canceled_at, Boolean enabled, Boolean cancled, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.created_at = created_at;
        this.published_at = published_at;
        this.canceled_at = canceled_at;
        this.enabled = enabled;
        this.cancled = cancled;
        this.user = user;
    }

    public LostObject(int id, String title, String description, String type, Date created_at, Date published_at, Date canceled_at, Boolean enabled, Boolean cancled, String username) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.created_at = created_at;
        this.published_at = published_at;
        this.canceled_at = canceled_at;
        this.enabled = enabled;
        this.cancled = cancled;
        this.username = username;
        btn1 = new JFXButton("");
        btn2 = new JFXButton("");
        btn3 = new JFXButton("");
        btn4 = new JFXButton("");
        btn5 = new JFXButton("");
        btn6 = new JFXButton("");

        btn3.setStyle("-fx-background-image: url('/Resources/Renew_24px.png')");
        btn3.setMinWidth(25);
        btn4.setStyle("-fx-background-image: url('/Resources/Trash_24px.png')");
        btn4.setMinWidth(25);
        btn5.setStyle("-fx-background-image: url('/Resources/Trash_24px.png')");
        btn5.setMinWidth(25);
        btn4.setMinWidth(25);
        btn6.setStyle("-fx-background-image: url('/Resources/Sent_24px.png')");
        btn6.setMinWidth(25);

        if ((enabled == false) && (cancled == false)) {
            //en attente

            btn1.setStyle("-fx-background-image: url('/Resources/Checkmark_26px.png')");
            btn1.setMinWidth(25);
            btn2.setStyle("-fx-background-image: url('/Resources/Cancel_26px.png')");
            btn2.setMinWidth(25);
        } else if ((enabled == true) && (cancled == false)) {
            //publié

            btn1.setVisible(false);
            btn2.setStyle("-fx-background-image: url('/Resources/Cancel_26px.png')");
            btn2.setMinWidth(25);
        } else if ((enabled == false) && (cancled == true)) {
            //cancled

            btn1.setStyle("-fx-background-image: url('/Resources/Checkmark_26px.png')");
            btn1.setMinWidth(25);
            btn2.setVisible(false);
        }
        btn1.setOnAction(event -> {
            LostObjectService LOservice = new LostObjectService();
            LOservice.accpet(id);
            btn1.setVisible(false);
            btn2.setStyle("-fx-background-image: url('/Resources/Cancel_26px.png')");
            btn2.setMinWidth(25);
            btn2.setVisible(true);

        });
        btn2.setOnAction(event -> {
            LostObjectService LOservice = new LostObjectService();
            LOservice.cancel(id);
            btn1.setStyle("-fx-background-image: url('/Resources/Checkmark_26px.png')");
            btn1.setMinWidth(25);
            btn2.setVisible(false);
            btn1.setVisible(true);

        });

        btn4.setOnAction(event -> {
            LostObjectService LOservice = new LostObjectService();
            LOservice.delete(id);
            GUI.LO_MyDmdController l = new GUI.LO_MyDmdController();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_MyAds.fxml"));
                btn4.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LostObject.class.getName()).log(Level.SEVERE, null, ex);
            }

            btn3.setVisible(false);
            btn4.setVisible(false);

        });
        btn5.setOnAction(event -> {
            LostObjectService LOservice = new LostObjectService();
            LOservice.delete(id);
            GUI.LO_MyDmdController l = new GUI.LO_MyDmdController();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/LO_MyDmd.fxml"));
                btn5.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LostObject.class.getName()).log(Level.SEVERE, null, ex);
            }

            btn3.setVisible(false);
            btn5.setVisible(false);

        });

        btn3.setOnAction(event -> {
            Parent root;
            try {
                Session s = new Session();
                s.setId_Lo(id);
                root = FXMLLoader.load(getClass().getResource("/GUI/LO_update.fxml"));
                btn3.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LostObject.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
         btn6.setOnAction(event -> {
            Parent root;
            try {
                Session s = new Session();
                s.setId_Lo(id);
                root = FXMLLoader.load(getClass().getResource("/GUI/LO_sendMail.fxml"));
                btn6.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LostObject.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    public LostObject(int id, String title, String description, String type, Date created_at, Date published_at, Date canceled_at, Boolean enabled, Boolean cancled, int id_user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.created_at = created_at;
        this.published_at = published_at;
        this.canceled_at = canceled_at;
        this.enabled = enabled;
        this.cancled = cancled;
        this.id_user = id_user;
    }

    public LostObject(String title, String description, String type, Date created_at, Date published_at, Date canceled_at, Boolean enabled, Boolean cancled, int id_user) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.created_at = created_at;
        this.published_at = published_at;
        this.canceled_at = canceled_at;
        this.enabled = enabled;
        this.cancled = cancled;
        this.id_user = id_user;
    }

    public LostObject(String title, String description, String type) {

        this.title = title;
        this.description = description;
        this.type = type;
    }

    public LostObject(String title, String description, String type, int id_user) {

        this.title = title;
        this.description = description;
        this.type = type;
        this.id_user = id_user;
    }

    public LostObject(int id, String title, String description, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public LostObject(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Date published_at) {
        this.published_at = published_at;
    }

    public Date getCanceled_at() {
        return canceled_at;
    }

    public void setCanceled_at(Date canceled_at) {
        this.canceled_at = canceled_at;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getCancled() {
        return cancled;
    }

    public void setCancled(Boolean cancled) {
        this.cancled = cancled;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public JFXButton getBtn6() {
        return btn6;
    }

    public void setBtn6(JFXButton btn6) {
        this.btn6 = btn6;
    }

    public JFXButton getBtn1() {
        return btn1;
    }

    public JFXButton getBtn5() {
        return btn5;
    }

    public void setBtn5(JFXButton btn5) {
        this.btn5 = btn5;
    }

    public JFXButton getBtn3() {
        return btn3;
    }

    public void setBtn3(JFXButton btn3) {
        this.btn3 = btn3;
    }

    public JFXButton getBtn4() {
        return btn4;
    }

    public void setBtn4(JFXButton btn4) {
        this.btn4 = btn4;
    }

    public void setBtn1(JFXButton btn1) {
        this.btn1 = btn1;
    }

    public JFXButton getBtn2() {
        return btn2;
    }

    public void setBtn2(JFXButton btn2) {
        this.btn2 = btn2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Object " + id + "{title=" + title + ", description=" + description + ", type=" + type + ", created_at=" + created_at + ", published_at=" + published_at + ", canceled_at=" + canceled_at + ", enabled=" + enabled + ", cancled=" + cancled + ", id_user=" + id_user + '}' + '\n';
    }

}
