package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BranchesBO;
import org.example.bo.BranchesBOimpl;
import org.example.entity.Books;
import org.example.entity.Branches;

public class BranchMngController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAdmninName;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextField txtLocation;

    private BranchesBO branchesBO = new BranchesBOimpl();

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtAdmninName.setText("");
        txtLocation.setText("");
        txtBranchName.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String branchName = txtBranchName.getText();
        if (branchesBO.delete(branchName)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            //clearFields();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String branchName = txtBranchName.getText();
        String location = txtLocation.getText();
        String admin = txtAdmninName.getText();

        if (branchName.isEmpty() || location.isEmpty() || admin.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
            return;
        }
        Branches branches = new Branches(branchName,location,admin);
        // Call save method of booksBO
        boolean isSaved = branchesBO.save(branches);

        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            // Clear fields or perform any other necessary action
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String branchName = txtBranchName.getText();
        String location = txtLocation.getText();
        String admin = txtAdmninName.getText();

        // Check if the branch name is empty
        if (branchName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter the branch name").show();
            return;
        }

        // Retrieve the existing branch
        Branches existingBranch = branchesBO.getBranches(branchName);

        if (existingBranch != null) {
            // Update the branch details
            existingBranch.setLocation(location);
            existingBranch.setBranchAdmin(admin);

            // Call update method of branchesBO
            boolean isUpdated = branchesBO.update(existingBranch);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Branch updated successfully").show();
                // Optionally, clear fields or perform any other necessary action
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update branch").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Branch not found").show();
        }
    }
    public void initialize() {
        // Add an event listener to txtTitle to handle Enter key press
        txtBranchName.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Call a method to fetch book details and display them
                fetchAndDisplayBranchDetails();
            }
        });
    }

    private void fetchAndDisplayBranchDetails() {
        String name = txtBranchName.getText();
        Branches branches= branchesBO.getBranches(name);

        if (branches != null) {
            txtLocation.setText(branches.getLocation());
            txtAdmninName.setText(branches.getBranchAdmin());
        } else {
            // Book not found
            new Alert(Alert.AlertType.ERROR, "Book not found").show();
        }
    }
}
