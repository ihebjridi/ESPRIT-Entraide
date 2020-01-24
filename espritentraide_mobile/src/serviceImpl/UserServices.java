
package serviceImpl;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.User;


public class UserServices {
 public User getUserByID(String json){
User p = null;
      
       try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            Map<String, Object> user = (Map<String, Object>) users.get("user");

           System.err.println(user);
                p = new User();
                p.setId(Integer.parseInt(user.get("id").toString()));
                p.setEmail(user.get("email").toString());
                p.setNom(user.get("username").toString());
               
               
               
                if((user.get("profile_pic").toString()).equals("{}")){
                p.setImagePath(p.getImagePath());
                }else{
                p.setImagePath(user.get("profile_pic").toString());
                }
                
                if((user.get("liste_amis").toString()).equals("{}")){
                p.setListAmis(p.getListAmis());
                }else{
                p.setListAmis(user.get("liste_amis").toString());
                }
          

        } catch (IOException ex) {
         }
       return p;
 }
 public User getUserByIDsecond(String json){
User p = null;
      
       try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            Map<String, Object> user = (Map<String, Object>) users.get("user");

          
                p = new User();
                p.setId(Integer.parseInt(user.get("id").toString()));
                p.setEmail(user.get("email").toString());
                p.setNom(user.get("username").toString());
                p.setPrenom(user.get("prenom").toString());
                if((user.get("mobile_number").toString()).equals("{}")){
                p.setNumTel(p.getNumTel());
                }else{
                p.setNumTel(Integer.parseInt(user.get("mobile_number").toString()));
                }
                p.setAge(Integer.parseInt(user.get("age").toString()));  
                 p.setImagePath((user.get("profile_pic").toString()));  
                
          

        } catch (IOException ex) {
         }
       return p;
 }

    
}