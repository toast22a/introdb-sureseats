package sureseats.view;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	private List<Seat> selected;
	
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button to_Back;

	@FXML
	private Button to_Reserve;

	@FXML
	private GridPane Seats_pane;

	public void initialize() {
		sdb = new SureseatsDB();
		ses = new SeatService(sdb);
		selected = new ArrayList<Seat>();

	}
	
	public void loadContent() {
		/*int r, c;
		int i;
		char[] row = new char[26];
		for (i = 65; i < 90; i++)
			row[i - 65] = ((char) i);*/
		List<Integer> dimensions = ses.getCinemaDimensions(schedule.getCinema());
		anchorPane.getChildren().remove(Seats_pane);
		Seats_pane = new GridPane();
		Seats_pane.setLayoutX(175);
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
			for(int row = 0 ; row < dimensions.get(0)-'A' ; row++) {
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
			for (int row = 0 ; row < dimensions.get(0)-'A' ; row++) {
				final char onPressRow = (char)(row+'A');
				final int onPressCol = col+1;
				Button b = new Button();
				b.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent e) {
						clickSeat(b, onPressRow, onPressCol); //stopped here!
					}
				});
				b.setPrefSize(30, 30);
				GridPane.setColumnIndex(b, col+1);
				GridPane.setRowIndex(b, row+1);
				Seats_pane.getChildren().add(b);
			}
		}
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
	
	public void clickSeat(Button button, char row, int col){
		
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
