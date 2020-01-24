/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.ServiceLostObject;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;

/**
 *
 * @author Seif Bejaoui
 */
public class LO_AddDmd {

    Form form;
   
       
        
        
        
       
       
    public LO_AddDmd() {
          
        form = new Form("Ajouter une demande",BoxLayout.y());
     

         form.getToolbar().addCommandToSideMenu("Recherche", null, e -> {
          LO_search search = new LO_search();
            search.getForm().show();
        });

        form.getToolbar().addCommandToSideMenu("Toutes les annonces", null, e -> {
            LO_ALLObject allObject = new LO_ALLObject();
            allObject.getForm().show();
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
            this.getForm().show();
        });
        
        form.getToolbar().addCommandToRightBar("Accueil", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getForm().show();
        });
        
        Label f3title = new Label("Titre :");
        TextField txttitle = new TextField("", "Titre", 20, TextField.ANY);
        Label f3description = new Label("Description :");
        TextArea txtdesc = new TextArea(5, 20);
        ServiceLostObject ServiceLO = new ServiceLostObject();
        Button ajouter = new Button("Ajouter");
        Validator v = new Validator();
        v.addConstraint(txttitle, new LengthConstraint(2));
        v.addConstraint(txtdesc, new LengthConstraint(2));
        v.addSubmitButtons(ajouter);

        ajouter.addActionListener(m -> {
            ServiceLO.AddAds(txttitle.getText(), txtdesc.getText(),"demande");
            Dialog.show("Ajout effectué", "Votre demande a été ajouté avec succees", "ok", null);
            LO_MyDmd myads = new LO_MyDmd();
            myads.getForm().show();
        });
        form.add(f3title).add(txttitle).add(f3description).add(txtdesc).add(ajouter);
        form.show();
        
        
    }
    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
