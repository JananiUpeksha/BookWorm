package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Branches {
    @Id
    private String branchName;

    private String location;

    private String branchAdmin;

    @OneToMany(mappedBy = "branch")
    private List<User> users;

    @OneToMany(mappedBy = "branch")
    private List<Books> books;

    public Branches() {
    }

    public Branches(String branchName, String location, String branchAdmin, List<User> users, List<Books> books) {
        this.branchName = branchName;
        this.location = location;
        this.branchAdmin = branchAdmin;
        this.users = users;
        this.books = books;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranchAdmin() {
        return branchAdmin;
    }

    public void setBranchAdmin(String branchAdmin) {
        this.branchAdmin = branchAdmin;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Branches{" +
                "branchName='" + branchName + '\'' +
                ", location='" + location + '\'' +
                ", branchAdmin='" + branchAdmin + '\'' +
                ", users=" + users +
                ", books=" + books +
                '}';
    }
}
