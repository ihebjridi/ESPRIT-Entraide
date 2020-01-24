/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.LostObject;
import Services.LostObjectService;

/**
 *
 * @author Seif Bejaoui
 */
public class Pi1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //LostObject L1 = new LostObject("clé Orange","Salle 32","demande");
        // LostObject L2  = new LostObject("clé TT","Salle c2","annonce");

        LostObjectService service = new LostObjectService();

        //service.InsetLostObject(L1);
        //service.InsetLostObject(L2);
        // LostObject L3  = new LostObject(95,"clé Tunisiana","Salle c2","annonce");
        //service.update(L3);
        //service.delete(0);
        //service.cancel(95);
        //service.accpet(95);
        System.out.println(service.selectAll());
        //System.out.println(service.selectAllAnnonce());
        //System.out.println(service.selectAllDemande());
        //System.out.println(service.searchByTitle("clé"));
        //System.out.println(service.searchByDescription("Salle"));
        //System.out.println(service.searchById(95));
        //System.out.println(service.selectMyAnnonce(7));
        //System.out.println(service.selectMyDemande(7));
        
        
    }

}
