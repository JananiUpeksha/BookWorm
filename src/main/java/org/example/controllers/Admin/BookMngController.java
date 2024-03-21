package org.example.controllers.Admin;

import TM.BooksTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.BooksBO;
import org.example.bo.custom.BranchesBO;
import org.example.bo.custom.impl.BooksBOimpl;
import org.example.bo.custom.impl.BranchesBOimpl;
import org.example.entity.Books;
import org.example.entity.Branches;

import java.io.IOException;
import java.util.List;

public class BookMngController {
    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtGenre;
    public TextField txtAvailable;
    public AnchorPane rootNode;
    public ComboBox<String> comboBranch;
    public TableView<BooksTm> tblBooks;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colAvailable;
    public TableColumn colBranch;
    public TextField txtId;
    private BooksBO booksBO = new BooksBOimpl();
    private BranchesBO branchesBO = new BranchesBOimpl();

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (validateBook()) {
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String genre = txtGenre.getText();
            String available = txtAvailable.getText();
            boolean isAvailable;
            String selectedBranch = comboBranch.getValue();

            if (!available.isEmpty()) {
                isAvailable = Boolean.parseBoolean(available);
            } else {
                isAvailable = true;
            }

            if (selectedBranch != null) {
                Branches branch = branchesBO.getBranches(selectedBranch);

                if (branch != null) {
                    Books book = new Books(title, author, genre, isAvailable, branch);

                    boolean isSaved = booksBO.save(book);

                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                        loadAllBooks();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to save book").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Selected branch not found").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please select a branch").show();
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        txtAvailable.setText("");
        comboBranch.getSelectionModel().clearSelection();
    }

    public void initialize() {
        setCellValueFactory();
        tableListener();
        loadBranches();
        loadAllBooks();
        txtTitle.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                //fetchAndDisplayBookDetails();
            }
        });
    }

    private void tableListener() {
        tblBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                BooksTm selectedBook = newValue;
                txtTitle.setText(selectedBook.getTitle());
                txtAuthor.setText(selectedBook.getAuthor());
                txtGenre.setText(selectedBook.getGenre());
                txtAvailable.setText(String.valueOf(selectedBook.isAvailability()));
                String branchName = selectedBook.getBranch();
                if (branchName != null) {
                    comboBranch.setValue(branchName);
                }
            }
        });
    }



    private void loadBranches() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<Branches> branches = branchesBO.getAll();

        for (Branches dto : branches) {
            obList.add(dto.getBranchName());
        }
        comboBranch.setItems(obList);
    }

    private void setCellValueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colAvailable.setCellFactory(column -> new TableCell<BooksTm, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item ? "Available" : "Unavailable");
                }
            }
        });
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
    }

    private void loadAllBooks() {
        List<Books> allBooks = booksBO.getAll();

        ObservableList<BooksTm> booksTms = FXCollections.observableArrayList();
        for (Books book : allBooks) {
            String branchName = book.getBranch() != null ? book.getBranch().getBranchName() : null;
            BooksTm booksTm = new BooksTm(book.getTitle(), book.getAuthor(), book.getGenre(), book.isAvailability(), branchName);
            booksTms.add(booksTm);
        }

        tblBooks.setItems(booksTms);
    }

    private boolean validateBook() {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String genre = txtGenre.getText().trim();
        String available = txtAvailable.getText().trim();

        if (title.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Title cannot be empty").show();
            return false;
        }
        if (!author.matches("^[A-Za-z.\\s]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Author").show();
            return false;
        }
        if (!genre.matches("^[A-Za-z-\\s]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Genre").show();
            return false;
        }
        if (!available.matches("^(true|false)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Availability").show();
            return false;
        }

        return true;
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/views/AdminDash.fxml"));
        Scene scene = new Scene(rootNode);
        Stage Stage = (Stage)this.rootNode.getScene().getWindow();
        Stage.setScene(scene);
        Stage.setTitle("Admin Dashzboard Form");
        Stage.centerOnScreen();
        Stage.show();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        Books booksDto = booksBO.getBook(id);

        if (booksDto != null) {
            txtTitle.setText(booksDto.getTitle());
            txtAuthor.setText(booksDto.getAuthor());
            txtGenre.setText(booksDto.getGenre());
            txtAvailable.setText(String.valueOf(booksDto.isAvailability()));
            comboBranch.getValue();

            Branches branch = booksDto.getBranch();
            if (branch != null) {
                comboBranch.setValue(branch.getBranchName());
            } else {
                comboBranch.setValue(null);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        boolean isDeleted = booksBO.delete(id);

        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            loadAllBooks();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete book").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String available = txtAvailable.getText();
        boolean isAvailable;
        String selectedBranch = comboBranch.getValue();

        if (!available.isEmpty()) {
            isAvailable = Boolean.parseBoolean(available);
        } else {
            isAvailable = true;
        }

        if (selectedBranch != null) {
            Branches branch = branchesBO.getBranches(selectedBranch);

            if (branch != null) {
                Books book = new Books(id, title, author, genre, isAvailable, branch);

                boolean isUpdated = booksBO.update(book);

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                    loadAllBooks();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update book").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select a branch").show();
        }
    }

}
