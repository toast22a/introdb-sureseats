package sureseats.view;

import java.io.IOException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sureseats.model.Cinema;
import sureseats.model.CinemaService;
import sureseats.model.CityService;
import sureseats.model.Film;
import sureseats.model.FilmService;
import sureseats.model.Mall;
import sureseats.model.MallService;
import sureseats.model.Province;
import sureseats.model.ProvinceService;
import sureseats.model.Reservation;
import sureseats.model.ReservationService;
import sureseats.model.Schedule;
import sureseats.model.ScheduleService;
import sureseats.model.Seat;
import sureseats.model.SeatService;
import sureseats.model.SureseatsDB;
import sureseats.model.User;
import sureseats.model.UserService;

public class Admin_Option2Controller {
	
	private SureseatsDB sureseatsDB;
	private ScheduleService ss;
	private SeatService ses;
	private UserService us;
	private ReservationService rs;
	private CinemaService cs;
	private FilmService fs;
	
	@FXML
    private TableView<Schedule> Sched_Table;

    @FXML
    private TableColumn<Schedule, Integer> COL_SID;

    @FXML
    private TableColumn<Schedule, LocalDateTime> COL_Start;

    @FXML
    private TableColumn<Schedule, LocalDateTime> COL_End;

    @FXML
    private TableColumn<Schedule, Cinema> COL_sched_Cinema;

    @FXML
    private TableColumn<Schedule, Film> COL_sched_FID;

    @FXML
    private TableView<Seat> Seats_Table;

    @FXML
    private TableColumn<Seat, Integer> COL__SeID;

    @FXML
    private TableColumn<Seat, String> COL_SeRow;

    @FXML
    private TableColumn<Seat, Integer> Col_SeCol;

    @FXML
    private TableColumn<Seat, Cinema> Col_SeCID;

    @FXML
    private TableView<Reservation> R_Table;

    @FXML
    private TableColumn<Reservation, Integer> COL_RID;

    @FXML
    private TableColumn<Reservation, String> COL_Rcode;

    @FXML
    private TableColumn<Reservation, String> COL_Rtype;

    @FXML
    private TableColumn<Reservation, LocalDateTime> COL_RDate;

    @FXML
    private TableColumn<Reservation, String> Col_RStatus;

    @FXML
    private TableColumn<Reservation, User> Col_RUID;

    @FXML
    private TableColumn<Reservation, Seat> COL_RSEID;

    @FXML
    private TableColumn<Reservation, Schedule> COL_RSID;

    @FXML
    private TableView<?> Films_Table;

    @FXML
    private TextField SStart;

    @FXML
    private TextField SSEnd;

    @FXML
    private TextField SCID;

    @FXML
    private TextField SFID;

    @FXML
    private Button Sched_load;

    @FXML
    private TextField SSID;
    
    @FXML
    private TextField SSeCID;

    @FXML
    private Button Sched_Search;

    @FXML
    private Button Sched_Delete;

    @FXML
    private Button Sched_Update;

    @FXML
    private Button Sched_Add;

    @FXML
    private TextField SSeRow;

    @FXML
    private TextField SSeCol;

    @FXML
    private Button Seats_load;

    @FXML
    private TextField SSeID;

    @FXML
    private Button Seats_Search;

    @FXML
    private Button Seats_Delete;

    @FXML
    private Button Seats_Update;

    @FXML
    private Tab RES_Load;

    @FXML
    private TextField RRid;

    @FXML
    private Button RSearch;

    @FXML
    private Button RDelete;

    @FXML
    private Button RUpdate;

    @FXML
    private Button RAdd;

    @FXML
    private TextField RRCode;

    @FXML
    private TextField RRType;

    @FXML
    private TextField RRDateTime;

    @FXML
    private TextField RRStatus;

    @FXML
    private TextField RUID;

    @FXML
    private TextField RSeID;

    @FXML
    private TextField RSID;

    @FXML
    private Button RAdd1;

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
    private Button gotoAdminA;

    @FXML
    private Button Sign_In;

