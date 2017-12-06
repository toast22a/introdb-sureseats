package sureseats.view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sureseats.model.*;

public class Admin_Option3Controller {
	
	private SureseatsDB sureseatsDB;
	private FilmService fs;
	private UserService us;
	private ProvinceService ps;
	private CityService cs;

    @FXML
    private Button admin1Next;

    @FXML
    private Button Back;

    @FXML
    private TableView<Film> Film_Table;

    @FXML
    private TableColumn<Film, Integer> COL_FID;

    @FXML
    private TableColumn<Film, String> COL_Title;

    @FXML
    private TableColumn<Film, String> COL_Genre;

    @FXML
    private TableColumn<Film, LocalDate> COL_Date;

    @FXML
    private TableColumn<Film, String> COL_Rating;

    @FXML
    private TableColumn<Film, String> COL_Cast;

    @FXML
    private TableColumn<Film,Integer> COL_Runtime;

    @FXML
    private TableColumn<Film, Double> COL_Price;

    @FXML
    private TableColumn<Film, String > COL_Synopsis;

    @FXML
    private TableColumn<Film, String> COL_Image;

    @FXML
    private TableView<User> Users_Table;

    @FXML
    private TableColumn<User, Integer> COL_UID;

    @FXML
    private TableColumn<User, String> COL_Username;

    @FXML
    private TableColumn<User, String> COL_Email;

    @FXML
    private TableColumn<User, String> COL_password;

    @FXML
    private TableColumn<User, String> COL_FName;

    @FXML
    private TableColumn<User, String> COL_FLast;

    @FXML
    private TableColumn<User, String> COL_mobile;

    @FXML
    private TableColumn<User, String> COL_Gender;

    @FXML
    private TableColumn<User, LocalDate> COL_Bbate;

    @FXML
    private TableColumn<User, LocalDateTime> COL_Last;

    @FXML
    private TableColumn<User, LocalDate> COL_ResDate;

    @FXML
    private TableColumn<User, Boolean> COL_Locked;

    @FXML
    private TableColumn<User, Province> COL_PID;

    @FXML
    private TableColumn<User, City> COL_CID;

    @FXML
    private TextField FFTitle;

    @FXML
    private TextField FFDate;

    @FXML
    private TextField FFPrice;

    @FXML
    private TextField FFRuntime;

    @FXML
    private TextField FFGenre;

    @FXML
    private TextField FFRating;

    @FXML
    private TextField FFCast;

    @FXML
    private TextField FFImage;

    @FXML
    private TextArea FFSynopsis;

    @FXML
    private TextField FFID;

    @FXML
    private Button FSearch;

    @FXML
    private Button FDelete;

    @FXML
    private Button FUpdate;

    @FXML
    private Button FAdd;

    @FXML
    private Button Fload;

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
    private ChoiceBox<City> UCID;

    @FXML
    private ChoiceBox<Province> UPID;

    @FXML
    private Button ULoad;
    
