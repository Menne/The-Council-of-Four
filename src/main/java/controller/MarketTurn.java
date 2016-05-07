package controller;

import model.Game;
import observerPattern.Observable;
import view.View;

public class MarketTurn extends Turn{
	
	public MarketTurn(View view, Game game){
		super(view,game);
	}

	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <C> void update(Observable o, C change) {
		// TODO Auto-generated method stub
		
	}

}
