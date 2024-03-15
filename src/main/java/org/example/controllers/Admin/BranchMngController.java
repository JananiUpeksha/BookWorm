package org.example.controllers.Admin;

import TM.BranchTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.BranchesBO;
import org.example.bo.custom.impl.BranchesBOimpl;
import org.example.dto.Books_UserDto;
import org.example.entity.Branches;

import java.io.IOException;
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
    private Books_UserDto books_userDto = new Books_UserDto();

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
        if (validateBranch()){
            String branchName = txtBranchName.getText();
            String location = txtLocation.getText();
            String admin = txtAdmninName.getText();

            if (branchName.isEmpty() || location.isEmpty() || admin.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
                return;
            }
            Branches branches = new Branches(branchName,location,admin);
            boolean isSaved = branchesBO.save(branches);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                loadAllBranches();
                tblBranch.refresh();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String branchName = txtBranchName.getText();
        String location = txtLocation.getText();
        String admin = txtAdmninName.getText();

        if (branchName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter the branch name").show();
            return;
        }

        Branches existingBranch = branchesBO.getBranches(branchName);

        if (existingBranch != null) {
            existingBranch.setLocation(location);
            existingBranch.setBranchAdmin(admin);

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
        txtBranchName.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                fetchAndDisplayBranchDetails();
            }
        });
        setCellvalueFactory();
        tableListener();
        loadAllBranches();

    }

    private void loadAllBranches() {
        ObservableList<BranchTm> observableList = FXCollections.observableArrayList();
        List<Branches> branchesList = branchesBO.getAll();
        for (Branches branch : branchesList) {
            observableList.add(new BranchTm(branch.getBranchName(), branch.getLocation(), branch.getBranchAdmin()));
        }
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
    private boolean validateBranch() {
        String branchName = txtBranchName.getText().trim();
        String location = txtLocation.getText().trim();
        String adminName = txtAdmninName.getText().trim();

        String branchNameRegex = "^[A-Za-z0-9\\s'-]+$";
        String locationRegex = "^[A-Za-z0-9\\s',.-]+$";
        String adminNameRegex = "^[A-Za-z\\s]+$";

        boolean isBranchNameValid = branchName.matches(branchNameRegex);
        boolean isLocationValid = location.matches(locationRegex);
        boolean isAdminNameValid = adminName.matches(adminNameRegex);

        if (!isBranchNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid branch name").show();
            return false;
        }
        if (!isLocationValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid location").show();
            return false;
        }
        if (!isAdminNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid admin name").show();
            return false;
        }

        return true;
    }

    public void homeOnActiom(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/AdminDash.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Admin Dashzboard Form");
        Stage.centerOnScreen();
        Stage.show();
    }

}
