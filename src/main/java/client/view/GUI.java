package client.view;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import client.ControllerGUI;
import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.ModelDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.MoveToNextDTO;
import client.modelDTO.actionsDTO.PickPoliticsCardDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.AddictionalMainActionDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import client.modelDTO.actionsDTO.standardActions.EngageAssistantDTO;
import client.modelDTO.gameTableDTO.BonusTileDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.gameTableDTO.RewardTokenDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.ClientViewNotify;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import server.model.bonus.AssistantsBonus;
import server.model.bonus.CoinsBonus;
import server.model.bonus.MainActionBonus;
import server.model.bonus.NobilityBonus;
import server.model.bonus.PoliticsCardsBonus;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.CouncilBalcony;

public class GUI extends ClientView{

	private final ControllerGUI controllerGUI;
	private final Map<ModelDTO, Image> imageMap;
	private Object currentParameter;
	private final ImageView king;
	
	public GUI(Connection connection, GameDTO clientGame, ControllerGUI controllerGUI) {
		super(connection, clientGame);
		this.controllerGUI=controllerGUI;
		this.controllerGUI.setClientGame(clientGame);
		this.controllerGUI.setView(this);
		this.king=new ImageView(getClass().getResource("images/various/king.png").toExternalForm());
		this.king.setFitHeight(70);
		this.king.setPreserveRatio(true);
		imageMap=new HashMap<>();
		imageMap.put(new CouncillorDTO(new CardColourDTO("Black")),new Image(getClass().getResource("images/councillors/Black.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("Blue")),new Image(getClass().getResource("images/councillors/Blue.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("Orange")),new Image(getClass().getResource("images/councillors/Orange.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("Pink")),new Image(getClass().getResource("images/councillors/Pink.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("Violet")),new Image(getClass().getResource("images/councillors/Violet.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("White")),new Image(getClass().getResource("images/councillors/White.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new AssistantsBonus(1)))), new Image(getClass().getResource("images/token/Assistants+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new AssistantsBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/token/Assistants+1Coins+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new AssistantsBonus(2)))), new Image(getClass().getResource("images/token/Assistants+2.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new CoinsBonus(1)))), new Image(getClass().getResource("images/token/Coins+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new CoinsBonus(2)))), new Image(getClass().getResource("images/token/Coins+2.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new CoinsBonus(3)))), new Image(getClass().getResource("images/token/Coins+3.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new NobilityBonus(1)))), new Image(getClass().getResource("images/token/Nobility+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1)))), new Image(getClass().getResource("images/token/Politics+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new AssistantsBonus(1),new PoliticsCardsBonus(1)))), new Image(getClass().getResource("images/token/Politics+1Assistants+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/token/Politics+1Score+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new ScoreBonus(1)))), new Image(getClass().getResource("images/token/Score+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new ScoreBonus(2)))), new Image(getClass().getResource("images/token/Score+2.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new ScoreBonus(3)))), new Image(getClass().getResource("images/token/Score+3.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Black")), new Image(getClass().getResource("images/cards/Black.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Blue")), new Image(getClass().getResource("images/cards/Blu.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Orange")), new Image(getClass().getResource("images/cards/Orange.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Pink")), new Image(getClass().getResource("images/cards/Pink.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Rainbow")), new Image(getClass().getResource("images/cards/Rainbow.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Violet")), new Image(getClass().getResource("images/cards/Violet.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("White")), new Image(getClass().getResource("images/cards/White.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Dorful"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new CoinsBonus(1),new ScoreBonus(2)))), new Image(getClass().getResource("images/seaPermitTile/3.1.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Burgen"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(3),new AssistantsBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.2.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Dorful"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new ScoreBonus(3),new AssistantsBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.3.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Burgen"), new CityDTO("Castrum"))),new HashSet<>(Arrays.asList(new CoinsBonus(3),new ScoreBonus(3)))), new Image(getClass().getResource("images/seaPermitTile/3.4.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Castrum"), new CityDTO("Dorful"))),new HashSet<>(Arrays.asList(new AssistantsBonus(1),new ScoreBonus(3)))), new Image(getClass().getResource("images/seaPermitTile/3.5.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"))),new HashSet<>(Arrays.asList(new CoinsBonus(3),new ScoreBonus(4)))), new Image(getClass().getResource("images/seaPermitTile/3.6.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Castrum"))),new HashSet<>(Arrays.asList(new CoinsBonus(3),new AssistantsBonus(2)))), new Image(getClass().getResource("images/seaPermitTile/3.7.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Dorful"))),new HashSet<>(Arrays.asList(new ScoreBonus(7)))), new Image(getClass().getResource("images/seaPermitTile/3.8.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Burgen"))),new HashSet<>(Arrays.asList(new CoinsBonus(3),new PoliticsCardsBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.9.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new NobilityBonus(2)))), new Image(getClass().getResource("images/seaPermitTile/3.10.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Burgen"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new CoinsBonus(3)))), new Image(getClass().getResource("images/seaPermitTile/3.11.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Burgen"), new CityDTO("Castrum"))),new HashSet<>(Arrays.asList(new AssistantsBonus(2)))), new Image(getClass().getResource("images/seaPermitTile/3.12.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Burgen"), new CityDTO("Dorful"), new CityDTO("Castrum"))),new HashSet<>(Arrays.asList(new AssistantsBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.13.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Castrum"), new CityDTO("Dorful"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new NobilityBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.14.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new MainActionBonus(),new CoinsBonus(2 )))), new Image(getClass().getResource("images/seaPermitTile/3.15.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Graden"), new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new NobilityBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.1.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"), new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new ScoreBonus(5)))), new Image(getClass().getResource("images/hillPermitTile/1.2.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Graden"))),new HashSet<>(Arrays.asList(new AssistantsBonus(2),new ScoreBonus(2),new NobilityBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.3.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Indur"), new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new NobilityBonus(1),new AssistantsBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.4.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"), new CityDTO("Graden"), new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new AssistantsBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.5.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new AssistantsBonus(4)))), new Image(getClass().getResource("images/hillPermitTile/1.6.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2),new CoinsBonus(4)))), new Image(getClass().getResource("images/hillPermitTile/1.7.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new AssistantsBonus(2),new CoinsBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.8.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Graden"))),new HashSet<>(Arrays.asList(new CoinsBonus(5)))), new Image(getClass().getResource("images/hillPermitTile/1.9.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2),new AssistantsBonus(2)))), new Image(getClass().getResource("images/hillPermitTile/1.10.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new MainActionBonus(),new ScoreBonus(2)))), new Image(getClass().getResource("images/hillPermitTile/1.11.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"), new CityDTO("Juvelar"), new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2)))), new Image(getClass().getResource("images/hillPermitTile/1.12.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"), new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2),new NobilityBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.13.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Graden"), new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new ScoreBonus(1),new CoinsBonus(2)))), new Image(getClass().getResource("images/hillPermitTile/1.14.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Hellar"), new CityDTO("Graden"))),new HashSet<>(Arrays.asList(new AssistantsBonus(3)))), new Image(getClass().getResource("images/hillPermitTile/1.15.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Lyram"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new AssistantsBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.2.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"))),new HashSet<>(Arrays.asList(new CoinsBonus(7)))), new Image(getClass().getResource("images/mountainPermitTile/2.1.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Osium"), new CityDTO("Kultos"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.3.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Lyram"), new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.4.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Lyram"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new ScoreBonus(3)))), new Image(getClass().getResource("images/mountainPermitTile/2.5.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Lyram"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(3)))), new Image(getClass().getResource("images/mountainPermitTile/2.6.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Osium"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new NobilityBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.7.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(4)))), new Image(getClass().getResource("images/mountainPermitTile/2.8.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(3),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.9.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Lyram"))),new HashSet<>(Arrays.asList(new AssistantsBonus(3),new CoinsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.10.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new ScoreBonus(5),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.11.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Naris"), new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.12.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Lyram"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new AssistantsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.13.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new MainActionBonus()))), new Image(getClass().getResource("images/mountainPermitTile/2.14.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new ScoreBonus(2),new PoliticsCardsBonus(2)))), new Image(getClass().getResource("images/mountainPermitTile/2.15.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Sea", new ScoreBonus(5)), new Image(getClass().getResource("images/bonusTiles/bonusMare.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Hill", new ScoreBonus(5)), new Image(getClass().getResource("images/bonusTiles/bonusColline.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Mountain", new ScoreBonus(5)), new Image(getClass().getResource("images/bonusTiles/bonusMontagna.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(25)), new Image(getClass().getResource("images/kingBonus/KingRewardTile1.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(18)), new Image(getClass().getResource("images/kingBonus/KingRewardTile2.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(12)), new Image(getClass().getResource("images/kingBonus/KingRewardTile3.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(7)), new Image(getClass().getResource("images/kingBonus/KingRewardTile4.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(3)), new Image(getClass().getResource("images/kingBonus/KingRewardTile5.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Blue", new ScoreBonus(5)), new Image(getClass().getResource("images/colourBonus/BlueBonus.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Bronze", new ScoreBonus(8)), new Image(getClass().getResource("images/colourBonus/BronzeBonus.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Gold", new ScoreBonus(20)), new Image(getClass().getResource("images/colourBonus/GoldBonus.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Silver", new ScoreBonus(12)), new Image(getClass().getResource("images/colourBonus/SilverBonus.png").toExternalForm()));
	}

	
	public Object getCurrentParameter() {
		return currentParameter;
	}

	public void setCurrentParameter(Object currentParameter) {
		this.currentParameter = currentParameter;
	}
	
	
	public Connection getConnection() {
		return this.connection;
	}
	
	
	@Override
	public void update(ClientViewNotify notify) {
		notify.updateView(this);	
	}

	@Override
	public void input() throws RemoteException {
		return;
	}

	@Override
	public void displayMessage(String string) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				controllerGUI.getMessageBox().appendText(string+"\n");	
			}
		});
	}
	
	
	public void insertParametersAndSend(ActionWithParameters actionWithParameters) throws RemoteException {
		this.disableActionButtons(true);
		actionWithParameters.setParser().setParameters(this, this.clientGame);
		if (actionWithParameters.checkIfParametersSetted())
			connection.sendAction(actionWithParameters);
		this.disableActionButtons(false);
	}
	
	@Override
	public void displayError(String string) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("WARNING");
				alert.setHeaderText(string);
				alert.showAndWait();
			}
		});		
	}
	
	@Override
	public void displayAvailableActions(List<ActionDTO> availableActions) {
		controllerGUI.getActions().get(0).setUserData(new ElectCouncillorDTO());
		controllerGUI.getActions().get(1).setUserData(new AcquirePermitTileDTO());
		controllerGUI.getActions().get(2).setUserData(new BuildByPermitTileDTO());
		controllerGUI.getActions().get(3).setUserData(new BuildByKingDTO());
		controllerGUI.getActions().get(4).setUserData(new EngageAssistantDTO());
		controllerGUI.getActions().get(5).setUserData(new ChangePermitTilesDTO());
		controllerGUI.getActions().get(6).setUserData(new ElectCouncillorByAssistantDTO());
		controllerGUI.getActions().get(7).setUserData(new AddictionalMainActionDTO());
		controllerGUI.getActions().get(8).setUserData(new MoveToNextDTO());
		controllerGUI.getPoliticsDeck().setUserData(new PickPoliticsCardDTO());
	}

	@Override
	public void displayGameTable(GameTableDTO clientGame) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(clientGame);
				displayCouncillors(clientGame);
				displayTokens(clientGame);
				displayPlayers(clientGame.getClientPlayers());
				displayRegions(clientGame);
				displayCities(clientGame);
				diplayBalconies(clientGame);
				displayPermitTiles(clientGame);
				displayKing(clientGame);
				displayEmporiums(clientGame);
			}
		});
		
	}
	
	private void displayRegions(GameTableDTO clientGame) {
		for (int i=0; i<this.controllerGUI.getRegions().size(); i++)
			this.controllerGUI.getRegions().get(i).setUserData(clientGame.getClientRegions().get(i));
	}
	
	private void displayCities(GameTableDTO clientGame) {
		for(RegionDTO regionDTO : clientGame.getClientRegions())
			for (CityDTO city : regionDTO.getCities()) {
				if ("Arkon".equals(city.getName()))
					controllerGUI.getCities().get(0).setUserData(city);
					controllerGUI.getCitysEmporiums().get(0).setUserData(city);
				if ("Burgen".equals(city.getName()))
					controllerGUI.getCities().get(1).setUserData(city);
					controllerGUI.getCitysEmporiums().get(1).setUserData(city);
				if ("Castrum".equals(city.getName()))
					controllerGUI.getCities().get(2).setUserData(city);
					controllerGUI.getCitysEmporiums().get(2).setUserData(city);
				if ("Dorful".equals(city.getName()))
					controllerGUI.getCities().get(3).setUserData(city);
					controllerGUI.getCitysEmporiums().get(3).setUserData(city);
				if ("Esti".equals(city.getName()))
					controllerGUI.getCities().get(4).setUserData(city);
					controllerGUI.getCitysEmporiums().get(4).setUserData(city);
				if ("Framek".equals(city.getName()))
					controllerGUI.getCities().get(5).setUserData(city);
					controllerGUI.getCitysEmporiums().get(5).setUserData(city);
				if ("Graden".equals(city.getName()))
					controllerGUI.getCities().get(6).setUserData(city);
					controllerGUI.getCitysEmporiums().get(6).setUserData(city);
				if ("Hellar".equals(city.getName()))
					controllerGUI.getCities().get(7).setUserData(city);
					controllerGUI.getCitysEmporiums().get(7).setUserData(city);
				if ("Indur".equals(city.getName()))
					controllerGUI.getCities().get(8).setUserData(city);
					controllerGUI.getCitysEmporiums().get(8).setUserData(city);
				if ("Juvelar".equals(city.getName()))
					controllerGUI.getCities().get(9).setUserData(city);
					controllerGUI.getCitysEmporiums().get(9).setUserData(city);
				if ("Kultos".equals(city.getName()))
					controllerGUI.getCities().get(10).setUserData(city);
					controllerGUI.getCitysEmporiums().get(10).setUserData(city);
				if ("Lyram".equals(city.getName()))
					controllerGUI.getCities().get(11).setUserData(city);
					controllerGUI.getCitysEmporiums().get(11).setUserData(city);
				if ("Merkatim".equals(city.getName()))
					controllerGUI.getCities().get(12).setUserData(city);
					controllerGUI.getCitysEmporiums().get(12).setUserData(city);
				if ("Naris".equals(city.getName()))
					controllerGUI.getCities().get(13).setUserData(city);
					controllerGUI.getCitysEmporiums().get(13).setUserData(city);
				if ("Osium".equals(city.getName()))
					controllerGUI.getCities().get(14).setUserData(city);
					controllerGUI.getCitysEmporiums().get(14).setUserData(city);
		}
	}
	

	private void displayKing(GameTableDTO clientGame){
		for(Pane cityPane : controllerGUI.getCities()){
				cityPane.getChildren().remove(king);
			if(((CityDTO)cityPane.getUserData()).getName().equals(clientGame.getKing()))
				cityPane.getChildren().add(king);			
		}
	}
		
	
	private void displayEmporiums(GameTableDTO clientGame){
	ImageView player1= new ImageView(getClass().getResource("images/emporiumsColours/blue.png").toExternalForm());
	ImageView player2= new ImageView(getClass().getResource("images/emporiumsColours/red.png").toExternalForm());
	ImageView player3= new ImageView(getClass().getResource("images/emporiumsColours/yellow.png").toExternalForm());
	ImageView player4= new ImageView(getClass().getResource("images/emporiumsColours/green.png").toExternalForm());
		for(HBox hBox: controllerGUI.getCitysEmporiums()){
			hBox.getChildren().clear();
			for(GenericPlayerDTO emporium: ((CityDTO)hBox.getUserData()).getBuildedEmporiums()){
				if(emporium.equals(1))
					hBox.getChildren().add(player1);
				if(emporium.equals(2))
					hBox.getChildren().add(player2);
				if(emporium.equals(3))
					hBox.getChildren().add(player3);
				if(emporium.equals(4))
					hBox.getChildren().add(player4);
			}
		}	
	}
	
	private void diplayBalconies(GameTableDTO clientGame) {
		for (int i=1; i<this.controllerGUI.getBalconies().size()-1; i++)
			controllerGUI.getBalconies().get(i).setUserData(clientGame.getClientRegions().get(i).getBalcony());
		controllerGUI.getBalconies().get(controllerGUI.getBalconies().size()-1).setUserData(clientGame.getClientKingBalcony());
	}
	
	private void displayPermitTiles(GameTableDTO clientGame) {
		controllerGUI.getSeaPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(0).getUncoveredPermitTiles()[0]));
		controllerGUI.getSeaPermitTile()[0].setUserData(new Integer(0));
		controllerGUI.getSeaPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(0).getUncoveredPermitTiles()[1]));
		controllerGUI.getSeaPermitTile()[1].setUserData(new Integer(1));
		controllerGUI.getHillPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(1).getUncoveredPermitTiles()[0]));
		controllerGUI.getHillPermitTile()[0].setUserData(new Integer(0));
		controllerGUI.getHillPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(1).getUncoveredPermitTiles()[1]));
		controllerGUI.getHillPermitTile()[1].setUserData(new Integer(1));
		controllerGUI.getMountainPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(2).getUncoveredPermitTiles()[0]));
		controllerGUI.getMountainPermitTile()[0].setUserData(new Integer(0));
		controllerGUI.getMountainPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(2).getUncoveredPermitTiles()[1]));
		controllerGUI.getMountainPermitTile()[1].setUserData(new Integer(1));
	}
	
	private void displayPlayers(List<GenericPlayerDTO> players){
		List<GenericPlayerDTO> orderedPlayers = new ArrayList<>(players);
		Collections.sort(orderedPlayers, new Comparator<GenericPlayerDTO>() {

			@Override
			public int compare(GenericPlayerDTO o1, GenericPlayerDTO o2) {
				if(o1.getPlayerNumber()<o2.getPlayerNumber())
					return -1;
				else 
					return 1;
			}
		});
		
		for(int i=0; i<orderedPlayers.size();i++){
			controllerGUI.getNamesLabels().get(i).setText(orderedPlayers.get(i).getName());
			controllerGUI.getScoreLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getScore()));
			controllerGUI.getCoinsLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getCoins()));
			controllerGUI.getAssistantsLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getAssistants()));
			controllerGUI.getNobilityLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getNobility()));
			controllerGUI.getRemainingEmporiumsLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getEmporiums()));	
			controllerGUI.getNumberOfPoliticsCards().get(i).setText(String.valueOf(orderedPlayers.get(i).getHand()));
			controllerGUI.getNumberOfCoveredPermitTiles().get(i).setText(String.valueOf(orderedPlayers.get(i).getNumberOfCoveredTiles()));
			controllerGUI.getPermitTilesOtherPlayers().get(i).getChildren().clear();
			for(PermitTileDTO permitTileDTO : orderedPlayers.get(i).getAvailablePermitTiles()){
				ImageView imageView=new ImageView(imageMap.get(permitTileDTO));
				controllerGUI.getPermitTilesOtherPlayers().get(i).getChildren().add(imageView);
				imageView.setFitHeight(60);
				imageView.setPreserveRatio(true);
			}
			controllerGUI.getGenericPlayerBonuses().get(i).getChildren().clear();
			for(BonusTileDTO bonusTileDTO : orderedPlayers.get(i).getPlayersFinalBonus()){
				ImageView imageView =new ImageView(imageMap.get(bonusTileDTO));
				controllerGUI.getGenericPlayerBonuses().get(i).getChildren().add(imageView);
				imageView.setFitWidth(50);
				imageView.setPreserveRatio(true);			
			}
		} 
	}
	
	private void displayCouncillors(GameTableDTO clientGame) {
		for (RegionDTO region : clientGame.getClientRegions())
			for (int i=0; i<4; i++) {
				controllerGUI.getCouncillors(region).get(i).setImage(imageMap.get(region.getBalcony()[i]));
				controllerGUI.getCouncillors(region).get(i).setUserData(region.getBalcony()[i]);
			}
		for (int i=0; i<4; i++) {
			controllerGUI.getKingCouncillors().get(i).setImage(imageMap.get(clientGame.getClientKingBalcony()[i]));
			controllerGUI.getKingCouncillors().get(i).setUserData(clientGame.getClientKingBalcony()[i]);
		}
		for (int i=0; i<8; i++) {
			controllerGUI.getCouncillorReserve().get(i).setImage(imageMap.get(clientGame.getClientCouncillorReserve().get(i)));
			controllerGUI.getCouncillorReserve().get(i).setUserData(clientGame.getClientCouncillorReserve().get(i));
		}
	}
	
	private void displayTokens(GameTableDTO clientGame){
		for (RegionDTO region : clientGame.getClientRegions())
			for (CityDTO city : region.getCities())
				controllerGUI.getRewardToken(city).setImage(imageMap.get(city.getRewardToken()));
	}

	@Override
	public void displayPlayer(ClientPlayerDTO player) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				controllerGUI.getHand().getChildren().clear();
				controllerGUI.getPermitTilesTurnedUpOwned().getChildren().clear();
				for (PoliticsCardDTO card : player.getHand()){
					ImageView imageView=new ImageView(imageMap.get(card));
					controllerGUI.getHand().getChildren().add(imageView);
					imageView.setFitWidth(50);
					imageView.setPreserveRatio(true);
					imageView.setOnMouseClicked(new EventHandler<Event>() {

						@Override
						public void handle(Event event) {
							controllerGUI.handlePoliticsCard(card);	
							
						}
					});
					imageView.setDisable(true);
				}
				for (PermitTileDTO permitTileDTO : player.getAvailablePermitTiles()){
					ImageView imageView=new ImageView(imageMap.get(permitTileDTO));
					controllerGUI.getPermitTilesTurnedUpOwned().getChildren().add(imageView);
					imageView.setFitHeight(70);
					imageView.setPreserveRatio(true);
				}
				controllerGUI.getPlayerCoins().setText(String.valueOf(player.getCoins()));
				controllerGUI.getPlayerAssistants().setText(String.valueOf(player.getAssistants().size()));
				controllerGUI.getPlayerNobility().setText(String.valueOf(player.getNobility()));
				controllerGUI.getPlayerScore().setText(String.valueOf(player.getScore()));
				controllerGUI.getPlayersBonuses().getChildren().clear();
				for(BonusTileDTO bonusTileDTO : player.getFinalBonuses()){
					ImageView imageView =new ImageView(imageMap.get(bonusTileDTO));
					controllerGUI.getPlayersBonuses().getChildren().add(imageView);
					imageView.setFitWidth(60);
					imageView.setPreserveRatio(true);
				}
			}
		});
		
	}

	@Override
	public void displayMarket(MarketDTO market) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void displayFinalRanking(ArrayList<GenericPlayerDTO> finalRankingTable) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void displayChatMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public RegionDTO askForRegionBoard(List<RegionDTO> acceptableRegions) {
		this.disableClickOnRegions(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RegionDTO region=(RegionDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnRegions(true);
		return region;
	}

	@Override
	public PermitTileDTO askForPermitTile(List<PermitTileDTO> acceptablePermitTiles) {
		this.disableClickOnPermitTilesInHand(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PermitTileDTO permitTile=(PermitTileDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnPermitTilesInHand(true);
		return permitTile;
	}

	@Override
	public CouncillorDTO askForCouncillor(List<CouncillorDTO> acceptableCouncillors) {
		this.disableClickOnCouncillorsInReserve(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CouncillorDTO councillor=(CouncillorDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnCouncillorsInReserve(true);
		return councillor;
	}
	
	@Override
	public CouncillorDTO[] askForCouncilBalcony(List<CouncillorDTO[]> acceptableCouncillors) {
		this.disableClickOnCouncilBalconies(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CouncillorDTO[] councilBalcony=(CouncillorDTO[]) this.currentParameter;
		System.out.println(councilBalcony);
		this.currentParameter=null;
		this.disableClickOnCouncilBalconies(true);
		return councilBalcony;
	}

	@Override
	public CityDTO askForCity(List<CityDTO> acceptableCities) {
		this.disableClickOnCities(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CityDTO city=(CityDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnCities(true);
		return city;
	}

	@Override
	public List<PoliticsCardDTO> askForPoliticsCards(List<PoliticsCardDTO> acceptablePoliticsCards) {
		this.disableClickOnPoliticsCards(false);
		List<PoliticsCardDTO> selectedCards=new ArrayList<>();
		while (selectedCards.size()<CouncilBalcony.getNumberofcouncillors()) {
			synchronized (this.controllerGUI) {
				try {
					while (currentParameter==null)
						this.controllerGUI.wait();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (this.currentParameter instanceof String) {
				this.currentParameter=null;
				break;
			}
			else
				selectedCards.add((PoliticsCardDTO) this.currentParameter);
			this.currentParameter=null;
			this.disableClickOnDescardButton(false);
		}
		this.disableClickOnPoliticsCards(true);
		this.disableClickOnDescardButton(true);
		return selectedCards;
	}

	@Override
	public int askForNumberOfPermitTile(List<Integer> acceptableNumberOfPermitTile) {
		this.disableClickOnPermitTilesInRegions(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int numberOfPermitTile=(int) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnPermitTilesInRegions(true);
		return numberOfPermitTile;
	}

	@Override
	public MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MarketableDTO offeringObject=(MarketableDTO) this.currentParameter;
		this.currentParameter=null;
		return offeringObject;
	}

	@Override
	public int askForPrice() {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int price=(int) this.currentParameter;
		this.currentParameter=null;
		return price;
	}
	
	@Override
	public boolean askForOtherSelling() {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		boolean choice=(boolean) this.currentParameter;
		this.currentParameter=null;
		return choice;
	}
	
	@Override
	public OfferDTO askForAcceptingAnOffer(List<OfferDTO> acceptableOffers) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		OfferDTO offer=(OfferDTO) this.currentParameter;
		this.currentParameter=null;
		return offer;
	}

	
	
	
	@Override
	public void ChooseCityBonus(int numberOfCities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PurchasedPermitTileBonus() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	private void disableClickOnRegions(boolean disabled) {
		for (int i=0; i<this.controllerGUI.getRegions().size(); i++)
			this.controllerGUI.getRegions().get(i).setDisable(disabled);
	}
	
	private void disableClickOnPermitTilesInHand(boolean disabled) {
		for (Object object : controllerGUI.getPermitTilesTurnedUpOwned().getChildren()) {
			ImageView imageView=(ImageView) object;
			imageView.setDisable(disabled);
		}
	}

	private void disableClickOnCouncillorsInReserve(boolean disabled) {
		for (int i=0; i<this.controllerGUI.getCouncillorReserve().size(); i++)
			this.controllerGUI.getCouncillorReserve().get(i).setDisable(disabled);
	}
	
	private void disableClickOnCouncilBalconies(boolean disabled) {
		for (int i=1; i<this.controllerGUI.getBalconies().size()-1; i++)
			controllerGUI.getBalconies().get(i).setDisable(disabled);
		controllerGUI.getBalconies().get(controllerGUI.getBalconies().size()-1).setDisable(disabled);
	}
	
	private void disableClickOnCities(boolean disabled) {
		for(Pane citiPane : controllerGUI.getCities())
			citiPane.setDisable(disabled);
	}
	
	private void disableClickOnPoliticsCards(boolean disabled) {
		for (Object object : controllerGUI.getHand().getChildren()){
			ImageView imageView=(ImageView) object;
			imageView.setDisable(disabled);
		}
	}
	
	private void disableClickOnDescardButton(boolean disabled) {
		this.controllerGUI.getDescardPoliticsCards().setDisable(disabled);
	}
	
	private void disableClickOnPermitTilesInRegions(boolean disabled) {
		for (int i=0; i<=1; i++) {
			controllerGUI.getSeaPermitTile()[i].setDisable(disabled);
			controllerGUI.getHillPermitTile()[i].setDisable(disabled);
			controllerGUI.getMountainPermitTile()[i].setDisable(disabled);
		}
	}


	public void disableActionButtons(boolean disabled) {
		this.controllerGUI.getPoliticsDeck().setDisable(disabled);
		for (Button button : this.controllerGUI.getActions())
			button.setDisable(disabled);
		}
	
}
