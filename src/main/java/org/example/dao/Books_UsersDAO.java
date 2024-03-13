package org.example.dao;

import org.example.entity.Books_Users;

import java.util.List;

public interface Books_UsersDAO {
    public boolean save(Books_Users books_users);
    public List<Books_Users> getAll();
}
