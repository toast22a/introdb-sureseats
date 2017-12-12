package sureseats.view;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sureseats.model.*;

public class ReservationController {
	
	private User user;
	private List<Seat> seats;
	private Schedule schedule;
	private String code;
	
	private SureseatsDB sdb;
	private ReservationService rs;
	private ScheduleService ss;
	private FilmService fs;
	

    @FXML
    private Button to_Back;

    @FXML
    private Text Seat_text;

    @FXML
    private Text Movie_Title;

    @FXML
    private Text Confirmation_Code;

    @FXML
    private Text Quantity_price;

    @FXML
    private Text Reservation_Code;

    @FXML
    private Text Total;
    
    public void initialize() {
		sdb = new SureseatsDB();
		rs = new ReservationService(sdb);
		ss = new ScheduleService(sdb);
		fs = new FilmService(sdb);
	}
    
    public void loadContent() {
    		String seatTextTemp = "";
    		for (Seat s : seats) {
    			seatTextTemp += String.valueOf(s.getRow()) + String.valueOf(s.getCol()) + ", ";
    		}
    		Seat_text.setText(seatTextTemp);
    		Movie_Title.setText(schedule.getFilm().getTitle());
    		if(seats.size()==1)
	    		Quantity_price.setText(seats.size() + " Ticket x "
	    				+ NumberFormat.getCurrencyInstance(new Locale("en", "PH")).format(schedule.getFilm().getPrice())
	    				+ " each");
    		else
	    		Quantity_price.setText(seats.size() + " Tickets x "
	    				+ NumberFormat.getCurrencyInstance(new Locale("en", "PH")).format(schedule.getFilm().getPrice())
	    				+ " each");
    		double totalPrice = schedule.getFilm().getPrice() * seats.size();
    		Total.setText(NumberFormat.getCurrencyInstance(new Locale("en", "PH")).format(totalPrice));
    		Confirmation_Code.setText(code);
    		Reservation_Code.setText(code);
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}

