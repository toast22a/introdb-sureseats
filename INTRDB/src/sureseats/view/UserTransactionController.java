package sureseats.view;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sureseats.model.SureseatsDB;
import sureseats.model.User;
import sureseats.model.UserService;

public class UserTransactionController {

	private User user;
	private SureseatsDB sureseatsDB;
	private UserService us;

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

	public void initialize() {
		sureseatsDB = new SureseatsDB();
		us = new UserService(sureseatsDB);
	}

	public void goback(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/GUI.fxml"));
		Parent tableViewParent = loader.load();

		GUIController gc = loader.<GUIController>getController();
		gc.setUser(user);
		gc.setGuestMode(false);

		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
