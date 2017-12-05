package sureseats.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserTransactionController {

    @FXML
    private Button to_Back;

    @FXML
    private TableView<?> Transac_Table;

    @FXML
    private TableColumn<?, ?> COL_Trans_Date;

    @FXML
    private TableColumn<?, ?> COL_Trans_type;

    @FXML
    private TableColumn<?, ?> COL_Trans_title;

    @FXML
    private TableColumn<?, ?> COL_Trans_Cinema;

    @FXML
    private TableColumn<?, ?> COL_Trans_Row;

    @FXML
    private TableColumn<?, ?> COL_Trans_Col;

    @FXML
    private TableColumn<?, ?> COL_Trans_Status;

    
    @FXML
    void toback(ActionEvent event) {

    }

}
