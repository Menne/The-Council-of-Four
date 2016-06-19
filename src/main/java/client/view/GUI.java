package client.view;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import client.ControllerGUI;
import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.ModelDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.gameTableDTO.RewardTokenDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.ClientViewNotify;
import javafx.application.Platform;
import javafx.scene.image.Image;
import server.model.bonus.AssistantsBonus;
import server.model.bonus.CoinsBonus;
import server.model.bonus.NobilityBonus;
import server.model.bonus.PoliticsCardsBonus;
import server.model.bonus.ScoreBonus;


public class GUI extends ClientView{

	private final ControllerGUI controllerGUI;
	private final Map<ModelDTO, Image> imageMap;
	private Object currentParameter;
	
	public GUI(Connection connection, GameDTO clientGame, ControllerGUI controllerGUI) {
		super(connection, clientGame);
		this.controllerGUI=controllerGUI;
		this.controllerGUI.setClientGame(clientGame);
		this.controllerGUI.setView(this);
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

	@Override
	public void displayMessage(String string) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				controllerGUI.getMessageBox().appendText(string+"\n");	
			}
		});
	}
	
	@Override
	public void displayError(String string) {
		this.controllerGUI.getMessageBox().appendText("ERROR: "+string);		
	}
	
	@Override
	public void displayAvailableActions(List<ActionDTO> availableActions) {
		return;
	}

	@Override
	public void displayGameTable(GameTableDTO clientGame) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				displayCouncillors(clientGame);
				displayTokens(clientGame);
			}
		});
		
	}
	
	private void displayCouncillors(GameTableDTO clientGame){
		for(RegionDTO region : clientGame.getClientRegions())
			for(int i=0; i<4; i++)
				controllerGUI.getCouncillors(region).get(i).setImage(imageMap.get(region.getBalcony()[i]));
		for(int i=0; i<4; i++)
			controllerGUI.getKingCouncillors().get(i).setImage(imageMap.get(clientGame.getClientKingBalcony()[i]));
			
	}
	
	private void displayTokens(GameTableDTO clientGame){
		for(RegionDTO region : clientGame.getClientRegions())
			for(CityDTO city : region.getCities())
				controllerGUI.getRewardToken(city).setImage(imageMap.get(city.getRewardToken()));
	}

	@Override
	public void displayPlayer(ClientPlayerDTO player) {
		this.controllerGUI.getMessageBox().appendText(player.toString());		
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
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (RegionDTO) this.currentParameter;
	}

	@Override
	public PermitTileDTO askForPermitTile(List<PermitTileDTO> acceptablePermitTiles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CouncillorDTO askForCouncillor(List<CouncillorDTO> acceptableCouncillors) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CouncillorDTO[] askForCouncilBalcony(List<CouncillorDTO[]> acceptableCouncillors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityDTO askForCity(List<CityDTO> acceptableCities) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (CityDTO) this.currentParameter;
	}

	@Override
	public List<CardColourDTO> askForPoliticsCards(List<CardColourDTO> acceptablePoliticsCards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askForNumberOfPermitTile(List<Integer> acceptableNumberOfPermitTile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askForPrice() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean askForOtherSelling() {
		return false;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public OfferDTO askForAcceptingAnOffer(List<OfferDTO> acceptableOffers) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public void ChooseCityBonus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PurchasedPermitTileBonus() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public Connection getConnection() {
		return this.connection;
	}
}
