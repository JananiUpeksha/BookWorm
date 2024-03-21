package org.example.bo.custom;

import org.example.dto.Books_UserDto;
import org.example.entity.Books_Users;
import org.hibernate.SessionBuilder;

import java.util.List;

public interface Books_UsersBO {
    public boolean save(Books_Users books_users);
    public List<Books_Users> getBooksUsersByUserId(int userId);
    public boolean updateBooksUsers(Books_Users books_users);
}
