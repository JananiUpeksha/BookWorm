package org.example.bo.custom.impl;

import jakarta.persistence.OptimisticLockException;
import org.example.bo.custom.BooksBO;
import org.example.dao.custom.BooksDAO;
import org.example.dao.custom.BranchDAO;
import org.example.dao.custom.impl.BranchDAOimpl;
import org.example.dao.DAOFactory;
import org.example.entity.Books;
import org.example.entity.Users;

import java.util.List;

public class BooksBOimpl implements BooksBO {
    private BranchDAO branchDAO = new BranchDAOimpl();
    BooksDAO booksDAO = (BooksDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKS);
    @Override
    public boolean save(Books dto) {
        try {
            Books newBooks = new Books(dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.isAvailability(),dto.getBranch());
            return booksDAO.save(newBooks);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return booksDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Books dto) {
        try {
            return booksDAO.update(dto);
        } catch (OptimisticLockException e) {
            // Handle OptimisticLockException
            System.err.println("OptimisticLockException occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed information
            return false;
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Books getBooks(String title) {
        return null;
    }

   /* @Override
    public Books getBooks(String title) {
        try {
            return booksDAO.getBooks(title);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    @Override
    public List<Books> getAll() {
        try {
            return booksDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Books getBook(int id) {
        //return userDAO.getUser(id);
        try {
            return booksDAO.getBook(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Books getBookByTitle(String title) throws Exception {
        try {
            return booksDAO.getBookByTitle(title);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            throw new Exception("Error while retrieving book by title: " + title, e);
        }
    }

}
