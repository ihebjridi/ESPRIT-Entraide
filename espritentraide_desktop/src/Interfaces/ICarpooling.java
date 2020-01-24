/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Carpooling;
import java.util.List;


public interface ICarpooling {

    public void InsertCarpooling(Carpooling p);
    public List<Carpooling> selectAll();
    public List<Carpooling> selectAllDemande();
    public List<Carpooling> selectAllAnnonce();
    public List<Carpooling> selectMyAnnonce(int id);
    public List<Carpooling> selectMyDemande(int id);
    public void delete(int idAds);
    public void update(Carpooling p);
    public List<Carpooling>  searchById(int idAds);
    public List<Carpooling>  searchByTitle(String title);
    public List<Carpooling>  searchByDescription(String description);
    public void cancel(int idAds);
    public void accept(int idAds);
     public List<Carpooling> admin_selectAll();
     public List<Carpooling> admin_selectAll_publish();
     public List<Carpooling> admin_selectAll_Attente();
     public List<Carpooling> admin_selectAll_Cancled();
    public void update1(int id, Carpooling p);
   
    
    

}
