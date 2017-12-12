package sureseats.view;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sureseats.model.*;

public class SeatsController {
	private User user;
	private Schedule schedule;
	private SureseatsDB sdb;
	private SeatService ses;
	private ReservationService rs;
	
	private List<Seat> selected;
	
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button to_Back;

	@FXML
	private Button to_Reserve;

	@FXML
	private GridPane Seats_pane;
	
	private Text seatsLabel;
	private Text priceLabel;

	public void initialize() {
		sdb = new SureseatsDB();
		ses = new SeatService(sdb);
		rs = new ReservationService(sdb);
		selected = new ArrayList<Seat>();

	}
	
	public void loadContent() {
		/*int r, c;
		int i;
		char[] row = new char[26];
		for (i = 65; i < 90; i++)
			row[i - 65] = ((char) i);*/
		List<Integer> dimensions = ses.getCinemaDimensions(schedule.getCinema());
		List<Seat> allSeats = ses.getSeatsInCinema(schedule.getCinema());
		List<Seat> occupiedSeats = ses.getOccupied(schedule);
		anchorPane.getChildren().remove(Seats_pane);
		Seats_pane = new GridPane();
		Seats_pane.setLayoutX(90+(dimensions.get(1)*30));
		Seats_pane.setLayoutY(60.0);
		Seats_pane.setAlignment(Pos.CENTER);
		//Seats_pane.setMinSize(1000,1000);
		Seats_pane.getColumnConstraints().clear();
		Seats_pane.getRowConstraints().clear();
		/*for (int col = 0 ; col < dimensions.get(1); col++) {
			Seats_pane.getColumnConstraints().add(new ColumnConstraints(30));
		}
		for (int row = 0 ; row < dimensions.get(0)-'A' ; row++) {
			Seats_pane.getRowConstraints().add(new RowConstraints(30));
		}*/
		/*for (int col = 0 ; col < 26; col++) {
			Seats_pane.getColumnConstraints().add(new ColumnConstraints(18));
		}
		for (int row = 0 ; row < 'Z'-'A' ; row++) {
			Seats_pane.getRowConstraints().add(new RowConstraints(18));
		}*/
		anchorPane.getChildren().add(Seats_pane);
		for (int col = 0 ; col < dimensions.get(1)+3 ; col+=dimensions.get(1)+2) {
			for(int row = 0 ; row < dimensions.get(0)-'A'+1 ; row++) {
				Text t  = new Text(String.valueOf((char)(row+'A')));
				GridPane.setColumnIndex(t, col);
				GridPane.setRowIndex(t, row+1);
				GridPane.setHalignment(t, HPos.CENTER);
				Seats_pane.getChildren().add(t);
			}
		}
		for (int col = 0 ; col < dimensions.get(1) ; col++) {
			Text t = new Text(String.valueOf(col+1));
			t.setTextAlignment(TextAlignment.CENTER);
			GridPane.setColumnIndex(t, col+1);
			GridPane.setRowIndex(t, 0);
			GridPane.setHalignment(t, HPos.CENTER);
			Seats_pane.getChildren().add(t);
		}
		for (int col = 0 ; col < dimensions.get(1) ; col++) {
			for (int row = 0 ; row < dimensions.get(0)-'A'+1 ; row++) {
				final char onPressRow = (char)(row+'A');
				final int onPressCol = col+1;
				Button b = new Button();
				b.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent e) {
						clickSeat(b, onPressRow, onPressCol); //stopped here!
					}
				});
				Seat seat = ses.getSeat(schedule.getCinema(), onPressRow, onPressCol);
				if (!allSeats.contains(seat))
					b.setVisible(false);
				if(occupiedSeats.contains(seat)) {
					b.setDisable(true);
					b.setStyle("-fx-background-color: red;");
				}
				b.setPrefSize(30, 30);
				GridPane.setColumnIndex(b, col+1);
				GridPane.setRowIndex(b, row+1);
				Seats_pane.getChildren().add(b);
			}
		}
		seatsLabel = new Text();
		seatsLabel.setLayoutX(35);
		seatsLabel.setLayoutY(350);
		seatsLabel.setWrappingWidth(100);
		priceLabel = new Text(NumberFormat.getCurrencyInstance(new Locale("en", "PH")).format(0));
		priceLabel.setLayoutX(35);
		priceLabel.setLayoutY(450);
		anchorPane.getChildren().add(seatsLabel);
		anchorPane.getChildren().add(priceLabel);
	}

	public void toback(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/schedule.fxml"));
		Parent tableViewParent = loader.load();
		scheduleController sc = loader.<scheduleController>getController();
		sc.setUser(user);
		sc.setFilm(schedule.getFilm());
		sc.loadContent();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}
	
	public void updateSeatsLabel() {
		String tmp = "";
		for (Seat s : selected) {
			tmp += String.valueOf(s.getRow()) + String.valueOf(s.getCol()) + ", ";
		}
		seatsLabel.setText(tmp);
	}
	
	public void updatePriceLabel() {
		double price = schedule.getFilm().getPrice();
		priceLabel.setText(NumberFormat.getCurrencyInstance(new Locale("en", "PH")).format(price*selected.size()));
	}
	
	public void select(Button button, Seat seat) {
		selected.add(seat);
		System.out.println(button.getStyleClass());
		button.setStyle("-fx-background-color: lawngreen;");
		updateSeatsLabel();
		updatePriceLabel();
	}
	
	public void deselect(Button button, Seat seat) {
		System.out.println("Hi");
		selected.remove(seat);
		button.setStyle("");
		updateSeatsLabel();
		updatePriceLabel();
	}
	
	public void clickSeat(Button button, char row, int col){
		Seat seat = ses.getSeat(schedule.getCinema(), row, col);
		if (seat.getId() != 0) {
			System.out.println(selected);
			if (selected.contains(seat))
				deselect(button, seat);
			else
				select(button, seat);
		}
	}
	
	public String processReservation() {
		if (selected.size() > 0) {
			String code = rs.generateCode();
			List<Seat> occupied = ses.getOccupied(schedule);
			while(rs.getReservationsWithCode(code).size() > 0) {
				code = rs.generateCode();
			}
			for(Seat s : selected) {
				if(occupied.contains(s))
					return "";
			}
			for (Seat s : selected) {
				Reservation r = new Reservation();
				r.setCode(code);
				r.setDatetime(LocalDateTime.now());
				r.setType("R");
				r.setStatus("pending");
				r.setSchedule(schedule);
				r.setSeat(s);
				r.setUser(user);
				rs.addReservation(r);
			}
			return code;
		}
		return "";
	}
	
	public void toReserve(ActionEvent event) throws IOException {
		String code = processReservation();
		if(!code.isEmpty()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/sureseats/view/Reservation.fxml"));
			Parent tableViewParent = loader.load();
			ReservationController rc = loader.<ReservationController>getController();
			rc.setUser(user);
			rc.setSchedule(schedule);
			rc.setSeats(selected);
			rc.setCode(code);
			rc.loadContent();
			Scene tableViewScene = new Scene(tableViewParent);
			// This line gets the Stage information
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			window.setScene(tableViewScene);
			window.show();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Reservation failed");
			alert.setContentText("There was a problem with your selection.");

			alert.showAndWait();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

}
