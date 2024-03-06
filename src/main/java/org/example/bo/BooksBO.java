package org.example.bo;

import org.example.entity.Books;
import org.example.entity.Users;

import java.util.List;

public interface BooksBO extends SuperBO{
    public boolean save(Books books);
    public boolean delete(String title);
    public boolean update(Books books);
    public Users getBooks(String title);
    public List<Books> getAll();
}
