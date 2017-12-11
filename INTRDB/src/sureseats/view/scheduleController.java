package sureseats.view;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sureseats.model.*;

public class scheduleController {
	private SureseatsDB sdb;
	private UserService us;
	private ScheduleService ss;
	private User user;
	private Film film;
	
	private List<TextArea> texts;
	private List<Button> buttons;
	private List<Schedule> scheds;

	@FXML
	private Button Sback;

	@FXML
	private Text STitle;

	@FXML
	private ImageView SMovie;

	@FXML
	private Text SDate;

	@FXML
	private TextArea SSched1;

	@FXML
	private TextArea SSched2;

	@FXML
	private TextArea SSched3;

	@FXML
	private Button SReserve1;

	@FXML
	private Button SReserve2;

	@FXML
	private Button SReserve3;

	/*
	 * public void initialize() { sdb = new SureseatsDB(); ss = new
	 * ScheduleService(sdb);
	 * 
	 * if (film == null) { film = new Film(); }
	 * 
	 * STitle.setText(film.getTitle()); //SMovie.setImage(new Image("/Resources/" +
	 * film.getImage())); SDate.setText(film.getDate().toString()); }
	 */

	public void initialize() {
		sdb = new SureseatsDB();
		us = new UserService(sdb);
		ss = new ScheduleService(sdb);

	}

	public void loadContent() {
		STitle.setText(film.getTitle());
		SMovie.setImage(new Image("/Resources/" + film.getImage()));
		SDate.setText(film.getDate().toString());
		
		texts = Arrays.asList(SSched1, SSched2, SSched3);
		buttons = Arrays.asList(SReserve1, SReserve2, SReserve3);
		scheds = ss.getSchedulesOfFilm(film);
		
		for (TextArea text : texts) {
			text.setEditable(false);
		}
		
		Iterator<TextArea> textsi = texts.iterator();
		Iterator<Button> buttonsi = buttons.iterator();
		Iterator<Schedule> schedsi = scheds.iterator();
		
		for (TextArea t : texts) {
			t.clear();
		}
		
		while (textsi.hasNext() && buttonsi.hasNext() && schedsi.hasNext()) {
			Schedule curr = schedsi.next();
			textsi.next().setText(
					curr.getCinema().getMall().getName() + "\n"
					+ "Cinema " + curr.getCinema().getNo() + "\n"
					+ NumberFormat.getCurrencyInstance(new Locale("en", "PH")).format(curr.getFilm().getPrice()) + "\n"
					+ curr.getStart().toLocalDate().toString() + "\n"
					+ curr.getStart().toLocalTime().format(DateTimeFormatter.ofPattern("h':'mm a")));
		}
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Button getBackButton() {
		return Sback;
	}

	public void toback(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/GUI.fxml"));
		Parent tableViewParent = loader.load();
		if (user != null) {
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

	public void toReserve(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/seats.fxml"));
		Parent tableViewParent = loader.load();
		SeatsController sc = loader.<SeatsController>getController();
		sc.setUser(user);
		sc.setSchedule(scheds.get(buttons.indexOf(event.getSource())));
		sc.loadContent();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

}