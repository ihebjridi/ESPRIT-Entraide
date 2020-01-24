
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

import java.util.Random;
import model.User;
import serviceImpl.UserServices;

public class resetpassword {

    Form f;
    int answer = 0;

    public resetpassword(Resources theme) {

        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "resetpassword");
        f = ctn.getComponentForm();
        f.getToolbar().addMaterialCommandToLeftBar("retour", FontImage.MATERIAL_ARROW_BACK, e -> {
            Login l = new Login(theme);
            l.getF().show();
        });
        
        TextField email = (TextField) uib.findByName("email", ctn);
        Button envoyer = (Button) uib.findByName("envoyer", ctn);
           String token = generateSessionKey(5);
        envoyer.addActionListener(x -> {
            ConnectionRequest req = new ConnectionRequest();
         
            req.setUrl("http://localhost/piService/addtokenforresetpassword.php?email=" + email.getText() + "&token=" + token + "");

            req.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
                         

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        
                ConnectionRequest con = new ConnectionRequest();
                        con.setUrl("http://localhost/piService/getUserByEmail.php?email=" + email.getText());
                        con.addResponseListener(new ActionListener<NetworkEvent>() {

                            @Override
                            public void actionPerformed(NetworkEvent evt) {
                                new UserServices().getUserByID(new String(con.getResponseData())).getNumTel();
                             
                                System.out.println(new UserServices().getUserByID(new String(con.getResponseData())).getNumTel());
                                                              User.setMyemail(email.getText());
                                String accountSid = "AC888e7d0423137d86274fb84425991923";
                                String authToken = "96dd3326b88cc5f24a57bbc6136deb37";
                              //  Twilio.init(accountSid, authToken);
//                                Message message = Message.creator(
//                                        new PhoneNumber("+216" +new UserServices().getUserByID(new String(con.getResponseData())).getNumTel() ),
//                                        new PhoneNumber("+17146768843"),
//                                        "votre code de Récupération de mot de passe " + token
//                                ).create();
//                          
                            }
                        });
                         NetworkManager.getInstance().addToQueue(con);
                          confirmtoken c = new confirmtoken(theme);
                                c.getF().show();
                         Dialog.show("succée", "un code de confirmation vous a été envoyer par mail ", "Ok", null);
                   

                    } else {
                        Dialog.show("Erreur", "erreur : email invalide", "Ok", null);
                    }
                }
            });

            NetworkManager.getInstance().addToQueue(req);

        });

    }

    public static String generateSessionKey(int length) {
        String alphabet
                = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
        int n = alphabet.length(); //10

        String result = new String();
        Random r = new Random(); //11

        for (int i = 0; i < length; i++) //12
        {
            result = result + alphabet.charAt(r.nextInt(n)); //13
        }
        return result;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
      


}
