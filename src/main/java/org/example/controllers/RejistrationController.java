package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.UserBO;
import org.example.bo.UserBOimpl;
import org.example.dao.BranchDAO;
import org.example.dao.BranchDAOimpl;
import org.example.entity.Branches;
import org.example.entity.Users;

import java.util.List;

public class RejistrationController {
    public ComboBox comboBranch;
    private UserBO userBO = new UserBOimpl();
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    private BranchDAO branchDAO = new BranchDAOimpl();
    @FXML
    void btnSigninOnAction(ActionEvent event) {
        // Retrieve user details from input fields
        String email = txtEmail.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        String selectedBranch = (String) comboBranch.getValue();

        // Create a new Users object with the retrieved details
        Users user = new Users();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setBranch(selectedBranch);

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
    public void initialize(){
        loadBranches();
    }


        private void loadBranches() {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<Branches> branches = branchDAO.getAll();

            for (Branches dto : branches) {
                obList.add(dto.getBranchName());
            }
            comboBranch.setItems(obList);
        }

}
