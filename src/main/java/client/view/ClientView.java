package client.view;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.GameTableDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.marketDTO.MarketDTO;
import modelDTO.marketDTO.MarketableDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.playerDTO.ClientPlayerDTO;
import observerPattern.Observer;

public abstract class ClientView implements Observer<ClientViewNotify> {

	protected final Connection connection;
	
	public ClientView(Connection connection) {
		this.connection=connection;
	}

	@Override
	public abstract void update(ClientViewNotify notify);
	
	
	public abstract void input() throws RemoteException;
	
	public abstract void welcome(String name) throws RemoteException;
	
	
	public abstract void displayMessage(String string);
	
	public abstract void displayError(String message);

	public abstract void displayAvailableActions(List<ActionDTO> availableActions);
	
	public abstract void displayGameTable(GameTableDTO clientGame);
	
	public abstract void displayPlayer(ClientPlayerDTO player);
	
	public abstract void displayMarket(MarketDTO market);
	
	public abstract void displayFinalRanking(ArrayList<GenericPlayerDTO> finalRankingTable);
	
	
	public abstract RegionDTO askForRegionBoard(List<RegionDTO> acceptableRegions);
	
	public abstract PermitTileDTO askForPermitTile(List<PermitTileDTO> acceptablePermitTiles);
	
	public abstract CardColourDTO askForCouncillor(List<CardColourDTO> acceptableCouncillors);
	
	public abstract CardColourDTO[] askForCouncilBalcony(List<CardColourDTO[]> acceptableCouncillors);
	
	public abstract CityDTO askForCity(List<CityDTO> acceptableCities);
	
	public abstract List<CardColourDTO> askForPoliticsCards(List<CardColourDTO> acceptablePoliticsCards);
	
	public abstract int askForNumberOfPermitTile(List<Integer> acceptableNumberOfPermitTile);
	
	public abstract MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer);
	
	public abstract int askForPrice();
	
	public abstract boolean askForOtherSelling();
	
	public abstract OfferDTO askForAcceptingAnOffer(List<OfferDTO> acceptableOffers);
	
	
	public abstract void ChooseCityBonus(List<CityDTO> acceptableCities);
	
	public abstract void PurchasedPermitTileBonus(List<PermitTileDTO> acceptablePermitTiles);

}