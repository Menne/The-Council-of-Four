 
package model;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import controller.BeginState;
import controller.State;
import controller.State;
import controller.State11;
import model.bonus.ScoreBonus;
import model.gameTable.GameTable;
import observerPattern.Observable;
import players.Player;
import view.GameNotify;
import view.ViewNotify;
 
public class Game extends Observable<ViewNotify>{
	
	private final List<Player> players;
	private final List<Player> marketPlayerList;
	private Player currentPlayer;
	private final GameTable gameTable;
	private State state;
	private boolean additionalMainActionBonus;
	private boolean lastLap;
	
               
	public Game(List<Player> players, GameTable gameTable){
		this.players=players;
		this.gameTable=gameTable;
		this.currentPlayer=this.players.get(0);
		this.state=new BeginState();
		this.additionalMainActionBonus=false;
		this.lastLap=false;
		this.marketPlayerList=new ArrayList<Player>();
		this.marketPlayerList.addAll(players);
	}


	public void normalNextPlayer(){
		Player temporaryPlayer=this.currentPlayer;
		this.players.remove(0);
		this.players.add(this.players.size(), temporaryPlayer);
		this.currentPlayer=this.players.get(0);
	}
	

	public void randomNextPlayer(){
		Player temporaryPlayer=this.currentPlayer;
		this.marketPlayerList.remove(0);
		this.marketPlayerList.add(this.marketPlayerList.size(), temporaryPlayer);
		this.currentPlayer=this.marketPlayerList.get(0);
	}
	
	
	public void lastLapNextPlayer() throws IndexOutOfBoundsException{
		players.remove(0);
		if(players.isEmpty())
			throw new IndexOutOfBoundsException("List of players is empty");
		this.currentPlayer=this.players.get(0);
	}
	
	public void pickPoliticsCard(){
		this.currentPlayer.getHand().add(this.gameTable.getPoliticsDeck().pickCard());
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public List<Player> getRandomPlayers() {
		return marketPlayerList;
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


	@Override
	public String toString() {
		return "Game\n\n Players: \n" + players + "\n\n CurrentPlayer: \n" + currentPlayer + "\n\n GameTable:\n" + gameTable
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
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()+player.getAssistants()>drawScore)
				drawScore=player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()+player.getAssistants();
				currentWinnerPlayer=player;
			}
		return currentWinnerPlayer;
	}

}