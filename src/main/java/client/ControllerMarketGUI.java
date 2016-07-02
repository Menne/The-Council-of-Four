package client;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.AssistantDTO;
import client.view.GUI;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller class for the market screen
 * @author Luca Scannapieco
 *
 */
public class ControllerMarketGUI {
	private GUI view;
	private GameDTO clientGame;
	private Stage merketStage;
	
	
	/**
	 * @return the stage where the market screen is shown
	 */
	public Stage getMerketStage() {
		return merketStage;
	}

	/**
	 * Sets the stage where to show the market
	 * @param merketStage
	 */
	public void setMerketStage(Stage merketStage) {
		this.merketStage = merketStage;
	}

	/**
	 * Sets the model DTO of the client
	 * @param clientGame
	 */
	public void setClientGame(GameDTO clientGame) {
		this.clientGame=clientGame;
	}
	
	/**
	 * Sets the instance of the client view
	 * @param view
	 */
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
	
	
	/**
	 * @return the reference to the button to do the action accept an offer
	 */
	public Button getAcceptAnOffer() {
		return acceptAnOffer;
	}

	/**
	 * @return the list of buttons for the market actions
	 */
	public List<Button> getActions() {
		return Arrays.asList(makeAnOffer, acceptAnOffer, skip);
	}

	/**
	 * @return the reference to the container of the permit tiles that the client can sell
	 */
	public HBox getAvailablePermitTiles() {
		return availablePermitTiles;
	}

	/**
	 * @return the reference to the container of the politics card that the client can sell
	 */
	public HBox getAvailablePoliticCards() {
		return availablePoliticCards;
	}

	/**
	 * @return the reference to the container of the assistants that the client can sell
	 */
	public HBox getAvailableAssistants() {
		return availableAssistants;
	}

	/**
	 * @return the reference to the container of the several offer that the player can accept
	 */
	public VBox getOffers() {
		return offers;
	}

	/**
	 * @return the reference to the market text area
	 */
	public TextArea getMessageBox() {
		return messageBox;
	}
	
	/**
	 * @return the reference to the button to do a sell action
	 */
	public Button getSell() {
		return sell;
	}

	/**
	 * @return the reference to the text field containing the chosen price for the selling object
	 */
	public TextField getPrice() {
		return price;
	}

	/**
	 * Is the method called by clicking on an action button.
	 * If the chosen action does not need parameters we directly send it through the connection;
	 * if it needs parameters we need a new thread, different to the thread application to set the parameters.
	 * @param event is the event to handle with the method
	 * @throws RemoteException if something goes wrong sending the action through the connection
	 */
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
								Logger logger=Logger.getAnonymousLogger();
								logger.log(Level.SEVERE, "Failed to send action with RMI", e);
							}
						}
					});
					return;
				}
		this.view.displayError("Sorry, action not available!");
	}
	
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	public void handlePoliticsCard(PoliticsCardDTO selectedCard) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedCard);
			this.notify();
		}
	}

	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	public void handlePermitTilesTurnedUp(PermitTileDTO selectedPermitTile) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedPermitTile);
			this.notify();
		}
	}
	
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	public void handleAssistants(AssistantDTO selectedAssistant) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedAssistant);
			this.notify();
		}
	}
	
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	public void handlePriceForm(int price) {
		synchronized (this) {
			this.view.setCurrentParameter(price);
			this.notify();
		}
	}
	
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	public void handleOfferSetted() {
		
		synchronized (this) {
			while(true){
				try{
					int priceInt=Integer.parseInt(price.getText());
					this.view.setCurrentParameter(priceInt);
					break;
				}catch(NumberFormatException e){
					Alert alert=new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Wrong price. Try again");
					alert.showAndWait();
					return;
				}
			}
			this.notify();
		}
	}
	
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	public void handleOffers(OfferDTO selectedOffer) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedOffer);
			this.notify();
		}
	}
	
	/**
	 * Changes the mouse style
	 * @param mouseEvent
	 */
	@FXML
	public void changeMouseStyle(MouseEvent mouseEvent) {
		Platform.runLater(new Runnable() {
		        
			@Override
			public void run() {
				((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
			}
		});
	}
	
	
	
}
