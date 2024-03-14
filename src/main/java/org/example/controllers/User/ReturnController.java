package org.example.controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ReturnController {
    public TextField txtName;
    @FXML
    private DatePicker borrowDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtAvailable;

    @FXML
    private TextField txtTitle;

    @FXML
    void btnReturnOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }
}
