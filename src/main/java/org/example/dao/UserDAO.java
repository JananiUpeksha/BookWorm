package org.example.dao;

import config.FactoryConfiguration;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public interface UserDAO extends SuperDAO{
    public boolean save(User user);
    public boolean delete(int id);
    public boolean update(User user);
    public User getUser(int id);
    public List<User> getAll();
}
