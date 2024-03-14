package org.example.controllers;

import TM.BooksTm;
import TM.UserTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BranchesBO;
import org.example.bo.BranchesBOimpl;
import org.example.bo.UserBO;
import org.example.bo.UserBOimpl;
import org.example.dao.BranchDAO;
import org.example.dao.BranchDAOimpl;
import org.example.dto.UserDto;
import org.example.entity.Books;
import org.example.entity.Branches;
import org.example.entity.Users;

import java.util.List;

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
    private BranchesBO branchesBO = new BranchesBOimpl();
    private BranchDAO branchDAO = new BranchDAOimpl();
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
        if (validateUser()){String email = txtEmail.getText();
            String name = txtName.getText();
            String password = txtPassword.getText();
            String selectedBranchName = (String) comboBranch.getValue();

            if (email.isEmpty() || name.isEmpty() || password.isEmpty() || selectedBranchName == null) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
                return;
            }

            // Retrieve the Branches object corresponding to the selected branch name
            Branches selectedBranch = branchDAO.getBranches(selectedBranchName);

            // Check if the branch is found
            if (selectedBranch == null) {
                new Alert(Alert.AlertType.ERROR, "Selected branch not found").show();
                return;
            }

            // Create a Users entity with the retrieved branch
            Users user = new Users(email, name, password, selectedBranch);

            // Call the save method of userBO to save the user
            boolean isSaved = userBO.save(user);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                // Clear fields or perform any other necessary action
            }
            tblUser.refresh();}
    }

    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        Users userDto = userBO.getUser(id);

        if (userDto != null) {
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
    }
    public void btnUpdateOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        int id = Integer.parseInt(txtId.getText());
        String selectedBranchName = (String) comboBranch.getValue();

        // Create a UserDto object with the updated information
        UserDto userDto = new UserDto(id, email, name, password, selectedBranchName);

        // Call the update method of userBO to save the changes
        boolean isUpdated = userBO.update(userDto);

        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully").show();
            // Optionally, clear fields or perform any other necessary action
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update user").show();
        }
        tblUser.refresh();
    }

    public void initialize(){
        loadBranches();
        CellValueFactory();
        loadAllUsers();
        tableListener();
    }

    private void tableListener() {
        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Users selectedUser = (Users) newValue;
                txtName.setText(selectedUser.getName());
                txtEmail.setText(selectedUser.getEmail());
                txtPassword.setText(selectedUser.getPassword());
                // Assuming branch is a String property of the Users class
                // If it's a separate entity, you may need to handle it differently
                comboBranch.setValue(selectedUser.getBranch());
            }
        });
    }

    private void loadAllUsers() {
        List<Users> allUsers = userBO.getAll();

        ObservableList<UserTm> userTms = FXCollections.observableArrayList();
        for (Users users : allUsers) {
            String branchName = users.getBranch() != null ? users.getBranch().getBranchName() : null;
            UserTm userTm = new UserTm(users.getEmail(), users.getName(),users.getPassword(), branchName);
            userTms.add(userTm);
        }

        tblUser.setItems(userTms);
    }

    private void CellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branches"));
    }

    private void loadBranches() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<Branches> branches = branchDAO.getAll();

        for (Branches dto : branches) {
            obList.add(dto.getBranchName());
        }
        comboBranch.setItems(obList);
    }
    private boolean validateUser() {
        String email = txtEmail.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();

        String emailRegex = "^\\S+@\\S+\\.com$";
        String nameRegex = "^.{4,}$";
        String passwordRegex = "^.{5,}$";

        boolean isEmailValid = email.matches(emailRegex);
        boolean isNameValid = name.matches(nameRegex);
        boolean isPasswordValid = password.matches(passwordRegex);

        if (!isEmailValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            return false;
        }
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Name must be at least 4 characters long").show();
            return false;
        }
        if (!isPasswordValid) {
            new Alert(Alert.AlertType.ERROR, "Password must be at least 5 characters long").show();
            return false;
        }

        return true;
    }

}
