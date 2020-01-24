package gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.Home;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import model.Event;






public class showEvent {

    private Form current;
    private Resources theme;
    private String fileName;
    private String filePath;
    private int productId;
    private int likes;
    private String type;
    boolean liked=false;
    boolean subscribed=false;
    String[] keysToImages = {"1ads", "2wdasd", "3zxczxc", "4ada", "5gfddfg","6hgfgh","7cbv ","8czxzcx","9asas","dadasas","cxzc","qweqwd","fsdsdfsfd","cxv","hyuju","kui","rere","wedw","sdf","wedr","ryrhjy","juyjy","ewrw","scvdhl","1aads", "2wdwqasd", "c3zxczxc", "4advcxa", "5gfddhfg","6hgfgtyhh","7cbmgfv ","8czxfbzcx","9adfsas","dadasreas","cxzwec","qwesdqwd","fsdsdwefsfd","cxvv","hyugfju","kutrrti","rerwee","wbedw","srtdf","wedweqr","ryrdshjy","jucxyjy","ewvrw","scwevdhl"};
    String[] keysToIcons = {"1adas", "2wdawasd", "3zxcqzxc", "4acda", "5gfdvxdfg","6hgdfgh","7cewbv ","8czxqcx","9asdas","dadaxcvsas","cxzfdc","qweqerwd","fsdsdfsqwfd","cxdsv","hyufju","kufgi","reewre","wedqww","sddsaf","wezxdr","ryrzxhjy","jucyjy","evwrw","scvewdhl"};

