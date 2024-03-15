package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Branches {
    @Id
    private String branchName;

    private String location;

    private String branchAdmin;

  @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
    private List<Users> users;

    @OneToMany(mappedBy = "branch")
    private List<Books> books;
    /*@OneToMany(mappedBy = "branch", fetch = FetchType.EAGER) // Fetch the books eagerly
    private List<Books> books;
    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    private List<Users> users;*/


    public Branches() {
    }

    public Branches(String branchName, String location, String branchAdmin, List<Users> users, List<Books> books) {
        this.branchName = branchName;
        this.location = location;
        this.branchAdmin = branchAdmin;
        this.users = users;
        this.books = books;
    }

    public Branches(String branchName, String location, String branchAdmin) {
        this.branchName = branchName;
        this.location = location;
        this.branchAdmin = branchAdmin;
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

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    /*@Override
    public String toString() {
        return "Branches{" +
                "branchName='" + branchName + '\'' +
                ", location='" + location + '\'' +
                ", branchAdmin='" + branchAdmin + '\'' +
                ", users=" + users +
                ", books=" + books +
                '}';
    }*/
    @Override
    public String toString() {
        return "Branches{" +
                "branchName='" + branchName + '\'' +
                ", location='" + location + '\'' +
                ", branchAdmin='" + branchAdmin + '\'' +
                ", users=" + (users != null ? users.size() : "not initialized") + // Check if users is null before accessing it
                ", books=" + (books != null ? books.size() : "not initialized") + // Check if books is null before accessing it
                '}';
    }

}
