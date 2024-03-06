package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.UserBO;
import org.example.bo.UserBOimpl;

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
        /*UserDAO userDAO = new UserDAOimpl();
        String email = txtEmail.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();

        // Create a UserDto object
        User user = new User(email, name, password);
        userDAO.save(user);

        // Save user data
        boolean success = userBO.save(user);
        if (success) {
            // Show success message
            new Alert(Alert.AlertType.CONFIRMATION, " User Saved Successfully!").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "User not Saved!").show();
        }*/
    }
}
