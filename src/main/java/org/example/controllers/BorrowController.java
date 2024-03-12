package org.example.controllers;

import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BooksBO;
import org.example.bo.BooksBOimpl;
import org.example.entity.Books;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class BorrowController {
    public TextField txtgenre;
    @FXML
    private DatePicker borrowDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtTitle;
    private BooksBO booksBO = new BooksBOimpl();

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String title = txtTitle.getText(); // Get the entered book title from the text field
        Books book = booksBO.getBooks(title); // Find the book by title
        if (book != null) {
            // Check if the book is available
            if (book.isAvailability()) {
                // Populate the author, genre, and availability fields if the book is available
                txtAuthor.setText(book.getAuthor());
                txtGenre.setText(book.getGenre());
            } else {
                // If the book is unavailable, display an alert
                new Alert(Alert.AlertType.WARNING, "The book is currently unavailable try another book").show();
            }
            txtAuthor.clear();
            txtGenre.clear();
        }
    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) {

    }
    private void calculateReturnDate() {
        borrowDate.setOnAction(event -> {
            LocalDate selectedDate = borrowDate.getValue();
            if (selectedDate != null) {
                LocalDate returnDateValue = selectedDate.plusDays(14);
                returnDate.setValue(returnDateValue);
                returnDate.setEditable(false);
            }
        });
    }

    @FXML
    void initialize() {
        calculateReturnDate();
    }
}
