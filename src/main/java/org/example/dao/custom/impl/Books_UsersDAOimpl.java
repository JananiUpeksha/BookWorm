package org.example.dao.custom.impl;

import config.FactoryConfiguration;
import org.example.dao.custom.Books_UsersDAO;
import org.example.entity.Books_Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Books_UsersDAOimpl implements Books_UsersDAO {
    @Override
    public boolean save(Books_Users books_users) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(books_users);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Books_Users> getAll() {
        return null;
    }
}
