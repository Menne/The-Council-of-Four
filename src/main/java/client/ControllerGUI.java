package client;

import java.rmi.RemoteException;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.ChatMessageDTO;
import client.modelDTO.actionsDTO.PickPoliticsCardDTO;
import client.modelDTO.actionsDTO.QuitDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.GUI;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * It's the controller class for the game screen.
 * @author Luca Scannapieco
 *
 */
public class ControllerGUI {
	
	private GameDTO clientGame;
	private GUI view;
	
	@FXML
	private AnchorPane root;
	
	@FXML
	private Tab tabPlayer1;
	
	@FXML
	private Tab tabPlayer2;
	
	@FXML
	private Tab tabPlayer3;
	
	@FXML
	private Tab tabPlayer4;
	
	@FXML
	private ImageView mapImage;

	@FXML
	private ImageView colourPlayer1;
	
	@FXML
	private ImageView colourPlayer2;
	
	@FXML
	private ImageView colourPlayer3;
	
	@FXML
	private ImageView colourPlayer4;

	@FXML
	private HBox emporiumsArkon;

	@FXML
	private HBox emporiumsBurgen;

	@FXML
	private HBox emporiumsCastrum;

	@FXML
	private HBox emporiumsDorful;

	@FXML
	private HBox emporiumsEsti;

	@FXML
	private HBox emporiumsFramek;

	@FXML
	private HBox emporiumsGraden;

	@FXML
	private HBox emporiumsHellar;

	@FXML
	private HBox emporiumsIndur;

	@FXML
	private HBox emporiumsJuvelar;

	@FXML
	private HBox emporiumsKultos;

	@FXML
	private HBox emporiumsLyram;

	@FXML
	private HBox emporiumsMerkatim;

	@FXML
	private HBox emporiumsNaris;

	@FXML
	private HBox emporiumsOsium;

	@FXML
	private ImageView seaRegion;
	
	@FXML
	private ImageView hillRegion;
	  
	@FXML
	private ImageView mountainRegion;
	
	@FXML
	private ImageView kingBalcony;
	
	@FXML
	private ImageView seaBalcony;
	
	@FXML
	private ImageView hillBalcony;
	
	@FXML
	private ImageView mountainBalcony;
	
	@FXML
	private ImageView seaConcillor1;
	
	@FXML
	private ImageView seaConcillor2;
	
	@FXML
	private ImageView seaConcillor3;
	
	@FXML
	private ImageView seaConcillor4;
	
	@FXML
	private ImageView hillConcillor1;
	
	@FXML
	private ImageView hillConcillor2;
	
	@FXML
	private ImageView hillConcillor3;
	
	@FXML
	private ImageView hillConcillor4;
	
	@FXML
	private ImageView mountainConcillor1;
	
	@FXML
	private ImageView mountainConcillor2;
	
	@FXML
	private ImageView mountainConcillor3;
	
	@FXML
	private ImageView mountainConcillor4;
	
	@FXML
	private ImageView kingConcillor1;
	
	@FXML
	private ImageView kingConcillor2;
	
	@FXML
	private ImageView kingConcillor3;
	
	@FXML
	private ImageView kingConcillor4;
	
	@FXML
	private ImageView reserveConcillor1;
	
	@FXML
	private ImageView reserveConcillor2;
	
	@FXML
	private ImageView reserveConcillor3;
	
	@FXML
	private ImageView reserveConcillor4;
	
	@FXML
	private ImageView reserveConcillor5;
	
	@FXML
	private ImageView reserveConcillor6;
	
	@FXML
	private ImageView reserveConcillor7;
	
	@FXML
	private ImageView reserveConcillor8;
	
	@FXML
	private Button m1; 
	
	@FXML
	private Button m2;
	
	@FXML
	private Button m3;
	
	@FXML
	private Button m4;
	
	@FXML
	private Button q1;
	
	@FXML
	private Button q2; 
	
	@FXML
	private Button q3;
	
	@FXML
	private Button q4;
	
	@FXML
	private Button skip;
	
	@FXML
	private ImageView rewardTokenA;
	
	@FXML
	private ImageView rewardTokenB;

	@FXML
	private ImageView rewardTokenC;
	
	@FXML
	private ImageView rewardTokenD;
	
	@FXML
	private ImageView rewardTokenE;
	
	@FXML
	private ImageView rewardTokenF;
	
