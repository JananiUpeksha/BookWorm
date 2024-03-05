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
}
