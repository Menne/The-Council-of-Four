package client;

import client.ModelDTO.GameDTO;

public class UpdateNotify {
	
	private GameDTO updatedGame;
	
	public UpdateNotify(GameDTO updatedGame) {
		this.updatedGame=updatedGame;
	}

	public GameDTO getUpdatedGame() {
		return updatedGame;
	}
	
}
