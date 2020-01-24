/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author ASUS PC
 */
public class Flatshare {

    private int id;
    private String title;
    private String description;
    private String type;
    private Date created_at;
    private Date published_at;
    private Date canceled_at;
    private Boolean enabled;
    private Boolean cancled;
    private int id_user;

    public Flatshare() {
    }

    public Flatshare(int id, int id_user, String title, String description, String type, Date created_at, Date published_at, Date canceled_at, Boolean enabled, Boolean cancled) {
        this.id = id;
        this.id_user = id_user;
        this.title = title;
        this.description = description;
        this.type = type;
        this.created_at = created_at;
        this.published_at = published_at;
        this.canceled_at = canceled_at;
        this.enabled = enabled;
        this.cancled = cancled;

    }

    public Flatshare(String title, String description, String type, Date created_at, Date published_at, Date canceled_at, Boolean enabled, Boolean cancled, int id_user) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.created_at = created_at;
        this.published_at = published_at;
        this.canceled_at = canceled_at;
        this.enabled = enabled;
        this.cancled = cancled;
        this.id_user = id_user;
    }

    public Flatshare(String title, String description, String type) {
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public Flatshare(String title, String description, String type, int id_user) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.id_user = id_user;
    }

    public Flatshare(int id, String title, String description, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Flatshare{" + "id=" + id + ", title=" + title + ", description=" + description + ", type=" + type + ", created_at=" + created_at + ", published_at=" + published_at + ", canceled_at=" + canceled_at + ", enabled=" + enabled + ", cancled=" + cancled + ", id_user=" + id_user + '}';
    }

}