    Random r = new Random();
    Random y = new Random();
    public showEvent(int id,String type) {
        this.productId = id ;
        this.type = type;
        checkIfLiked(productId, 1);
        System.err.println("type ="+type);
        checkIfSubscribed(1, type);
        System.err.println(liked);
    }
    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
        
       
        Toolbar.setGlobalToolbar(true);
        
        
    }
    
    public void start() throws IOException {
        Form f = new Form("Informations de Evenement", new BorderLayout());
        
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage ajouterIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        FontImage modifierIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT, s);
        FontImage afficherIcon = FontImage.createMaterial(FontImage.MATERIAL_DASHBOARD, s);
        FontImage exitIcon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        Command cAjouter = new Command("Ajouter");
        // cModifier = new Command("Modifier / Supprimer");
        Command cAfficher = new Command("Afficher");
        Command cExit = new Command("Exit");
        f.getToolbar().addCommandToSideMenu(cAjouter);
        //f.getToolbar().addCommandToSideMenu(cModifier);
        f.getToolbar().addCommandToSideMenu(cAfficher);
        f.getToolbar().addCommandToSideMenu(cExit);
        Event p = getProduct(productId);
        
 
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
            //lets check how many likes we have in this product 
            checkLikes(p.getId());
            
            int mm = Display.getInstance().convertToPixels(3);
            final EncodedImage placeholder = EncodedImage.createFromImage(
            FontImage.createMaterial(FontImage.MATERIAL_SYNC, new Style()).
                scaled(300, 300), false);
            final EncodedImage icon = EncodedImage.createFromImage(
            FontImage.createMaterial(FontImage.MATERIAL_SYNC, new Style()).
                scaled(30, 30), false);
            final EncodedImage iconn = EncodedImage.createFromImage(
            FontImage.createMaterial(FontImage.MATERIAL_SYNC, new Style()).
                scaled(30, 30), false);
            Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            System.err.println("specific image name:"+p.getImage());
            Image im = URLImage.createToStorage(placeholder,keysToImages[r.nextInt(keysToImages.length)],"http://localhost/piService/livre/"+p.getImage());
            ImageViewer v = new ImageViewer(im);
            //add likes number
            Label likeL = new Label("Likes :"+this.likes);
            Container rateCtn = new Container();
            rateCtn.setLayout(new BorderLayout());
            rateCtn.add(BorderLayout.EAST,likeL);
            ctn2.add(rateCtn);
            ctn2.add(v);
            //Ctn3 & Ctn4 containes title and like/dislike button
            Container ctn3 = new Container(new BoxLayout(BoxLayout.X_AXIS)); //ctn to show when product not liked already
            Container ctn4 = new Container(new BoxLayout(BoxLayout.X_AXIS)); //ctn to show when product already liked
            //like&dislike button & icon
            ctn3.add(new Label(p.getTitre()));
            ctn4.add(new Label(p.getTitre()));

            Image likeimg = URLImage.createToStorage(icon,keysToIcons[r.nextInt(keysToIcons.length)],"http://localhost/piService/event/icons/icon.png");
            Button likebtn = new Button(likeimg);
            
            likebtn.setUIID("likeB");
            likebtn.getAllStyles().setMarginLeft(260);
            //dislike
            Image dislikeimg = URLImage.createToStorage(iconn,keysToIcons[y.nextInt(keysToIcons.length)],"http://localhost/piService/event/icons/iconLiked.png");
            Button dislikebtn = new Button(dislikeimg);
            dislikebtn.setUIID("dislikeB");
            dislikebtn.getAllStyles().setMarginLeft(290);
           
            ctn3.add(likebtn);//like container
            ctn3.setScrollableX(true);
            ctn4.add(dislikebtn);//dislike container
            ctn4.setScrollableX(true);
            //check if user already liked the product 
            //and show like btn if false
            checkIfLiked(productId, 1);
            if (liked == false){
             ctn2.add(ctn3);
            }
            else {
             ctn2.add(ctn4);
            }
            //*************************//

            
            ctn2.add(new Label(p.getDescription()));
            ctn2.add(new Label("Lieu : "+p.getLieu()));
            Label dt = new Label(p.getDate());
            Container dateCtn = new Container();
            dateCtn.setLayout(new BorderLayout());
            dateCtn.add(BorderLayout.EAST,dt);
            ctn2.add(dateCtn);
            ctn2.setScrollableY(true);
            f.addComponent(BorderLayout.CENTER,ctn2);
            CheckBox subscribeBtn = new CheckBox("subscribe");
            System.err.println(subscribed);
            if(subscribed == true)
                {
                subscribeBtn.setSelected(true);
                }
            else
                {
                subscribeBtn.setSelected(false);
                }
            ctn2.add(subscribeBtn);
            //Listen to Like Action //
            likebtn.addActionListener(e->{
                likeProduct(p.getId(),1);
                checkLikes(p.getId());
                likeL.setText("Likes :"+this.likes);
                ctn2.replace(ctn3, ctn4, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 200));
            });
            dislikebtn.addActionListener(e->{
                dislikeProduct(p.getId(),1);
                checkLikes(p.getId());
                likeL.setText("Likes :"+this.likes);
                ctn2.replace(ctn4, ctn3, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, 200));
            });
            subscribeBtn.addActionListener(e->{
                if(subscribeBtn.isSelected())
                    {
                        subscribe(1, "mouhamed.foudhaili@esprit.tn", p.getType());
                    }
                else {
                        unsubscribe(1, p.getType());
                }
            });
            f.setScrollableY(true);
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
     public Event getProduct(int id ) {
        Event am = new Event();

        ConnectionRequest con = new ConnectionRequest() {
            protected void readResponse(InputStream in) throws IOException {
                
                 JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    Map<String, Object> obj = (Map<String, Object>) data.get("produit");
 
                        am.setId(Integer.parseInt(obj.get("id").toString()));
                        am.setTitre(obj.get("titre").toString());
                        am.setDescription(obj.get("description").toString());
                        am.setIdMember(Integer.parseInt(obj.get("idMembre").toString()));
                        am.setLieu(obj.get("lieu").toString());
                        am.setDate(obj.get("dateAdd").toString());
                        am.setImage(obj.get("imageName").toString());
                        am.setType(obj.get("type").toString());
                    
                } catch (IOException err) {
                    Log.e(err);
                }
            }
        };
        con.setUrl("http://localhost/piService/event/getById.php?id="+id);
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return am;
    }
     public void likeProduct(int idP,int idM){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/like.php?idP="+idP+"&idM="+idM);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        checkIfLiked(idP, idM);
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
     public void dislikeProduct(int idP,int idM){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/dislike.php?idP="+idP+"&idM="+idM);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        checkIfLiked(idP, idM);
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
     public void checkIfLiked(int idP,int idM){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/checkIfLiked.php?idP="+idP+"&idM="+idM);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        int x = Integer.parseInt(s);
                        System.err.println(x);
                        if(x>0)
                        {
                        liked=true;
                        }
                        else liked=false;
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
     public void checkLikes(int idP){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/checkLikes.php?idP="+idP);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        int x = Integer.parseInt(s);
                        System.err.println("Total Likes = "+x);
                        likes=x;
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
    
   public void subscribe(int idMembre,String Email,String type){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/subscribe.php?idMembre="+idMembre+"&type="+type+"&email="+Email);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        Dialog.show("Confirmation", "Subscribed !", "Ok", null);
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
    public void unsubscribe(int idMembre,String type){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/unsubscribe.php?idMembre="+idMembre+"&type="+type);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        Dialog.show("Confirmation", "Unsubscribe !", "Ok", null);
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
    public void checkIfSubscribed(int idMembre,String Type){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/checkIfSubscribed.php?idMembre="+idMembre+"&type="+Type);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        int x = Integer.parseInt(s);
                        System.err.println(x);
                        if(x>0)
                        {
                        subscribed=true;
                        }
                        else subscribed=false;
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
}
