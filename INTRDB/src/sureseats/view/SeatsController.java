package sureseats.view;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SeatsController {




	    @FXML
	    private Button to_Back;

	    @FXML
	    private Button to_Reserve;

	    @FXML
	    private GridPane Seats_pane;

	    
	    public void initialize()
	    {
	    	int r,c;
	    	int i;
	    	char[] row= new char[26];
	    	for(i=65;i<90;i++)
	    		row[i-65]= ((char)i);
	    	
	    	
	    	
	    }
	    
	    
	    public void toback(ActionEvent event) throws IOException
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
