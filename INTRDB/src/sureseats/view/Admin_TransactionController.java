package sureseats.view;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Admin_TransactionController {

    @FXML
    private Button Back;

    @FXML
    private Button admin1Next;

    @FXML
    private Tab Claimed_Table;

    @FXML
    private TableView<?> Unclaimed_Table;

    @FXML
    private TableView<?> Active_Table;

    @FXML
    private TableView<?> Inactive_Table;

    @FXML
    private TableView<?> Genre_Tickets_Table;



    @FXML
    void ToSignIn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
  	   loader.setLocation(getClass().getResource("/sureseats/view/Sign_In.fxml"));
         Parent tableViewParent = loader.load(); 
         Scene tableViewScene = new Scene(tableViewParent);
         //This line gets the Stage information
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         
         window.setScene(tableViewScene);
         window.show();
    }
    
    public void toback(ActionEvent event) throws IOException
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
