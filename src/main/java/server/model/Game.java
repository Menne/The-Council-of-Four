 
package server.model;

import java.io.IOException;import java.util.ArrayList;
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
import server.view.notifies.GameNotify;
import server.view.notifies.ViewNotify;
 
public class Game extends Observable<ViewNotify>{
	
	private  List<Player> players;
	private  Market market;
	private Player currentPlayer;
	private GameTable gameTable;
	private State state;
	private boolean additionalMainActionBonus;
	private boolean lastLap;
	private static final int initialNumberOfCards=6;
	private static final int intialNumberOfEmporiums=10;
	
	
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
		this.additionalMainActionBonus=false;
		this.lastLap=false;
		this.market=new Market(this.players);
		this.notifyObserver(new GameNotify(this, players));
	}


	public void nextPlayer(){
		if(!lastLap){
			Player temporaryPlayer=this.currentPlayer;
			this.players.remove(0);
			this.players.add(this.players.size(), temporaryPlayer);
			this.currentPlayer=this.players.get(0);
		}
		else{
			players.remove(0);
			if(players.isEmpty())
				throw new IndexOutOfBoundsException("List of players is empty");
			this.currentPlayer=this.players.get(0);
		}
	}
	
	public void startMarket(){
		this.market.clearMarket();
		this.market.shuffleBuyingPlayerList();
		this.market.sellingNextPlayer();

	}
	
	public void pickPoliticsCard(){
		this.currentPlayer.getHand().add(this.gameTable.getPoliticsDeck().pickCard());
	}

	public List<Player> getPlayers() {
		return players;
	}

	public GameTable getGameTable() {
		return gameTable;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public State getState() {
		return state;
	}


	public Market getMarket() {
		return market;
	}


	public void setState(State state) {
		this.state = state;
	}


	public boolean isAdditionalMainActionBonus() {
		return additionalMainActionBonus;
	}


	public void setAdditionalMainActionBonus(boolean additionalMainActionBonus) {
		this.additionalMainActionBonus = additionalMainActionBonus;
	}


	public boolean isLastLap() {
		return lastLap;
	}


	public void setLastLap(boolean lastLap) {
		this.lastLap = lastLap;
	}
	
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}


	public Player addPlayer(String playerName){
		Player player = new Player();
		this.players.add(player);
		return player;
	}


	@Override
	public String toString() {
		return "Game\n\n Players: \n" + players + "\n\n CurrentPlayer: \n" + currentPlayer + "\n\n" + gameTable
				+"\n\n"+state.toString(this);
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
	 * @return
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
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()+player.getNumberOfAssistants()>drawScore)
				drawScore=player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()+player.getNumberOfAssistants();
				currentWinnerPlayer=player;
			}
		return currentWinnerPlayer;
	}

}