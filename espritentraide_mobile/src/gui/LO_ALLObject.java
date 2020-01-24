/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.LostObject;
import Services.ServiceLostObject;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Seif Bejaoui
 */
public class LO_ALLObject {

    Form form, f2;
    SpanLabel lb;
    private String str;
    private ArrayList<LostObject> list;
    Resources theme = UIManager.initFirstTheme("/theme");

    public LO_ALLObject() {

        form = new Form("Toutes les annonces", BoxLayout.y());
        
         form.getToolbar().addCommandToSideMenu("Recherche", null, e -> {
          LO_search search = new LO_search();
            search.getForm().show();
        });

        form.getToolbar().addCommandToSideMenu("Toutes les annonces", null, e -> {
            this.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Les objets perdus", null, e -> {
            LO_LostObject lostObject = new LO_LostObject();
            lostObject.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Les objets trouves", null, e -> {
            LO_FoundObject foundObject = new LO_FoundObject();
            foundObject.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Mes annonces", null, e -> {
            LO_MyAds myAds = new LO_MyAds();
            myAds.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Mes demandes", null, e -> {
            LO_MyDmd myDmd = new LO_MyDmd();
            myDmd.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Ajouter une annonce", null, e -> {
            LO_AddAds addAds = new LO_AddAds();
            addAds.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Ajouter une demande", null, e -> {
            LO_AddDmd addDmd = new LO_AddDmd();
            addDmd.getForm().show();
        });

        form.getToolbar().addCommandToRightBar("Accueil", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getForm().show();
        });

        ServiceLostObject ServiceLO = new ServiceLostObject();
        list = ServiceLO.getAllObject();
         if(list.size() == 0){
            Container c1 = new Container(new FlowLayout(Component.CENTER));
            Label err = new Label("Pas de resultats");
            c1.add(err);
            form.add(c1);
        }
        for (LostObject t : list) {
            Container c1 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.y());
            //ImageViewer img = new ImageViewer(theme.getImage(t.getImage()));
            ImageViewer img = new ImageViewer(theme.getImage("round.png").fill(75, 75));
            Label l2 = new Label(t.getTitle());
            Label l1 = new Label(t.getDescription());
            c1.getStyle().setBorder(Border.createLineBorder(1));
            c2.add(l2).add(l1);
            c1.add(img).add(c2);
            form.add(c1);
            form.show();
            l1.addPointerPressedListener(e -> {

                EncodedImage enc = EncodedImage.createFromImage(theme.getImage("round.png").fill(150, 150), false);
                Image image = URLImage.createToStorage(enc, "http://localhost/revision/img.png", "http://localhost/revision/img.png");
                ImageViewer img1 = new ImageViewer(image);

                Label type = new Label("**" + t.getType() + "**");
                Label title = new Label("Title : " + t.getTitle());
                Label desc = new Label("Description : " + t.getDescription());
                Label date = new Label("Crée le : " + String.valueOf(t.getCreated_at()));
                Label date1 = new Label("username le : " + t.getUsername());
                f2 = new Form("Details annonce", BoxLayout.y());
                f2.add(img1).add(type).add(title).add(desc).add(date).add(date1);

                f2.getToolbar().addCommandToOverflowMenu("Encoyer mail", null, e3 -> {
                    String msg = "Bonjour, je voudrais vous contacter à propos de l'annonce de "+t.getTitle();
                    Message m = new Message(msg);
                    //m.sendMessageViaCloud("seifeddine.bejaoui@esprit.tn","seifbejaouiii@gmail.com","Seif Bejaoui", t.getTitle(), msg);
                    Display.getInstance().sendMessage(new String[]{t.getUsermail()}, t.getTitle(), m);

                });
                f2.getToolbar().addCommandToOverflowMenu("Back", null, e3 -> {
                    this.getForm().show();
                });
                f2.show();
            });
            c1.setLeadComponent(l1);

        }

    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
