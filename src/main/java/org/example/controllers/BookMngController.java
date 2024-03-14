package org.example.controllers;

import TM.BooksTm;
import TM.BranchTm;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BooksBO;
import org.example.bo.BooksBOimpl;
import org.example.bo.BranchesBO;
import org.example.bo.BranchesBOimpl;
import org.example.dao.BranchDAO;
import org.example.dao.BranchDAOimpl;
import org.example.dto.BooksDto;
import org.example.dto.BranchesDto;
import org.example.entity.Books;
import org.example.entity.Branches;

import java.sql.SQLException;
import java.util.List;

public class BookMngController {
    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtGenre;
    public TextField txtAvailable;
    public AnchorPane rootNode;
    public ComboBox <String> comboBranch;
    public TableView<BooksTm> tblBooks;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colAvailable;
    public TableColumn colBranch;
    private BooksBO booksBO = new BooksBOimpl();
    private BranchesBO branchesBO = new BranchesBOimpl();
    private BranchDAO branchDAO = new BranchDAOimpl();
        public void btnSaveOnAction(ActionEvent actionEvent) {
            if(validateBook()){
                String title = txtTitle.getText();
                String author = txtAuthor.getText();
                String genre = txtGenre.getText();
                String available = txtAvailable.getText();
                boolean isAvailable;
                String selectedBranch = (String) comboBranch.getValue();


                if (!available.isEmpty()) {
                    isAvailable = Boolean.parseBoolean(available);
                } else {
                    isAvailable = true; // Default to true if the field is empty
                }


                if (selectedBranch != null) {
                    Branches branch = branchesBO.getBranches(comboBranch.getValue());

                    if (branch != null) {
                        Books books = new Books(title, author, genre, isAvailable);
                        books.setBranch(branch);

                        boolean isSaved = booksBO.save(books);

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
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String available = txtAvailable.getText();
        boolean isAvailable;

        if (!available.isEmpty()) {
            isAvailable = Boolean.parseBoolean(available);
        } else {
            isAvailable = true; // Default to true if the field is empty
        }

        String selectedBranch = (String) comboBranch.getValue(); // Get the selected branch

        if (selectedBranch != null) {
            try {
                // Retrieve the existing book
                Books existingBook = booksBO.getBooks(title);

                if (existingBook != null) {
                    // Update the book details
                    existingBook.setAuthor(author);
                    existingBook.setGenre(genre);
                    existingBook.setAvailability(isAvailable);

                    // Retrieve the branch object associated with the selected branch name
                    Branches branch = branchesBO.getBranches(selectedBranch);
                    if (branch != null) {
                        existingBook.setBranch(branch); // Set the updated branch

                        // Call update method of booksBO
                        boolean isUpdated = booksBO.update(existingBook);

                        if (isUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                            loadAllBooks();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace if an exception occurs
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
            String title = txtTitle.getText();
            if (booksBO.delete(title)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                loadAllBooks();
                //clearFields();
            }
        }

        public void btnClearOnAction(ActionEvent actionEvent) {
            txtTitle.setText("");
            txtAuthor.setText("");
            txtGenre.setText("");
            txtAvailable.setText("");
            comboBranch.getSelectionModel().clearSelection();
        }
    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        tableListner();
        loadBranches();
        loadAllBooks();
        txtTitle.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                // Call a method to fetch book details and display them
                fetchAndDisplayBookDetails();
            }
        });
    }

    private void tableListner() {
        tblBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                BooksTm selectedBook = (BooksTm) newValue;
                txtTitle.setText(selectedBook.getTitle());
                txtAuthor.setText(selectedBook.getAuthor());
                txtGenre.setText(selectedBook.getGenre());
                txtAvailable.setText(String.valueOf(selectedBook.isAvailability()));
                String branchName = selectedBook.getBranch();
                if (branchName != null) {
                    comboBranch.setValue(String.valueOf(branchName));
                }
            }
        });
    }

    private void fetchAndDisplayBookDetails() {
        String title = txtTitle.getText();
        Books book = booksBO.getBooks(title);

        if (book != null) {
            txtAuthor.setText(book.getAuthor());
            txtGenre.setText(book.getGenre());
            txtAvailable.setText(String.valueOf(book.isAvailability()));

            // Assuming you have a branch associated with the book
            Branches branch = book.getBranch();
            if (branch != null) {
                comboBranch.setValue(branch.getBranchName());
            } else {
                // Clear branch combo box if no branch associated
                comboBranch.setValue(null);
            }
        } else {
            // Book not found
            new Alert(Alert.AlertType.ERROR, "Book not found").show();
            // Clear field
        }
    }

    private void loadBranches() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<Branches> branches = branchDAO.getAll();

        for (Branches dto : branches) {
            obList.add(dto.getBranchName());
        }
        comboBranch.setItems(obList);
    }

    private void setCellValueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        // Customize the colAvailable cell value factory to display "Available" for true and "Unavailable" for false
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


}
