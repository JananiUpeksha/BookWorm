package org.example.dao.custom;

import config.FactoryConfiguration;
import org.example.dao.SuperDAO;
import org.example.dto.BooksDto;
import org.example.entity.Books;
import org.hibernate.Session;

import java.util.List;

public interface BooksDAO extends SuperDAO {
   public boolean save(Books books);
    public boolean delete(int id);
    public boolean update(Books books);
    public Books getBookByTitle(String title) throws Exception;
    public List<Books> getAll();

    Books getBook(int id);

}