    public void initialize() {
		sureseatsDB = new SureseatsDB();
		ss= new ScheduleService(sureseatsDB);
		us = new UserService(sureseatsDB);
		ses = new SeatService(sureseatsDB);
		rs= new ReservationService(sureseatsDB);
		cs= new CinemaService (sureseatsDB);
		fs= new FilmService(sureseatsDB);

		try {
			loadSched(null);
			loadSeats(null);
			loadReservations(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    
    public void loadSched(ActionEvent event) throws IOException
    {
    	  ObservableList<Schedule> s_data = FXCollections.observableArrayList(
    	    		ss.getAll()
    	    	);
    	COL_SID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	COL_Start.setCellValueFactory(new PropertyValueFactory<>("start"));
    	COL_End.setCellValueFactory(new PropertyValueFactory<>("end"));
    	COL_sched_Cinema.setCellValueFactory(new PropertyValueFactory<>("cinema"));
    	COL_sched_FID.setCellValueFactory(new PropertyValueFactory<>("film"));
    	
    	Sched_Table.setItems(s_data);
    	
    }
    

    
    public void loadSeats(ActionEvent event) throws IOException
    {
    	  ObservableList<Seat> se_data = FXCollections.observableArrayList(
    	    		ses.getAll()
    	    	);
    	 COL__SeID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	 COL_SeRow.setCellValueFactory(new PropertyValueFactory<>("row"));
    	 Col_SeCol.setCellValueFactory(new PropertyValueFactory<>("col"));
    	 Col_SeCID.setCellValueFactory(new PropertyValueFactory<>("cinema"));
    
    	Seats_Table.setItems(se_data);
    	
    }
    
 /*   @FXML
    private TableView<Seat> Seats_Table;

    @FXML
    private TableColumn<Seat, Integer> COL__SeID;

    @FXML
    private TableColumn<Seat, String> COL_SeRow;

    @FXML
    private TableColumn<Seat, Integer> Col_SeCol;

    @FXML
    private TableColumn<Seat, Cinema> Col_SeCID;
*/
   
    

	
	
	public void searchSeats(ActionEvent event) throws IOException {
		loadSeats(null);
		try {
			ObservableList<Seat> se_data = FXCollections.observableArrayList();
			Seat s = ses.getSeat(Integer.parseInt(COL__SeID.getText()));
			if (s.getId() != 0)
				se_data.add(s);
			Seats_Table.setItems(se_data);
		} catch (Exception e) {
			System.out.println("Invalid ID");
		}
	}
	
	public void addSeats(ActionEvent event) throws IOException{
		try {
			Seat se = new Seat();
			se.setRow( COL_SeRow.getText());
			se.setCol( Integer.parseInt(Col_SeCol.getText()));
			se.setCinema(cs.getCinema(Integer.parseInt( SSeCID.getText())));
			ses.addSeat(se);
			loadSeats(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
    
    public void updateSeats(ActionEvent event) throws IOException{
    	try {
    		Seat s = ses.getSeat(Integer.parseInt( COL__SeID.getText()));
    		s.setRow( COL_SeRow.getText());
			s.setCol( Integer.parseInt(Col_SeCol.getText()));
			s.setCinema(cs.getCinema(Integer.parseInt( SSeCID.getText())));
			ses.updateSeat(s);
			loadSched(null);
    	} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
    
    public void deleteSeats(ActionEvent event) throws IOException{
    	try {
  		ses.deleteSeat(Integer.parseInt(COL__SeID.getText()));
  		loadSeats(null);
    	} catch (Exception e) {
			System.out.println("Invalid info");
		}
  	}
/*	@FXML
    private TableView<Schedule> Sched_Table;

    @FXML
    private TableColumn<Schedule, Integer> COL_SID;

    @FXML
    private TableColumn<Schedule, LocalDateTime> COL_Start;

    @FXML
    private TableColumn<Schedule, LocalDateTime> COL_End;

    @FXML
    private TableColumn<Schedule, Cinema> COL_sched_Cinema;

    @FXML*/

	
 /*   private TableColumn<Schedule, Film> COL_sched_FID;*/
    

    
    
    public void searchSched(ActionEvent event) throws IOException {
		loadSched(null);
		try {
			ObservableList<Schedule> s_data = FXCollections.observableArrayList();
			Schedule s = ss.getSchedule(Integer.parseInt(COL_SID.getText()));
			if (s.getId() != 0)
				s_data.add(s);
			Sched_Table.setItems(s_data);
		} catch (Exception e) {
			System.out.println("Invalid ID");
		}
	}
    
    public void deleteSched(ActionEvent event) throws IOException{
    	try {
		ss.deleteSchedule(Integer.parseInt(COL_SID.getText()));
		loadSched(null);
    	} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
    
    
	public void addSched(ActionEvent event) throws IOException{
		try {
			Schedule sched = ss.getSchedule(Integer.parseInt(COL_SID.getText()));
			sched.setStart(LocalDateTime.parse((CharSequence) COL_Start));
			sched.setEnd(LocalDateTime.parse((CharSequence)COL_End));
			sched.setCinema(cs.getCinema(Integer.parseInt( COL_sched_Cinema.getText())));
			sched.setFilm(fs.getFilm(Integer.parseInt( COL_sched_FID.getText())));
			ss.addSchedule(sched);
			loadSched(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
	public void updateSched(ActionEvent event) throws IOException{
		try {
		Schedule sched = ss.getSchedule(Integer.parseInt(COL_SID.getText()));
		sched.setStart(LocalDateTime.parse((CharSequence) COL_Start));
		sched.setEnd(LocalDateTime.parse((CharSequence)COL_End));
		sched.setCinema(cs.getCinema(Integer.parseInt( COL_sched_Cinema.getText())));
		sched.setFilm(fs.getFilm(Integer.parseInt( COL_sched_FID.getText())));
		ss.updateSchedule(sched);
		loadSched(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
	
    
    public void loadReservations(ActionEvent event) throws IOException
    {
    	  ObservableList<Reservation> se_data = FXCollections.observableArrayList(
    	    		rs.getAll()
    	    	);
    	  COL_RID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	  COL_Rcode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	  COL_Rtype.setCellValueFactory(new PropertyValueFactory<>("type"));
    	  COL_RDate.setCellValueFactory(new PropertyValueFactory<>("datetime"));
    	  Col_RStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    	  Col_RUID.setCellValueFactory(new PropertyValueFactory<>("user"));
    	  COL_RSEID.setCellValueFactory(new PropertyValueFactory<>("seat"));
    	  COL_RSID.setCellValueFactory(new PropertyValueFactory<>("schedule"));
    	  
    	  R_Table.setItems(se_data);
    	
    }
    
/*    @FXML
    private TableView<Reservation> R_Table;

    @FXML
    private TableColumn<Reservation, Integer> COL_RID;

    @FXML
    private TableColumn<Reservation, String> COL_Rcode;

    @FXML
    private TableColumn<Reservation, String> COL_Rtype;

    @FXML
    private TableColumn<Reservation, LocalDateTime> COL_RDate;

    @FXML
    private TableColumn<Reservation, String> Col_RStatus;

    @FXML
    private TableColumn<Reservation, User> Col_RUID;

    @FXML
    private TableColumn<Reservation, Seat> COL_RSEID;

    @FXML
    private TableColumn<Reservation, Schedule> COL_RSID;*/
    
  
 
    
    public void searchSReservation(ActionEvent event) throws IOException {
		loadReservations(null);
		try {
			ObservableList<Reservation> se_data = FXCollections.observableArrayList();
			Reservation r = rs.getReservation(Integer.parseInt(COL_RID.getText()));
			if (r.getId() != 0)
				se_data.add(r);
			R_Table.setItems(se_data);
		} catch (Exception e) {
			System.out.println("Invalid ID");
		}
    }
    
	public void addRes(ActionEvent event) throws IOException{
		try {
			Reservation r = rs.getReservation(Integer.parseInt(COL_RID.getText()));
			r.setCode( COL_Rcode.getText());
			r.setType( COL_Rtype.getText());
			r.setDatetime(LocalDateTime.parse((CharSequence) COL_Start));
			r.setStatus(Col_RStatus.getText());
			r.setUser(us.getUser(Integer.parseInt( Col_RUID.getText())));
			r.setSchedule(ss.getSchedule(Integer.parseInt(COL_RSID.getText())));
			r.setSeat(ses.getSeat(Integer.parseInt( COL_RSEID.getText())));
			rs.addReservation(r);
			loadReservations(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
    
    public void updateRes(ActionEvent event) throws IOException{
    	try {
		Reservation r = rs.getReservation(Integer.parseInt(COL_RID.getText()));
		r.setCode( COL_Rcode.getText());
		r.setType( COL_Rtype.getText());
		r.setDatetime(LocalDateTime.parse((CharSequence) COL_Start));
		r.setStatus(Col_RStatus.getText());
		r.setUser(us.getUser(Integer.parseInt( Col_RUID.getText())));
		r.setSchedule(ss.getSchedule(Integer.parseInt(COL_RSID.getText())));
		r.setSeat(ses.getSeat(Integer.parseInt( COL_RSEID.getText())));
		rs.updateReservation(r);
		loadReservations(null);
		} catch (Exception e) {
			System.out.println("Invalid info");
		}
	}
    
    public void deleteReservation(ActionEvent event) throws IOException{
    	try {
  		rs.deleteReservation(Integer.parseInt(COL_RID.getText()));
  		loadReservations(null);
    	} catch (Exception e) {
			System.out.println("Invalid info");
		}
  	}
    
    public void toback(ActionEvent event) throws IOException
    {
 	   FXMLLoader loader = new FXMLLoader();
 	   loader.setLocation(getClass().getResource("/sureseats/view/Admin_optionsA.fxml"));
        Parent tableViewParent = loader.load(); 
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void ToNext(ActionEvent event) throws IOException
    {
 	   FXMLLoader loader = new FXMLLoader();
 	   loader.setLocation(getClass().getResource("/sureseats/view/Admin_optionsC.fxml"));
        Parent tableViewParent = loader.load(); 
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    

}
