package controller;

import model.Game;
import model.GameState;
import observerPattern.Observable;
import observerPattern.Observer;
import view.View;

public class GameLogic extends Observable implements Observer{
	
	private final Game game;
	private Turn currentTurn;
	
	public GameLogic(Game game){
		this.game=game;
//		view.registerObserver(this);
	}
	


	public Game getGame() {
		return game;
	}

	public void play() {
		int numberOfTurns=0;
		while(!this.game.getGameState().equals(GameState.END)){
			while(numberOfTurns!=this.game.getPlayers().size()){
				this.currentTurn=new NormalTurn(this);
				this.game.setCurrentTurn(this.currentTurn);
				this.currentTurn.executeTurn();
				this.game.nextPlayer();
			}
			this.currentTurn=new MarketTurn(this);
			this.currentTurn.executeTurn();				
		}
	
	}


	@Override
	public <C> void update(Observable o, C change) {
		if(this.currentTurn instanceof NormalTurn){
			NormalTurn currentTurn=(NormalTurn) this.currentTurn;
			currentTurn.receivePack(change);
		}
		
	}
	

}
