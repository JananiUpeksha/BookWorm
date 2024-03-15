package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.entity.Books;

import java.util.List;

public interface BooksBO extends SuperBO {

    public boolean save(Books books);
    public boolean delete(int id);
    public boolean update(Books books);
    public Books getBooks(String title);
    public List<Books> getAll();

    Books getBook(int id);
    public Books getBookByTitle(String title) throws Exception;

}
