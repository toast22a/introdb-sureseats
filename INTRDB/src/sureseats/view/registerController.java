package sureseats.view;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;

public class registerController {

    @FXML
    private Button rRegister;

    @FXML
    private Button rback;

    @FXML
    private TextField rUsername;

    @FXML
    private TextField rEmail;

    @FXML
    private TextField rMobile;

    @FXML
    private TextField rFName;

    @FXML
    private TextField rLName;

    @FXML
    private PasswordField rPassword;

    @FXML
    private PasswordField rVPassword;

    @FXML
    private ChoiceBox<?> rp1;

    @FXML
    private ChoiceBox<?> rCity;

    @FXML
    private ChoiceBox<?> rProvince;

    @FXML
    private CheckBox rCBFemale;

    @FXML
    private CheckBox rCBMale;

    @FXML
    private DatePicker rBirthdate;

    @FXML
    private ChoiceBox<?> rp2;

    @FXML
    private ChoiceBox<?> rp3;
    
    public void goMain(ActionEvent event) throws IOException
    {
 	   FXMLLoader loader = new FXMLLoader();
 	   loader.setLocation(getClass().getResource("/sureseats/view/GUI.fxml"));
        Parent tableViewParent = loader.load(); 
        Scene tableViewScene = new Scene(tableViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

}
