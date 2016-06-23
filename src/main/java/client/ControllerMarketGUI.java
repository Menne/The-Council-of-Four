package client;

import client.modelDTO.GameDTO;
import client.view.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControllerMarketGUI {
	private GUI view;
	private GameDTO clientGame;
	
	public void setClientGame(GameDTO clientGame) {
		this.clientGame=clientGame;
	}
	
	public void setView(GUI view) {
		this.view=view;
	}
	
	@FXML
	private HBox availablePermitTiles;
	
	@FXML
	private HBox availablePoliticCards;

	@FXML
	private HBox availableAssistants;
	
	@FXML
	private VBox offers;
	
	@FXML
	private Button sellPermitTile;
	
	@FXML
	private Button sellPoliticCard;
	
	@FXML
	private Button sellAssistant;
	
	@FXML
	private TextField pricePermitTile;
	
	@FXML
	private TextField pricePoliticCard;
	
	@FXML
	private TextField priceAssistant;
	
	@FXML
	private TextArea messageBox;

	public HBox getAvailablePermitTiles() {
		return availablePermitTiles;
	}

	public HBox getAvailablePoliticCards() {
		return availablePoliticCards;
	}

	public HBox getAvailableAssistants() {
		return availableAssistants;
	}

	public VBox getOffers() {
		return offers;
	}

	public Button getSellPermitTile() {
		return sellPermitTile;
	}

	public Button getSellPoliticCard() {
		return sellPoliticCard;
	}

	public Button getSellAssistant() {
		return sellAssistant;
	}

	public TextField getPricePermitTile() {
		return pricePermitTile;
	}

	public TextField getPricePoliticCard() {
		return pricePoliticCard;
	}

	public TextField getPriceAssistant() {
		return priceAssistant;
	}

	public TextArea getMessageBox() {
		return messageBox;
	}
	
	
}
