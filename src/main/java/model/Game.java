
package model;
 
import java.util.List;

import controller.BeginState;
import controller.State;
import controller.State11;
import model.gameTable.GameTable;
import observerPattern.Observable;
import players.Player;
import view.ViewNotify;
 
public class Game extends Observable<ViewNotify>{
	
	private final List<Player> players;
	private Player currentPlayer;
	private final GameTable gameTable;
	private GameState gameState;
	private State state;
	private boolean additionalMainActionBonus;
	
               
	public Game(List<Player> players, GameTable gameTable){
		this.players=players;
		this.gameTable=gameTable;
		this.currentPlayer=this.players.get(0);
		this.gameState=GameState.RUNNING;
		this.state=new BeginState();
		this.state=new State11();
		this.additionalMainActionBonus=false;
	}


	public void nextPlayer(){
		this.currentPlayer=this.players.get(this.players.indexOf(currentPlayer)+1);
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


	public GameState getGameState() {
		return gameState;
	}

	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	
	public boolean isAdditionalMainActionBonus() {
		return additionalMainActionBonus;
	}


	public void setAdditionalMainActionBonus(boolean additionalMainActionBonus) {
		this.additionalMainActionBonus = additionalMainActionBonus;
	}


	@Override
	public String toString() {
		return "Game\n\n Players: \n" + players + "\n\n CurrentPlayer: \n" + currentPlayer + "\n\n GameTable:\n" + gameTable + "]";
	}	
}