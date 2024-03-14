package org.example.dao.custom.impl;

import config.FactoryConfiguration;
import org.example.dao.custom.UserDAO;
import org.example.entity.Users;
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
    public boolean save(Users user) {
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
            Users user = session.get(Users.class, id);
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
    public boolean update(Users user) {
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
    public Users getUser(int id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Users.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Users> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Users> query = session.createQuery("FROM Users", Users.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Users getUserByName(String name) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Users> query = session.createQuery("FROM Users WHERE name = :name", Users.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
