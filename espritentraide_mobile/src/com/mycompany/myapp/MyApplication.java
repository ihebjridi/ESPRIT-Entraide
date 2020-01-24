package com.mycompany.myapp;

import com.codename1.ui.Form;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;

public class MyApplication {

    private Form current;
    private Resources theme, themelog;

   

    public void init(Object context) {

        themelog = UIManager.initFirstTheme("/themelog");
        Toolbar.setGlobalToolbar(true);
    
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }

        Login l = new Login(themelog);
        l.getF().show();

    }

    public void stop() {
    }

    public void destroy() {
    }

}
