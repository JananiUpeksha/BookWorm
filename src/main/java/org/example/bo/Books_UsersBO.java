package org.example.bo;

import org.example.entity.Books_Users;

import java.util.List;

public interface Books_UsersBO {
    public boolean save(Books_Users dto);
    public List<Books_Users> getAll();
}
