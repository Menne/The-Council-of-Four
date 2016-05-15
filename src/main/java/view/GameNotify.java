package view;

import model.Game;

public class GameNotify implements ViewNotify {
	
	private final Game game;
	
	public GameNotify(Game game) {
		this.game=game;
	}

	@Override
	public void stamp(View view) {
		System.out.println(game.toString());
		
	}

}
