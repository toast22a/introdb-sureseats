package sureseats.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sureseats.model.*;

public class scheduleController {
	private User user;
	private Film film;
	private SureseatsDB sdb;
	private ScheduleService ss;

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

	/*public void initialize() {
		sdb = new SureseatsDB();
		ss = new ScheduleService(sdb);
		
		if (film == null) {
			film = new Film();
		}
		
		STitle.setText(film.getTitle());
		//SMovie.setImage(new Image("/Resources/" + film.getImage()));
		SDate.setText(film.getDate().toString());
	}*/
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
}