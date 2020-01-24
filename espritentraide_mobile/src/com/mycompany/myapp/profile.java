package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import javafx.scene.image.ImageView;
import model.User;
import serviceImpl.UserServices;


public class profile {

    Form f;

    EncodedImage encoded;

    public profile(Resources theme) {

        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "Profile");
        f = ctn.getComponentForm();
        f.refreshTheme();
        Label nom = (Label) uib.findByName("nom", ctn);
        Label image = (Label) uib.findByName("image", ctn);
        Label email = (Label) uib.findByName("email", ctn);
        Button update = (Button) uib.findByName("UpdateProfile", ctn);
        ImageViewer imagev = (ImageViewer) uib.findByName("ImageViewer", ctn);

        ConnectionRequest con = new ConnectionRequest();

        try {
            encoded = EncodedImage.create("/load.png");
            // Pro only feature, uncomment if you have a pro subscription
            // Log.bindCrashProtection(true);
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.setUrl("http://localhost/piService/getUserByEmail.php?email=" + User.getMyemail());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                nom.setText(new UserServices().getUserByID(new String(con.getResponseData())).getNom());
                email.setText(new UserServices().getUserByID(new String(con.getResponseData())).getEmail());
                Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                FontImage p = FontImage.createMaterial(FontImage.MATERIAL_ADD_CIRCLE_OUTLINE, s);
                EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 5, p.getHeight() * 5), false);
Image imgServer = URLImage.createToStorage(placeholder, new UserServices().getUserByID(new String(con.getResponseData())).getEmail(), "http://localhost/piService/images/" + new UserServices().getUserByID(new String(con.getResponseData())).getImagePath());

                System.out.println(new UserServices().getUserByID(new String(con.getResponseData())).getImagePath());
                image.setIcon(imgServer.scaled(600, 800));

            }
        });
        NetworkManager.getInstance().addToQueue(con);
        f.getToolbar().addCommandToOverflowMenu("Retour vers le menu principal", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Home h = new Home(theme);
                h.getF().showBack();
            }
        });

        update.addActionListener(x -> {
            UpdateProfile up = new UpdateProfile(theme);
            up.getF().show();

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
