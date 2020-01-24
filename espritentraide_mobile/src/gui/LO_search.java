/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.LostObject;
import Services.ServiceLostObject;
import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Seif Bejaoui
 */
public class LO_search {

    Form form, f2;
    private ArrayList<LostObject> list;
    Resources theme = UIManager.initFirstTheme("/theme");

    public LO_search() {
        form = new Form("Recherche", BoxLayout.y());

        form.getToolbar().addCommandToSideMenu("Recherche", null, e -> {
             LO_search search = new LO_search();
            search.getForm().show();
        });

        form.getToolbar().addCommandToSideMenu("Toutes les annonces", null, e -> {
            LO_ALLObject all = new LO_ALLObject();
            all.getForm().show();
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

        Label mot = new Label("Mot clé :");
        TextField fmot = new TextField();
        Label ftype = new Label("Type d'annonce :");
        CheckBox annonce = new CheckBox("Objet trouvé");
        CheckBox demande = new CheckBox("Objet perdu");
        Label fdate = new Label("Date de publication :");
        Picker p = new Picker();
        Button recherche = new Button("Chercher");
        form.add(mot).add(fmot).add(ftype).add(annonce).add(demande).add(fdate).add(p).add(recherche);
        form.show();
        recherche.addPointerPressedListener(ed -> {
           LO_result result = new LO_result(fmot.getText(),annonce.isSelected(),demande.isSelected(),p.getDate());
           result.getForm().show();
        });

    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

}
