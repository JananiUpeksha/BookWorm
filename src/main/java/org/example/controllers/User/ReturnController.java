package org.example.controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.Books_UsersBO;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.Books_UsersBOimpl;
import org.example.bo.custom.impl.UserBOimpl;
import org.example.dto.Books_UserDto;
import org.example.entity.Books_Users;
import org.example.entity.Users;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReturnController {
    public TextField txtName;
    @FXML
    private DatePicker borrowDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtAvailable;

    @FXML
    private TextField txtTitle;
    private Books_UsersBO books_usersBO = new Books_UsersBOimpl();
    private UserBO userBO = new UserBOimpl();

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        String name = txtName.getText();
        if (name != null && !name.isEmpty()) {
            try {
                Users user = userBO.getUserByName(name);
                if (user != null) {
                    List<Books_Users> borrowedBooks = books_usersBO.getBooksUsersByUserId(user.getId());
                    for (Books_Users borrowedBook : borrowedBooks) {
                        if (!borrowedBook.getIsReturn()) { // Check if the book is not returned
                            borrowedBook.setIsReturn(true); // Set isReturn to true
                            borrowedBook.getBook().setAvailability(true); // Set availability to true
                            books_usersBO.updateBooksUsers(borrowedBook); // Update the record
                            // Show success message
                            new Alert(Alert.AlertType.INFORMATION, "Book returned successfully").show();
                            // Clear fields
                            txtName.clear();
                            txtTitle.clear();
                            borrowDate.setValue(null);
                            returnDate.setValue(null);
                            return; // Exit the method after processing
                        }
                    }
                    // If no book is found that is not returned
                    new Alert(Alert.AlertType.ERROR, "No book borrowed by this user or already returned").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "User not found").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while processing").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a username").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String name = txtName.getText();
        if (name != null && !name.isEmpty()) {
            try {
                Users user = userBO.getUserByName(name);
                if (user != null) {
                    List<Books_Users> borrowedBooks = books_usersBO.getBooksUsersByUserId(user.getId());
                    for (Books_Users borrowedBook : borrowedBooks) {
                        if (!borrowedBook.getIsReturn()) { // Check if the book is not returned
                            txtTitle.setText(borrowedBook.getBook().getTitle());
                            borrowDate.setValue(borrowedBook.getIssueDate());
                            returnDate.setValue(borrowedBook.getReturnDate());
                            return; // Exit the method after setting the fields
                        }
                    }
                    // If no book is found that is not returned
                    new Alert(Alert.AlertType.ERROR, "No book borrowed by this user or already returned").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "User not found").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while processing").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a username").show();
        }
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/UserDash.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Book History Form");
        Stage.centerOnScreen();
        Stage.show();
    }
}
