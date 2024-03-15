package org.example.dto;

import jakarta.persistence.Id;
import org.example.entity.Books;
import org.example.entity.Branches;

public class BooksDto {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean availability = true;
    private String branch;

    public BooksDto(int id, String title, String author, String genre, boolean availability, String branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }


    public BooksDto() {
    }

    /*public BooksDto(String title, String author, String genre, boolean availability,String selectedBranch) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.selectedBranch = selectedBranch;
    }*/
    public BooksDto(String title, String author, String genre, boolean availability) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    // Constructor with selectedBranch parameter
    public BooksDto(String title, String author, String genre, boolean availability, String branch) {
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

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


    @Override
    public String toString() {
        return "BooksDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability=" + availability +
                ", branch='" + branch + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailability() {
        return availability;
    }
}
