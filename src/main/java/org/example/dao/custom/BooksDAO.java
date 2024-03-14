package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Books;

import java.util.List;

public interface BooksDAO extends SuperDAO {
   public boolean save(Books books);
    public boolean delete(String title);
    public boolean update(Books books);
    public Books getBooks(String title);
    public List<Books> getAll();
}
