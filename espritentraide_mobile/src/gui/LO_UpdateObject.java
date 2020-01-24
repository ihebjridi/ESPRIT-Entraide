/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Seif Bejaoui
 */
public class LO_UpdateObject {

    Form form;

    public LO_UpdateObject() {

        form = new Form("Les objets perdus",BoxLayout.y());

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
            LO_AddDmd addDmd = new LO_AddDmd();
            addDmd.getForm().show();
        });

//        lb = new SpanLabel("");
//        form.add(lb);
//        ServiceLostObject serviceTask = new ServiceLostObject();
//        ArrayList<LostObject> lis = serviceTask.getList2();
//        lb.setText(lis.toString());
        form.getToolbar().addCommandToRightBar("Accueil", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getForm().show();
        });
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
