package org.example.dao.custom;

import config.FactoryConfiguration;
import org.example.entity.Books_Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface Books_UsersDAO {
    public boolean save(Books_Users books_users);
    public List<Books_Users> getAll();
    public List<Books_Users>getBooksUsersByUserId(int userId);
    public boolean updateBooksUsers(Books_Users books_users);
}
