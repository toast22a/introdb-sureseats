package sureseats.view;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import sureseats.model.*;

public class sign_inController {
	private User user;
	private SureseatsDB sureseatsDB;
	private UserService us;

	@FXML
	private Button LBSignIn;

	@FXML
	private TextField Lusername;

	@FXML
	private PasswordField Lpassword;

	public void initialize() {
		sureseatsDB = new SureseatsDB();
		us = new UserService(sureseatsDB);
	}
	
	public boolean signIn() {
		if (!(Lusername.getText().equals("")) && !(Lpassword.getText().equals(""))){
			user = us.getUser(Lusername.getText(), Lpassword.getText());
			return (user.getId() > 0);
		}
		return false;
	}

	public void gotoNext(ActionEvent event) throws IOException {
		if (Lusername.getText().equals("admin")) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/sureseats/view/admin_optionsA.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			// This line gets the Stage information
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} else {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/sureseats/view/GUI.fxml"));
			Parent tableViewParent = loader.load();
			if (signIn()) {
				GUIController gc = loader.<GUIController>getController();
				gc.setUser(user);
				gc.setGuestMode(false);
				user.setLastlogin(LocalDateTime.now());
				us.updateUser(user);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Sign up unsuccessful");
				alert.setContentText("Invalid credentials");

				alert.showAndWait();
			}
			Scene tableViewScene = new Scene(tableViewParent);
			// This line gets the Stage information
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
	}

}
