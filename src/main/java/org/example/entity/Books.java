package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Books {

    @Id
    private String title;
    @Column
    private String author;
    @Column
    private String genre;
    private boolean availability = true;
    @ManyToMany
    @JoinTable(
            name = "books_users", // Specify the name of the join table
            joinColumns = @JoinColumn(name = "book_title") // Column name in the join table for Books
           // inverseJoinColumns = @JoinColumn(name = "user_id") // Column name in the join table for Users
    )
    private List<Users> users;

    @ManyToOne
    private Branches branch;


    public Books() {
    }

    public Books(String title, String author, String genre, boolean availability, List<Users> users, Branches branch) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.users = users;
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
                ", user=" + users +
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
        return users;
    }

    public void setUser(List<Users> user) {
        this.users = user;
    }

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }
}
