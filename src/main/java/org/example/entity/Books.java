package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column
    private String author;
    @Column
    private String genre;
    @Column
    private boolean availability = true;
    @ManyToMany
    @JoinTable(
            name = "books_users", // Specify the name of the join table
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
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

    public Books(int id, String title, String author, String genre, boolean availability,  Branches branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability=" + availability +
                ", users=" + users +
                ", branch=" + branch +
                '}';
    }
}
