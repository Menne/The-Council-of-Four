package client.view;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.ClientGUI;
import client.ControllerChooseMap;
import client.ControllerGUI;
import client.ControllerMarketGUI;
import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.ModelDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.MoveToNextDTO;
import client.modelDTO.actionsDTO.PickPoliticsCardDTO;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PickPermitTileActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
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
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.gameTableDTO.RewardTokenDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.AssistantDTO;
import client.view.notifies.ClientViewNotify;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.model.bonus.AssistantsBonus;
import server.model.bonus.CoinsBonus;
import server.model.bonus.MainActionBonus;
import server.model.bonus.NobilityBonus;
import server.model.bonus.PoliticsCardsBonus;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.CouncilBalcony;

public class GUI extends ClientView{

	private ControllerMarketGUI controllerMarketGUI;
	private ControllerChooseMap controllerChooseMap;
	private final ControllerGUI controllerGUI;
	private final Map<ModelDTO, Image> imageMap;
	private Object currentParameter;
	private final ImageView king;
	private TextArea currentTextArea;
	
	public GUI(Connection connection, GameDTO clientGame, ControllerGUI controllerGUI) {
		super(connection, clientGame);
		this.controllerGUI=controllerGUI;
		this.controllerGUI.setClientGame(clientGame);
		this.controllerGUI.setView(this);
		this.currentTextArea=controllerGUI.getMessageBox();
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
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Osium"), new CityDTO("Kultos"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.3.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Lyram"), new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.4.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Lyram"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new ScoreBonus(3)))), new Image(getClass().getResource("images/mountainPermitTile/2.5.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Lyram"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(3)))), new Image(getClass().getResource("images/mountainPermitTile/2.6.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Osium"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new NobilityBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.7.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(4)))), new Image(getClass().getResource("images/mountainPermitTile/2.8.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(3),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.9.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Lyram"))),new HashSet<>(Arrays.asList(new AssistantsBonus(3),new CoinsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.10.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new ScoreBonus(5),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.11.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Naris"), new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.12.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Lyram"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new AssistantsBonus(2)))), new Image(getClass().getResource("images/mountainPermitTile/2.13.png").toExternalForm()));
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
		imageMap.put(new AssistantDTO(), new Image(getClass().getResource("images/various/Assistant.png").toExternalForm()));
		imageMap.put(new GenericPlayerDTO(1), new Image(getClass().getResource("images/emporiumsColours/blue.png").toExternalForm()));
		imageMap.put(new GenericPlayerDTO(2), new Image(getClass().getResource("images/emporiumsColours/red.png").toExternalForm()));
		imageMap.put(new GenericPlayerDTO(3), new Image(getClass().getResource("images/emporiumsColours/yellow.png").toExternalForm()));
		imageMap.put(new GenericPlayerDTO(4), new Image(getClass().getResource("images/emporiumsColours/green.png").toExternalForm()));
	}

	
	public Object getCurrentParameter() {
		return currentParameter;
	}

	public void setCurrentParameter(Object currentParameter) {
		this.currentParameter = currentParameter;
	}
	
	@Override
	public void update(ClientViewNotify notify) {
		notify.updateView(this);	
	}

	@Override
	public void input() throws RemoteException {
		return;
	}
	
	
	public void insertParametersAndSend(ActionWithParameters actionWithParameters) throws RemoteException {
		this.disableActionButtons(true);
		actionWithParameters.setParser().setParameters(this, this.clientGame);
		if (actionWithParameters.checkIfParametersSet())
			connection.sendAction(actionWithParameters);
		this.disableActionButtons(false);
	}
	
	
	
	@Override
	public void askForMap() {
		Platform.runLater(()-> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClientGUI.class.getResource("ChooseMap.fxml"));
			AnchorPane root=null;
			try {
				root = (AnchorPane)loader.load();
			} catch (IOException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.SEVERE, "Failed to load chooseMap screen", e);
			}
			controllerChooseMap=loader.getController();
			controllerChooseMap.getMap1().setUserData(1);
			controllerChooseMap.getMap2().setUserData(2);
			controllerChooseMap.getMap3().setUserData(3);
			controllerChooseMap.getMap4().setUserData(4);
			controllerChooseMap.getMap5().setUserData(5);
			controllerChooseMap.getMap6().setUserData(6);
			controllerChooseMap.getMap7().setUserData(7);
			controllerChooseMap.getMap8().setUserData(8);
			controllerChooseMap.setView(GUI.this);
			Stage chooseMapStage=new Stage();
			chooseMapStage.setTitle("Choose Map");
			Scene scene=new Scene(root);
			chooseMapStage.setScene(scene);
			controllerChooseMap.setChooseMapStage(chooseMapStage);
			chooseMapStage.show();
			controllerChooseMap.changeMouseStyle();
			});	
	}


	@Override
	public void startGame() {
		controllerGUI.getMapImage().setImage(new Image(getClass().getResource("images/maps/map"+String.valueOf
				(this.clientGame.getClientGameTable().getMapNumber())+".jpg").toExternalForm()));			
	}
	
	
	@Override
	public void displayAvailableActions() {
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
		
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof ChooseCityActionDTO || action instanceof PickPermitTileActionDTO
					|| action instanceof PurchasedPermitTileActionDTO)
				try {
					this.insertParametersAndSend((ActionWithParameters) action);
				} catch (RemoteException e) {
					Logger logger=Logger.getAnonymousLogger();
					logger.log(Level.SEVERE, "Failed to send action with RMI", e);
				}
		
		Platform.runLater(()-> {
			controllerGUI.getActions().get(0).getParent().setEffect(null);
			controllerGUI.getActions().get(4).getParent().setEffect(null);
			InnerShadow innerShadow= new InnerShadow();
			innerShadow.setChoke(0.5);
			innerShadow.setHeight(150);
			innerShadow.setWidth(150);
			innerShadow.setBlurType(BlurType.GAUSSIAN);
			innerShadow.setColor(Color.web("ffffff"));
			controllerGUI.getPoliticsDeck().setEffect(null);
			for (ActionDTO action : clientGame.getAvailableActions()){
				if(action.getClass()==PickPoliticsCardDTO.class){
					controllerGUI.getPoliticsDeck().setEffect(new Glow(0.8));
				}
				if(action.getClass()==ElectCouncillorDTO.class)
					controllerGUI.getActions().get(0).getParent().setEffect(innerShadow);
				if(action.getClass()==EngageAssistantDTO.class)
					controllerGUI.getActions().get(4).getParent().setEffect(innerShadow);
			}
		});		
	}

	@Override
	public void displayGameTable() {
		Platform.runLater(()-> {
			if (controllerChooseMap!=null && controllerChooseMap.getChooseMapStage().isShowing()){
				Alert alert=new Alert(AlertType.INFORMATION);
			   	alert.setTitle("Map Not Chosen");
			   	alert.setHeaderText("You did not choose the map.\n"
			   			+ "The system has choose for you the map numeber " + clientGame.getClientGameTable().getMapNumber());
			   	alert.showAndWait();
			   	controllerChooseMap.getChooseMapStage().close();
			}
			unlockPlayersTabs();
			displayCouncillors();
			displayTokens();
			displayPlayers();
			displayRegions();
			displayCities();
			diplayBalconies();
			displayPermitTiles();
			displayKing();
			displayEmporiums();
			displayBonusTiles();
		});		
	}
	
	private void unlockPlayersTabs(){
		for (GenericPlayerDTO player: clientGame.getClientGameTable().getClientPlayers())
			this.controllerGUI.getPlayersTabs().get(player.getPlayerNumber()-1).setDisable(false);
	}
	
	private void displayBonusTiles() {
		controllerGUI.getColorBonusBlue().setOpacity(0);
		controllerGUI.getColorBonusBronze().setOpacity(0);
		controllerGUI.getColorBonusGold().setOpacity(0);
		controllerGUI.getColorBonusSilver().setOpacity(0);;
		for (BonusTileDTO bonusTile : clientGame.getClientGameTable().getColourBonuses()){
			if ("Blue".equals(bonusTile.getType()))
				controllerGUI.getColorBonusBlue().setOpacity(1);
			if ("Silver".equals(bonusTile.getType()))
				controllerGUI.getColorBonusSilver().setOpacity(1);
			if ("Gold".equals(bonusTile.getType()))
				controllerGUI.getColorBonusGold().setOpacity(1);
			if ("Bronze".equals(bonusTile.getType()))
				controllerGUI.getColorBonusBronze().setOpacity(1);
		}
		
		if (clientGame.getClientGameTable().getNextKingRewardTile().getBonus().getScoreAdvancement()==18)
			controllerGUI.getKingBonusTiles().get(0).setImage(null);
		if (clientGame.getClientGameTable().getNextKingRewardTile().getBonus().getScoreAdvancement()==12)
			controllerGUI.getKingBonusTiles().get(1).setImage(null);
		if (clientGame.getClientGameTable().getNextKingRewardTile().getBonus().getScoreAdvancement()==7)
			controllerGUI.getKingBonusTiles().get(2).setImage(null);
		if (clientGame.getClientGameTable().getNextKingRewardTile().getBonus().getScoreAdvancement()==3)
			controllerGUI.getKingBonusTiles().get(3).setImage(null);
		if (clientGame.getClientGameTable().getNextKingRewardTile()==null)
			controllerGUI.getKingBonusTiles().get(4).setImage(null);	
	}


	private void displayRegions() {
		for (int i=0; i<clientGame.getClientGameTable().getClientRegions().size(); i++){
			if(clientGame.getClientGameTable().getClientRegions().get(i).getRegionBonus()==null)
				controllerGUI.getRegionBonusTiles().get(i).setImage(null);
			this.controllerGUI.getRegions().get(i).setUserData(clientGame.getClientGameTable().getClientRegions().get(i));
		}
	}
	
	private void displayCities() {
		for (RegionDTO regionDTO : clientGame.getClientGameTable().getClientRegions())
			for (CityDTO city : regionDTO.getCities()) {
				if ("Arkon".equals(city.getName())){
					controllerGUI.getCities().get(0).setUserData(city);
					controllerGUI.getCitysEmporiums().get(0).setUserData(city);
					}
				if ("Burgen".equals(city.getName())){
					controllerGUI.getCities().get(1).setUserData(city);
					controllerGUI.getCitysEmporiums().get(1).setUserData(city);
					}
				if ("Castrum".equals(city.getName())){
					controllerGUI.getCities().get(2).setUserData(city);
					controllerGUI.getCitysEmporiums().get(2).setUserData(city);
					}
				if ("Dorful".equals(city.getName())){
					controllerGUI.getCities().get(3).setUserData(city);
					controllerGUI.getCitysEmporiums().get(3).setUserData(city);
					}
				if ("Esti".equals(city.getName())){
					controllerGUI.getCities().get(4).setUserData(city);
					controllerGUI.getCitysEmporiums().get(4).setUserData(city);
					}
				if ("Framek".equals(city.getName())){
					controllerGUI.getCities().get(5).setUserData(city);
					controllerGUI.getCitysEmporiums().get(5).setUserData(city);
					}
				if ("Graden".equals(city.getName())){
					controllerGUI.getCities().get(6).setUserData(city);
					controllerGUI.getCitysEmporiums().get(6).setUserData(city);
					}
				if ("Hellar".equals(city.getName())){
					controllerGUI.getCities().get(7).setUserData(city);
					controllerGUI.getCitysEmporiums().get(7).setUserData(city);
					}
				if ("Indur".equals(city.getName())){
					controllerGUI.getCities().get(8).setUserData(city);
					controllerGUI.getCitysEmporiums().get(8).setUserData(city);
					}
				if ("Juvelar".equals(city.getName())){
					controllerGUI.getCities().get(9).setUserData(city);
					controllerGUI.getCitysEmporiums().get(9).setUserData(city);
					}
				if ("Kultos".equals(city.getName())){
					controllerGUI.getCities().get(10).setUserData(city);
					controllerGUI.getCitysEmporiums().get(10).setUserData(city);
					}
				if ("Lyram".equals(city.getName())){
					controllerGUI.getCities().get(11).setUserData(city);
					controllerGUI.getCitysEmporiums().get(11).setUserData(city);
					}
				if ("Merkatim".equals(city.getName())){
					controllerGUI.getCities().get(12).setUserData(city);
					controllerGUI.getCitysEmporiums().get(12).setUserData(city);
					}
				if ("Naris".equals(city.getName())){
					controllerGUI.getCities().get(13).setUserData(city);
					controllerGUI.getCitysEmporiums().get(13).setUserData(city);
					}
				if ("Osium".equals(city.getName())){
					controllerGUI.getCities().get(14).setUserData(city);
					controllerGUI.getCitysEmporiums().get(14).setUserData(city);
					}
		}
	}
	

	private void displayKing(){
		for(Pane cityPane : controllerGUI.getCities()){
				cityPane.getChildren().remove(king);
			if(((CityDTO)cityPane.getUserData()).getName().equals(clientGame.getClientGameTable().getKing()))
				cityPane.getChildren().add(king);			
		}
	}
	
	private void displayEmporiums(){
		for(HBox hBox: controllerGUI.getCitysEmporiums()){
			hBox.getChildren().clear();
			for(GenericPlayerDTO emporium: ((CityDTO)hBox.getUserData()).getBuildedEmporiums()){
				ImageView imageView=new ImageView(imageMap.get(emporium));
				imageView.setFitHeight(18);
				imageView.setPreserveRatio(true);
				hBox.getChildren().add(imageView);
			}
		}
	}
	
	private void diplayBalconies() {
		for (int i=0; i<this.controllerGUI.getBalconies().size()-1; i++)
			controllerGUI.getBalconies().get(i).setUserData(clientGame.getClientGameTable().getClientRegions().get(i).getBalcony());
		controllerGUI.getBalconies().get(controllerGUI.getBalconies().size()-1).setUserData(clientGame.getClientGameTable().getClientKingBalcony());
	}
	
	private void displayPermitTiles() {
		for (int i=0; i<2; i++) {
			controllerGUI.getSeaPermitTile()[i].setImage(imageMap.get(clientGame.getClientGameTable().getClientRegions().get(0).getUncoveredPermitTiles()[i]));
			controllerGUI.getSeaPermitTile()[i].setUserData(new Integer(i));
			controllerGUI.getHillPermitTile()[i].setImage(imageMap.get(clientGame.getClientGameTable().getClientRegions().get(1).getUncoveredPermitTiles()[i]));
			controllerGUI.getHillPermitTile()[i].setUserData(new Integer(i));
			controllerGUI.getMountainPermitTile()[i].setImage(imageMap.get(clientGame.getClientGameTable().getClientRegions().get(2).getUncoveredPermitTiles()[i]));
			controllerGUI.getMountainPermitTile()[i].setUserData(new Integer(i));
		}
	}
	
	private void displayPlayers(){
		List<GenericPlayerDTO> orderedPlayers = new ArrayList<>(clientGame.getClientGameTable().getClientPlayers());
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
				imageView.setDisable(true);
			}
			controllerGUI.getGenericPlayerBonuses().get(i).getChildren().clear();
			for(BonusTileDTO bonusTileDTO : orderedPlayers.get(i).getPlayersFinalBonus()){
				ImageView imageView =new ImageView(imageMap.get(bonusTileDTO));
				controllerGUI.getGenericPlayerBonuses().get(i).getChildren().add(imageView);
				imageView.setFitWidth(50);
				imageView.setPreserveRatio(true);
				imageView.setDisable(true);
			}
		} 
	}
	
	private void displayCouncillors() {
		for (RegionDTO region : clientGame.getClientGameTable().getClientRegions())
			for (int i=0; i<4; i++) {
				controllerGUI.getCouncillors(region).get(i).setImage(imageMap.get(region.getBalcony()[i]));
				controllerGUI.getCouncillors(region).get(i).setUserData(region.getBalcony()[i]);
			}
		for (int i=0; i<4; i++) {
			controllerGUI.getKingCouncillors().get(i).setImage(imageMap.get(clientGame.getClientGameTable().getClientKingBalcony()[i]));
			controllerGUI.getKingCouncillors().get(i).setUserData(clientGame.getClientGameTable().getClientKingBalcony()[i]);
		}
		for (int i=0; i<8; i++) {
			controllerGUI.getCouncillorReserve().get(i).setImage(imageMap.get(clientGame.getClientGameTable().getClientCouncillorReserve().get(i)));
			controllerGUI.getCouncillorReserve().get(i).setUserData(clientGame.getClientGameTable().getClientCouncillorReserve().get(i));
		}
	}
	
	private void displayTokens(){
		for (RegionDTO region : clientGame.getClientGameTable().getClientRegions())
			for (CityDTO city : region.getCities())
				controllerGUI.getRewardToken(city).setImage(imageMap.get(city.getRewardToken()));
	}

	@Override
	public void displayPlayer() {
		Platform.runLater(()-> {
			List<ImageView> politicsCards=new ArrayList<>();
			controllerGUI.getHand().getChildren().clear();
			controllerGUI.getPermitTilesTurnedUpOwned().getChildren().clear();
			controllerGUI.getPermitTilesTurnedDownOwned().getChildren().clear();
			for (PoliticsCardDTO card : clientGame.getClientPlayer().getHand()){
				ImageView imageView=new ImageView(imageMap.get(card));
				controllerGUI.getHand().getChildren().add(imageView);
				imageView.setFitWidth(50);
				imageView.setPreserveRatio(true);
				imageView.setDisable(true);
				imageView.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						controllerGUI.handlePoliticsCard(card);	
						imageView.setDisable(true);
						imageView.setOpacity(0.4);
					}
				});
				imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						controllerGUI.changeMouseStyle(event);
							
					}
				});
				politicsCards.add(imageView);
				controllerGUI.setPoliticsCards(politicsCards);
			}
			for (PermitTileDTO permitTileDTO : clientGame.getClientPlayer().getAvailablePermitTiles()){
				ImageView imageView=new ImageView(imageMap.get(permitTileDTO));
				controllerGUI.getPermitTilesTurnedUpOwned().getChildren().add(imageView);
				imageView.setFitHeight(70);
				imageView.setPreserveRatio(true);
				imageView.setDisable(true);
				imageView.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						controllerGUI.handlePermitTilesTurnedUp(permitTileDTO);	
							
					}
				});
				imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						controllerGUI.changeMouseStyle(event);
							
					}
				});
			}
			for (PermitTileDTO permitTileDTO : clientGame.getClientPlayer().getCoveredPermitTiles()){
				ImageView imageView=new ImageView(imageMap.get(permitTileDTO));
				controllerGUI.getPermitTilesTurnedDownOwned().getChildren().add(imageView);
				imageView.setFitHeight(70);
				imageView.setPreserveRatio(true);
				imageView.setDisable(true);
				imageView.setOpacity(0.6);
				imageView.setOnMouseClicked(new EventHandler<Event>() {	
					@Override
					public void handle(Event event) {
						controllerGUI.handlePermitTilesTurnedUp(permitTileDTO);	
							
					}
				});
				imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						controllerGUI.changeMouseStyle(event);
							
					}
				});
			}
			controllerGUI.getPlayerCoins().setText(String.valueOf(clientGame.getClientPlayer().getCoins()));
			controllerGUI.getPlayerAssistants().setText(String.valueOf(clientGame.getClientPlayer().getAssistants().size()));
			controllerGUI.getPlayerNobility().setText(String.valueOf(clientGame.getClientPlayer().getNobility()));
			controllerGUI.getPlayerScore().setText(String.valueOf(clientGame.getClientPlayer().getScore()));
			controllerGUI.getPlayersBonuses().getChildren().clear();
			for(BonusTileDTO bonusTileDTO : clientGame.getClientPlayer().getFinalBonuses()){
				ImageView imageView =new ImageView(imageMap.get(bonusTileDTO));
				controllerGUI.getPlayersBonuses().getChildren().add(imageView);
				imageView.setFitWidth(60);
				imageView.setPreserveRatio(true);
				imageView.setDisable(true);
			}
		});	
	}
	
	@Override
	public void startMarket() {
		Platform.runLater(()-> {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClientGUI.class.getResource("MarketView.fxml"));
			try {
				AnchorPane root=(AnchorPane)loader.load();
				controllerMarketGUI=loader.getController();
				Stage marketStage=new Stage();
				marketStage.setTitle("Market");
				marketStage.setScene(new Scene(root));
				controllerMarketGUI.setMerketStage(marketStage);
				controllerMarketGUI.setClientGame(clientGame);
				controllerMarketGUI.setView(GUI.this);
				controllerMarketGUI.getMakeAnOffer().setUserData(new MakeAnOfferDTO());
				controllerMarketGUI.getSkip().setUserData(new MoveToNextDTO());
				controllerMarketGUI.getAcceptAnOffer().setUserData(new AcceptAnOfferDTO());
				currentTextArea=controllerMarketGUI.getMessageBox();
				controllerMarketGUI.getMerketStage().show();
			} catch (IOException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.SEVERE, "Failed to load market screen", e);
			}				
		});		
	}

	@Override
	public void displayMarket() {
		Platform.runLater(()-> {
			
			displayMarketStuff();	
			displayOffers();
			controllerMarketGUI.getMakeAnOffer().setText("Start Offering");
			controllerMarketGUI.getMakeAnOffer().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					try {
						controllerMarketGUI.startAction(event);
					} catch (RemoteException e) {
						Logger logger=Logger.getAnonymousLogger();
						logger.log(Level.SEVERE, "Failed to send action with RMI", e);
					}
						
				}
			});
			controllerMarketGUI.getSkip().setText("Skip Action");
			controllerMarketGUI.getSkip().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					try {								
						controllerMarketGUI.startAction(event);
					} catch (RemoteException e) {
						Logger logger=Logger.getAnonymousLogger();
						logger.log(Level.SEVERE, "Failed to send action with RMI", e);
					}
						
				}
			});
			controllerMarketGUI.getAcceptAnOffer().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						controllerMarketGUI.startAction(event);
					} catch (RemoteException e) {
						Logger logger=Logger.getAnonymousLogger();
						logger.log(Level.SEVERE, "Failed to send action with RMI", e);
					}					
				}
				});
			});
	}
	
	private void displayMarketStuff(){
		controllerMarketGUI.getAvailablePoliticCards().getChildren().clear();
		for(PoliticsCardDTO card : clientGame.getClientPlayer().getHand()){
			ImageView imageView=new ImageView(imageMap.get(card));
			controllerMarketGUI.getAvailablePoliticCards().getChildren().add(imageView);
			imageView.setUserData(card);	
			imageView.setFitWidth(50);
			imageView.setPreserveRatio(true);
			imageView.setDisable(true);
			imageView.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					controllerMarketGUI.handlePoliticsCard(card);
					imageView.setOpacity(0.4);
				}
			});
			imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					controllerMarketGUI.changeMouseStyle(event);
				}
			});
		}
		controllerMarketGUI.getAvailablePermitTiles().getChildren().clear();
		
		
		for(PermitTileDTO permitTileDTO : clientGame.getClientPlayer().getAvailablePermitTiles()){
			ImageView imageView=new ImageView(imageMap.get(permitTileDTO));
			controllerMarketGUI.getAvailablePermitTiles().getChildren().add(imageView);
			imageView.setUserData(permitTileDTO);
			imageView.setFitWidth(50);
			imageView.setPreserveRatio(true);
			imageView.setDisable(true);
			imageView.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					controllerMarketGUI.handlePermitTilesTurnedUp(permitTileDTO);		
					imageView.setOpacity(0.4);
				}
			});
			imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					controllerMarketGUI.changeMouseStyle(event);
				}
			});
		}
		
		
		Image image=new Image(getClass().getResource("images/various/Assistant.png").toExternalForm());
		controllerMarketGUI.getAvailableAssistants().getChildren().clear();
		for(AssistantDTO assistantDTO : clientGame.getClientPlayer().getAssistants()){
			ImageView imageView=new ImageView(image);
			imageView.setFitWidth(50);
			imageView.setPreserveRatio(true);
			imageView.setUserData(assistantDTO);
			controllerMarketGUI.getAvailableAssistants().getChildren().add(imageView);
			imageView.setDisable(true);
			imageView.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					controllerMarketGUI.handleAssistants(assistantDTO);
					imageView.setOpacity(0.4);
				}
			});
			imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					controllerMarketGUI.changeMouseStyle(event);
				}
			});
		}
	}
	
	private void displayOffers(){
		controllerMarketGUI.getOffers().getChildren().clear();
		for (OfferDTO offerDTO : clientGame.getMarket().getOffersList()){
			HBox offer=new HBox();
			offer.setSpacing(120);
			Label name=new Label(offerDTO.getOfferingPlayer());
			ImageView sellingObject=new ImageView(imageMap.get(offerDTO.getOfferedObjectDTO()));
			sellingObject.setFitWidth(30);
			sellingObject.setPreserveRatio(true);
			Label price=new Label(String.valueOf(offerDTO.getPrice()));
			offer.getChildren().add(name);
			offer.getChildren().add(sellingObject);
			offer.getChildren().add(price);
			offer.setDisable(true);
			offer.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					controllerMarketGUI.handleOffers(offerDTO);					
				}
				
			});
			controllerMarketGUI.getOffers().getChildren().add(offer);
			offer.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					controllerMarketGUI.changeMouseStyle(event);
					
				}
			});
		}
	}
	
	@Override
	public void displayMessage(String string) {
		Platform.runLater(()-> {
			currentTextArea.appendText("SYSTEM: "+string+"\n");	
		});
	}
	
	@Override
	public void displayError(String string) {
		Platform.runLater(()-> {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("WARNING");
			alert.setHeaderText(string);
			alert.showAndWait();
		});		
	}
	
	@Override
	public void displayFinalRanking() {
		Platform.runLater(()-> {
			Alert alert=new Alert(AlertType.INFORMATION);
			String string="";
			for (int i=0; i<clientGame.getClientGameTable().getClientPlayers().size();i++){
				string=string+String.valueOf(i+1) + "\t"+clientGame.getClientGameTable().getClientPlayers().get(i).getName() + 
						"\tscore: " + clientGame.getClientGameTable().getClientPlayers().get(i).getScore() + "\tassistants: " +
						clientGame.getClientGameTable().getClientPlayers().get(i).getAssistants() + "\tcards: " + 
						clientGame.getClientGameTable().getClientPlayers().get(i).getHand() + "\n";
			}
			alert.setContentText(string);
			alert.showAndWait();
			Platform.exit();
		});		
	}
	
	@Override
	public void displayChatMessage(String message) {
		Platform.runLater(()-> {
			controllerGUI.getMessageBox().appendText(message+"\n");	
			});		
	}
	
	@Override
	public void closeMarket() {
		Platform.runLater(()-> {
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("MARKET FINISHED!");
			alert.setHeaderText("Market phase is over, click ok to continue the game.");
			alert.showAndWait();
			currentTextArea=controllerGUI.getMessageBox();
			controllerMarketGUI.getMerketStage().close();			
		});
	}
	
		
	@Override
	public RegionDTO askForRegionBoard() {
		this.disableClickOnRegions(false);
		synchronized (this.controllerGUI) {
				while (currentParameter==null)
					try {
						this.controllerGUI.wait();
					} catch (InterruptedException e) {
						Logger logger=Logger.getAnonymousLogger();
						logger.log(Level.WARNING, "interrupted", e);
						Thread.currentThread().interrupt();
					}
		}
		RegionDTO region=(RegionDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnRegions(true);
		return region;
	}

	@Override
	public PermitTileDTO askForPermitTile() {
		this.disableClickOnPermitTilesInHand(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		PermitTileDTO permitTile=(PermitTileDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnPermitTilesInHand(true);
		return permitTile;
	}

	@Override
	public CouncillorDTO askForCouncillor() {
		this.disableClickOnCouncillorsInReserve(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		CouncillorDTO councillor=(CouncillorDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnCouncillorsInReserve(true);
		return councillor;
	}
	
	@Override
	public CouncillorDTO[] askForCouncilBalcony() {
		this.disableClickOnCouncilBalconies(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
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
		this.disableClickOnCities(false, acceptableCities);
		
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		CityDTO city=(CityDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnCities(true, acceptableCities);
		return city;
	}

	@Override
	public List<PoliticsCardDTO> askForPoliticsCards() {
		this.disableClickOnPoliticsCards(false, 1);
		List<PoliticsCardDTO> selectedCards=new ArrayList<>();
		while (selectedCards.size()<CouncilBalcony.getNumberofcouncillors()) {
			synchronized (this.controllerGUI) {
				try {
					while (currentParameter==null)
						this.controllerGUI.wait();
				} catch (InterruptedException e) {
					Logger logger=Logger.getAnonymousLogger();
					logger.log(Level.WARNING, "interrupted", e);
					Thread.currentThread().interrupt();
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
		this.disableClickOnPoliticsCards(true, 1);
		this.disableClickOnDescardButton(true);
		return selectedCards;
	}

	@Override
	public int askForNumberOfPermitTile(RegionDTO selectedRegion) {
		this.disableClickOnPermitTilesInRegions(false, selectedRegion);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		int numberOfPermitTile=(int) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnPermitTilesInRegions(true, selectedRegion);
		return numberOfPermitTile;
	}

	@Override
	public MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer) {
		this.controllerMarketGUI.getMakeAnOffer().setDisable(true);
		this.controllerMarketGUI.getAcceptAnOffer().setDisable(true);
		this.controllerMarketGUI.getSkip().setDisable(true);
		this.disableClickOnObjectsToSell(false, acceptableObjectsToOffer);
		synchronized (this.controllerMarketGUI) {
			try {
				while (currentParameter==null)
					this.controllerMarketGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		MarketableDTO offeringObject=(MarketableDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnObjectsToSell(true, acceptableObjectsToOffer);
		return offeringObject;
	}

	@Override
	public int askForPrice() {
		this.disablePriceInsertion(false);
		this.disableClickOnOfferSettedButtons(false);
		synchronized (this.controllerMarketGUI) {
			try {
				while (currentParameter==null)
					this.controllerMarketGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		int price=(int) this.currentParameter;
		this.currentParameter=null;
		this.disablePriceInsertion(true);
		this.disableClickOnOfferSettedButtons(true);
		return price;
	}
	
	@Override
	public boolean askForOtherSelling() {
		Platform.runLater(()-> {
				controllerMarketGUI.getMakeAnOffer().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {				
						synchronized(controllerMarketGUI){
							currentParameter=true;
							controllerMarketGUI.notify();
						}
					}
				});
				controllerMarketGUI.getMakeAnOffer().setText("Next Offer");
				controllerMarketGUI.getSkip().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
						synchronized(controllerMarketGUI){					
							currentParameter=false;	
							controllerMarketGUI.notify();
						}
					}
				});
				controllerMarketGUI.getSkip().setText("Stop Offering");
				controllerMarketGUI.getMakeAnOffer().setDisable(false);
				controllerMarketGUI.getSkip().setDisable(false);
				
			});
		
		synchronized(controllerMarketGUI){
			try {
				
				while(currentParameter==null)
					controllerMarketGUI.wait();
				
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		Boolean choice=(boolean) this.currentParameter;
		this.currentParameter=null;
		this.controllerMarketGUI.getAcceptAnOffer().setDisable(false);
		return choice;
	}
	
	@Override
	public OfferDTO askForAcceptingAnOffer() {
		this.controllerMarketGUI.getMakeAnOffer().setDisable(true);
		this.controllerMarketGUI.getAcceptAnOffer().setDisable(true);
		this.controllerMarketGUI.getSkip().setDisable(true);
		this.disableClickOnOffers(false);
		synchronized (this.controllerMarketGUI) {
			try {
				while (currentParameter==null)
					this.controllerMarketGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		OfferDTO offer=(OfferDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnOffers(true);
		return offer;
	}
	
	@Override
	public PermitTileDTO askForPermitTileUncoveredAndCovered() {
		this.disableClickOnPermitTilesInHand(false);
		this.disableClickOnPermitTilesCovered(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (InterruptedException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		PermitTileDTO permitTile=(PermitTileDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnPermitTilesInHand(true);
		this.disableClickOnPermitTilesCovered(true);
		return permitTile;
	}

	
	
	private void disableClickOnRegions(boolean disabled) {
		Platform.runLater(()-> {
			for (ImageView regionImageView : controllerGUI.getRegions()){
				regionImageView.setDisable(disabled);
				if(disabled==false)
					regionImageView.setEffect(new Glow(0.6));
				else
					regionImageView.setEffect(null);
			}
		});
	}
	
	private void disableClickOnPermitTilesInHand(boolean disabled) {
		Platform.runLater(()-> {
			for (Object object : controllerGUI.getPermitTilesTurnedUpOwned().getChildren()) {
				ImageView imageView=(ImageView) object;
				imageView.setDisable(disabled);
				if(disabled==false)
					imageView.setEffect(new Glow(0.6));
				else
					imageView.setEffect(null);
			}
		});
	}

	private void disableClickOnCouncillorsInReserve(boolean disabled) {
		Platform.runLater(()-> {
			for (int i=0; i<controllerGUI.getCouncillorReserve().size(); i++){
				controllerGUI.getCouncillorReserve().get(i).setDisable(disabled);
				if(!disabled)
					controllerGUI.getCouncillorReserve().get(i).setEffect(new Glow(0.6));
				else
					controllerGUI.getCouncillorReserve().get(i).setEffect(null);
			}
			});		
	}
	
	private void disableClickOnCouncilBalconies(boolean disabled) {
		Platform.runLater(()-> {
				for (int i=0; i<controllerGUI.getBalconies().size()-1; i++){
					controllerGUI.getBalconies().get(i).setDisable(disabled);
					if(!disabled)
						controllerGUI.getBalconies().get(i).setEffect(new Glow(1));
					else
						controllerGUI.getBalconies().get(i).setEffect(null);	
				}
				controllerGUI.getBalconies().get(controllerGUI.getBalconies().size()-1).setDisable(disabled);
				if(!disabled)
					controllerGUI.getBalconies().get(controllerGUI.getBalconies().size()-1).setEffect(new Glow(1));
				else
					controllerGUI.getBalconies().get(controllerGUI.getBalconies().size()-1).setEffect(null);
			});		
	}
	
	private void disableClickOnCities(boolean disabled, List<CityDTO> acceptableCities) {
		Platform.runLater(()-> {
				for (CityDTO city : acceptableCities)
					for (Pane cityPane : controllerGUI.getCities())
						if (city.equals((CityDTO) cityPane.getUserData()))
							cityPane.setDisable(disabled);				
			});
	}
	
	private void disableClickOnPoliticsCards(boolean disabled, int opacity) {
		Platform.runLater(()-> {
				for (Object object : controllerGUI.getHand().getChildren()){
					ImageView imageView=(ImageView) object;
					imageView.setDisable(disabled);
					imageView.setOpacity(opacity);
					if(!disabled)
						imageView.setEffect(new Glow(0.7));
					else
						imageView.setEffect(null);
				}
			});		
		
	}
	
	private void disableClickOnDescardButton(boolean disabled) {
		Platform.runLater(()-> {
				controllerGUI.getDescardPoliticsCards().setDisable(disabled);
				InnerShadow innerShadow= new InnerShadow();
				innerShadow.setChoke(0.9);
				innerShadow.setBlurType(BlurType.GAUSSIAN);
				innerShadow.setColor(Color.web("7c6d6d"));
				if(!disabled)
					controllerGUI.getDescardPoliticsCards().setEffect(innerShadow);
				else
					controllerGUI.getDescardPoliticsCards().setEffect(null);
			});		
	}
	
	private void disableClickOnPermitTilesInRegions(boolean disabled, RegionDTO selectedRegion) {
		Platform.runLater(()-> {
				if ("Sea".equals(selectedRegion.getName()))
					for (int i=0; i<=1; i++) {
						controllerGUI.getSeaPermitTile()[i].setDisable(disabled);
						if(!disabled)
							controllerGUI.getSeaPermitTile()[i].setEffect(new Glow(0.5));
						else
							controllerGUI.getSeaPermitTile()[i].setEffect(null);
					}
				if ("Hill".equals(selectedRegion.getName()))
					for (int i=0; i<=1; i++) {
						controllerGUI.getHillPermitTile()[i].setDisable(disabled);
						if(!disabled)
							controllerGUI.getHillPermitTile()[i].setEffect(new Glow(0.5));
						else
							controllerGUI.getHillPermitTile()[i].setEffect(null);
					}
				if ("Mountain".equals(selectedRegion.getName()))
					for (int i=0; i<=1; i++) {
						controllerGUI.getMountainPermitTile()[i].setDisable(disabled);
						if(!disabled)
							controllerGUI.getMountainPermitTile()[i].setEffect(new Glow(0.5));
						else
							controllerGUI.getMountainPermitTile()[i].setEffect(null);
					}
			});		
	}


	public void disableActionButtons(boolean disabled) {
		Platform.runLater(()-> {
				controllerGUI.getPoliticsDeck().setDisable(disabled);
				for (Button button : controllerGUI.getActions())
					button.setDisable(disabled);
			});		
	}
	

	private void disableClickOnPermitTilesCovered(boolean disabled) {
		Platform.runLater(()-> {
				for (Object object : controllerGUI.getPermitTilesTurnedDownOwned().getChildren()) {
					ImageView imageView=(ImageView) object;
					imageView.setDisable(disabled);
					imageView.setOpacity(1);
					if(!disabled)
						imageView.setEffect(new Glow(0.6));
					else
						imageView.setEffect(null);
				}
			});
	}

	private void disableClickOnObjectsToSell(boolean disabled, List<MarketableDTO> acceptableObjectsToOffer) {
		Platform.runLater(()-> {

				for (Object object : controllerMarketGUI.getAvailablePoliticCards().getChildren()) {
					ImageView imageView=(ImageView) object;
					imageView.setDisable(disabled);
					if (imageView.getOpacity()!=1 && !disabled)
						imageView.setDisable(true);
				}
				for (Object object : controllerMarketGUI.getAvailablePermitTiles().getChildren()) {
					ImageView imageView=(ImageView) object;
					imageView.setDisable(disabled);
				}
				for (Object object : controllerMarketGUI.getAvailableAssistants().getChildren()) {
					ImageView imageView=(ImageView) object;
					imageView.setDisable(disabled);
				}
			});
	}
	
	private void disablePriceInsertion(boolean disabled) {
		Platform.runLater(()-> {
				controllerMarketGUI.getPrice().setDisable(disabled);
			});
	}
	
	private void disableClickOnOfferSettedButtons(boolean disabled) {
		Platform.runLater(()-> {
				controllerMarketGUI.getSell().setDisable(disabled);
			});
	}
	
	private void disableClickOnOffers(boolean disabled) {
		Platform.runLater(()-> {
				for (Object offer : controllerMarketGUI.getOffers().getChildren()){
					((HBox) offer).setDisable(disabled);
				}
			});		
	}

}
