package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.UserBO;
import org.example.bo.UserBOimpl;
import org.example.entity.Users;

import java.io.IOException;
import java.util.List;

public class UserLoginController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;
    private UserBO userBO = new UserBOimpl();

    @FXML
    void createAccountOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/Registration.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Registration Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        String username = txtName.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter both username and password.").show();
            return;
        }

        List<Users> users = userBO.getAll();

        // Check if any user has the provided username and password
        boolean userFound = false;
        for (Users user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                userFound = true;
                break;
            }
        }

        if (userFound) {
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/UserDash.fxml"));
            Scene scene = new Scene(rootNode);
            Stage Stage = (Stage)this.rootNode.getScene().getWindow();
            Stage.setScene(scene);
            Stage.setTitle("User Login Form");
            Stage.centerOnScreen();
            Stage.show();
        } else {
            // Username and/or password are incorrect
            new Alert(Alert.AlertType.ERROR, "Incorrect username or password.").show();
        }

    }
}
