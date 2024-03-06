package org.example.dao;

import config.FactoryConfiguration;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOimpl implements UserDAO {
    /*public boolean saveUser(User dto) {
        try(Session session = FactoryConfiguration.getInstance().getSession())
        {
            Transaction transaction= session.beginTransaction();

            User user= new User(dto.getEmail(),dto.getName(),dto.getPassword());
            session.persist(user);

            transaction.commit();
            session.close();
            return true;
        }
    }
    public boolean delete(int id) throws Exception {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        session.remove(session.get(User.class,id));

        transaction.commit();
        session.close();
        return true;
    }

    public boolean update(User dto) throws Exception {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        User user = new User(dto.getEmail(), dto.getName(), dto.getPassword());
        session.update(user);

        transaction.commit();
        session.close();

        return true;
    }*/
    @Override
    public boolean save(User user) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(int id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
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
    public boolean update(User user) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        /*try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            User existingUser = session.get(User.class, user.getId());
            if (existingUser != null) {
                existingUser.setEmail(user.getEmail());
                existingUser.setName(user.getName());
                existingUser.setPassword(user.getPassword());
                session.update(existingUser);
                transaction.commit();
                return true;
            }
            return false; // Return false if user with given ID does not exist
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }*/
    }
    @Override
    public User getUser(int id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<User> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}