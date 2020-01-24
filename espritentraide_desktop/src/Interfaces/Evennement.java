/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Entities.evennement;
/**
 *
 * @author Nebras
 */

    
public interface Evennement {
    public void insert(evennement e);
    public List<evennement> selectFrontAll();
}
