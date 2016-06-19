package client.view;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.ControllerGUI;
import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.ClientViewNotify;
import javafx.application.Platform;
import javafx.scene.image.Image;


public class GUI extends ClientView{

	private final ControllerGUI controllerGUI;
	private final Map<String, Image> imageMap;
	private Object currentParameter;
	private Object lock;
	
	public GUI(Connection connection, GameDTO clientGame, ControllerGUI controllerGUI) {
		super(connection, clientGame);
		this.controllerGUI=controllerGUI;
		this.controllerGUI.setClientGame(clientGame);
		this.controllerGUI.setView(this);
		imageMap=new HashMap<String,Image>();
		imageMap.put("Black",new Image(getClass().getResource("councillors/Black.jpg").toExternalForm()));
		imageMap.put("Blue",new Image(getClass().getResource("councillors/Blue.jpg").toExternalForm()));
		imageMap.put("Orange",new Image(getClass().getResource("councillors/Orange.jpg").toExternalForm()));
		imageMap.put("Pink",new Image(getClass().getResource("councillors/Pink.jpg").toExternalForm()));
		imageMap.put("Violet",new Image(getClass().getResource("councillors/Violet.jpg").toExternalForm()));
		imageMap.put("White",new Image(getClass().getResource("councillors/White.jpg").toExternalForm()));
		lock=new Object();
	}

	
	
	public Object getCurrentParameter() {
		return currentParameter;
	}



	public void setCurrentParameter(Object currentParameter) {
		this.currentParameter = currentParameter;
	}



	public Object getLock() {
		return lock;
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
		// TODO Auto-generated method stub		
	}

	@Override
	public void displayGameTable(GameTableDTO clientGame) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				for(RegionDTO region : clientGame.getClientRegions())
					for(int i=0; i<4; i++)
						controllerGUI.getCouncillors(region).get(i).setImage(imageMap.get(region.getBalcony()[i].getName()));
				for(int i=0; i<4; i++)
					controllerGUI.getKingCouncillors().get(i).setImage(imageMap.get(clientGame.getClientKingBalcony()[i].getName()));
			}
		});
		
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
		System.out.println("ho reso cliccabile le regioni");
		synchronized (lock) {
			try {
				while(currentParameter==null)
					lock.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("ho ritornato currentParameter"+((RegionDTO)currentParameter).toString());
		return null;
	}

	@Override
	public PermitTileDTO askForPermitTile(List<PermitTileDTO> acceptablePermitTiles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardColourDTO askForCouncillor(List<CardColourDTO> acceptableCouncillors) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CardColourDTO[] askForCouncilBalcony(List<CardColourDTO[]> acceptableCouncillors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityDTO askForCity(List<CityDTO> acceptableCities) {
		// TODO Auto-generated method stub
		return null;
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
