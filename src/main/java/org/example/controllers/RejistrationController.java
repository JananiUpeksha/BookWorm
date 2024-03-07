package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.UserBO;
import org.example.bo.UserBOimpl;
import org.example.entity.Users;

public class RejistrationController {
    private UserBO userBO = new UserBOimpl();
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;


    @FXML
    void btnSigninOnAction(ActionEvent event) {
        // Retrieve user details from input fields
        String email = txtEmail.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();

        // Create a new Users object with the retrieved details
        Users user = new Users();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);

        // Call the save method of the UserBO to save the user
        boolean saved = userBO.save(user);

        if (saved) {
            // User saved successfully, you can proceed with any further actions
            System.out.println("User saved successfully!");
        } else {
            // Error occurred while saving user
            System.out.println("Failed to save user.");
        }

    }
}
