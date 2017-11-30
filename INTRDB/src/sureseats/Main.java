package sureseats;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	private Stage primaryStage;
	private static AnchorPane mainLayout;
	
	
	 @Override
	 public void start(Stage primaryStage) throws IOException {
		 this.setPrimaryStage(primaryStage);
		 showMain();
	 }
	 
	 public void showMain() throws IOException {
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(Main.class.getResource("/sureseats/view/GUI.fxml"));
		 setMainLayout(loader.load()); 
		 Scene GUI = new Scene(mainLayout);
		 primaryStage.setScene(GUI);
		 primaryStage.show();
		 primaryStage.setResizable(false);
	 }
	
	public static void main (String[] args)
	{
		launch(args);
	}
	
	public void showScene() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(Main.class.getResource("/sureseats/view/Register.fxml"));
		 AnchorPane register =loader.load();
		 mainLayout= (register);
	}
	
	

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public AnchorPane getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(AnchorPane mainLayout) {
		this.mainLayout = mainLayout;
	}
}
