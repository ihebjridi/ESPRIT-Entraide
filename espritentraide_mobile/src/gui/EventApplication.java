package gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ShareButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;


import com.codename1.io.JSONParser;
import com.codename1.io.services.ImageDownloadService;
import com.codename1.messaging.Message;
import com.codename1.social.FacebookConnect;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Login;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import model.Event;
import model.User;






/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class EventApplication {
         
    private Form current;
    private Resources theme;
    private String fileName;
    private String filePath;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
        
        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
        
    }
    
    public void start(Resources theme) {
        Form f = new Form("Gestion des Evenements", BoxLayout.y());
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage ajouterIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        FontImage modifierIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT, s);
        FontImage afficherIcon = FontImage.createMaterial(FontImage.MATERIAL_DASHBOARD, s);
        FontImage exitIcon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        Command cAjouter = new Command("Ajouter");
       
        Command cAfficher = new Command("Afficher");
        Command cExit = new Command("Retour vers acceuil");
        
        f.getToolbar().addCommandToSideMenu(cAjouter);
        
        f.getToolbar().addCommandToSideMenu(cAfficher);
        f.getToolbar().addCommandToSideMenu(cExit);
        f.addCommandListener(e->{
            if (e.getCommand()== cExit) {
                
            Home l = new Home(theme);
            l.getF().show();
            }
        });
