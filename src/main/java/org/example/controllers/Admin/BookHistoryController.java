package org.example.controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BookHistoryController {
    public AnchorPane rootNode;
    public TextField txtTitle;
    public TableView tblBooks;
    public TableColumn colUser;
    public TableColumn colBDate;
    public TableColumn colRDate;
    public TableColumn ColisReturned;

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
