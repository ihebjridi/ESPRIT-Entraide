/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Oussama
 */
public class Commentaire {
    private int id;
    private int id_user;
    private String text;
    private int id_event;
    
    public Commentaire(int id, String text, int id_user, int id_event) {
        this.id = id;
        this.id_user = id_user;
        this.id_event = id_event;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", id_user=" + id_user + ", id_event=" + id_event + ", text=" + text + '}';
    }
    

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
   
}
