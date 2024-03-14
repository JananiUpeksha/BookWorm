package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Users;

import java.util.List;

public interface UserDAO extends SuperDAO {
    public boolean save(Users user);
    public boolean delete(int id);
    public boolean update(Users user);
    public Users getUser(int id);
    public List<Users> getAll();
    Users getUserByName(String name);
}
