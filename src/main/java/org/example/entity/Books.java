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
    private List<User> user;

    @ManyToOne
    private Branches branch;


}
