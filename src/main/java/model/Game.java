
package model;
 
import java.util.List;
import controller.State;
import controller.State11;
import model.actions.Action;
import model.actions.PickPoliticsCard;
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
	
               
	public Game(List<Player> players, GameTable gameTable){
		this.players=players;
		this.gameTable=gameTable;
		this.currentPlayer=this.players.get(0);
		this.gameState=GameState.RUNNING;
		Action pickCard=new PickPoliticsCard(this);
		pickCard.executeAction();
		this.state=new State11();		
	}


	public void nextPlayer(){
		this.currentPlayer=this.players.get(this.players.indexOf(currentPlayer)+1);
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

	@Override
	public String toString() {
		return "Game\n\n Players: \n" + players + "\n\n CurrentPlayer: \n" + currentPlayer + "\n\n GameTable:\n" + gameTable + "]";
	}	
}