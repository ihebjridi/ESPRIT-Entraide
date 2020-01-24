/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Flatshare;
import Entities.User;
import java.util.List;

/**
 *
 * @author ASUS PC
 */
public interface IFlatshare {

    public void InsertCOL(Flatshare f);

    public List<Flatshare> selectAll();

    public List<Flatshare> selectAllDemande();

    public List<Flatshare> selectAllAnnonce();

    public List<Flatshare> selectMyAnnonce(int id);

    public List<Flatshare> selectMyDemande(int id);

    public void delete(int idAds);

    public void update(Flatshare f);

    public List<Flatshare> searchById(int idAds);

    public List<Flatshare> searchByTitle(String title);

    public List<Flatshare> searchByDescription(String description);

    public void cancel(int idAds);

    public void accept(int idAds);

    public List<Flatshare> admin_selectAll();

    public List<Flatshare> admin_selectAll_Published();

    public List<Flatshare> admin_selectAll_Attente();

    public List<Flatshare> admin_selectAll_Cancled();

    public void update1(int id, Flatshare p);

    public String getUser(int id);

}
