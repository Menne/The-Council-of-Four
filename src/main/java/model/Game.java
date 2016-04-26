
package model;
 
import java.util.List;
import gameTable.GameTable;
import players.Player;
 
public class Game {
	private final List<Player> players;
	private Player currentPlayer;
	private final GameTable gameTable;
	private GameState gameState;
               
	public Game(List<Player> players, GameTable gameTable){
		this.players=players;
		this.gameTable=gameTable;
		this.currentPlayer=this.players.get(0);
		this.gameState=GameState.RUNNING;
	}
	
	public void setCurrentPlayer(){
		this. currentPlayer=this.players.get(this.players.indexOf(currentPlayer)+1);
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

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public String toString() {
		return "Game [players=" + players + ", currentPlayer=" + currentPlayer + ", gameTable=" + gameTable + "]";
	}	
}