	@FXML
	private ImageView rewardTokenG;
	
	@FXML
	private ImageView rewardTokenH;

	@FXML
	private ImageView rewardTokenI;
	
	@FXML
	private ImageView rewardTokenJ;
	
	@FXML
	private ImageView rewardTokenK;
	
	@FXML
	private ImageView rewardTokenL;
	
	@FXML
	private ImageView rewardTokenM;
	
	@FXML
	private ImageView rewardTokenN;
	
	@FXML
	private ImageView rewardTokenO;
	
	@FXML
	private ImageView permitTileSea0;
	
	@FXML
	private ImageView permitTileSea1;
	
	@FXML
	private ImageView permitTileHill0;
	
	@FXML
	private ImageView permitTileHill1;
	
	@FXML
	private ImageView permitTileMountain0;
	
	@FXML
	private ImageView permitTileMountain1;
	
	@FXML
	private Pane arkon;
	
	@FXML
	private Pane burgen;
	
	@FXML
	private Pane castrum;
	
	@FXML
	private Pane dorful;
	
	@FXML
	private Pane esti;
	
	@FXML
	private Pane framek;
	
	@FXML
	private Pane graden;
	
	@FXML
	private Pane hellar;
	
	@FXML
	private Pane indur;
	
	@FXML
	private Pane juvelar;
	
	@FXML
	private Pane kultos;
	
	@FXML
	private Pane lyram;
	
	@FXML
	private Pane merkatim;
	
	@FXML
	private Pane naris;
	
	@FXML
	private Pane osium;
	
	@FXML
	private ImageView politicsDeck;
	
	@FXML
	private ImageView regionBonusSea;
	
	@FXML
	private ImageView regionBonusHill;
	
	@FXML
	private ImageView regionBonusMountain;
	
	@FXML
	private ImageView colorBonusBlue;
	
	@FXML
	private ImageView colorBonusGold;
	
	@FXML
	private ImageView colorBonusSilver;
	
	@FXML
	private ImageView colorBonusBronze;
	
	@FXML
	private ImageView kingRewardTile1;
	
	@FXML
	private ImageView kingRewardTile2;
	
	@FXML
	private ImageView kingRewardTile3;
	
	@FXML
	private ImageView kingRewardTile4;

	@FXML
	private ImageView kingRewardTile5;
	
	@FXML
	private VBox councillorReserve;
	
	@FXML
	private Label scorePlayer1;
	
	@FXML
	private Label scorePlayer2;
	
	@FXML
	private Label scorePlayer3;
	
	@FXML
	private Label scorePlayer4;
	
	@FXML
	private Label nobilityPlayer1;
	
	@FXML
	private Label nobilityPlayer2;
	
	@FXML
	private Label nobilityPlayer3;
	
	@FXML
	private Label nobilityPlayer4;
	
	@FXML
	private Label coinsPlayer1;
	
	@FXML
	private Label coinsPlayer2;
	
	@FXML
	private Label coinsPlayer3;
	
	@FXML
	private Label coinsPlayer4;
	
	@FXML
	private Label assistantsPlayer1;
	
	@FXML
	private Label assistantsPlayer2;
	
	@FXML
	private Label assistantsPlayer3;
	
	@FXML
	private Label assistantsPlayer4;
	
	@FXML
	private Label remainingEmporiumPlayer1;
	
	@FXML
	private Label remainingEmporiumPlayer2;
	
	@FXML
	private Label remainingEmporiumPlayer3;
	
	@FXML
	private Label remainingEmporiumPlayer4;
	
	@FXML
	private Label namePlayer1;
	
	@FXML
	private Label namePlayer2;
	
	@FXML
	private Label namePlayer3;
	
	@FXML
	private Label namePlayer4;
	
	@FXML
	private Label playerName;
	
	@FXML
	private Label playerScore;
	
	@FXML
	private Label playerCoins;
	
	@FXML
	private Label playerAssistants;
	
	@FXML
	private Label playerNobility;
	
	@FXML
	private HBox hand;
	
	@FXML
	private Label numberOfCoveredPermitTilePlayer1;
	
	@FXML
	private Label numberOfCoveredPermitTilePlayer2;
	
	@FXML
	private Label numberOfCoveredPermitTilePlayer3;
	
	@FXML
	private Label numberOfCoveredPermitTilePlayer4;
	
