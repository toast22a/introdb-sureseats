package sureseats.view;

import java.io.IOException;
import java.net.URL;

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
import sureseats.Main0;

public class GUIController {
    @FXML
    private AnchorPane apMain;

    @FXML
    private Button register1;

    public Button getRegister1() {
		return register1;
	}

	public void setRegister1(Button register1) {
		this.register1 = register1;
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
    private Button Login;

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

   private Main0 main;
   
   @FXML
   private void reg() throws Exception{
	   Stage stage = (Stage) apMain.getScene().getWindow();
	   main.register(stage);
   }
   
   
/* @FXML
   private void reg() throws IOException {
	 apMain.getChildren().setAll(FXMLLoader.load("sureseats/view/register.fxml"));
   }
   */
   
   public void changeScreenButtonPushed(ActionEvent event) throws IOException
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
   
     @FXML
   private void ChangeScene(ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       
       String sceneFile = "/sureseats/view/Register.fxml";
       Parent root = null;
       URL    url  = null;
       try
       {
           url  = getClass().getResource( sceneFile );
           root = FXMLLoader.load( url );
           System.out.println( "  fxmlResource = " + sceneFile );
       }
       catch ( Exception ex )
       {
           System.out.println( "Exception on FXMLLoader.load()" );
           System.out.println( "  * url: " + url );
           System.out.println( "  * " + ex );
           System.out.println( "    ----------------------------------------\n" );
           throw ex;
       }
       
       root = (AnchorPane) FXMLLoader.load(getClass().getResource("/sureseats/view/Register.fxml"));
       Scene scene = new Scene(root);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide(); //optional
               app_stage.setScene(scene);
               app_stage.show();  
               }
     
     @FXML private void handleButtonAction(ActionEvent e)throws IOException{
    	 Stage stage; 
    	 Parent root;
    	
    	 if(e.getSource()==register1)
    	 {
    		 stage=(Stage) register1.getScene().getWindow();
    		 root = FXMLLoader.load(getClass().getResource("/sureseats/view/Register.fxml"));
    	 }else
    		 stage=(Stage) login1.getScene().getWindow();
		 root = FXMLLoader.load(getClass().getResource("/sureseats/view/Sign_Inr.fxml"));
		 Scene scene = new Scene(root);
	     	stage.setScene(scene);
	     	stage.show();
     }
     	
          
   }


