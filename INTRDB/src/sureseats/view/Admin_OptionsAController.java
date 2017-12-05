package sureseats.view;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sureseats.model.*;

public class Admin_OptionsAController {
	private SureseatsDB sdb;
	private UserService us;
	private ProvinceService ps;
	private CityService cs;

	@FXML
	private Tab User_Malls;

	@FXML
	private TableView<User> Users_Table;

	@FXML
	private TableView<Province> Province_Table;

	@FXML
	private TableView<?> City_Table;

	@FXML
	private TextField CCID;

	@FXML
	private Button CSearch;

	@FXML
	private Button CDelete;

	@FXML
	private Button CUpdate;

	@FXML
	private TextField CCNo;

	@FXML
	private TextField CCtype;

	@FXML
	private TextField CMID;

	@FXML
	private Button Cadd;

	@FXML
	private TextField MMID;

	@FXML
	private Button MSearch;

	@FXML
	private Button MDelete;

	@FXML
	private Button MUpdate;

	@FXML
	private Button Madd;

	@FXML
	private TextField MMType;

	@FXML
	private TextField MCID;

	@FXML
	private TextField UUID;

	@FXML
	private Button USearch;

	@FXML
	private Button UDelete;

	@FXML
	private Button Update;

	@FXML
	private TextField UUsername;

	@FXML
	private RadioButton UFemale;

	@FXML
	private RadioButton Umale;

	@FXML
	private RadioButton Ulocked;

	@FXML
	private RadioButton UUnlocked;

	@FXML
	private TextField UUEmail;

	@FXML
	private TextField UUMobile;

	@FXML
	private TextField UUFirst;

	@FXML
	private TextField UUProvince;

	@FXML
	private TextField UUPassword;

	@FXML
	private TextField UUBdate;

	@FXML
	private TextField UULast;

	@FXML
	private Button UAdd;

	@FXML
	private ChoiceBox<?> UCID;

	@FXML
	private ChoiceBox<?> UPID;

	@FXML
	private TextField PPID;

	@FXML
	private Button PSearch;

	@FXML
	private Button PDelete;

	@FXML
	private Button PUpdate;

	@FXML
	private Button PAdd;

	@FXML
	private TextField PPName;

	@FXML
	private TextField CCTID;

	@FXML
	private Button CTSearch;

	@FXML
	private Button CTDelete;

	@FXML
	private Button CTUpdate;

	@FXML
	private Button CTAdd;

	@FXML
	private TextField CCTName;

	@FXML
	private TextField CCTType;

	@FXML
	private TextField CTPID;

	@FXML
	private Button admin1Next;

	@FXML
	private Button Back;
	
	/*@SuppressWarnings("unchecked")
	public void initUserTable() {
		ObservableList<User> allUsers = FXCollections.observableArrayList(us.getAll());
		
		

		TableColumn<User, String> UUsernameCol = new TableColumn<User, String>("Username");
		UUsernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		
		for 
		
		Users_Table.getColumns().setAll(UUsernameCol);

		Users_Table.setItems(allUsers);
	}*/
	
	@SuppressWarnings("unchecked")
	public void initUserTable() {
		ObservableList<User> allUsers = FXCollections.observableArrayList(us.getAll());
		Users_Table.getColumns().setAll();
		for (Field f : User.class.getDeclaredFields()) {
			if (!Modifier.isFinal(f.getModifiers())) {
				TableColumn<User, String> col = new TableColumn<User, String>(f.getName());
				col.setCellValueFactory(new PropertyValueFactory<User, String>(f.getName()));
				Users_Table.getColumns().add(col);
			}
		}
		Users_Table.setItems(allUsers);
	}
	
	@SuppressWarnings("unchecked")
	public void initProvinceTable() {
		ObservableList<Province> allProvinces = FXCollections.observableArrayList(ps.getAll());
		Province_Table.getColumns().setAll();
		for (Field f : Province.class.getDeclaredFields()) {
			if (!Modifier.isFinal(f.getModifiers())) {
				TableColumn<Province, String> col = new TableColumn<Province, String>(f.getName());
				col.setCellValueFactory(new PropertyValueFactory<Province, String>(f.getName()));
				Province_Table.getColumns().add(col);
			}
		}
		Province_Table.setItems(allProvinces);
	}

	public void initialize() {
		sdb = new SureseatsDB();
		us = new UserService(sdb);
		ps = new ProvinceService(sdb);
		cs = new CityService(sdb);
		initUserTable();
		initProvinceTable();
	}

	public void toNext(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/Admin_optionsB.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

	public void ToSignIn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/Sign_In.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

}
