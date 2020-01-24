
package com.mycompany.myapp;


import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

import java.util.Random;
import model.User;

public class confirmtoken {

    Form f;

    public confirmtoken(Resources theme) {

        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "confirmtoken");
       TextField email = (TextField)uib.findByName("email", ctn);
        Button envoyer = (Button) uib.findByName("envoyer", ctn);
        envoyer.addActionListener(x -> {
            ConnectionRequest req = new ConnectionRequest();
         
            req.setUrl("http://localhost/piService/confirmtoken.php?email=" + User.getMyemail() + "&token=" +email.getText() + "");

            req.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                       reset  r = new reset(theme);
                       r.getF().show();
                    } else {
                        Dialog.show("Erreur", "erreur", "Ok", null);
                    }
                }
            });

            NetworkManager.getInstance().addToQueue(req);

        });

        f = ctn.getComponentForm();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}

