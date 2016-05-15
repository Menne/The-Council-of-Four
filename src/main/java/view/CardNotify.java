package view;

import model.Game;

public class CardNotify implements ViewNotify {

	private final Game game;
	
	public CardNotify(Game game) {
		this.game=game;
	}
	
	@Override
	public void stamp(View view) {
		System.out.println("Card Picked! This is your hand:\n"+
				this.game.getCurrentPlayer().getHand()+
				"\n"+this.game.getState().toString(game));
		

	}

}
