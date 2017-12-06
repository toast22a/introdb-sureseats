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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sureseats.model.*;

public class GUIController {
	private User user;
	private Film film;
	private SureseatsDB sdb;
	private FilmService fs;
	
	
	
	@FXML
	private AnchorPane apMain;
	
    @FXML
    private Button moviesBtnTHistory;

	@FXML
	private Button moviesBtnRegister;

	public Button getMoviesBtnRegister() {
		return moviesBtnRegister;
	}

	public void setMoviesBtnRegister(Button moviesBtnRegister) {
		this.moviesBtnRegister = moviesBtnRegister;
	}

	public void setMoviesBtnTransaction(Button moviesBtnTHistory) {
		this.moviesBtnRegister = moviesBtnTHistory;
	}
	public Button getMoviesBtnLogin() {
		return moviesBtnLogin;
	}

	public void setMoviesBtnLogin(Button moviesBtnLogin) {
		this.moviesBtnLogin = moviesBtnLogin;
	}

	public ImageView getPanel00() {
		return panel00;
	}

	public void setPanel00(ImageView panel00) {
		this.panel00 = panel00;
	}

	public ImageView getPanel01() {
		return panel01;
	}

	public void setPanel01(ImageView panel01) {
		this.panel01 = panel01;
	}

	public ImageView getPanel02() {
		return panel02;
	}

	public void setPanel02(ImageView panel02) {
		this.panel02 = panel02;
	}

	public ImageView getPanel03() {
		return panel03;
	}

	public void setPanel03(ImageView panel03) {
		this.panel03 = panel03;
	}

	public ImageView getPanel04() {
		return panel04;
	}

	public void setPanel04(ImageView panel04) {
		this.panel04 = panel04;
	}

	public ImageView getPanel05() {
		return panel05;
	}

	public void setPanel05(ImageView panel05) {
		this.panel05 = panel05;
	}

	@FXML
	private Button moviesBtnLogin;

	@FXML
	private ImageView panel00;

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
	private Button login1;

	/*
	 * @FXML private void reg() throws Exception{ Stage stage = (Stage)
	 * apMain.getScene().getWindow(); main.register(stage); }
	 */

	/*
	 * @FXML private void reg() throws IOException {
	 * apMain.getChildren().setAll(FXMLLoader.load("sureseats/view/register.fxml"));
	 * }
	 */
	
	public void initialize() {
		sdb = new SureseatsDB();
		fs = new FilmService(sdb);
		
		List<ImageView> ivs = Arrays.asList(panel00, panel01, panel02, panel03, panel04, panel05);
		List<Film> films = fs.getFilmsInMonth();
		
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
		
		//debug line -- fix this asap!
		//film = fs.getFilm(6);
	}

	public void gotoreg(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/register.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

	public void gotologin(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/sign_in.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
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
	

	public void gotoTrans(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/User_TransactionHistory.fxml"));
		Parent tableViewParent = loader.load();
		//scheduleController sc = loader.<scheduleController>getController();
		//sc.setUser(user);
		//sc.setFilm(film);
		UserTransactionController utc = loader.<UserTransactionController>getController();
		utc.setUser(user);
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
	
	public void setGuestMode(boolean choice) {
		moviesBtnRegister.visibleProperty().set(choice);
		moviesBtnLogin.visibleProperty().set(choice);
	
	}
}