//class user to controle user input
        Validator v = new Validator();
        /*********************************/
        TextField tfTitre = new TextField("", "Titre");
        TextField tfDescription = new TextField("", "Description");
        TextField tfLieu = new TextField("", "Lieu");
        
        
        ComboBox<Map<String, Object>> tfType = new ComboBox<> (
          "Conférance",
          "Gala","Formation,...");
        
        //get current date
        Calendar cal = Calendar.getInstance();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("YYYY:MM:DD/HH:mm:ss");
        String tfDate = sdf.format(cal.getTime());
        
        Label i = new Label();
        i.setWidth(200);
        i.setHeight(200);
        
        f.add(tfTitre);
        f.add(tfDescription);
        f.add(tfLieu);
       
        f.add(tfType);
        CheckBox shareFb = new CheckBox("Partager sur facebook");

        f.add(shareFb);
        Button upload = new Button("Upload");
        Button btnOk = new Button("Ajouter");
        f.add(upload);
        f.add(btnOk);
        
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
               
            }
        });
        btnOk.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                int i = tfType.getSelectedIndex();
  
                Event p = new Event(tfTitre.getText(), tfDescription.getText(), 0,tfLieu.getText(),""+tfType.getModel().getItemAt(i),tfDate,fileName);
                ConnectionRequest req = new ConnectionRequest();
                req.addArgument("image", fileName);
                req.setUrl("http://localhost/piService/event/insert.php?titre=" + p.getTitre() + "&descritpion=" + p.getDescription() + "&lieu=" + p.getLieu() + "&date="+p.getDate()+"&type="+p.getType()+"&idMembre="+User.getIdofuserAlreadyloggedin());
                System.out.println("http://localhost/piService/event/insert.php?titre=" + p.getTitre() + "&descritpion=" + p.getDescription() + "&lieu=" + p.getLieu() + "&date="+p.getDate()+"&type="+p.getType()+"&idMembre="+User.getIdofuserAlreadyloggedin());
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
                if(shareFb.isSelected()){
                    try {                 
                        
                        shareImageOnFacebook(filePath,p.getTitre(),p.getDescription());
                    } catch (IOException ex) {
                    }
                   
                }
                //Send Sms to Subscribers 
                //sendSms("+21623468000");
                //Get All Subs to this type and Send email to them ;)
                ArrayList<String>list = getSubs(p.getType());
                for (String s: list){
                System.err.println("Sending to :"+s);
                sendMail(s);
                }
      
            }
        });
    
         upload.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
               Display.getInstance().openGallery(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (ev != null && ev.getSource() != null) {
                    filePath = (String) ev.getSource();
                    
                    int fileNameIndex = filePath.lastIndexOf("/") + 1;
                    fileName = filePath.substring(fileNameIndex);
                    Image imgg;
                    try {
                        imgg = Image.createImage(filePath);
                        if (imgg!=null) {
                        ImageIO imgIO = ImageIO.getImageIO();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        imgIO.save(imgg, out, ImageIO.FORMAT_JPEG, 1);
                        byte[] ba = out.toByteArray();
                        fileName = Base64.encode(ba);
                            System.err.println(fileName);
                        }
                    } catch (IOException ex) {
                    }
                  
                  
                    
                }
            }
        }, Display.GALLERY_IMAGE);     
                
            }
        });

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
    private void createFileEntry(Form hi, String file) {
   Label fileField = new Label(file);
   Button delete = new Button();
   Button view = new Button();
   FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
   FontImage.setMaterialIcon(view, FontImage.MATERIAL_OPEN_IN_NEW);
   Container content = BorderLayout.center(fileField);
   int size = Storage.getInstance().entrySize(file);
   content.add(BorderLayout.EAST, BoxLayout.encloseX(new Label(size + "bytes"), delete, view));
   delete.addActionListener((e) -> {
       Storage.getInstance().deleteStorageFile(file);
       content.setY(hi.getWidth());
       hi.getContentPane().animateUnlayoutAndWait(150, 255);
       hi.removeComponent(content);
       hi.getContentPane().animateLayout(150);
   });
   view.addActionListener((e) -> {
       try(InputStream is = Storage.getInstance().createInputStream(file)) {
           String s = Util.readToString(is, "UTF-8");
           Dialog.show(file, s, "OK", null);
       } catch(IOException err) {
           Log.e(err);
       }
   });
   hi.add(content);
}
    //function using Multipart request to share product
    public void shareImageOnFacebook(String filename,String titre,String Description) throws IOException{
                            MultipartRequest req = new MultipartRequest();
                            String endpoint;
                            if(FacebookConnect.getInstance().isFacebookSDKSupported()) {
                                endpoint = "EAACEdEose0cBAAN5JBLlbUGwrIztdiZAG4ZALbFpsHixCGk7tRSGa1ydut1AdGgtl3mU7oNJd2Kj3B2qgsrXq0jlz6TDAfVpSZCGSo4ZAKGTZBUi0HkdTubKOWTUIYTJz8deelLvcXfp2mpAiI7Ad7ybqOASP2Hkx3yKfRpth2mmg4DV5rqLAN0bIm1aT7ykSGiTFP1Par4dXQQaspxCy";
                            } else {
                                endpoint = "https://graph.facebook.com/me/photos?access_token=EAACEdEose0cBAAN5JBLlbUGwrIztdiZAG4ZALbFpsHixCGk7tRSGa1ydut1AdGgtl3mU7oNJd2Kj3B2qgsrXq0jlz6TDAfVpSZCGSo4ZAKGTZBUi0HkdTubKOWTUIYTJz8deelLvcXfp2mpAiI7Ad7ybqOASP2Hkx3yKfRpth2mmg4DV5rqLAN0bIm1aT7ykSGiTFP1Par4dXQQaspxCy";
                            }
                            req.setUrl(endpoint);
                            req.addArgument("message","Titre:"+titre+"Description:"+Description);
                            InputStream is = null;
                            is = FileSystemStorage.getInstance().openInputStream(filename);
                            req.addData("source", is, FileSystemStorage.getInstance().getLength(filename), "image/jpeg");
                            NetworkManager.getInstance().addToQueue(req);
                            System.err.println("shared on facebook...");
        }
    public void sendSms(String phone){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/smsService.php?phone="+phone);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.err.println("Sms Sent");
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
    }
    public void sendMail(String Email){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/event/mailService/sendmail.php?email="+Email);
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.err.println("Mail Sent");
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
    }
    public ArrayList<String> getSubs(String type) {
        ArrayList<String> am = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) data.get("subscriber");
                    am.clear();
                    for (Map<String, Object> obj : list) {
                        String me = new String();
                        me=(obj.get("emailMembre").toString());
                        am.add(me);
                    }

                } catch (IOException err) {
                    Log.e(err);
                }
            }

        };
        con.setUrl("http://localhost/piService/event/getSubs.php?type="+type);
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (am == null) {
            return null;
        } else {
            return am;
        }
    }
}
