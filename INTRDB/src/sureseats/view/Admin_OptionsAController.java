package sureseats.view;

import java.io.IOException;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Admin_OptionsAController {

    @FXML
    private Tab User_Malls;

    @FXML
    private TableView<?> Users_Table;

    @FXML
    private TableView<?> Province_Table;

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
    
    public void toNext(ActionEvent event) throws IOException
    {
 	   FXMLLoader loader = new FXMLLoader();
 	   loader.setLocation(getClass().getResource("/sureseats/view/Admin_optionsB.fxml"));
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
