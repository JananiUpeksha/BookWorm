package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class User {
    @Id
    private String email;
    private String name;
    private String password;

    @ManyToMany(mappedBy = "user")
    private List<Books> books;
    @ManyToOne
    private Branches branch;
}
