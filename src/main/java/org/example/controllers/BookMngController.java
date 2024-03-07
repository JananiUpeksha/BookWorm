package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BooksBO;
import org.example.bo.BooksBOimpl;
import org.example.entity.Books;
import org.example.entity.Users;

import java.util.List;

public class BookMngController {
    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtGenre;
    public TextField txtAvailable;
    public AnchorPane rootNode;
    private BooksBO booksBO = new BooksBOimpl();

        public void btnSaveOnAction(ActionEvent actionEvent) {
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String genre = txtGenre.getText();
            String available = txtAvailable.getText();
            boolean isAvailable;

            if (!available.isEmpty()) {
                isAvailable = Boolean.parseBoolean(available);
            } else {
                isAvailable = true; // Default to true if the field is empty
            }

            Books books = new Books(title, author, genre, isAvailable);

            // Call save method of booksBO
            boolean isSaved = booksBO.save(books);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                // Clear fields or perform any other necessary action
            }
        }

        public void btnUpdateOnAction(ActionEvent actionEvent) {
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String genre = txtGenre.getText();
            String available = txtAvailable.getText();
            boolean isAvailable;

            if (!available.isEmpty()) {
                isAvailable = Boolean.parseBoolean(available);
            } else {
                isAvailable = true; // Default to true if the field is empty
            }

            // Retrieve the existing book
            Books existingBook = booksBO.getBooks(title);

            if (existingBook != null) {
                // Update the book details
                existingBook.setAuthor(author);
                existingBook.setGenre(genre);
                existingBook.setAvailability(isAvailable);

                // Call update method of booksBO
                boolean isUpdated = booksBO.update(existingBook);

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Book updated successfully").show();
                    // Optionally, clear fields or perform any other necessary action
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update book").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Book not found").show();
            }
        }

        public void btnDeleteOnAction(ActionEvent actionEvent) {
            String title = txtTitle.getText();
            if (booksBO.delete(title)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                //clearFields();
            }
        }

        public void btnClearOnAction(ActionEvent actionEvent) {
            txtTitle.setText("");
            txtAuthor.setText("");
            txtGenre.setText("");
            txtAvailable.setText("");
        }
    public void initialize() {
        // Add an event listener to txtTitle to handle Enter key press
        txtTitle.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Call a method to fetch book details and display them
                fetchAndDisplayBookDetails();
            }
        });
    }

    private void fetchAndDisplayBookDetails() {
        String title = txtTitle.getText();
        Books book = booksBO.getBooks(title);

        if (book != null) {
            txtAuthor.setText(book.getAuthor());
            txtGenre.setText(book.getGenre());
            txtAvailable.setText(String.valueOf(book.isAvailability()));
        } else {
            // Book not found
            new Alert(Alert.AlertType.ERROR, "Book not found").show();
        }
    }

}