	@FXML
	private Label numberOfPoliticsCardInHandPlayer1;
	
	@FXML
	private Label numberOfPoliticsCardInHandPlayer2;
	
	@FXML
	private Label numberOfPoliticsCardInHandPlayer3;
	
	@FXML
	private Label numberOfPoliticsCardInHandPlayer4;
	
	@FXML
	private TextArea messageBox;
	
	@FXML
	private TextArea chatInputBox;
	
	@FXML
	private HBox permitTilesTurnedUpPlayer1;
	
	@FXML
	private HBox permitTilesTurnedUpPlayer2;
	
	@FXML
	private HBox permitTilesTurnedUpPlayer3;
	
	@FXML
	private HBox permitTilesTurnedUpPlayer4;
	
	@FXML
	private HBox permitTilesTurnedUpOwned;
	
	@FXML
	private HBox permitTilesTurnedDownOwned;
	
	@FXML
	private Button descardPoliticsCards;
	
	@FXML
	private VBox playersBonuses;
	
	@FXML
	private HBox genericPlayerBonuses1;
	
	@FXML
	private HBox genericPlayerBonuses2;
	
	@FXML
	private HBox genericPlayerBonuses3;
	
	@FXML
	private HBox genericPlayerBonuses4;
	
	@FXML
	private List<ImageView> politicsCards;
	
	@FXML
	private List<ImageView> permitTilesTurnedUp;
	
	@FXML
	private List<ImageView> permitTilesTurnedDown;
	
	@FXML
	private ImageView emporium;
	
	@FXML
	private Label numberOfEmporiums;
	
	public Label getNumberOfEmporiums() {
		return numberOfEmporiums;
	}

