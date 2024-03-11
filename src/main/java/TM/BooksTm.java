package TM;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.example.entity.Branches;
import org.example.entity.Users;

import java.util.List;

public class BooksTm {
    private String title;
    private String author;
    private String genre;
    private boolean availability = true;
    @ManyToOne
    private String branch;

    public BooksTm() {
    }

    public BooksTm(String title, String author, String genre, boolean availability, String branch) {
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
