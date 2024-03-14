package org.example.controllers.Admin;

import TM.BranchTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.BooksBO;
import org.example.bo.custom.impl.BooksBOimpl;
import org.example.bo.custom.BranchesBO;
import org.example.bo.custom.impl.BranchesBOimpl;
import org.example.entity.Books;
import org.example.entity.Branches;

import java.io.IOException;
import java.util.List;

public class AdminDashController {
    public TableView tblBranches;
    public TableColumn colBranchName;
    public TableColumn colLocation;
    public TableColumn colAdmin;
    public AnchorPane rootNode;
    public AnchorPane bookpane;
    public Label lbl1;
    public Label lbl2;
    public Label lbl3;
    public Label lbl4;
    private BranchesBO branchesBO =  new BranchesBOimpl();
    private BooksBO booksBO = new BooksBOimpl();

    public void btnBooksOnACtion(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/BookMng.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Book Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void btnUsersOnACtion(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/UserMng.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("User Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void btnBranchesOnACtion(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/BranchMng.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Branch Manage Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void btnUserHistoryOnACtion(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/BookHistory.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Book History Form");
        Stage.centerOnScreen();
        Stage.show();
    }
    public void initialize() {
        setCellvalueFactory();
        loadAllBooks();
        tblBranches.getSelectionModel().clearSelection();
        rootNode.requestFocus();
        loadLastFourBooks();
    }

    private void loadAllBooks() {
        ObservableList<BranchTm> observableList = FXCollections.observableArrayList();
        List<Branches> branchList = branchesBO.getAll();
        for (Branches branches : branchList) {
            observableList.add(new BranchTm(branches.getBranchName(), branches.getLocation(), branches.getBranchAdmin()));
        }
        tblBranches.setItems(observableList);
    }

    private void setCellvalueFactory() {
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("branchAdmin"));
    }
    private void loadLastFourBooks() {
        List<Books> allBooks = booksBO.getAll();

        // If there are less than 4 books, set labels accordingly
        if (allBooks.size() < 4) {
            lbl1.setText(allBooks.size() >= 1 ? allBooks.get(0).getTitle() : "");
            lbl2.setText(allBooks.size() >= 2 ? allBooks.get(1).getTitle() : "");
            lbl3.setText(allBooks.size() >= 3 ? allBooks.get(2).getTitle() : "");
            lbl4.setText(allBooks.size() >= 4 ? allBooks.get(3).getTitle() : "");
        } else {
            // If there are 4 or more books, set labels to the last 4 books
            lbl1.setText(allBooks.get(allBooks.size() - 4).getTitle());
            lbl2.setText(allBooks.get(allBooks.size() - 3).getTitle());
            lbl3.setText(allBooks.get(allBooks.size() - 2).getTitle());
            lbl4.setText(allBooks.get(allBooks.size() - 1).getTitle());
        }
    }


}
