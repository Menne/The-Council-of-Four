package client;

import java.io.IOException;

import client.view.GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientGUI extends Application {

	private Stage primaryStage;
    private BorderPane rootLayout;
     	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("The Council of Four");
		initRootLayout();
		welcome();
 	}
	
	public void initRootLayout(){
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("RootLayout.fxml"));
		 try {
			 
			rootLayout=(BorderPane) loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Scene scene = new Scene(rootLayout);
		 primaryStage.setScene(scene);
		 primaryStage.show();
	}
	
	public void welcome(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Welcome.fxml"));
		try {
			
			AnchorPane welcomeOverview = (AnchorPane) loader.load();
			rootLayout.setCenter(welcomeOverview);
			
			ControllerLogin controllerLogin= loader.getController();
			controllerLogin.setClientGUI(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public ControllerGUI getControllerGUI(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GUIGioco.fxml"));
		try {
			AnchorPane gameOverwiew=(AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loader.getController();
	}
	
	public void showGame(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GUIGioco.fxml"));
		try {
			
			AnchorPane gameOverwiew=(AnchorPane) loader.load();
			rootLayout.setCenter(gameOverwiew);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
