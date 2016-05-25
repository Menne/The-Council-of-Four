 
package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.BeginState;
import controller.State;
import controller.WaitingForPlayersState;
import initializer.Initializer;
import model.bonus.ScoreBonus;
import model.gameTable.GameTable;
import model.market.Market;
import observerPattern.Observable;
import players.Player;
import view.ViewNotify;
 
public class Game extends Observable<ViewNotify>{
	
	private final List<Player> players;
	private final Market market;
	private Player currentPlayer;
	private final GameTable gameTable;
	private State state;
	private boolean additionalMainActionBonus;
	private boolean lastLap;
	
               
	public Game() throws IOException{
		this.players=new ArrayList<Player>();
		Initializer init= new Initializer();
		this.gameTable=init.initialize();		
		this.state=new WaitingForPlayersState();
		this.additionalMainActionBonus=false;
		this.lastLap=false;
		this.market=new Market(this.players);		
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


	public void addPlayer(String playerName){
		int numOfPlayers=this.players.size();
		this.players.add(new Player(numOfPlayers+1, playerName, numOfPlayers+1, numOfPlayers+10, this.gameTable.getPoliticsDeck()));
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