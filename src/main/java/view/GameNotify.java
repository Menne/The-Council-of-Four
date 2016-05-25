package view;

import client.ClientNotify;
import client.GameDTONotify;
import client.ModelDTO.GameDTO;
import model.Game;

public class GameNotify implements ViewNotify {
	
	private final Game game;
	
	public GameNotify(Game game) {
		this.game=game;
	}

	@Override
	public ClientNotify toClientNotify() {
		GameDTO gameDTO=new GameDTO();
		gameDTO.map(this.game);
		System.out.println("player almost Added!!!!!");
		return new GameDTONotify(gameDTO);
		
	}

}
