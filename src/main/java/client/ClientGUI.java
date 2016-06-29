package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class to launch if the user wants to play from a GUI.
 * @author Luca Scannapieco
 *
 */
public class ClientGUI extends Application {

	private Stage primaryStage;
    private ScrollPane rootLayout;
    
    /**
     * Sets the primary stage
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("The Council of Four");
		initRootLayout();
		welcome();
 	}
	
	/**
	 * Loads and shows the root layout of our application.
	 */
	public void initRootLayout(){
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("RootLayout.fxml"));
		 try {
			 
			rootLayout=(ScrollPane) loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Scene scene = new Scene(rootLayout);
		 primaryStage.setScene(scene);
		 primaryStage.show();
	}
	
	/**
	 * Loads and shows the login screen of our application.
	 */
	public void welcome(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Welcome.fxml"));
		try {
			
			AnchorPane welcomeOverview = (AnchorPane) loader.load();
			rootLayout.setContent(welcomeOverview);
			
			ControllerLogin controllerLogin= loader.getController();
			controllerLogin.setClientGUI(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	/**
	 * Loads the screen of the game.
	 * @return the controller of the screen, got from the fxmlLoader.
	 */
	public ControllerGUI showGame(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GUIGioco.fxml"));
		try {
			
			AnchorPane gameOverwiew=(AnchorPane) loader.load();
			rootLayout.setContent(gameOverwiew);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loader.getController();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public ControllerMarketGUI showMarket() {
		// TODO Auto-generated method stub
		return null;
	}
}
