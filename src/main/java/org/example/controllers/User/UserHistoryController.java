package org.example.controllers.User;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.Books_UsersBO;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.Books_UsersBOimpl;
import org.example.bo.custom.impl.UserBOimpl;
import org.example.entity.Books_Users;
import org.example.entity.Users;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class UserHistoryController {
    public AnchorPane rootNode;
    public TextField txtName;
    public TableView tblBooks;
    public TableColumn colTitle;
    public TableColumn colBDate;
    public TableColumn colRDate;
    public TableColumn ColisReturned;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final Books_UsersBO booksUsersBO = new Books_UsersBOimpl();
    private UserBO userBO = new UserBOimpl();

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String username = txtName.getText();
        if (username != null && !username.isEmpty()) {
            try {
                Users user = userBO.getUserByName(username);
                if (user != null) {
                    List<Books_Users> userBooks = booksUsersBO.getBooksUsersByUserId(user.getId());
                    userTable(userBooks);
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

    private void userTable(List<Books_Users> userBooks) {
        ObservableList<Books_Users> booksList = FXCollections.observableArrayList(userBooks);
        tblBooks.setItems(booksList);

        colTitle.setCellValueFactory(new PropertyValueFactory<>("book")); // Assuming 'book' is a property in Books_Users representing the book
        ColisReturned.setCellValueFactory(new PropertyValueFactory<>("isReturn"));
        colBDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        colRDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
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
