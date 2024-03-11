package org.example.controllers;

import TM.UserDashTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BooksBO;
import org.example.bo.BooksBOimpl;
import org.example.entity.Books;

import java.util.List;

public class UserDashController {
    public AnchorPane rootNode;
    public TableView tblUserBooks;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    private BooksBO booksBO = new BooksBOimpl();

    public void initialize() {
        setCellvalueFactory();
        loadAllBooks();
        tblUserBooks.getSelectionModel().clearSelection();
        rootNode.requestFocus();
    }

    private void loadAllBooks() {
        ObservableList<UserDashTm> observableList = FXCollections.observableArrayList();
        List<Books> booksList = booksBO.getAll();
        for (Books book : booksList) {
            observableList.add(new UserDashTm(book.getTitle(), book.getAuthor(), book.getGenre()));
        }
        tblUserBooks.setItems(observableList);
    }

    private void setCellvalueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    }


}
