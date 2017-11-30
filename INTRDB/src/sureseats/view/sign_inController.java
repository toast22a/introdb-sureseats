package sureseats.view;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class sign_inController {

    @FXML
    private Button LBSignIn;

    @FXML
    private TextField Lusername;

    @FXML
    private PasswordField Lpassword;
    
    
    public void gotoNext(ActionEvent event) throws IOException
    {
    	if(Lusername.getText().equals("admin"))
    	{
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/sureseats/view/admin_options.fxml"));
    		Parent tableViewParent = loader.load(); 
    		Scene tableViewScene = new Scene(tableViewParent);
    		//This line gets the Stage information
    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow(); 
    		window.setScene(tableViewScene);
    		window.show();
    	}
    	else
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

}

