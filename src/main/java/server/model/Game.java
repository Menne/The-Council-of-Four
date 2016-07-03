 
package server.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import observerPattern.Observable;
import server.model.gameTable.BonusTile;
import server.model.gameTable.City;
import server.model.gameTable.Emporium;
import server.model.gameTable.GameTable;
import server.model.mappers.GameDTOMapper;
import server.model.mappers.GameMapperInterface;
import server.model.market.Market;
import server.model.player.Player;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.State;
import server.serverNotifies.EndGameServerNotify;
import server.serverNotifies.GameTableServerNotify;
import server.serverNotifies.PlayerServerNotify;
import server.serverNotifies.ServerViewNotify;

/**
 * contains all the informations of the game
 * @author andreapasquali
 *
 */
public class Game extends Observable<ServerViewNotify>{
	
	private List<Player> players;
	private Market market;
	private Player currentPlayer;
	private GameTable gameTable;
	private State state;
	private boolean lastLap;
	private static final int initialNumberOfCards=6;
	private static final int intialNumberOfEmporiums=10;
	private final List<Player> quittedPlayers;
	private GameDTOMapper gameMapper;
	private int mapNumber;
	
	/**
	 * constructor of the class Game;
	 * initializes only the empty list of quitted players, the map number and creates a new GameDTOMapper
	 */
	public Game() {
		this.quittedPlayers=new ArrayList<>();
		this.gameMapper=new GameDTOMapper();
		this.mapNumber=1;
	}
	
	/**
	 * initialies the gametable and the player, setting all the parameters of the players and the state
	 * of the game.
	 * @param playerList List of playing players
	 * @throws IOException
	 */
	public void start(List<Player> playerList) throws IOException{
		Initializer init= new Initializer();
		init.setMapNumber(mapNumber);
		this.gameTable=init.initialize();
		this.players=playerList;
		for(Player player : players){
			player.incrementAssistants(player.getPlayerNumber());
			player.setScore(0);
			player.setNobility(0);
			player.setCoins(player.getPlayerNumber()+9);
			for(int i=0;i<initialNumberOfCards;i++)
				player.addCardToHand(this.gameTable.getPoliticsDeck().pickCard());
			for(int i=0;i<getIntialnumberofemporiums();i++)
				player.getRemainigEmporiums().add(new Emporium(player));
		}
		this.currentPlayer=this.players.get(0);
		this.state=new BeginState();
		this.lastLap=false;
		this.market=new Market();
		
		for (Player player : this.players) 
			this.notifyObserver(new PlayerServerNotify(this, player, Arrays.asList(player)));
		this.notifyObserver(new GameTableServerNotify(this, players, true));
		this.state.updateClients(this);
	}

	/**
	 * change the current player to the next, in a different method if it is the last lap
	 */
	public void nextPlayer() {
		if (!lastLap) {	
			Player player=this.players.remove(0);
			this.players.add(this.players.size(), player);
			this.currentPlayer=this.players.get(0);
		}
		else{
			this.quittedPlayers.add(players.remove(0));
			if (players.isEmpty())
				this.endGame();
			else
				this.currentPlayer=this.players.get(0);
			}
	}
	
	/**
	 * assigns all the end Game bonuses, sending an EndGameNotify with to all players
	 */
	public void endGame(){
		this.assignFinalBonus();
		this.assignBonusPermitTilesEndGame();
		this.assignBonusNobilityEndGame();
		this.sortFinalRankingTable();
		notifyObserver(new EndGameServerNotify(this, quittedPlayers));
	}
	
	/**
	 * @return last Player of the game
	 */
	public Player lastPlayer(){
		Player lastPlayer=players.get(0);
		for(Player player : players)
			if(player.getPlayerNumber()>=lastPlayer.getPlayerNumber())
				lastPlayer=player;
		return lastPlayer;
	}
	
	/**
	 * contains all the methods for starting the market
	 */
	public void startMarket(){
		this.market.clearMarket();
		this.market.getSellingPlayerList().addAll(players);
		this.market.sortSellingPlayerList();
		this.market.getBuyingPlayerList().addAll(players);
		this.market.shuffleBuyingPlayerList();
		this.market.sellingNextPlayer(this);

	}
	
  /**
   * assigns a politic card to the current player of the game
   */
	public void pickPoliticsCard(){
		this.currentPlayer.getHand().add(this.gameTable.getPoliticsDeck().pickCard());
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public GameTable getGameTable() {
		return gameTable;
	}
	
	public static int getIntialnumberofemporiums() {
		return intialNumberOfEmporiums;
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public State getState() {
		return this.state;
	}

	public List<Player> getQuittedPlayers() {
		return quittedPlayers;
	}

	public Market getMarket() {
		return this.market;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isLastLap() {
		return this.lastLap;
	}

	public void setLastLap(boolean lastLap) {
		this.lastLap = lastLap;
	}
	
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public GameMapperInterface getGameMapper() {
		return this.gameMapper;
	}

	public int getMapNumber() {
		return mapNumber;
	}

	public void setMapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}

	/**
	 * assigns 5 point to first players and 2 to seconds
	 */
	public void assignBonusNobilityEndGame(){
		int first=0;
		int second=0;
		int count=0;
		for(Player player: this.quittedPlayers){
			if(player.getNobility()>first){
				second=first;
				first=player.getNobility();
			}
			if(player.getNobility()<first && player.getNobility()>second){
					second=player.getNobility();
			}
		}
		for(Player player: this.quittedPlayers){
			if(player.getNobility()==first){
				player.incrementScore(5);
				count++;
			}
		}
		if(count==1){
			for(Player player: this.quittedPlayers)
				if(player.getNobility()==second)
					player.incrementScore(2);
		}
	}
	
	/**
	 * assigns to every player his list of score bonus
	 */
	public void assignFinalBonus(){
		for(Player player : this.quittedPlayers){
			for(BonusTile bonusTileonus: player.getPlayersFinalBonus())
				bonusTileonus.getBonus().assignBonusToPlayer(player);
		}
	}
	
	/**
	 * assigns 3 points to the player who has more permit tiles
	 */
	public void assignBonusPermitTilesEndGame(){
		int numberOfPermitTiles=0;
		for(Player player : this.quittedPlayers){
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()>numberOfPermitTiles)
				numberOfPermitTiles=player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size();
		}
		for(Player player : this.quittedPlayers)
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()==numberOfPermitTiles)
				player.incrementScore(3);
	}
	
	/**
	 * orders the players comparing them by their score.
	 */
	public void sortFinalRankingTable(){
		Collections.sort(this.quittedPlayers, new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				if(o1.getPlayerNumber()==o2.getPlayerNumber())
					return 0;
				if(o1.getScore()<o2.getScore())
					return 1;
				if(o1.getScore()>o2.getScore())
					return -1;
				if(o1.getNumberOfAssistants()+o1.getHand().size()<o2.getNumberOfAssistants()+o2.getHand().size())
					return 1;
				if(o1.getNumberOfAssistants()+o1.getHand().size()<o2.getNumberOfAssistants()+o2.getHand().size())
					return -1;
				return o1.getPlayerNumber()<o2.getPlayerNumber() ? -1 : 1;
			}
		});
	}

}