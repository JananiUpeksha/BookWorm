package org.example.controllers.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.UserBOimpl;
import org.example.controllers.Admin.UserMngController;
import org.example.dao.custom.BranchDAO;
import org.example.dao.custom.impl.BranchDAOimpl;
import org.example.dto.UserDto;
import org.example.entity.Branches;
import org.example.entity.Users;

import java.io.IOException;
import java.util.List;

public class UserSettingsController {
    public AnchorPane rootNode;
    public TextField txtName;
    public TextField txtPassword;
    public TextField txtEmail;
    public ComboBox comboBranch;
    public TextField txtId;
    private BranchDAO branchDAO = new BranchDAOimpl();
    private UserBO userBO = new UserBOimpl();


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        int userId = Integer.parseInt(txtId.getText());

        // Validate the input fields
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
            return;
        }

        // Retrieve the selected branch from the combo box
        String selectedBranchName = (String) comboBranch.getValue();
        Branches selectedBranch = branchDAO.getBranches(selectedBranchName);

        // Create a UserDto object with the updated information
        UserDto userDto = new UserDto(userId, email, name, password, selectedBranchName);

        // Call the update method of userBO to save the changes
        boolean isUpdated = userBO.update(userDto);

        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully").show();
            //clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update user").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        if (!name.isEmpty()) {
            Users userDto = userBO.getUserByName(name);

            if (userDto != null) {
                txtId.setText(String.valueOf(userDto.getId()));
                txtEmail.setText(userDto.getEmail());
                txtName.setText(userDto.getName());
                txtPassword.setText(userDto.getPassword());

                // Initialize the branch to avoid LazyInitializationException
                Branches branch = userDto.getBranch();
                if (branch != null) {
                    comboBranch.setValue(branch.getBranchName());
                } else {
                    // Handle the case where the branch is not initialized
                    comboBranch.setValue(null);
                    // Display an error message or handle it in a way suitable for your application
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "User not found").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please enter a name to search").show();
        }
    }
    public void initialize(){
        loadCombo();
    }

    private void loadCombo() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<Branches> branches = branchDAO.getAll();

        for (Branches dto : branches) {
            obList.add(dto.getBranchName());
        }
        comboBranch.setItems(obList);
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
