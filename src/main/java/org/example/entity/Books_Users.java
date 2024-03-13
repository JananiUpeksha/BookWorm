package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "books_users")
public class Books_Users {
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


    public Books_Users() {
    }

    public Books_Users(int id, Users user, Books book, LocalDate issueDate, LocalDate returnDate, Boolean isReturn) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.isReturn = isReturn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getReturn() {
        return isReturn;
    }

    public void setReturn(Boolean aReturn) {
        isReturn = aReturn;
    }

    @Override
    public String toString() {
        return "Books_Users{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                ", isReturn=" + isReturn +
                '}';
    }
}
