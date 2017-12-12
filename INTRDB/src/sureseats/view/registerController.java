package sureseats.view;

import javafx.scene.control.TextField;
import java.time.LocalDate;

import javafx.stage.Stage;
import sureseats.model.CityService;
import sureseats.model.MallService;
import sureseats.model.ProvinceService;
import sureseats.model.User;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;

import sureseats.model.*;

public class registerController {

	// here is the declaration of list of strings for the choice boxes
	// ObservableList<String> provinceList= FXCollections.observableArrayList({})
	// ObservableList<String> cityList= FXCollections.observableArrayList({})
	// ObservableList<String> preferredList= FXCollections.observableArrayList({})

	private User user;
	private SureseatsDB sdb;
	private ProvinceService ps;
	private CityService cs;
	private UserService us;
	private MallService ms;

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
	private ChoiceBox<Mall> rp1;

	@FXML
	private ChoiceBox<City> rCity;

	@FXML
	private ChoiceBox<Province> rProvince;

	@FXML
	private CheckBox rCBFemale;

	@FXML
	private CheckBox rCBMale;

	@FXML
	private DatePicker rBirthdate;

	@FXML
	private ChoiceBox<Mall> rp2;

	@FXML
	private ChoiceBox<Mall> rp3;

	@FXML
	private void handleFemale() {
		if (rCBFemale.isSelected())
			rCBMale.setSelected(false);
	}

	@FXML
	private void handleMale() {
		if (rCBMale.isSelected())
			rCBFemale.setSelected(false);
	}

	public void goMain(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sureseats/view/GUI.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}
	
	public String validate() {
		if (rUsername.getText().isEmpty())
			return "No username provided";
		if (rEmail.getText().isEmpty())
			return "No email address provided";
		if (rMobile.getText().isEmpty())
			return "No mobile number provided";
		if (rPassword.getText().isEmpty())
			return "No password provided";
		if (!rPassword.getText().equals(rVPassword.getText()))
			return "Did not retype password correctly";
		if (rFName.getText().isEmpty())
			return "No first name provided";
		if (rLName.getText().isEmpty())
			return "No last name provided";
		if (rBirthdate.getValue() == null)
			return "No birthdate provided";
		if (!rCBFemale.isSelected() && !rCBMale.isSelected())
			return "No gender provided";
		if (rCity.getValue() == null)
			return "No city provided";
		if (rProvince.getValue() == null)
			return "No province provided";
		if (rp1.getValue() == null)
			return "Need at least one preferred mall!";
		if (rp1.getValue() == rp2.getValue())
			return "Cannot have duplicate preferred malls!";
		if (rp2.getValue() == rp3.getValue())
			return "Cannot have duplicate preferred malls!";
		if (rp1.getValue() == rp3.getValue())
			return "Cannot have duplicate preferred malls!";
		if (us.getUser(rUsername.getText()).getId() != 0)
			return "Username is taken";
		if (us.getUserWithEmail(rEmail.getText()).getId() != 0)
			return "Email has already been used";
		return "";
	}

	public void confirmRegister(ActionEvent event) throws IOException {

		/*
		 * SureseatsDB sureseatsDB = new SureseatsDB(); ProvinceService pservice = new
		 * ProvinceService(sureseatsDB); List<Province> listOfProvinces =
		 * pservice.getAll()
		 */
		String msg = validate();
		if (msg.isEmpty()){
			user = new User();
			user.setEmail(rEmail.getText());
			user.setPassword(rPassword.getText());
			user.setFirstname(rFName.getText());
			user.setLastname(rLName.getText());
			user.setUsername(rUsername.getText());
			user.setMobileno(rMobile.getText());
			if (rCBFemale.isSelected())
				user.setGender("Female");
			else if (rCBMale.isSelected())
				user.setGender("Male");
			user.setBdate(rBirthdate.getValue());
			user.setIslocked(false);
			user.setProvince(rProvince.getValue());
			user.setCity(rCity.getValue());
			
			user = us.addUser(user);
			
			us.addPreferred(user, rp1.getValue());
			us.addPreferred(user, rp2.getValue());
			us.addPreferred(user, rp3.getValue());

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sign Up Complete!");
			alert.setHeaderText(" Registration confirmed ");
			alert.setContentText(" Registration is successful !");

			alert.showAndWait();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/sureseats/view/GUI.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			// This line gets the Stage information
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Registration unsuccessful");
			alert.setContentText(msg);

			alert.showAndWait();
		}
	}

	@FXML
	public void initialize() {
		sdb = new SureseatsDB();
		ps = new ProvinceService(sdb);
		cs = new CityService(sdb);
		us = new UserService(sdb);
		ms = new MallService(sdb);

		ObservableList<Province> provinceList = FXCollections.observableArrayList(ps.getAll());
		ObservableList<City> cityList = FXCollections.observableArrayList(cs.getAll());
		ObservableList<Mall> mallList = FXCollections.observableArrayList(ms.getAll());

		rProvince.setItems(provinceList);
		rCity.setItems(cityList);
		rp1.setItems(mallList);
		rp2.setItems(mallList);
		rp3.setItems(mallList);

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
