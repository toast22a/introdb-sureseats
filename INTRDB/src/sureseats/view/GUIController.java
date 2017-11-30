package sureseats.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class GUIController {
    @FXML
    private AnchorPane apMain;

    @FXML
    private Button moviesBtnRegister;



	public Button getMoviesBtnRegister() {
		return moviesBtnRegister;
	}

	public void setMoviesBtnRegister(Button moviesBtnRegister) {
		this.moviesBtnRegister = moviesBtnRegister;
	}

	public Button getMoviesBtnLogin() {
		return moviesBtnLogin;
	}

	public void setMoviesBtnLogin(Button moviesBtnLogin) {
		this.moviesBtnLogin = moviesBtnLogin;
	}

	public ImageView getPanel00() {
		return panel00;
	}

	public void setPanel00(ImageView panel00) {
		this.panel00 = panel00;
	}

	public ImageView getPanel01() {
		return panel01;
	}

	public void setPanel01(ImageView panel01) {
		this.panel01 = panel01;
	}

	public ImageView getPanel02() {
		return panel02;
	}

	public void setPanel02(ImageView panel02) {
		this.panel02 = panel02;
	}

	public ImageView getPanel03() {
		return panel03;
	}

	public void setPanel03(ImageView panel03) {
		this.panel03 = panel03;
	}

	public ImageView getPanel04() {
		return panel04;
	}

	public void setPanel04(ImageView panel04) {
		this.panel04 = panel04;
	}

	public ImageView getPanel05() {
		return panel05;
	}

	public void setPanel05(ImageView panel05) {
		this.panel05 = panel05;
	}



	@FXML
    private Button moviesBtnLogin;

    @FXML
    private ImageView panel00;

    @FXML
    private ImageView panel01;

    @FXML
    private ImageView panel02;

    @FXML
    private ImageView panel03;

    @FXML
    private ImageView panel04;

    @FXML
    private ImageView panel05;
    @FXML
    private Button login1;

   
/*   @FXML
   private void reg() throws Exception{
	   Stage stage = (Stage) apMain.getScene().getWindow();
	   main.register(stage);
   }*/
   
   
/* @FXML
   private void reg() throws IOException {
	 apMain.getChildren().setAll(FXMLLoader.load("sureseats/view/register.fxml"));
   }
   */
   
   public void gotoreg(ActionEvent event) throws IOException
   {
	   FXMLLoader loader = new FXMLLoader();
	   loader.setLocation(getClass().getResource("/sureseats/view/register.fxml"));
       Parent tableViewParent = loader.load(); 
       Scene tableViewScene = new Scene(tableViewParent);
       //This line gets the Stage information
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       
       window.setScene(tableViewScene);
       window.show();
   }
  
   public void gotologin (ActionEvent event) throws IOException
   {
	   FXMLLoader loader = new FXMLLoader();
	   loader.setLocation(getClass().getResource("/sureseats/view/sign_in.fxml"));
       Parent tableViewParent = loader.load(); 
       Scene tableViewScene = new Scene(tableViewParent);
       //This line gets the Stage information
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       
       window.setScene(tableViewScene);
       window.show();
   }
   
   public void gotoSched (ActionEvent event) throws IOException
   {
	   FXMLLoader loader = new FXMLLoader();
	   loader.setLocation(getClass().getResource("/sureseats/view/schedule.fxml"));
       Parent tableViewParent = loader.load(); 
       Scene tableViewScene = new Scene(tableViewParent);
       //This line gets the Stage information
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       
       window.setScene(tableViewScene);
       window.show();
   }
   
          
   }


