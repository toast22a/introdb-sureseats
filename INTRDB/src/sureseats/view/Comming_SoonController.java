package sureseats.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sureseats.model.SureseatsDB;
import sureseats.model.User;
import sureseats.model.UserService;

public class Comming_SoonController {

    @FXML
    private Button moviesBtnLogin1;

    @FXML
    private Button moviesBtnTHistory;

    @FXML
    private Button moviesBtnRegister;

    @FXML
    private Button moviesBtnLogin;

    @FXML
    private GridPane CommingSoon_Pane;

    @FXML
    private ImageView panel01;

    @FXML
    private ImageView panel02;

    @FXML
    private ImageView panel03;

    @FXML
    private ImageView panel04;

    @FXML
    private ImageView panel05;

    @FXML
    private ImageView panel00;
    
    private User user;
	private SureseatsDB sureseatsDB;
	private UserService us;

	public void initialize() {
		sureseatsDB = new SureseatsDB();
		us = new UserService(sureseatsDB);
	}

	public void goback(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/GUI.fxml"));
		Parent tableViewParent = loader.load();
		if(user!=null) {
		GUIController gc = loader.<GUIController>getController();
		gc.setUser(user);
		gc.setGuestMode(false);
		}
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
	
	@FXML
	public void gotoSched(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/schedule.fxml"));
		Parent tableViewParent = loader.load();
		//scheduleController sc = loader.<scheduleController>getController();
		//sc.setUser(user);
		//sc.setFilm(film);
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

    @FXML
    void gotoTrans(ActionEvent event) {

    }

    @FXML
    void gotologin(ActionEvent event) {

    }

    @FXML
    void gotoreg(ActionEvent event) {

    }

}

