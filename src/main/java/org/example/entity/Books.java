package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Books {
    @Override
    public String toString() {
        return "Books{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability=" + availability +
                ", user=" + user +
                ", branch=" + branch +
                '}';
    }

    @Id
    private String title;
    private String author;
    private String genre;
    private boolean availability = true;

    @ManyToMany
    private List<User> user;

    @ManyToOne
    private Branches branch;


    public Books() {
    }

    public Books(String title, String author, String genre, boolean availability, List<User> user, Branches branch) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.user = user;
        this.branch = branch;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }
}
