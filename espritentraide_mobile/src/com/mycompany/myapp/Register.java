
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;


public class Register {
       Form f ;
       public  Register(Resources theme){
           
          UIBuilder uib = new UIBuilder();
       Container ctn = uib.createContainer(theme,"Registration");
    f = ctn.getComponentForm();
     TextField nom = (TextField)uib.findByName("Nom",ctn);
     TextField email = (TextField)uib.findByName("Email",ctn);
     TextField password1 = (TextField)uib.findByName("password1",ctn);
     TextField password2 = (TextField)uib.findByName("password2",ctn);
     TextField numtel = (TextField)uib.findByName("numtel",ctn);
     Button b1 = (Button)uib.findByName("Button",ctn);
    
    b1.addActionListener(l->{
         if(nom.getText().equals("")||email.getText().equals("")||password1.getText().equals("")||password2.getText().equals("")){
         Dialog.show("Erreur", "Veuiller remplir tous les champs", "Ok", null);
     }else if(!password1.getText().equals(password2.getText())){
       Dialog.show("Erreur", "Les deux mots de passes ne sont pas identiques", "Ok", null);   
     }
           ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/Registration.php?nom=" + nom.getText() + "&email=" + email.getText() + "&password="+ password1.getText()+"&numtel="+numtel.getText());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                        Dialog.show("success", "inscription avec succée", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
 
                NetworkManager.getInstance().addToQueue(req);
    
    }); 
    
        
    f.getToolbar().addCommandToOverflowMenu("Retour vers authentification", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
        Login login =  new Login(theme);
        login.getF().showBack();
         }
     });
       }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
       
}
