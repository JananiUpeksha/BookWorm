package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.UserBO;
import org.example.bo.UserBOimpl;
import org.example.dto.UserDto;
import org.example.entity.Users;

public class UserMngController {

    public ComboBox comboBranch;
    public TableView tblUser;
    public TableColumn colName;
    public TableColumn colPassword;
    public TableColumn colBranch;
    public TableColumn colEmail;
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;
    @FXML
    public TextField txtId;

    private UserBO userBO = new UserBOimpl();
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmail.setText("");
        txtName.setText("");
        txtPassword.setText("");
        txtId.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        if (userBO.delete(id)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            //clearFields();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String name = txtName.getText();
        String address = txtPassword.getText();
        /*UserDto userDto = new UserDto(email, name, address);
        boolean isSaved = userBO.save(userDto);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            //clearField();
        }*/
        if (email.isEmpty() || name.isEmpty() || address.isEmpty()) {
            // If any of the fields are empty, show an error message and return
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
            return;
        }
        Users user = new Users(email, name, address);

        // Call save method of userBO
        boolean isSaved = userBO.save(user);

        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            // Clear fields or perform any other necessary action
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();

        /*UserDto userDto = new UserDto(email, name, password);
        boolean isUpdated = userBO.update(userDto);

        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "User Updated").show();
            // Clear fields or perform any other necessary action
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update user").show();
        }*/
        // Convert userId to int (assuming userId is a String representing the ID)
        int userId = Integer.parseInt(txtId.getText());

        // Retrieve the existing user by ID
        Users existingUser = userBO.getUser(userId);

        if (existingUser != null) {
            // Update the user details
            existingUser.setName(name);
            existingUser.setEmail(email);
            existingUser.setPassword(password);

            // Call update method of userBO
            boolean isUpdated = userBO.update(existingUser);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully").show();
                // Optionally, clear fields or perform any other necessary action
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update user").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        Users userDto = userBO.getUser(id);
        if (userDto != null) {
            txtEmail.setText(userDto.getEmail());
            txtName.setText(userDto.getName());
            txtPassword.setText(userDto.getPassword());
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found").show();
        }
    }
}
