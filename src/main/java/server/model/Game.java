 
package server.model;

import java.io.IOException;import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import initializer.Initializer;
import observerPattern.Observable;
import players.Player;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.Emporium;
import server.model.gameTable.GameTable;
import server.model.market.Market;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.State;
import server.view.notifies.PlayerNotify;
import server.view.notifies.ViewNotify;
 
public class Game extends Observable<ViewNotify>{
	
	private List<Player> players;
	private Market market;
	private Player currentPlayer;
	private GameTable gameTable;
	private State state;
	private boolean lastLap;
	private static final int initialNumberOfCards=6;
	private static final int intialNumberOfEmporiums=10;
	private final List<Player> quittedPlayers;
	
	public Game() {
		this.quittedPlayers=new ArrayList<>();
	}
	
	public void start(List<Player> playerList) throws IOException{
		Initializer init= new Initializer();
		this.gameTable=init.initialize();
		this.players=playerList;
		for(Player player : players){
			player.incrementAssistants(player.getPlayerNumber());
			player.setScore(0);
			player.setNobility(0);
			player.setCoins(player.getPlayerNumber()+9);
			for(int i=0;i<initialNumberOfCards;i++)
				player.addCardToHand(this.gameTable.getPoliticsDeck().pickCard());
			for(int i=0;i<intialNumberOfEmporiums;i++)
				player.getRemainigEmporiums().add(new Emporium(player));
		}
		this.currentPlayer=this.players.get(0);
		this.state=new BeginState();
		this.lastLap=false;
		this.market=new Market();
		
		for (Player player : this.players)
			this.notifyObserver(new PlayerNotify(player, Arrays.asList(player)));
		
		this.state.updateClients(this);
		
	}


	public void nextPlayer() {
		if (!lastLap) {	
			Player player=this.players.remove(0);
			this.players.add(this.players.size(), player);
			this.currentPlayer=this.players.get(0);
		}
		else{
			this.players.remove(0);
			if (players.isEmpty())
				throw new IndexOutOfBoundsException("List of players is empty");
			this.currentPlayer=this.players.get(0);
			}
	}
	
	public Player lastPlayer(){
		Player lastPlayer=players.get(0);
		for(Player player : players)
			if(player.getPlayerNumber()>=lastPlayer.getPlayerNumber())
				lastPlayer=player;
		return lastPlayer;
	}
	
	public void startMarket(){
		this.market.clearMarket();
		this.market.getSellingPlayerList().addAll(players);
		this.market.sortSellingPlayerList();
		this.market.getBuyingPlayerList().addAll(players);
		this.market.shuffleBuyingPlayerList();
		this.market.sellingNextPlayer(this);

	}
	
	public void pickPoliticsCard(){
		this.currentPlayer.getHand().add(this.gameTable.getPoliticsDeck().pickCard());
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public GameTable getGameTable() {
		return gameTable;
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

	
	/**
	 * assigns 5 point to first players and 2 to seconds
	 */
	public void assignBonusNobilityEndGame(){
		int first=0;
		int second=0;
		int count=0;
		for(Player player: this.players){
			if(player.getNobility()>first){
				second=first;
				first=player.getNobility();
			}
			if(player.getNobility()<first && player.getNobility()>second){
					second=player.getNobility();
			}
		}
		for(Player player: this.players){
			if(player.getNobility()==first){
				player.incrementScore(5);
				count++;
			}
		}
		if(count==1){
			for(Player player: this.players)
				if(player.getNobility()==second)
					player.incrementScore(2);
		}
	}
	
	/**
	 * assigns to every player his list of score bonus
	 */
	public void assignFinalBonus(){
		for(Player player : this.players){
			for(ScoreBonus scoreBonus: player.getPlayersFinalBonus())
				scoreBonus.assignBonusToPlayer(player);
		}
	}
	
	/**
	 * assigns 5 points to the player who has more permit tiles
	 */
	public void assignBonusPermitTilesEndGame(){
		int numberOfPermitTiles=0;
		for(Player player : this.players){
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()>numberOfPermitTiles)
				numberOfPermitTiles=player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size();
		}
		for(Player player : this.players)
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()==numberOfPermitTiles)
				player.incrementScore(3);
	}
	
	/**
	 * recognises which player has won the game
	 */
	public Player selectWinner(){
		int winnerScore=0;
		Player currentWinnerPlayer = null;
		for(Player player : this.players){
			if(player.getScore()>winnerScore)
				winnerScore=player.getScore();
		}
		List<Player> winnerPlayers= new ArrayList<Player>(); //list of players with the winnerScore (per gestire il caso di pareggio)  
		for(Player player : this.players){
			if(player.getScore()==winnerScore)
				winnerPlayers.add(player);
		}
		if(winnerPlayers.size()==1)
			return winnerPlayers.get(0);
		int drawScore=0;
		for(Player player: winnerPlayers){
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()+player.getNumberOfAssistants()>drawScore){
				drawScore=player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()+player.getNumberOfAssistants();
				currentWinnerPlayer=player;
			}	
		}
		return currentWinnerPlayer;
	}


}