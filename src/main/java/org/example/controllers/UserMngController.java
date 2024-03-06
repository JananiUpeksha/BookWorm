package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.UserBO;
import org.example.bo.UserBOimpl;
import org.example.dto.UserDto;

public class UserMngController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    private UserBO userBO = new UserBOimpl();
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmail.clear();
        txtName.clear();
        txtEmail.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int id = lblId.getText();
        if (UserBO.delete(id)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            //clearFields();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String name = txtName.getText();
        String address = txtPassword.getText();
        UserDto userDto = new UserDto(email, name, address);
        boolean isSaved = userBO.save(userDto);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            //clearField();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        /*String id = textId.getText();
        String name = textName.getText();
        String address = textAddress.getText();
        StudentDto studentDto = new StudentDto(id, name, address);
        try {
            boolean isUpdated = studentBO.updateStudnt(studentDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearField();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }
}
