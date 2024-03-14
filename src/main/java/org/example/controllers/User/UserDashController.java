package org.example.controllers.User;

import TM.UserDashTm;
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

public class UserDashController {
    public AnchorPane rootNode;
    public TableView tblUserBooks;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public Label lbl1;
    public Label lbl2;
    public Label lbl3;
    public Label lbl4;
    private BooksBO booksBO = new BooksBOimpl();
    private BranchesBO branchesBO = new BranchesBOimpl();

    public void initialize() {
        setCellvalueFactory();
        loadAllBooks();
        tblUserBooks.getSelectionModel().clearSelection();
        rootNode.requestFocus();
        loadLastFourBranches();
    }

   private void loadAllBooks() {
        ObservableList<UserDashTm> observableList = FXCollections.observableArrayList();
        List<Books> booksList = booksBO.getAll();
        for (Books book : booksList) {
            observableList.add(new UserDashTm(book.getTitle(), book.getAuthor(), book.getGenre()));
        }
        tblUserBooks.setItems(observableList);
    }

    private void setCellvalueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

    private void loadLastFourBranches() {
        List<Branches> allBranches = branchesBO.getAll();

        if (allBranches.size() >= 1) {
            lbl1.setText(allBranches.get(allBranches.size() - 1).getBranchName());
        }

        if (allBranches.size() >= 2) {
            lbl2.setText(allBranches.get(allBranches.size() - 2).getBranchName());
        }

        if (allBranches.size() >= 3) {
            lbl3.setText(allBranches.get(allBranches.size() - 3).getBranchName());
        }

        if (allBranches.size() >= 4) {
            lbl4.setText(allBranches.get(allBranches.size() - 4).getBranchName());
        }
    }

    public void btnBorrowPageOnACtion(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/Borrow.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Borrow Book Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void returnOnACtion(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/Return.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Book Return Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void userHistoryOnACtion(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/UserHistory.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("User History Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void userSettingOnACtion(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/UserSettings.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("User Settings Form");
        Stage.centerOnScreen();
        Stage.show();
    }
}
