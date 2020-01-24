/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;
import java.util.Objects;
import javax.swing.text.html.HTML.Tag;

/**
 *
 * @author Mohamed
 */
public class Question {
    private int id;
    private String title;
    private int created_at;
    private User user;
    private List<Tag> tags;
    private List<Answer> answers;

    public Question() {
    }

    public Question(int id, String title, int created_at, User user, List<Tag> tags, List<Answer> answers) {
        this.id = id;
        this.title = title;
        this.created_at = created_at;
        this.user = user;
        this.tags = tags;
        this.answers = answers;
    }

    public Question(int id, String title, int created_at, User user, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.created_at = created_at;
        this.user = user;
        this.tags = tags;
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

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + this.created_at;
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + Objects.hashCode(this.tags);
        hash = 47 * hash + Objects.hashCode(this.answers);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Question other = (Question) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.created_at != other.created_at) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        if (!Objects.equals(this.answers, other.answers)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", title=" + title + ", created_at=" + created_at + ", user=" + user + ", tags=" + tags + ", answers=" + answers + '}';
    }
    
    
    
    
}
