package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import gui.HomeForm;
import model.User;
import serviceImpl.UserServices;

public class Login {

    Form f;

    public Login(Resources theme) {
        UIBuilder uib = new UIBuilder();
        // UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
      
        Container ctn = uib.createContainer(theme, "LoginGUI");
        f = ctn.getComponentForm();
        TextField login = (TextField) uib.findByName("Login", ctn);
        TextField password = (TextField) uib.findByName("password", ctn);
        Button lbl = (Button) uib.findByName("register", ctn);
        Button btn = (Button) uib.findByName("Button", ctn);
        Label lbl5 = (Label) uib.findByName("reset", ctn);
        lbl5.addPointerPressedListener(x -> {
            resetpassword res = new resetpassword(theme);
            res.getF().show();

        });
        lbl.setIcon(FontImage.createMaterial(FontImage.MATERIAL_EXIT_TO_APP, lbl.getUnselectedStyle()));
        lbl.addActionListener(l -> {
            Register r = new Register(theme);
            r.getF().show();

        });

        btn.addActionListener(e -> {
            ConnectionRequest req = new ConnectionRequest();
            req.setUrl("http://localhost/piService/Authentification.php?login=" + login.getText() + "&password=" + password.getText() + "");
            System.out.println("http://localhost/piService/Authentification.php?login=\" + login.getText() + \"&password=\" + password.getText() + \"\");\n");
            req.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    if (s.equals("success")) {
                        User.setMyemail(login.getText());

                        HomeForm h = new HomeForm();
                        h.getForm().show();
                        
                        ConnectionRequest cn = new ConnectionRequest();
                        cn.setUrl("http://localhost/piService/getUserByEmail.php?email=" + login.getText());
                        cn.addResponseListener(new ActionListener<NetworkEvent>() {

                            @Override
                            public void actionPerformed(NetworkEvent evt) {
                                User u = new UserServices().getUserByID(new String(cn.getResponseData()));
                                User.setIdofuserAlreadyloggedin(u.getId());
                            }
                        });
                        NetworkManager.getInstance().addToQueue(cn);

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
