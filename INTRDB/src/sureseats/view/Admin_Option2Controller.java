package sureseats.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Admin_Option2Controller {

    @FXML
    private TableView<?> Films_Table;

    @FXML
    private TableView<?> Sched_Table;

    @FXML
    private TableView<?> Seats_Table;

    @FXML
    private TableView<?> R_Table;

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
    private TextField FFDate;

    @FXML
    private TextField FFTitle;

    @FXML
    private TextField FFCast;

    @FXML
    private TextField FFRuntime;

    @FXML
    private TextField FFGenre;

    @FXML
    private TextField FFRating;

    @FXML
    private TextField FFPrice;

    @FXML
    private TextField FFImage;

    @FXML
    private TextArea FFSynopsis;

    @FXML
    private TextField SStart;

    @FXML
    private TextField SSEnd;

    @FXML
    private TextField SCID;

    @FXML
    private TextField SFID;

    @FXML
    private TextField SSID;

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
    private TextField SSeID;

    @FXML
    private Button Seats_Search;

    @FXML
    private Button Seats_Delete;

    @FXML
    private Button Seats_Update;

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
    private Button gotoAdminA;

    @FXML
    private Button Sign_In;
    
    public void toback(ActionEvent event) throws IOException
    {
 	   FXMLLoader loader = new FXMLLoader();
 	   loader.setLocation(getClass().getResource("/sureseats/view/Admin_OptionsA.fxml"));
        Parent tableViewParent = loader.load(); 
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void ToSignIn(ActionEvent event) throws IOException
    {
 	   FXMLLoader loader = new FXMLLoader();
 	   loader.setLocation(getClass().getResource("/sureseats/view/Sign_In.fxml"));
        Parent tableViewParent = loader.load(); 
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    

}
