package client.view;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.AddPlayerDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.ClientViewNotify;
import observerPattern.Observer;

public abstract class ClientView implements Observer<ClientViewNotify> {

	protected final Connection connection;
	protected final GameDTO clientGame;
	
	public ClientView(Connection connection, GameDTO gameDTO) {
		this.connection=connection;
		this.clientGame=gameDTO;
	}
	
	public void welcome(String name) throws RemoteException {
		AddPlayerDTO actionDTO=new AddPlayerDTO(name);
		this.connection.sendAction(actionDTO);
	}

	@Override
	public abstract void update(ClientViewNotify notify);
	
	
	public abstract void input() throws RemoteException;
	
	public abstract void displayMessage(String string);
	
	public abstract void displayError(String message);
	
	public abstract void startGame(GameTableDTO gameTableDTO);

	public abstract void displayAvailableActions(List<ActionDTO> availableActions);
	
	public abstract void displayGameTable(GameTableDTO clientGame);
	
	public abstract void displayPlayer(ClientPlayerDTO player);
	
	public abstract void displayMarket(MarketDTO market);
	
	public abstract void displayFinalRanking(ArrayList<GenericPlayerDTO> finalRankingTable);
	
	public abstract void displayChatMessage(String message);
	
	public abstract void startMarket();
	
	public abstract void closeMarket();
	
	
	public abstract RegionDTO askForRegionBoard();
	
	public abstract PermitTileDTO askForPermitTile();
	
	public abstract CouncillorDTO askForCouncillor();
	
	public abstract CouncillorDTO[] askForCouncilBalcony();
	
	public abstract CityDTO askForCity(List<CityDTO> acceptableCities);
	
	public abstract List<PoliticsCardDTO> askForPoliticsCards();
	
	public abstract int askForNumberOfPermitTile(RegionDTO SelectedRegion);
	
	public abstract MarketableDTO askForMakingAnOffer();
	
	public abstract int askForPrice();
	
	public abstract boolean askForOtherSelling();
	
	public abstract OfferDTO askForAcceptingAnOffer();
	
	public abstract PermitTileDTO askForPermitTileUncoveredAndCovered();
	
	public abstract void askForMap() throws RemoteException;
	
	
	public abstract void ChooseCityBonus(int numberOfCities);
	
	public abstract void PurchasedPermitTileBonus();

	public abstract void PickPermitTileBonus();

}