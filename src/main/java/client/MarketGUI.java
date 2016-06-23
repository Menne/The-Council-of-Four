package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MarketGUI extends Application {
	
	private Stage primaryStage;
	private AnchorPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;

	}
	
	public ControllerMarketGUI showMarket(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MarketView.fxml"));
		try {
			
			this.root=loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		return loader.getController();
	}

}
