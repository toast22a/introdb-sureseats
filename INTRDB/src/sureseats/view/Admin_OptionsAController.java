package sureseats.view;

import java.io.IOException;

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
import sureseats.model.Cinema;
import sureseats.model.CinemaService;
import sureseats.model.City;
import sureseats.model.CityService;
import sureseats.model.Mall;
import sureseats.model.MallService;
import sureseats.model.Province;
import sureseats.model.ProvinceService;
import sureseats.model.SureseatsDB;
import sureseats.model.User;
import sureseats.model.UserService;

public class Admin_OptionsAController {

	private SureseatsDB sureseatsDB;
	private CinemaService cs;

	private MallService ms;

	private ProvinceService ps;

	private UserService us;

	private CityService cts;

	@FXML
	private Button admin1Next;

	@FXML
	private Button Back;

	@FXML
	private TableView<Cinema> Cinema_Table;

	@FXML
	private TableColumn<Cinema, Integer> COL_CID;

	@FXML
	private TableColumn<Cinema, String> COL_Cno;

	@FXML
	private TableColumn<Cinema, String> COL_CTpye;

	@FXML
	private TableColumn<Cinema, Mall> Cinema_COL_MID;

	@FXML
	private Tab User_Malls;

	@FXML
	private Button M_load;

	@FXML
	private TableView<Mall> Mall_Tables;

	@FXML
	private TableColumn<Mall, Integer> COL_MID;

	@FXML
	private TableColumn<Mall, String> COL_MName;

	@FXML
	private TableColumn<Mall, City> Mall_COL_CID;

	@FXML
	private TableView<Province> Province_Table;

	@FXML
	private TableColumn<Province, Integer> COL_PID;

	@FXML
	private TableColumn<Province, String> COL_PName;

	@FXML
	private Tab TCTID;

	@FXML
	private Button CT_load;

	@FXML
	private TableView<City> City_Table;

	@FXML
	private TableColumn<City, Integer> COL_CTID;

	@FXML
	private TableColumn<City, String> COT_CTNAME;

	@FXML
	private TableColumn<City, String> COL_CTType;

	@FXML
	private TableColumn<City, Province> City_COL_PID;

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
	private Button Cload;

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
	private Button P_Load;

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

	public void initialize() {
		sureseatsDB = new SureseatsDB();
		us = new UserService(sureseatsDB);
		cs = new CinemaService(sureseatsDB);
		ps = new ProvinceService(sureseatsDB);
		ms = new MallService(sureseatsDB);
		cts = new CityService(sureseatsDB);
		
		try {
			loadCinema(null);
			loadProvince(null);
			loadMall(null);
			loadCity(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadCinema(ActionEvent event) throws IOException {
		ObservableList<Cinema> C_data = FXCollections.observableArrayList(cs.getAll());
		COL_CID.setCellValueFactory(new PropertyValueFactory<>("id"));
		COL_Cno.setCellValueFactory(new PropertyValueFactory<>("no"));
		COL_CTpye.setCellValueFactory(new PropertyValueFactory<>("type"));
		Cinema_COL_MID.setCellValueFactory(new PropertyValueFactory<>("mall"));

		Cinema_Table.setItems(C_data);

	}

	public void loadProvince(ActionEvent event) throws IOException {
		ObservableList<Province> p_data = FXCollections.observableArrayList(ps.getAll());

		COL_PID.setCellValueFactory(new PropertyValueFactory<>("id"));
		COL_PName.setCellValueFactory(new PropertyValueFactory<>("name"));

		Province_Table.setItems(p_data);

	}

	public void searchProvince(ActionEvent event) throws IOException {
		loadProvince(null);
		try {
			ObservableList<Province> p_data = FXCollections.observableArrayList();
			Province p = ps.getProvince(Integer.parseInt(PPID.getText()));
			if (p.getId() != 0)
				p_data.add(p);
			Province_Table.setItems(p_data);
		} catch (Exception e) {
			System.out.println("Invalid ID");
		}
	}
	
	public void addProvince(ActionEvent event) throws IOException{
		Province p = new Province();
		p.setName(PPName.getText());
		ps.addProvince(p);
		loadProvince(null);
	}
	
	public void updateProvince(ActionEvent event) throws IOException{
		Province p = ps.getProvince(Integer.parseInt(PPID.getText()));
		p.setName(PPName.getText());
		ps.updateProvince(p);
		loadProvince(null);
	}
	
	
	
	public void deleteProvince(ActionEvent event) throws IOException{
		ps.deleteProvince(Integer.parseInt(PPID.getText()));
		loadProvince(null);
	}

	public void loadMall(ActionEvent event) throws IOException {
		ObservableList<Mall> m_data = FXCollections.observableArrayList(ms.getAll());

		COL_MID.setCellValueFactory(new PropertyValueFactory<>("id"));
		COL_MName.setCellValueFactory(new PropertyValueFactory<>("name"));
		Mall_COL_CID.setCellValueFactory(new PropertyValueFactory<>("city"));

		Mall_Tables.setItems(m_data);

	}

	public void loadCity(ActionEvent event) throws IOException {
		ObservableList<City> ct_data = FXCollections.observableArrayList(cts.getAll());

		COL_CTID.setCellValueFactory(new PropertyValueFactory<>("id"));
		COT_CTNAME.setCellValueFactory(new PropertyValueFactory<>("name"));
		COL_CTType.setCellValueFactory(new PropertyValueFactory<>("type"));
		City_COL_PID.setCellValueFactory(new PropertyValueFactory<>("province"));

		City_Table.setItems(ct_data);

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
