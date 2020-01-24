package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
//import gui.GroupFrom;
import gui.EventApplication;
import java.io.IOException;

import model.User;

import serviceImpl.UserServices;


public class Home {

    Form f;
    EncodedImage encoded;

    public Home(Resources theme) {

        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "MainMenu");
        f = ctn.getComponentForm();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/getUserByEmail.php?email=" + User.getMyemail());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    encoded = EncodedImage.create("/load.png");
                    
                } catch (IOException ex) {

                }

                
                Image imgServer = URLImage.createToStorage(encoded, new UserServices().getUserByID(new String(con.getResponseData())).getEmail(), "http://localhost/piService/images/" + new UserServices().getUserByID(new String(con.getResponseData())).getImagePath());
                Image profilePic = imgServer;
                Image mask = theme.getImage("profile.png");
                mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
                profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
                Label profilePicLabel = new Label(profilePic);
                profilePicLabel.setIcon(mask);

                Container sidemenuTop = BorderLayout.center(profilePicLabel);
                sidemenuTop.setUIID("SidemenuTop");

                f.getToolbar().addComponentToSideMenu(sidemenuTop);
                f.getToolbar().addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_DASHBOARD, e -> {
            Home h = new Home(theme);
            h.getF().show();
            
        });
           
        f.getToolbar().addMaterialCommandToSideMenu("Mon profil", FontImage.MATERIAL_FOLDER, e -> {
            profile p = new profile(theme);
            p.getF().show();
        });
   
        f.getToolbar().addMaterialCommandToSideMenu("Object-perdu", FontImage.MATERIAL_STORE, e -> {
                   
                       new EventApplication().start(theme);

        }); 
        f.getToolbar().addMaterialCommandToSideMenu("Collocation", FontImage.MATERIAL_STORE, e -> {
                   
                       new EventApplication().start(theme);

        }); 
        f.getToolbar().addMaterialCommandToSideMenu("Covoiturage", FontImage.MATERIAL_STORE, e -> {
                   
                       new EventApplication().start(theme);

        }); 
        f.getToolbar().addMaterialCommandToSideMenu("Tutorat", FontImage.MATERIAL_STORE, e -> {
                   
                       new EventApplication().start(theme);

        });
        
      
        
        f.getToolbar().addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_STORE, e -> {
                   
                       new EventApplication().start(theme);

        });

        f.getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {

            Login l = new Login(theme);
            l.getF().show();

        });
        f.refreshTheme();

        f.getToolbar().addCommandToOverflowMenu("Se deconnecter", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });

            }
        });
        NetworkManager.getInstance().addToQueue(con);

        

    }   public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
