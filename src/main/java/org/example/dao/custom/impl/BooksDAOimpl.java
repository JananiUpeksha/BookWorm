package org.example.dao.custom.impl;

import config.FactoryConfiguration;
import org.example.dao.custom.BooksDAO;
import org.example.entity.Books;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BooksDAOimpl implements BooksDAO {
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
            Books books = session.get(Books.class, title);
            if (books != null) {
                session.delete(books);
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
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(books);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Books getBooks(String title) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Books.class, title);
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
