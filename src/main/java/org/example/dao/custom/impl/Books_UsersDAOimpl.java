package org.example.dao.custom.impl;

import config.FactoryConfiguration;
import org.example.dao.custom.Books_UsersDAO;
import org.example.entity.Books_Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class Books_UsersDAOimpl implements Books_UsersDAO {
    @Override
    public boolean save(Books_Users books_users) {
       /* Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            session.persist(books_users);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction if an exception occurs
            }
            e.printStackTrace();
            return false;
        }*/
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(books_users); // Use saveOrUpdate instead of persist
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction if an exception occurs
            }
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Books_Users> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Books_Users", Books_Users.class).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Books_Users> getBooksUsersByUserId(int userId) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "FROM Books_Users bu WHERE bu.user.id = :userId";
            Query<Books_Users> query = session.createQuery(hql, Books_Users.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean updateBooksUsers(Books_Users books_users) {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            session.update(books_users);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction if an exception occurs
            }
            e.printStackTrace();
            return false;
        }
    }
}
