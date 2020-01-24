/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Nebras
 */
public class evennement {

    private int id;
    private int id_user;
    private String title;
    private String lieu;
    private Date date;
    private String description;
    private Date created_at;
    private Date published_at;
    private Date canceled_at;
    private Boolean enabled ;
    private Boolean cancled;
    private int rating;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Date published_at) {
        this.published_at = published_at;
    }

    public Date getCanceled_at() {
        return canceled_at;
    }

    public void setCanceled_at(Date canceled_at) {
        this.canceled_at = canceled_at;
    }

   

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getCancled() {
        return cancled;
    }

    public void setCancled(Boolean cancled) {
        this.cancled = cancled;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "evennement{" + "id=" + id + ", id_user=" + id_user + ", title=" + title + ", lieu=" + lieu + ", date=" + date + ", description=" + description + ", created_at=" + created_at + ", published_at=" + published_at + ", canceled_at=" + canceled_at + ", enabled=" + enabled + ", cancled=" + cancled + ", rating=" + rating + '}';
    }

    public evennement() {
        
    }
    
    
     public evennement(int id, String title, Date date, String lieu, String description,
             Date created_at,Date published_at,Date canceled_at,boolean enabled,boolean cancled,int id_user) {
        this.id = id;
        this.title = title;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.enabled = enabled;
        this.cancled = cancled;
        this.created_at = created_at;
        this.published_at = published_at;
        this.canceled_at = canceled_at;
        this.id_user = id_user;

    }
    
    
}
