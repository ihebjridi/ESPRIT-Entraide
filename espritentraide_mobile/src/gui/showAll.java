package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.MyListModel;
import com.mycompany.myapp.MyListModelEvent;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import model.Event;





public class showAll {

    private Form current;
    private Resources theme;
    private String fileName;
    private String filePath;
    Container ctn2;
    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

       
        Toolbar.setGlobalToolbar(true);
       
    }
    
    public void start() throws IOException {
     
        Form f = new Form("Liste des Evenements", BoxLayout.x());
        
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage ajouterIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        FontImage modifierIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT, s);
        FontImage afficherIcon = FontImage.createMaterial(FontImage.MATERIAL_DASHBOARD, s);
        FontImage exitIcon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        Command cAjouter = new Command("Ajouter");
        Command cModifier = new Command("Modifier / Supprimer");
        Command cAfficher = new Command("Afficher");
        Command cExit = new Command("Retour vers acceuil");
        f.getToolbar().addCommandToSideMenu(cAjouter);
        f.getToolbar().addCommandToSideMenu(cModifier);
        f.getToolbar().addCommandToSideMenu(cAfficher);
        f.getToolbar().addCommandToSideMenu(cExit);

        f.addCommandListener(e->{
            if(e.getCommand()==cAjouter){
                new EventApplication().start(theme);
           
            }
           
            if(e.getCommand()==cAfficher){
                try {
                    new showAll().start();
                } catch (IOException ex) {
                }
            }
            if(e.getCommand()==cExit){
                
            Home l = new Home(theme);
            l.getF().show();
            
        
            }
        });
      ArrayList<Event> productsList = getAllm();
        int mm = Display.getInstance().convertToPixels(3);
        final EncodedImage placeholder = EncodedImage.createFromImage(
        FontImage.createMaterial(FontImage.MATERIAL_SYNC, new Style()).
                scaled(300, 300), false);

        String[] listdesproduits = new String[productsList.size()];
        com.codename1.ui.List myList = new com.codename1.ui.List();
        for (int i = 0; i < productsList.size(); i++) {
        listdesproduits[i] = productsList.get(i).getImage();
        }
  
        MyListModelEvent model = new MyListModelEvent(listdesproduits);
        int ii=0;
        
            
            
         ctn2 = new Container();
        for (Event p : productsList) {
             ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Image im = model.getItemAtt(ii, p.getImage());
            ImageViewer v = new ImageViewer(im);
            ctn2.add(v);
            //*******************
//            ctn2.add(new Label("Titre :"+p.getTitre()));
//            ctn2.add(new Label("Description :" + p.getDescription()));
//            ctn2.add(new Label("Lieu :" +p.getLieu()));
//            System.out.println(ctn2.add(new Label("Type :"+p.getType()))) ;
//               ******************************************
            Button moreInfo = new Button("More");
           ctn2.add(moreInfo);
            f.addComponent(ctn2);
            ii++;
            
             moreInfo.addActionListener(e -> {
                 Form Fmore=new Form("Details", BoxLayout.y());
                 Container co= new Container(new BoxLayout(BoxLayout.Y_AXIS));
                     co.add(new Label("Titre :"+p.getTitre()));
          co.add(new Label("Description :" + p.getDescription()));
         co.add(new Label("Lieu :" +p.getLieu()));
          System.out.println(co.add(new Label("Type :"+p.getType()))) ;
          Fmore.add(co);
            Fmore.getToolbar().addCommandToRightBar("back", null, (ev)->{
                showAll h=new showAll();
                try {
               
          h.start();
           } catch (IOException ex) {
                }
          });
          Fmore.show();
                    //new showEvent(p.getId(),p.getType()).start();
                 
            });
             
             /*ShareButton sb = new ShareButton();
             sb.setTextToShare("lol");
             f.add(BorderLayout.CENTER, sb);*/
        }
        
        
        f.setScrollableX(true);
        
        f.show();
            
        
   
    }
    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }
    
    public ArrayList<Event> getAllm() {
        ArrayList<Event> am = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) data.get("produit");
                    am.clear();

                    for (Map<String, Object> obj : list) {
                        Event me = new Event();
                        me.setId(Integer.parseInt(obj.get("id").toString()));
                        me.setTitre(obj.get("titre").toString());
                        me.setDescription(obj.get("description").toString());
                        me.setIdMember(Integer.parseInt(obj.get("idMembre").toString()));
                        me.setLieu(obj.get("lieu").toString());
                        me.setDate(obj.get("dateAdd").toString());
                        me.setImage(obj.get("imageName").toString());
                        me.setType(obj.get("type").toString());
                        am.add(me);
                    }

                } catch (IOException err) {
                    Log.e(err);
                }
            }

        };
        con.setUrl("http://localhost/piService/event/select.php");
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (am == null) {
            return null;
        } else {
            return am;
        }
    }
   
}
