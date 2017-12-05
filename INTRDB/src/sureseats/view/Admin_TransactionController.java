package sureseats.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

public class Admin_TransactionController {

    @FXML
    private TableView<?> Claimed_Table;

    @FXML
    private Tab Unclaimed_Table;

    @FXML
    private Tab Cancelled_Table;

    @FXML
    private Tab Active_Table;

    @FXML
    private TableView<?> Users_Table;

    @FXML
    private Tab Inactive_Table;

    @FXML
    private TableView<?> Province_Table;

    @FXML
    private Tab Genre_Tickets_Table;

    @FXML
    private TableView<?> City_Table;

    @FXML
    private Button admin1Next;

    @FXML
    private Button Back;

    @FXML
    void ToSignIn(ActionEvent event) {

    }

    @FXML
    void toNext(ActionEvent event) {

    }

}
