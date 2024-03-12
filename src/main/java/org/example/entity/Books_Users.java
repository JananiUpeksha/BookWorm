package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "books_users")
public class Books_Users {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_name") // Assuming "name" is unique in Users
    private Users user;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "book_title", referencedColumnName = "title"),
            @JoinColumn(name = "book_author", referencedColumnName = "author")
    })
    private Books book;*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_name") // Assuming "name" is unique in Users
    private Users user;

    @ManyToOne
   /* @JoinColumn(name = "book_title")*/ // Assuming "title" is the primary key of the Books entity
    private Books book;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "is_return")
    private Boolean isReturn;


}
