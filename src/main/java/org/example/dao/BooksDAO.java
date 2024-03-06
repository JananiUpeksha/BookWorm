package org.example.dao;

import org.example.entity.Books;
import org.example.entity.Users;

import java.util.List;

public interface BooksDAO extends SuperDAO{
    public boolean save(Books books);
    public boolean delete(String title);
    public boolean update(Books books);
    public Users getBooks(String title);
    public List<Books> getAll();}
