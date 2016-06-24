package client;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.AssistantDTO;
import client.view.GUI;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private Button sell;
	
	@FXML
	private TextField price;
	
	@FXML
	private TextArea messageBox;
	
	@FXML
	private Button makeAnOffer;
	
	@FXML
	private Button acceptAnOffer;
	
	@FXML
	private Button skip;
	
	
	public Button getSkip() {
		return skip;
	}

	public Button getMakeAnOffer() {
		return makeAnOffer;
	}
	
	

	public Button getAcceptAnOffer() {
		return acceptAnOffer;
	}

	public List<Button> getActions() {
		return Arrays.asList(makeAnOffer, acceptAnOffer, skip);
	}

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

	public TextArea getMessageBox() {
		return messageBox;
	}
	
	public Button getSell() {
		return sell;
	}

	public TextField getPrice() {
		return price;
	}

	@FXML
	public void startAction(Event event) throws RemoteException {
		ActionDTO selectedAction=(ActionDTO) ((Button) event.getSource()).getUserData();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action.getClass()==(selectedAction.getClass())) 
				if (!(selectedAction instanceof ActionWithParameters)) {
					this.view.getConnection().sendAction(selectedAction);
					return;
				}
				else if (selectedAction instanceof ActionWithParameters) {
					ExecutorService executor=Executors.newSingleThreadExecutor();
					executor.submit(new Runnable() {
						
						@Override
						public void run() {
							try {
								view.insertParametersAndSend((ActionWithParameters) selectedAction);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					this.view.disableActionButtons(false);
					return;
				}
		this.view.displayError("Sorry, action not available!");
	}
	
	
	public void handlePoliticsCard(PoliticsCardDTO selectedCard) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedCard);
			this.notify();
		}
	}

	public void handlePermitTilesTurnedUp(PermitTileDTO selectedPermitTile) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedPermitTile);
			this.notify();
		}
	}
	
	public void handleAssistants(AssistantDTO selectedAssistant) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedAssistant);
			this.notify();
		}
	}
	
	public void handlePriceForm(int price) {
		synchronized (this) {
			this.view.setCurrentParameter(price);
			this.notify();
		}
	}
	
	public void handleOfferSetted() {
		synchronized (this) {
			this.view.setCurrentParameter(Integer.parseInt(price.getText()));
			this.notify();
		}
	}
	
	public void handleOffers(OfferDTO selectedOffer) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedOffer);
			this.notify();
		}
	}
	
	
	
}
