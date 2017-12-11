package sureseats.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sureseats.model.*;

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
	private SureseatsDB sdb;
	private FilmService fs;

	public void initialize() {
		sdb = new SureseatsDB();
		fs = new FilmService(sdb);
		
		List<ImageView> ivs = Arrays.asList(panel00, panel01, panel02, panel03, panel04, panel05);
		List<Film> films = fs.getComingSoon();
		
		Iterator<ImageView> ivsi = ivs.iterator();
		Iterator<Film> filmsi = films.iterator();
		
		for (ImageView iv : ivs) {
			iv.setImage(null);
		}
		
		ivsi.forEachRemaining(iv -> {
			if (filmsi.hasNext()) {
				String url = filmsi.next().getImage();
				iv.setImage(new Image("/Resources/" + url));
			} else {
				iv.setVisible(false);
			}
		});
	}

	public void goback(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/GUI.fxml"));
		Parent tableViewParent = loader.load();
		GUIController gc = loader.<GUIController>getController();
		gc.setUser(user);
		gc.setGuestMode(user==null);
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

