package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

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
import client.view.ClientView;
import client.view.Connection;
import client.view.notifies.ClientViewNotify;
import javafx.application.Application;



public class GUI extends ClientView {

	private final MainApp mainApp;
	
	public GUI(Connection connection, GameDTO clientGame) {
		super(connection);
		this.mainApp=new MainApp();
		Application.launch(MainApp.class);
	}

	@Override
	public void update(ClientViewNotify notify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void welcome(String name) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void displayMessage(String string) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void displayError(String string) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void displayAvailableActions(List<ActionDTO> availableActions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGameTable(GameTableDTO clientGame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayPlayer(ClientPlayerDTO player) {
		// TODO Auto-generated method stub
		
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
	public RegionDTO askForRegionBoard(List<RegionDTO> acceptableRegions) {
		// TODO Auto-generated method stub
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
	public void ChooseCityBonus(List<CityDTO> acceptableCities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PurchasedPermitTileBonus(List<PermitTileDTO> acceptablePermitTiles) {
		// TODO Auto-generated method stub
		
	}


}
