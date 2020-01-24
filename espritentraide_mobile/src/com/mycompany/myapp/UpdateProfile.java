
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import static com.codename1.io.Log.p;
import static com.codename1.io.Log.p;
import static com.codename1.io.Log.p;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import model.User;
import serviceImpl.UserServices;

public class UpdateProfile {

    Form f;
    String Imagecode;
    String filePath;

    public UpdateProfile(Resources theme) {
        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "UpdateProfile");
        f = ctn.getComponentForm();
        TextField nom = (TextField) uib.findByName("Nom", ctn);
        TextField lastname = (TextField) uib.findByName("lastname", ctn);
        TextField age = (TextField) uib.findByName("age", ctn);
        TextField phonenumber = (TextField) uib.findByName("phonenumber", ctn);
        Button b1 = (Button) uib.findByName("Button", ctn);
        Button b2 = (Button) uib.findByName("Addimage", ctn);

        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/piService/getUserByEmail.php?email=" + User.getMyemail());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                nom.setText(new UserServices().getUserByID(new String(con.getResponseData())).getNom());
                lastname.setText(new UserServices().getUserByID(new String(con.getResponseData())).getPrenom());
                age.setText(new UserServices().getUserByID(new String(con.getResponseData())).getAge() + "");
                phonenumber.setText(new UserServices().getUserByID(new String(con.getResponseData())).getNumTel() + "");

            }
        });
        NetworkManager.getInstance().addToQueue(con);
    
           f.getToolbar().addMaterialCommandToLeftBar("retour", FontImage.MATERIAL_ARROW_BACK, e -> {
            profile p = new profile(theme);
                p.getF().showBack();
        });
        b2.addActionListener(x -> {

            ActionListener callback = e -> {
                if (e != null && e.getSource() != null) {
                    filePath = (String) e.getSource();

                }
            };
            if (FileChooser.isAvailable()) {
                FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);

           }
        else {
                Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
            }
        });
        b1.addActionListener(d -> {
          /*  ImageIO imgIO = ImageIO.getImageIO();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                imgIO.save(Image.createImage(filePath), out, ImageIO.FORMAT_JPEG, 1);

                byte[] ba = out.toByteArray();
                Imagecode = Base64.encode(ba);
//System.out.println("data :"+Imagecode);

            } catch (IOException ex) {

            }*/

            ConnectionRequest req = new ConnectionRequest();
            req.setUrl("http://localhost/piService/UpdateProfile.php?nom=" + nom.getText() + "&prenom=" + lastname.getText() + "&age=" + age.getText() + "&mobile_number=" + phonenumber.getText() + "&email=" + User.getMyemail());
            ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void handleErrorResponseCode(int code, String message) {
                   // System.out.println("Code :" + code + " Msg :" + message);
                }

            };
            request.setPost(true);
            request.setHttpMethod("POST");
            request.addArgument("Image", Imagecode);
            request.addArgument("email", User.getMyemail());
            request.setUrl("http://localhost/piService/movefile.php");
            NetworkManager.getInstance().addToQueueAndWait(request);
            req.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        Dialog.show("success", "Profil  est mis à jour ", "Ok", null);
                        f.refreshTheme();
                    } else {
                        Dialog.show("Erreur", "erreur", "Ok", null);
                    }
                }
            });

            NetworkManager.getInstance().addToQueue(req);
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
