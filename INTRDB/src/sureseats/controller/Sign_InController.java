package sureseats.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

public class Sign_InController {
	 @FXML
	    private Button btnLogin;

	    @FXML
	    private TextField txtusername;

	    @FXML
	    private Text lblUsername;

	    @FXML
	    private PasswordField txtpassword;

	    @FXML
	    private Text lblPassword;
	    
	    public void Login(ActionEvent e ) {
	    	if(lblUsername.equals("Diane"))
	    		System.out.println("SAME NAME");
	    } 


}
