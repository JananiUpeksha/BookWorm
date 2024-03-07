package org.example.bo;

import org.example.entity.Books;

import java.util.List;

public interface BooksBO extends SuperBO{
    public boolean save(Books books);
    public boolean delete(String title);
    public boolean update(Books books);
    public Books getBooks(String title);
    public List<Books> getAll();
}
