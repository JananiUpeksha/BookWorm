package org.example.entity;

import jakarta.persistence.*;
import org.example.dto.UserDto;

import java.util.List;

@Entity
public class Users  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String password;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    /*@JoinTable(
            name = "Books_Users",
            joinColumns =  @JoinColumn(name = "name"),
            inverseJoinColumns = @JoinColumn(name = "title")
    )*/
    private List<Books> books;

    @ManyToOne
    private Branches branch;

    public Users() {
    }

    public Users(String email, String name, String password, List<Books> books, Branches branch) {
        //this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.books = books;
        this.branch = branch;
    }

    public Users(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
    public Users(String email, String name, String password, Branches branch) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.branch = branch;
    }


    public int getId() {
        return id;
    }

   /* public void setId(int id) {
        this.id = id;
    }
*/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }
}
