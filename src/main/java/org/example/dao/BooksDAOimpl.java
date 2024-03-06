package org.example.dao;

import config.FactoryConfiguration;
import org.example.entity.Books;
import org.example.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BooksDAOimpl implements BooksDAO{
    @Override
    public boolean save(Books books) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(books);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String title) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Users user = session.get(Users.class, title);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Books books) {
        return false;
    }

    @Override
    public Users getBooks(String title) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Users.class, title);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Books> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Books> query = session.createQuery("FROM Books ", Books.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
