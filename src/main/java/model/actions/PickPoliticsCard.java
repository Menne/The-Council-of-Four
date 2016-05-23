package model.actions;

import model.Game;
import view.GameNotify;

public class PickPoliticsCard implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
		
		game.setState(game.getState().pickPoliticsCardTransition());
		game.notifyObserver(new GameNotify(game));
		return true;
	}

	
	@Override
	public String toString() {
		return "pc: pick a politics card";
	}

}
