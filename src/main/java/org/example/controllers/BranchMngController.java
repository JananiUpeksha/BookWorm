package org.example.controllers;

import TM.BranchTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BranchesBO;
import org.example.bo.BranchesBOimpl;
import org.example.dto.BranchesDto;
import org.example.entity.Books;
import org.example.entity.Branches;

import java.sql.SQLException;
import java.util.List;

public class BranchMngController {
    public TableView tblBranch;
    public TableColumn colBranchName;
    public TableColumn colLocation;
    public TableColumn colAdminName;
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
            loadAllBranches();
            tblBranch.refresh();
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
            loadAllBranches();
            tblBranch.refresh();
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
                loadAllBranches();
                tblBranch.refresh();
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
        setCellvalueFactory();
        tableListener();
        loadAllBranches();

    }

    private void loadAllBranches() {
        ObservableList<BranchTm> observableList = FXCollections.observableArrayList();
        List<Branches> branchesList = branchesBO.getAll(); // Assuming you have a branchesBO object
        for (Branches branch : branchesList) {
            // Create BranchTm object using branch details and add it to the observable list
            observableList.add(new BranchTm(branch.getBranchName(), branch.getLocation(), branch.getBranchAdmin()));
        }
        // Clear existing items and set the new observable list
        tblBranch.getItems().clear();
        tblBranch.setItems(observableList);
    }

   private void tableListener() {
       tblBranch.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue != null) {
               BranchTm selectedBranch = (BranchTm) newValue;
               txtBranchName.setText(selectedBranch.getBranchName());
               txtLocation.setText(selectedBranch.getLocation());
               txtAdmninName.setText(selectedBranch.getBranchAdmin());
           }
       });
    }

    private void setCellvalueFactory() {
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAdminName.setCellValueFactory(new PropertyValueFactory<>("branchAdmin"));
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
