package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.UserBOimpl;
import org.example.dao.custom.BranchDAO;
import org.example.dao.custom.impl.BranchDAOimpl;
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
        String email = txtEmail.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        String selectedBranchName = (String) comboBranch.getValue();

        Branches selectedBranch = null;
        List<Branches> branches = branchDAO.getAll();
        for (Branches branch : branches) {
            if (branch.getBranchName().equals(selectedBranchName)) {
                selectedBranch = branch;
                break;
            }
        }

        if (selectedBranch == null) {
            System.out.println("Selected branch not found!");
            return;
        }

        Users user = new Users();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setBranch(selectedBranch);

        boolean saved = userBO.save(user);

        if (saved) {
            System.out.println("User saved successfully!");
        } else {
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
