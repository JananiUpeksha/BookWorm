package org.example.dto;

import jakarta.persistence.Id;

public class BooksDto {
    @Id
    private String title;
    private String author;
    private String genre;
    private boolean availability = true;

    public BooksDto() {
    }

    public BooksDto(String title, String author, String genre, boolean availability) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
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

    @Override
    public String toString() {
        return "BooksDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability=" + availability +
                '}';
    }
}
