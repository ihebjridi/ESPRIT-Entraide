
package model;



import java.io.Serializable;
import java.util.ArrayList;



public class User implements Serializable{
    private static int id ; 
    private String  nom ; 
    private String  prenom ;
    private int age;
    private String Descrption;
    private String password;
    private int enabled;
    private String Role ;
    private String ImagePath;
    private int numTel;
    private String listinvitation;
    private String listAmis;
    private String email; 
    private static int idofuserAlreadyloggedin;
    private static String myemail;
    private String username;
    private String profil_pic;
    private String pays;



     public User(int id) {
        this.id = id;
    }

    public User(int id, String username, String email,int age, String nom, String prenom, String profil_pic) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profil_pic = profil_pic;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public User(int id, String username, String profil_pic) {
        this.id = id;
        this.username = username;
        this.profil_pic = profil_pic;
    }

    
    public User(String username, String email, String profil_pic) {
        this.username = username;
        this.email = email;
        this.profil_pic = profil_pic;
    }

  

    public User() {
    }

    public User(String name, String email) {
        this.nom = name;
        this.email = email;
    }


    public User(String name, String password, String email,String Role) {
        this.nom = name;
        this.password = password;
        this.email = email;
        this.Role=Role;
    }

     
    public User(String nom, String prenom, int age, String Descrption, String password, String email,int numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.Descrption = Descrption;
        this.password = password;
        this.email = email;
        this.numTel=numtel;
    }
 
      public User(int id ,String nom, String prenom, int age, String Descrption, String password, String email,String Role,int numTel) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.Descrption = Descrption;
        this.password = password;
        this.email = email;
        this.Role=Role;
        this.numTel=numTel;
    }
         public User(int id ,String nom, String prenom, int age, String Descrption,int enabled ,String password, String email,String Role,String listeamis,String imagePath) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.Descrption = Descrption;
        this.enabled=enabled;
        this.password = password;
        this.email = email;
        this.Role=Role;
        this.listAmis=listeamis;
        this.ImagePath=imagePath;
       
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String name) {
        this.nom = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getListinvitation() {
        return listinvitation;
    }

    public void setListinvitation(String listinvitation) {
        this.listinvitation = listinvitation;
    }

    public String getListAmis() {
        return listAmis;
    }

    public void setListAmis(String listAmis) {
        this.listAmis = listAmis;
    }

    public static int getIdofuserAlreadyloggedin() {
        return idofuserAlreadyloggedin;
    }

    public static void setIdofuserAlreadyloggedin(int idofuserAlreadyloggedin) {
        User.idofuserAlreadyloggedin = idofuserAlreadyloggedin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescrption() {
        return Descrption;
    }

    public void setDescrption(String Descrption) {
        this.Descrption = Descrption;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public static String getMyemail() {
        return myemail;
    }

    public static void setMyemail(String myemail) {
        User.myemail = myemail;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }
 
    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfil_pic() {
        return profil_pic;
    }

    public void setProfil_pic(String profil_pic) {
        this.profil_pic = profil_pic;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", Descrption=" + Descrption + ", password=" + password + ", enabled=" + enabled + ", Role=" + Role + ", ImagePath=" + ImagePath + ", numTel=" + numTel + ", listinvitation=" + listinvitation + ", listAmis=" + listAmis + ", email=" + email + ", username=" + username + ", profil_pic=" + profil_pic + ", pays=" + pays + '}';
    }

    

  





   
   
    
}
