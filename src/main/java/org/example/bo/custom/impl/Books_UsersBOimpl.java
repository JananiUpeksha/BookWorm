package org.example.bo.custom.impl;

import org.example.bo.custom.Books_UsersBO;
import org.example.dao.custom.Books_UsersDAO;
import org.example.dao.custom.impl.Books_UsersDAOimpl;
import org.example.entity.Books_Users;

import java.util.List;

public class Books_UsersBOimpl implements Books_UsersBO {
    private Books_UsersDAO booksUsersDAO = new Books_UsersDAOimpl();
    @Override
    public boolean save(Books_Users books_users) {
        return booksUsersDAO.save(books_users);
    }

    @Override
    public List<Books_Users> getAll() {
        return null;
    }
}
