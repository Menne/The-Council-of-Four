package controller;

import model.Game;
import model.GameState;
import observerPattern.Observable;
import view.View;

public class GameLogic extends Observable {
	
	private final Game game;
	
	public GameLogic(Turn turn, Game game, View view){
		this.game=game;
		this.play(view);
	}
	
	
	public Game getGame() {
		return game;
	}

	private void play(View view) {
		int numberOfTurns=0;
		Turn turn;
		while(!this.game.getGameState().equals(GameState.END)){
			while(numberOfTurns!=this.game.getPlayers().size()){
				turn=new NormalTurn(view, this.game);
				this.game.setCurrentTurn(turn);
				turn.executeTurn();
				this.game.nextPlayer();
			}
			turn=new MarketTurn(view, this.game);
			turn.executeTurn();
			
		
		
		}
	
	}
	

}
