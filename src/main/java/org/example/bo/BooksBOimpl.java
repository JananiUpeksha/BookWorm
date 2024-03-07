package org.example.bo;

import org.example.dao.BooksDAO;
import org.example.dao.DAOFactory;
import org.example.entity.Books;

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
    public boolean update(Books dto) {
        try {
            return booksDAO.update(new Books(dto.getTitle(), dto.getAuthor(), dto.getAuthor(),dto.isAvailability()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Books getBooks(String title) {
        try {
            return booksDAO.getBooks(title);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Books> getAll() {
        try {
            return booksDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
