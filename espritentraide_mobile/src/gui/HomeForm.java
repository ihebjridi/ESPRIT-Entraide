/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import Services.ServiceLostObject;
import Entities.LostObject;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Login;
import com.mycompany.myapp.profile;

/**
 *
 * @author sana
 */
public class HomeForm {

    Form form;
    Button btn_LO;
    Resources theme;

    public HomeForm() {
       
        form = new Form("Esprit Entr'aide", BoxLayout.y());
        btn_LO = new Button("Object-perdu");

        Button profil = new Button("Mon profil");
        Button col = new Button("Collocation");
        Button cov = new Button("Covoiturage");
        Button tut = new Button("Tutorat");
        Button log = new Button("Logout");

        form.add(profil).add(btn_LO).add(col).add(cov).add(tut).add(log);
        btn_LO.addActionListener((e) -> {
            LO_ALLObject allObject = new LO_ALLObject();
            allObject.getForm().show();
        });

        profil.addActionListener((e) -> {
             theme = UIManager.initFirstTheme("/themelog");
            profile p = new profile(theme);
            p.getF().show();
        });
        col.addActionListener((e) -> {

        });
        cov.addActionListener((e) -> {

        });

        tut.addActionListener((e) -> {

        });
        log.addActionListener((e) -> {
             theme = UIManager.initFirstTheme("/themelog");
            Login l = new Login(theme);
            l.getF().show();
        });

    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
