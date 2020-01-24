 
package com.mycompany.myapp;

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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import model.User;


public class reset {
     Form f;

    public reset(Resources theme) {

        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "reset");
        TextField password = (TextField) uib.findByName("password", ctn);
         TextField password1 = (TextField) uib.findByName("password1", ctn);
        Button envoyer = (Button) uib.findByName("envoyer", ctn);
          f = ctn.getComponentForm();
          f.getToolbar().addMaterialCommandToLeftBar("retour", FontImage.MATERIAL_ARROW_BACK, e -> {
          Login l = new Login(theme);
          l.getF().show();
        });
        
        envoyer.addActionListener(x -> {
            ConnectionRequest req = new ConnectionRequest();
         
            req.setUrl("http://localhost/piService/reset.php?email=" + User.getMyemail() + "&password=" +password.getText() + "");

            req.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                      Dialog.show("succée", "votre mot de passe est mis à jour ", "Ok", null);
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