    public void initialize()
    {
    	sureseatsDB = new SureseatsDB();
    	fs= new FilmService(sureseatsDB );
    	us= new UserService(sureseatsDB );
    	ps= new ProvinceService(sureseatsDB );
		try {
			loadFilm(null);
			loadUser(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    
    public void loadFilm(ActionEvent event) throws IOException
    {
    	  ObservableList<Film> f_data = FXCollections.observableArrayList(
    	    		fs.getAll()
    	    	);
    	COL_FID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	COL_Title.setCellValueFactory(new PropertyValueFactory<>("title"));
    	COL_Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    	COL_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	COL_Rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
    	COL_Cast.setCellValueFactory(new PropertyValueFactory<>("cast"));
    	COL_Runtime.setCellValueFactory(new PropertyValueFactory<>("runtime"));
    	COL_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
    	COL_Synopsis.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
    	COL_Image.setCellValueFactory(new PropertyValueFactory<>("image"));
    	Film_Table.setItems(f_data);
    	
    }
    
	public void searchFilm(ActionEvent event) throws IOException {
		loadFilm(null);
		try {
			ObservableList<Film> f_data = FXCollections.observableArrayList();
			Film f = fs.getFilm(Integer.parseInt(FFID.getText()));
			if (f.getId() != 0)
				f_data.add(f);
			Film_Table.setItems(f_data);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
	public void addFilm(ActionEvent event) throws IOException{
		try {
			Film f = new Film();
			f.setTitle(FFTitle.getText());
			f.setGenre(FFGenre.getText());
			f.setDate(LocalDate.parse(FFDate.getText()));
			f.setRating(FFRating.getText());
			f.setCast(FFCast.getText());
			f.setRuntime(Integer.parseInt(FFRuntime.getText()));
			f.setPrice(Double.parseDouble(FFPrice.getText()));
			f.setSynopsis(FFSynopsis.getText());
			f.setImage(FFImage.getText());
			fs.addFilm(f);
			loadFilm(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
	public void updateFilm(ActionEvent event) throws IOException{
		try {
			Film f = fs.getFilm(Integer.parseInt(FFID.getText()));
			f.setTitle(FFTitle.getText());
			f.setGenre(FFGenre.getText());
			f.setDate(LocalDate.parse(FFDate.getText()));
			f.setRating(FFRating.getText());
			f.setCast(FFCast.getText());
			f.setRuntime(Integer.parseInt(FFRuntime.getText()));
			f.setPrice(Double.parseDouble(FFPrice.getText()));
			f.setSynopsis(FFSynopsis.getText());
			f.setImage(FFImage.getText());
			fs.updateFilm(f);
			loadFilm(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
	public void deleteFilm(ActionEvent event) throws IOException{
		try {
			fs.deleteFilm(Integer.parseInt(FFID.getText()));
			loadFilm(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
    
    public void loadUser(ActionEvent event) throws IOException
    {
    	  ObservableList<User> u_data = FXCollections.observableArrayList(
    	    		us.getAll()
    	    	);
    	COL_UID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	COL_Username.setCellValueFactory(new PropertyValueFactory<>("user"));
    	COL_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    	COL_password.setCellValueFactory(new PropertyValueFactory<>("password"));
    	COL_FName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
    	COL_FLast.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    	COL_mobile.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
    	COL_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    	COL_Bbate.setCellValueFactory(new PropertyValueFactory<>("bdate"));
    	COL_Last.setCellValueFactory(new PropertyValueFactory<>("lastlogin"));
    	COL_ResDate.setCellValueFactory(new PropertyValueFactory<>("rdate"));
    	COL_Locked.setCellValueFactory(new PropertyValueFactory<>("islocked"));
    	COL_PID.setCellValueFactory(new PropertyValueFactory<>("province"));
    	COL_CID.setCellValueFactory(new PropertyValueFactory<>("city"));
    	
    	Users_Table.setItems(u_data);
    	
    }
    
	public void searchUser(ActionEvent event) throws IOException {
		loadUser(null);
		try {
			ObservableList<User> u_data = FXCollections.observableArrayList();
			User u = us.getUser(Integer.parseInt(UUID.getText()));
			if (u.getId() != 0)
				u_data.add(u);
			Users_Table.setItems(u_data);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
	public void addUser(ActionEvent event) throws IOException{
		try {
			User u = new User();
			
			u.setUsername(UUsername.getText());
			u.setEmail(UUEmail.getText());
			u.setPassword(UUPassword.getText());
			u.setMobileno(UUMobile.getText());
			u.setFirstname(UUFirst.getText());
			u.setLastname(UULast.getText());
			
			if (Umale.isSelected()) {
				u.setGender("M");
			} else {
				u.setGender("F");
			}
			
			u.setBdate(LocalDate.parse(UUBdate.getText()));
			
			if (Ulocked.isSelected()) {
				u.setIslocked(true);
			} else {
				u.setIslocked(false);
			}
			
			if (UPID.getValue() != null && UCID.getValue() != null) {
				u.setProvince(UPID.getValue());
				u.setCity(UCID.getValue());
				us.addUser(u);
				loadUser(null);
			}
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
	public void updateUser(ActionEvent event) throws IOException{
		try {
			User u = us.getUser(Integer.parseInt(UUID.getText()));
			u.setUsername(UUsername.getText());
			u.setEmail(UUEmail.getText());
			u.setPassword(UUPassword.getText());
			u.setMobileno(UUMobile.getText());
			u.setFirstname(UUFirst.getText());
			u.setLastname(UULast.getText());
			
			if (Umale.isSelected()) {
				u.setGender("M");
			} else {
				u.setGender("F");
			}
			
			u.setBdate(LocalDate.parse(UUBdate.getText()));
			
			if (Ulocked.isSelected()) {
				u.setIslocked(true);
			} else {
				u.setIslocked(false);
			}
			
			if (UPID.getValue() != null && UCID.getValue() != null) {
				u.setProvince(UPID.getValue());
				u.setCity(UCID.getValue());
				us.updateUser(u);
				loadUser(null);
			}
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
	public void deleteUser(ActionEvent event) throws IOException{
		try {
			us.deleteUser(Integer.parseInt(UUID.getText()));
			loadUser(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
    
    @FXML
     public void Toback(ActionEvent event) throws IOException {
    	 FXMLLoader loader = new FXMLLoader();
    	 loader.setLocation(getClass().getResource("/sureseats/view/Admin_optionsB.fxml"));
         Parent tableViewParent = loader.load(); 
         Scene tableViewScene = new Scene(tableViewParent);
         //This line gets the Stage information
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
          
         window.setScene(tableViewScene);
         window.show();
    }

    @FXML
    public void toNext(ActionEvent event)throws IOException {
    	FXMLLoader loader = new FXMLLoader();
   	 	loader.setLocation(getClass().getResource("/sureseats/view/Admin_Transaction.fxml"));
        Parent tableViewParent = loader.load(); 
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         
        window.setScene(tableViewScene);
        window.show();

    }

}
