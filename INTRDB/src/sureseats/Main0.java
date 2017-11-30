package sureseats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main0 extends Application {
	
	  @Override
	    public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("/sureseats/view/GUI.fxml"));
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
	  
	  public void register(Stage stage) throws Exception {
		  Parent root = FXMLLoader.load(getClass().getResource("/sureseats/view/Register.fxml"));
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	  }
	  
	  
	  public static void main(String[] args) {
	        launch(args);
	    }

}
