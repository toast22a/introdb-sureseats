package sureseats.view;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import sureseats.model.SureseatsDB;
import sureseats.model.*;

public class UserTransactionController {

	private User user;
	private SureseatsDB sdb;
	private ReservationService rs;
	private ScheduleService ss;
	private FilmService fs;

	@FXML
	private Button to_Back;

	@FXML
	private TableView<List<String>> Transac_Table;

	@FXML
	private TableColumn<?, ?> COL_Trans_Date;

	@FXML
	private TableColumn<?, ?> COL_Trans_type;

	@FXML
	private TableColumn<List<String>, String> COL_Trans_title;

	@FXML
	private TableColumn<?, ?> COL_Trans_Cinema;

	@FXML
	private TableColumn<?, ?> COL_Trans_Row;

	@FXML
	private TableColumn<?, ?> COL_Trans_Col;

	@FXML
	private TableColumn<?, ?> COL_Trans_Status;
	
	public void loadTable() {
		/*ObservableList<Reservation> r_data;
		if (user != null)
			r_data = FXCollections.observableArrayList(rs.getReservationsWithUID(user.getId()));
		else
			r_data = FXCollections.observableArrayList();
		COL_Trans_Date.setCellValueFactory(new PropertyValueFactory<>("datetime"));
		COL_Trans_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		COL_Trans_title.setCellValueFactory(new Callback<CellDataFeatures<Reservation, String>, ObservableValue<String>>(){
			public ObservableValue<String> call(CellDataFeatures<Reservation, String> p){
				return p.getValue().getSchedule().getFilm().getTitle();
			}
		});*/
		List<String> header;
		ObservableList<List<String>> data;
		data = FXCollections.observableArrayList(rs.getTransactionHistory(user.getId()));
		
		header = data.get(0);
		data.remove(0);
		
		List<TableColumn<List<String>,String>> cols = new ArrayList<TableColumn<List<String>,String>>();
		/*COL_Trans_title.setCellFactory(col -> new TableCell<Reservation, Schedule>(){
			@Override
			protected void updateItem(Schedule schedule, boolean empty) {
				super.updateItem(schedule, empty);
				if(empty)
					setText(null);
				else
					setText(schedule.getFilm().getTitle());
			}
		});
		COL_Trans_title.setCellValueFactory(new PropertyValueFactory<>("schedule"));*/
		for (int i = 0 ; i < header.size() ; i++) {
			TableColumn<List<String>, String> col = new TableColumn<List<String>, String>(header.get(i));
			final int colNo = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>(){
				public ObservableValue<String> call(CellDataFeatures<List<String>, String> p){
					return new SimpleStringProperty(p.getValue().get(colNo));
				}
			});
			cols.add(col);
		}
		Transac_Table.getColumns().setAll(cols);
		Transac_Table.setItems(data);
	}

	public void initialize() {
		sdb = new SureseatsDB();
		rs = new ReservationService(sdb);
		ss = new ScheduleService(sdb);
		fs = new FilmService(sdb);
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
		loadTable();
	}

}