	public ImageView getEmporium() {
		return emporium;
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
	 **/
	public void setView(GUI view) {
		this.view=view;
	}
	
	
	public AnchorPane getRoot() {
		return root;
	}
	
	
	public void setPoliticsCards(List<ImageView> politicsCards) {
		this.politicsCards=politicsCards;
	}
	
	public void setPermitTilesTurnedUp(List<ImageView> permitTilesTurnedUp) {
		this.permitTilesTurnedUp=permitTilesTurnedUp;
	}
	
	public void setPermitTilesTurnedDown(List<ImageView> permitTilesTurnedDown) {
		this.permitTilesTurnedDown=permitTilesTurnedDown;
	}
	
	/**
	 * @return the list of players tab showing the state of all the players of the game
	 */
	public List<Tab> getPlayersTabs(){
		return Arrays.asList(tabPlayer1, tabPlayer2, tabPlayer3, tabPlayer4);
	}

	/**
	 * @return the ImageView containing the map of the game
	 */
	public ImageView getMapImage() {
		return mapImage;
	}

	/**
	 * @return the list of button actions
	 */
	public List<Button> getActions() {
		return Arrays.asList(m1, m2, m3, m4, q1, q2, q3, q4, skip);
	}

	/**
	 * @return the ImageView containing the politics deck
	 */
	public ImageView getPoliticsDeck() {
		return politicsDeck;
	}
	
	/**
	 * @return the list of HBox containing the bonus tile got from each player of the game
	 */
	public List<HBox> getGenericPlayerBonuses(){
		return Arrays.asList(genericPlayerBonuses1,genericPlayerBonuses2,genericPlayerBonuses3,genericPlayerBonuses4);
	}
	
	/**
	 * @return the list of the 3 ImageView of the Bonus Tiles of the Regions
	 */
	public List<ImageView> getRegionBonusTiles(){
		return Arrays.asList(regionBonusSea,regionBonusHill,regionBonusMountain);
	}
	
	/**
	 * @return the ImageView of the 5 king reward tiles
	 */
	public List<ImageView> getKingBonusTiles(){
		return Arrays.asList(kingRewardTile1,kingRewardTile2, kingRewardTile3, kingRewardTile4, kingRewardTile5);
	}
	
	/**
	 * @return the ImageView containing blue colour bonus tile
	 */
	public ImageView getColorBonusBlue() {
		return colorBonusBlue;
	}

	/**
	 * @return the ImageView containing gold colour bonus tile
	 */
	public ImageView getColorBonusGold() {
		return colorBonusGold;
	}

	/**
	 * @return the ImageView containing silver colour bonus tile
	 */
	public ImageView getColorBonusSilver() {
		return colorBonusSilver;
	}

	/**
	 * @return the ImageView containing bronze colour bonus tile
	 */
	public ImageView getColorBonusBronze() {
		return colorBonusBronze;
	}

	/**
	 * @return the VBox containing the bonus tiles got from the player
	 */
	public VBox getPlayersBonuses() {
		return playersBonuses;
	}

	/**
	 * @return the HBox containing the available permit tiles of the players
	 */
	public HBox getPermitTilesTurnedUpOwned() {
		return permitTilesTurnedUpOwned;
	}

	/**
	 * @return the HBox containing the already used permit tiles of the player
	 */
	public HBox getPermitTilesTurnedDownOwned() {
		return permitTilesTurnedDownOwned;
	}

	/**
	 * @return the list of HBox containing the permit tiles of all the players of the game
	 */
	public List<HBox> getPermitTilesOtherPlayers(){
		return Arrays.asList(permitTilesTurnedUpPlayer1,permitTilesTurnedUpPlayer2,permitTilesTurnedUpPlayer3,permitTilesTurnedUpPlayer4);
	}

	/**
	 * @return the list of Pane of the cities
	 */
	public List<Pane> getCities(){
		return Arrays.asList(arkon,burgen,castrum,dorful,esti,framek,graden,hellar,indur,juvelar,kultos,lyram,merkatim,naris,osium);
	}

	/**
	 * @return the HBox containing the politics cards in the player's hand
	 */
	public HBox getHand() {
		return hand;
	}
	
	/**
	 * @return the list ImageView containing the regions
	 */
	public List<ImageView> getRegions() {
		return Arrays.asList(seaRegion, hillRegion, mountainRegion);
	}
	
	/**
	 * @return the list of ImageView containing the balconies
	 */
	public List<ImageView> getBalconies() {
		return Arrays.asList(seaBalcony, hillBalcony, mountainBalcony, kingBalcony);
	}

	/**
	 * @return the TextArea where the chat and system message ae shown
	 */
	public TextArea getMessageBox() {
		return messageBox;
	}
	
	/**
	 * @return the label that indicates the player name
	 */
	public Label getPlayerName() {
		return playerName;
	}

	/**
	 * @return the label that indicates the player score
	 */
	public Label getPlayerScore() {
		return playerScore;
	}

	/**
	 * @return the label that indicates the player coins
	 */
	public Label getPlayerCoins() {
		return playerCoins;
	}

	/**
	 * @return the label that indicates the number of assistants of the players
	 */
	public Label getPlayerAssistants() {
		return playerAssistants;
	}

	/**
	 * @return the label that indicates the player nobility score
	 */
	public Label getPlayerNobility() {
		return playerNobility;
	}
	
	/**
	 * @return the list of ImageView of the councillor reserve
	 */
	public List<ImageView> getCouncillorReserve(){
		return Arrays.asList(reserveConcillor1,reserveConcillor2,reserveConcillor3,reserveConcillor4,reserveConcillor5,reserveConcillor6,reserveConcillor7,reserveConcillor8);
	}
	
	/**
	 * @return the list of labels that indicates the number of politics cards in the hand of all players of the game
	 */
	public List<Label> getNumberOfPoliticsCards(){
		return Arrays.asList(numberOfPoliticsCardInHandPlayer1,numberOfPoliticsCardInHandPlayer2,numberOfPoliticsCardInHandPlayer3,numberOfPoliticsCardInHandPlayer4);
	}
	/**
	 * @return the list of labels that indicates the number of used permit tiles of all players of the game
	 */
	public List<Label> getNumberOfCoveredPermitTiles(){
		return Arrays.asList(numberOfCoveredPermitTilePlayer1,numberOfCoveredPermitTilePlayer2,numberOfCoveredPermitTilePlayer3,numberOfCoveredPermitTilePlayer4);
	}
	
	/**
	 * @return the array of ImageViews of the sea permit tiles
	 */
	public ImageView[] getSeaPermitTile(){
		ImageView[] array=new ImageView[2];
		array[0]=permitTileSea0;
		array[1]=permitTileSea1;
		return array;
	}
	
	/**
	 * @return the array of ImageViews of the hill permit tiles 
	 */
	public ImageView[] getHillPermitTile(){
		ImageView[] array=new ImageView[2];
		array[0]=permitTileHill0;
		array[1]=permitTileHill1;
		return array;
	} 
	/**
	 * @return the array of ImageViews of the mountain permit tiles 
	 */
	public ImageView[] getMountainPermitTile(){
		ImageView[] array=new ImageView[2];
		array[0]=permitTileMountain0;
		array[1]=permitTileMountain1;
		return array;
	}
	
	/**
	 * @param balcony
	 * @return the list of ImageView of the councillor of a given balcony
	 */
	public List<ImageView> getCouncillors(RegionDTO balcony){
		switch(balcony.getName()){
		case "Sea":
			return Arrays.asList(seaConcillor1,seaConcillor2,seaConcillor3,seaConcillor4);
		case "Hill":
			return Arrays.asList(hillConcillor1,hillConcillor2,hillConcillor3,hillConcillor4);
		case "Mountain":
			return Arrays.asList(mountainConcillor1,mountainConcillor2,mountainConcillor3,mountainConcillor4);
		default:
			throw new IllegalArgumentException("Region does not exist!");
		}
	}
	
	/**
	 * @return the list of ImageViews of the king conucillors 
	 */
	public List<ImageView> getKingCouncillors(){
		return Arrays.asList(kingConcillor1,kingConcillor2,kingConcillor3,kingConcillor4);
	}
	
	/**
	 * @return the list of labels that indicate the name of all the players of the game
	 */
	public List<Label> getNamesLabels(){
		return Arrays.asList(namePlayer1,namePlayer2,namePlayer3,namePlayer4);
	}
	
	/**
	 * @return the list of labels that indicate the number of remaining of all the players of the game
	 */
	public List<Label> getRemainingEmporiumsLabels(){
		return Arrays.asList(remainingEmporiumPlayer1,remainingEmporiumPlayer2,remainingEmporiumPlayer3,remainingEmporiumPlayer4);
	}
	
	/**
	 * @return the list of labels that indicate the score of all the players of the game
	 */
	public List<Label> getScoreLabels(){
		return Arrays.asList(scorePlayer1,scorePlayer2,scorePlayer3,scorePlayer4);
	}
	
	/**
	 * @return the list of labels that indicate the coins of all the players of the game
	 */
	public List<Label> getCoinsLabels(){
		return Arrays.asList(coinsPlayer1,coinsPlayer2, coinsPlayer3, coinsPlayer4);
	}
	
	/**
	 * @return the list of labels that indicate the number of assistants of all the players of the game
	 */
	public List<Label> getAssistantsLabels(){
		return Arrays.asList(assistantsPlayer1,assistantsPlayer2,assistantsPlayer3,assistantsPlayer4);
	}
	
	/**
	 * @return the list of labels that indicate the score nobility of all the players of the game
	 */
	public List<Label> getNobilityLabels(){
		return Arrays.asList(nobilityPlayer1,nobilityPlayer2,nobilityPlayer3,nobilityPlayer4);
	}

	/**
	 * @return the ImageView of the colour of the player number 1
	 */
	public ImageView getColourPlayer1() {
		return colourPlayer1;
	}

	/**
	 * @return the ImageView of the colour of the player number 2
	 */
	public ImageView getColourPlayer2() {
		return colourPlayer2;
	}

	/**
	 * @return the ImageView of the colour of the player number 3
	 */
	public ImageView getColourPlayer3() {
		return colourPlayer3;
	}

	/**
	 * @return the ImageView of the colour of the player number 4
	 */
	public ImageView getColourPlayer4() {
		return colourPlayer4;
	}
	
	/**
	 * @return the list of HBox containing the emporium builded on each city
	 */
	public List<HBox> getCitysEmporiums() {
		return Arrays.asList(emporiumsArkon, emporiumsBurgen, emporiumsCastrum, emporiumsDorful, emporiumsEsti, emporiumsFramek,
				emporiumsGraden, emporiumsHellar, emporiumsIndur, emporiumsJuvelar, emporiumsKultos, emporiumsLyram, emporiumsMerkatim, emporiumsNaris, emporiumsOsium);
	}

	/**
	 * 
	 * @param city the given city
	 * @return the ImageView of the reward token of a given city
	 */
	public ImageView getRewardToken(CityDTO city){
		switch(city.getName()){
			case "Arkon":
				return rewardTokenA;
			case "Burgen":
				return rewardTokenB;
			case "Castrum":
				return rewardTokenC;
			case "Dorful":
				return rewardTokenD;
			case "Esti":
				return rewardTokenE;
			case "Framek":
				return rewardTokenF;
			case "Graden":
				return rewardTokenG;
			case "Hellar":
				return rewardTokenH;
			case "Indur":
				return rewardTokenI;
			case "Juvelar":
				return rewardTokenJ;
			case "Kultos":
				return rewardTokenK;
			case "Lyram":
				return rewardTokenL;
			case "Merkatim":
				return rewardTokenM;
			case "Naris":
				return rewardTokenN;
			case "Osium":
				return rewardTokenO;
			default:
				throw new IllegalArgumentException("City "+city+"does not exist!");
			
		}
	}
	
	/**
	 * @return the button where the player as to click to discard less then 4 politics card
	 */
	public Button getDescardPoliticsCards() {
		return descardPoliticsCards;
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
					executor.submit(()-> {
							try {
								view.insertParametersAndSend((ActionWithParameters) selectedAction);
							} catch (RemoteException e) {
								Logger logger=Logger.getAnonymousLogger();
								logger.log(Level.SEVERE, "Failed to send action with RMI", e);
							}
						});
					return;
				}
		this.view.displayError("Sorry, action not available!");
	}
	
	
	/**
	 * Is the method called by clicking on pick politics card button.
	 * It sends the action through the connection
	 * @param event is the event to handle with the method
	 * @throws RemoteException if something goes wrong sending the action through the connection
	 */
	@FXML
	public void startActionPickPoliticsCard() throws RemoteException {
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof PickPoliticsCardDTO) {
				this.view.getConnection().sendAction(action);
				return;
			}
		this.view.displayError("You can't pick a politics card now");
	}
	
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	@FXML
	public void clickOnRegion(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((ImageView) event.getSource()).getUserData());
			this.notify();
		}
	}
	
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	@FXML
	public void clickOnPermitTileInRegion(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((ImageView) event.getSource()).getUserData());
			this.notify();
		}
	}
	 
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	@FXML
	public void clickOnBalcony(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((ImageView) event.getSource()).getUserData());
			this.notify();
		}
	}
	
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	@FXML
	public void clickOnCouncillorsReserve(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((ImageView) event.getSource()).getUserData());
			this.notify();
		}
	}
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	@FXML
	public void clickOnCity(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((Pane) event.getSource()).getUserData());
			this.notify();
		}
	}
	/**
	 * Sets the current parameter of the view and wake the thread that was waiting for the parameter up
	 * @param selectedCard
	 */
	@FXML
	public void stopDiscarding() {
		synchronized (this) {
			this.view.setCurrentParameter("stop");
			this.notify();
		}
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
	 * changes the mouse style with a hand
	 * @param mouseEvent
	 */
    @FXML
    public void changeMouseStyle(MouseEvent mouseEvent) {
    	Platform.runLater(()-> {
	    	Image image = new Image(getClass().getResource("view/images/cursor/hand.png").toExternalForm());
	    	((Node) mouseEvent.getSource()).setCursor(new ImageCursor(image));
	        });
    }
    
    /**
     * This method is call
     * @throws RemoteException
     */
    @FXML
    public void clickOnQuitGame() throws RemoteException {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Quit Game");
    	alert.setHeaderText("Do you really want to leave the game?");
    	Optional<ButtonType> result=alert.showAndWait();
    	if (result.get()==ButtonType.OK){
    		view.getConnection().sendAction(new QuitDTO());
    		Platform.exit();
    	}
    	else
    		return;
    }
    

    /**
	 * Is the method called by pressing enter after inserting a chat message.
	 * It sends the action through the connection
	 * @param key is the event to handle with the method
	 * @throws RemoteException if something goes wrong sending the action through the connection
	 */
    @FXML
    public void sendMessage(KeyEvent key) throws RemoteException {
        Platform.runLater(()-> {
    	    if (key.getCode().equals(KeyCode.ENTER)) {
    	    	try {
					view.getConnection().sendAction(new ChatMessageDTO(clientGame.getClientPlayer().getName()
							+ ": " + chatInputBox.getText().substring(0, chatInputBox.getText().length()-1)));
				} catch (RemoteException e) {
					Logger logger=Logger.getAnonymousLogger();
					logger.log(Level.SEVERE, "Failed to send message with RMI", e);
				}
    	    	chatInputBox.clear();
    	    }
    	   });
    }

}