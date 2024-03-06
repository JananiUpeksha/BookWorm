package org.example.bo;

import org.example.dao.BooksDAO;
import org.example.dao.DAOFactory;
import org.example.dao.UserDAO;
import org.example.entity.Books;
import org.example.entity.Users;

import java.util.List;

public class BooksBOimpl implements BooksBO {
    BooksDAO booksDAO = (BooksDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKS);
    @Override
    public boolean save(Books dto) {
        try {
            Books newBooks = new Books(dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.isAvailability());
            return booksDAO.save(newBooks);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String title) {
        try {
            return booksDAO.delete(title);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Books books) {
        return false;
    }

    @Override
    public Users getBooks(String title) {
        try {
            return booksDAO.getBooks(title);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Books> getAll() {
        return null;
    }
}
