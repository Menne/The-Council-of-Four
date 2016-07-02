package client.view;

import java.rmi.RemoteException;
import java.util.List;

import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.AddPlayerDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.view.notifies.ClientViewNotify;
import observerPattern.Observer;


/**
 * This class modelizes a generic client view and provides all the methods which are inherited from both 
 * CLI and GUI, and eventually from other kind of views
 * @author cg31
 *
 */
public abstract class ClientView implements Observer<ClientViewNotify> {

	protected final Connection connection;
	protected final GameDTO clientGame;
	
	/**
	 * constructor of a generic ClientView
	 * @param connection is the kind of connection the user selected to communicate with the server
	 * @param gameDTO is the client status of the game
	 */
	public ClientView(Connection connection, GameDTO gameDTO) {
		this.connection=connection;
		this.clientGame=gameDTO;
	}
	
	/**
	 * This method is invoked when a client connects to the server and the user inserts his nickname.
	 * A new AddPlayerDTO action is sent to the server containing the information about the name of the player
	 * @param name is the name the user inserted
	 * @throws RemoteException if there are troubles with the RMI connection
	 */
	public void welcome(String name) throws RemoteException {
		AddPlayerDTO actionDTO=new AddPlayerDTO(name);
		this.connection.sendAction(actionDTO);
	}
	

	public Connection getConnection() {
		return connection;
	}

	/**
	 * Updates the view according to the notify which has arrived to the client and sent to the view
	 */
	public abstract void update(ClientViewNotify notify);
	
	/**
	 * This method provides the logic of handling user's input. Actually it is only used in CLI view
	 * @throws RemoteException if there are troubles with the RMI connection
	 */
	public abstract void input() throws RemoteException;
	
	/**
	 * This method displays a message to the view in a proper way according to the selected view
	 * @param message is the message which has to be displayed
	 */
	public abstract void displayMessage(String message);
	
	/**
	 * This method displays an error message to the view in a proper way according to the selected view
	 * @param error is the error which has to be displayed
	 */
	public abstract void displayError(String error);
	
	/**
	 * This method displays the game table when the game starts
	 */
	public abstract void startGame();

	/**
	 * This method displays to the view the available actions of the player
	 * @param availableActions are the available actions for the current game state
	 */
	public abstract void displayAvailableActions();
	
	/**
	 * This method displays to the view the current game table
	 * @param clientGame is the updated game table which has to be displayed
	 */
	public abstract void displayGameTable();
	
	/**
	 * This method displays to the view all the informations about the client player
	 * @param player is the updated status of the client player
	 */
	public abstract void displayPlayer();
	
	/**
	 * This method displays to the view the current state of the market
	 * @param market is the updated market
	 */
	public abstract void displayMarket();
	
	/**
	 * This method displays to the view the final ranking of the game which has just terminated
	 * @param finalRankingTable is the list of players with the relative informations
	 */
	public abstract void displayFinalRanking();
	
	/**
	 * This method displays a chat message form another player in a proper way according to the selected view
	 * @param message is the chat sent from another player
	 */
	public abstract void displayChatMessage(String message);
	
	/**
	 * This method's purpose is to notify the user that the market phase is started
	 */
	public abstract void startMarket();
	
	/**
	 * This method's purpose is to notify the user that the market phase is terminated
	 */
	public abstract void closeMarket();
	
	
	/**
	 * This method is invoked when the action DTO needs a RegionDTO parameter to be set
	 * @return the region the user has selected
	 */
	public abstract RegionDTO askForRegionBoard();
	
	/**
	 * This method is invoked when the action DTO needs a PermitTileDTO parameter to be set
	 * @return the region the user has selected
	 */
	public abstract PermitTileDTO askForPermitTile();
	
	/**
	 * This method is invoked when the action DTO needs a CouncillorDTO parameter to be set
	 * @return the region the user has selected
	 */
	public abstract CouncillorDTO askForCouncillor();
	
	/**
	 * This method is invoked when the action DTO needs a council balcony DTO parameter to be set
	 * @return the region the user has selected
	 */
	public abstract CouncillorDTO[] askForCouncilBalcony();
	
	/**
	 * This method is invoked when the action DTO needs a CityDTO parameter to be set
	 * @param acceptableCities is the list of cities from which the user can select one
	 * @return the city the user has selected
	 */
	public abstract CityDTO askForCity(List<CityDTO> acceptableCities);
	
	/**
	 * This method is invoked when the action DTO needs a list of politics cards DTO parameter to be set
	 * @return the list of politics cards the user has selected
	 */
	public abstract List<PoliticsCardDTO> askForPoliticsCards();
	
	/**
	 * This method is invoked when the action DTO needs a number of permit tile parameter to be set
	 * @param selectedRegion is the region that the user has previously selected
	 * @return the number of permit tile the user has selected
	 */
	public abstract int askForNumberOfPermitTile(RegionDTO selectedRegion);
	
	/**
	 * This method is invoked when the action DTO needs a MarketableDTO parameter to be set
	 * @param acceptableObjectsToOffer are the objects that the player can offer to others
	 * @return the marketable object the user has selected
	 */
	public abstract MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer);
	
	/**
	 * This method is invoked when the action DTO needs a price parameter to be set
	 * @return the price the user has selected
	 */
	public abstract int askForPrice();
	
	/**
	 * This method is invoked when the action DTO needs a boolean parameter to be set
	 * @return a boolean which indicates the choice of the user
	 */
	public abstract boolean askForOtherSelling();
	
	/**
	 * This method is invoked when the action DTO needs a OfferDTO parameter to be set
	 * @return the offer the user has selected
	 */
	public abstract OfferDTO askForAcceptingAnOffer();
	
	/**
	 * This method is invoked when the action DTO needs a PermitTileDTO (covered or uncovered) parameter to be set
	 * @return the permit tile the user has selected
	 */
	public abstract PermitTileDTO askForPermitTileUncoveredAndCovered();
	
	
	/**
	 * This method is invoked when the first player connects to the game, so that he can choose from 
	 * the available maps the one that he prefers
	 */
	public abstract void askForMap() throws RemoteException;

}