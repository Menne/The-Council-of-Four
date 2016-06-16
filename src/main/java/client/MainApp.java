package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
    private BorderPane rootLayout;
    private final GUI viewGUI;

    public MainApp(GUI viewGUI) {
		this.viewGUI=viewGUI;
	}
     	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("The Council of Four");
		initRootLayout();
		showGame();
 	}
	
	public void initRootLayout(){
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(GUI.class.getResource("RootLayout.fxml"));
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
	
	public void showGame(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GUIGioco.fxml"));
		try {
			
			AnchorPane gameOverwiew=(AnchorPane) loader.load();
			rootLayout.setCenter(gameOverwiew);
			
			loader.getController();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
