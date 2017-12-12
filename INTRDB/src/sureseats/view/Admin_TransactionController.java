package sureseats.view;

import java.io.IOException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
import sureseats.model.*;

public class Admin_TransactionController {

	private SureseatsDB sdb;
	private UserService us;
	private ReportService rs;
	@FXML
	private Button Back;

	@FXML
	private Button admin1Next;

	@FXML
	private TableView<List<String>> Pending_Table;

	@FXML
	private TableView<List<String>> Active_Table;

	@FXML
	private TableView<List<String>> Inactive_Table;

	@FXML
	private TableView<List<String>> Genre_Tickets_Table;

	@FXML
	private TableView<List<String>> Cancelled_Table;

	public void ActiveLoadTable() {

		List<String> header;
		ObservableList<List<String>> data;
		data = FXCollections.observableArrayList(rs.getActiveUsers());

		header = data.get(0);
		data.remove(0);

		List<TableColumn<List<String>, String>> cols = new ArrayList<TableColumn<List<String>, String>>();

		for (int i = 0; i < header.size(); i++) {
			TableColumn<List<String>, String> col = new TableColumn<List<String>, String>(header.get(i));
			final int colNo = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
					return new SimpleStringProperty(p.getValue().get(colNo));
				}
			});
			cols.add(col);
		}
		Active_Table.getColumns().setAll(cols);
		Active_Table.setItems(data);
	}

	public void InactiveLoadTable() {

		List<String> header;
		ObservableList<List<String>> data;
		data = FXCollections.observableArrayList(rs.getInactiveUsers());

		header = data.get(0);
		data.remove(0);

		List<TableColumn<List<String>, String>> cols = new ArrayList<TableColumn<List<String>, String>>();

		for (int i = 0; i < header.size(); i++) {
			TableColumn<List<String>, String> col = new TableColumn<List<String>, String>(header.get(i));
			final int colNo = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
					return new SimpleStringProperty(p.getValue().get(colNo));
				}
			});
			cols.add(col);
		}
		Inactive_Table.getColumns().setAll(cols);
		Inactive_Table.setItems(data);
	}

	public void PendingLoadTable() {

		List<String> header;
		ObservableList<List<String>> data;
		data = FXCollections.observableArrayList(rs.getPendingTickets());

		header = data.get(0);
		data.remove(0);

		List<TableColumn<List<String>, String>> cols = new ArrayList<TableColumn<List<String>, String>>();

		for (int i = 0; i < header.size(); i++) {
			TableColumn<List<String>, String> col = new TableColumn<List<String>, String>(header.get(i));
			final int colNo = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
					return new SimpleStringProperty(p.getValue().get(colNo));
				}
			});
			cols.add(col);
		}
		Pending_Table.getColumns().setAll(cols);
		Pending_Table.setItems(data);
	}
	
	public void CancelledLoadTable() {

		List<String> header;
		ObservableList<List<String>> data;
		data = FXCollections.observableArrayList(rs.getCancelledTickets());

		header = data.get(0);
		data.remove(0);

		List<TableColumn<List<String>, String>> cols = new ArrayList<TableColumn<List<String>, String>>();

		for (int i = 0; i < header.size(); i++) {
			TableColumn<List<String>, String> col = new TableColumn<List<String>, String>(header.get(i));
			final int colNo = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
					return new SimpleStringProperty(p.getValue().get(colNo));
				}
			});
			cols.add(col);
		}
		Cancelled_Table.getColumns().setAll(cols);
		Cancelled_Table.setItems(data);
	}
	
	public void GenreTicketsLoadTable() {
		List<String> header;
		ObservableList<List<String>> data;
		data = FXCollections.observableArrayList(rs.getMonthXGenre());

		header = data.get(0);
		data.remove(0);

		List<TableColumn<List<String>, String>> cols = new ArrayList<TableColumn<List<String>, String>>();

		for (int i = 0; i < header.size(); i++) {
			TableColumn<List<String>, String> col = new TableColumn<List<String>, String>(header.get(i));
			final int colNo = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
					return new SimpleStringProperty(p.getValue().get(colNo));
				}
			});
			cols.add(col);
		}
		Genre_Tickets_Table.getColumns().setAll(cols);
		Genre_Tickets_Table.setItems(data);
	}

	public void initialize() {
		sdb = new SureseatsDB();
		us = new UserService(sdb);
		rs = new ReportService(sdb);
		
		ActiveLoadTable();
		InactiveLoadTable();
		PendingLoadTable();
		CancelledLoadTable();
		GenreTicketsLoadTable();
	}

	@FXML
	void ToSignIn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/Sign_In.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

	public void toback(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/Admin_optionsC.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

}
