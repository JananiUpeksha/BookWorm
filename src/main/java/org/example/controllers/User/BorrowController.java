package org.example.controllers.User;

import TM.BooksTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.BooksBO;
import org.example.bo.custom.Books_UsersBO;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.BooksBOimpl;
import org.example.bo.custom.impl.Books_UsersBOimpl;
import org.example.bo.custom.impl.UserBOimpl;
import org.example.dto.Books_UserDto;
import org.example.entity.Books;
import org.example.entity.Books_Users;
import org.example.entity.Users;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class BorrowController {
    public TextField txtgenre;
    public TextField txtName;
    public Label lblId;
    public TextField txtId;
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
    private static BooksBO booksBO = new BooksBOimpl();
    private Books_UsersBO books_usersBO = new Books_UsersBOimpl();
    private UserBO userBO = new UserBOimpl();
    private Books_UserDto books_userDto = new Books_UserDto();


    @FXML
    void btnSearchOnAction(ActionEvent event) throws Exception {
        String title = txtTitle.getText();

        if (title != null && !title.isEmpty()) {
            Books book = booksBO.getBookByTitle(title);

            if (book != null) {
                if (book.isAvailability()) {
                    txtAuthor.setText(book.getAuthor());
                    txtGenre.setText(book.getGenre());
                    txtId.setText(String.valueOf(book.getId()));
                } else {
                    new Alert(Alert.AlertType.WARNING, "This book is currently unavailable").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Book not found").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a book title").show();
        }
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

    public void btnSearchIdOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        if (name != null && !name.isEmpty()) {
            Users user = userBO.getUserByName(name);
            if (user != null) {
                lblId.setText(String.valueOf(user.getId()));
            } else {
                new Alert(Alert.AlertType.ERROR, "User not found").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a username").show();
        }
    }
    /*public void btnBorrowOnAction(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        LocalDate issueDate = borrowDate.getValue();
        LocalDate returnDateValue = returnDate.getValue();
       // String currentUserName = txtName.getText(); // Retrieving username from the text field
        int userId;

        try {
            userId = Integer.parseInt(lblId.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid user ID").show();
            return;
        }

        Users user = userBO.getUser(userId); // Retrieve the user by ID

        if (user == null) {
            new Alert(Alert.AlertType.ERROR, "User not found").show();
            return;
        }

        if (title != null && !title.isEmpty() && author != null && !author.isEmpty() && issueDate != null && returnDateValue != null) {
            Books_Users entity = new Books_Users();
            Books book = booksBO.getBooks(title);

            entity.setUser(user);
            entity.setBook(book);
            entity.setIssueDate(issueDate);
            entity.setReturnDate(returnDateValue);
            entity.setReturn(false);

            boolean isSaved = books_usersBO.save(entity);
            if (isSaved) {
                updateBookAvailability(book);
                new Alert(Alert.AlertType.CONFIRMATION, "Your book borrowing process is successful").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save transaction data").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please fill in all the required fields").show();
        }
    }

    // Method to update the book's availability
    private void updateBookAvailability(Books book) {
        if (book != null) {
            book.setAvailability(false);
            booksBO.update(book);
        }
    }*/
    public void btnBorrowOnAction(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        LocalDate issueDate = borrowDate.getValue();
        LocalDate returnDateValue = returnDate.getValue();

        int userId;

        try {
            userId = Integer.parseInt(lblId.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid user ID").show();
            return;
        }

        Users user = userBO.getUser(userId);

        if (user == null) {
            new Alert(Alert.AlertType.ERROR, "User not found").show();
            return;
        }

        if (title != null && !title.isEmpty() && issueDate != null && returnDateValue != null) {
            try {
                // Get the book by title
                Books book = booksBO.getBookByTitle(title);

                // Check if the book exists
                if (book != null) {
                    // Check if the book is available
                    if (book.isAvailability()) {
                        // Save borrowing transaction
                        boolean isReturn = false; // Assuming the book is not returned initially
                        Books_Users booksUsers = new Books_Users(user, book, issueDate, returnDateValue, isReturn);
                        boolean isSaved = books_usersBO.save(booksUsers);

                        if (isSaved) {
                            // Update book availability to unavailable
                            book.setAvailability(false);
                            booksBO.update(book);

                            new Alert(Alert.AlertType.CONFIRMATION, "Your book borrowing process is successful").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Failed to save transaction data").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Book is not available").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Book not found").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while processing the borrowing").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please fill in all the required fields").show();
        }
    }


    public void homeOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/UserDash.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Book History Form");
        Stage.centerOnScreen();
        Stage.show();
    }
}
