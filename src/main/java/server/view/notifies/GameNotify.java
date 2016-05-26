package server.view.notifies;

import modelDTO.GameDTO;
import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.GameDTONotify;
import server.model.Game;

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
