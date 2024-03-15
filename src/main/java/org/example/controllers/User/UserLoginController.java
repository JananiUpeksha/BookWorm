package org.example.controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.UserBOimpl;
import org.example.entity.Users;

import javax.imageio.IIOParam;
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
    private List<Users> users;

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

        boolean userFound = false;
        for (Users user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                userFound = true;
                break;
            }
        }

        if (userFound) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserDash.fxml"));
            Parent rootNode = loader.load();
            Scene scene = new Scene(rootNode);
            Stage stage = (Stage)this.rootNode.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("User Dash Board");
            stage.centerOnScreen();
            stage.show();

        } else {
            // Username and/or password are incorrect
            new Alert(Alert.AlertType.ERROR, "Incorrect username or password.").show();
        }

    }
    @FXML
    void initialize() {
        // Load the list of users when the controller is initialized
        users = userBO.getAll();

        // Add a listener to the username text field to monitor text changes
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            // Call the method to display the password based on the entered username
            displayPassword(newValue);
        });
    }

    // Other methods...

    // Method to display the password based on the entered username
    private void displayPassword(String username) {
        // Check if the username is at least half entered
        if (username.length() >= txtName.getLength() / 2) {
            // Find the user with the entered username
            for (Users user : users) {
                if (user.getName().startsWith(username)) {
                    // Display the password corresponding to the entered username
                    txtPassword.setText(user.getPassword());
                    return; // Exit the method after displaying the password
                }
            }
        } else {
            // Clear the password field if the username is less than half entered
            txtPassword.clear();
        }
    }
}
