package org.example.controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.BooksBO;
import org.example.bo.custom.Books_UsersBO;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.BooksBOimpl;
import org.example.bo.custom.impl.Books_UsersBOimpl;
import org.example.bo.custom.impl.UserBOimpl;
import org.example.dto.Books_UserDto;
import org.example.entity.Books;
import org.example.entity.Users;

import java.time.LocalDate;


public class BorrowController {
    public TextField txtgenre;
    public TextField txtName;
    public Label lblId;
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
    void btnSearchOnAction(ActionEvent event) {
        String title = txtTitle.getText();
        Books book = booksBO.getBooks(title);
        if (book != null) {
            if (book.isAvailability()) {
                txtAuthor.setText(book.getAuthor());
                txtGenre.setText(book.getGenre());
            } else {
                new Alert(Alert.AlertType.WARNING, "The book is currently unavailable try another book").show();
            }
        }
    }
 /*   @FXML
    void btnBorrowOnAction(ActionEvent event) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        LocalDate issueDate = borrowDate.getValue();
        LocalDate returnDateValue = returnDate.getValue();
        String currentUserName = txtName.getText(); // Retrieving username from the text field
        int userId;

        String bookTitle = txtTitle.getText();
        if (bookTitle != null && !bookTitle.isEmpty()) {
            TransactionDto dto = new TransactionDto(userId, bookTitle, LocalDate.now(), lblDueDate.getText(), false);

            BooksDto bookDto = null;
            try {
                // Save the transaction data
                Books_UsersBO.save(userId, bookTitle, dto);

                // Update the book availability
                bookDto = booksBo.getBook(bookTitle);
                if (bookDto != null) {
                    bookDto.isAvailable("Unavailable");
                    booksBo.updateBook(bookDto);
                } else {
                    // Handle the case where the book is not found
                    new Alert(Alert.AlertType.ERROR, "Book not found").show();
                    return;
                }
            } catch (SQLException e) {
                // Print the stack trace of the SQL exception
                e.printStackTrace();
                throw new RuntimeException(e); // Rethrow the exception as a runtime exception
            }

            new Alert(Alert.AlertType.CONFIRMATION, "Your book borrow process is successful").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please enter a book title").show();
        }


    }
*/
    /*@FXML
    void btnBorrowOnAction(ActionEvent event) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        LocalDate issueDate = borrowDate.getValue();
        LocalDate returnDateValue = returnDate.getValue();
        String currentUserName = txtName.getText(); // Retrieving username from the text field
        int userId = Integer.parseInt(lblId.getText()); // Assuming userId is fetched from the label

        try {
            userId = Integer.parseInt(lblId.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid user ID").show();
            return;
        }

        if (title != null && !title.isEmpty() && author != null && !author.isEmpty() && issueDate != null && returnDateValue != null) {
            Users user = userBO.getUser(userId);

            if (user != null) {
                // Create a new Books_Users object
                Books_Users entity = new Books_Users();

                // Set properties of the Books_Users object
                entity.setUser(user);
                entity.setIssueDate(issueDate);
                entity.setReturnDate(returnDateValue);
                entity.setReturn(false); // Assuming it's initially not returned

                // Save the Books_Users entity
                boolean isSaved = books_usersBO.save(entity);
                if (isSaved) {
                    // Update the availability of the book
                    Books book = booksBO.getBooks(title);
                    if (book != null) {
                        book.setAvailability(false);
                        booksBO.update(book);
                    }
                    new Alert(Alert.AlertType.CONFIRMATION, "Your book borrowing process is successful").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save transaction data").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "User not found").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please fill in all the required fields").show();
        }
    }*/
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
}
