package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Books {

    @Id
    private String title;
    private String author;
    private String genre;
    private boolean availability = true;


    @ManyToMany
    private List<Users> user;

    @ManyToOne
    private Branches branch;


    public Books() {
    }

    public Books(String title, String author, String genre, boolean availability, List<Users> user, Branches branch) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.user = user;
        this.branch = branch;

    }
    public Books(String title, String author, String genre, boolean availability) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public Books(String title, String author, String genre, boolean availability, Branches selectedBranch) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.branch = selectedBranch;
    }


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

    public List<Users> getUser() {
        return user;
    }

    public void setUser(List<Users> user) {
        this.user = user;
    }

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }
}
