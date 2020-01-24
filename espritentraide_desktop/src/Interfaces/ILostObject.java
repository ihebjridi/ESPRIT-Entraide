/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.LostObject;
import java.util.List;

/**
 *
 * @author Seif Bejaoui
 */
public interface ILostObject {

    public void InsetLostObject(LostObject p);

    public List<LostObject> selectAll();

    public List<LostObject> selectAllDemande();

    public List<LostObject> selectAllAnnonce();

    public List<LostObject> selectMyAnnonce(int id);

    public List<LostObject> selectMyDemande(int id);

    public LostObject getLostObject(int id);

    public void delete(int idAds);

    public void update(LostObject p);

    public List<LostObject> searchById(int idAds);

    public List<LostObject> searchByTitle(String idAds, String type);

    public List<LostObject> searchByDescription(String idAds, String type);

    public List<LostObject> searchByUser(String idAds, String type);

    public List<LostObject> searchMineByTitle(String idAds, String type);

    public List<LostObject> searchMineByDescription(String idAds, String type);

    public List<LostObject> searchAdminByTitle(String idAds, String type);

    public List<LostObject> searchAdminByDescription(String idAds, String type);

    public List<LostObject> searchAdminByUser(String idAds, String type);

    public void cancel(int idAds);

    public void accpet(int idAds);

    public List<LostObject> admin_selectAll();

    public List<LostObject> admin_selectAll_trier();

    public List<LostObject> admin_selectAll_publish();

    public List<LostObject> admin_selectAll_Attente();

    public List<LostObject> admin_selectAll_Cancled();

}
