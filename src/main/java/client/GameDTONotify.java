package client;

import client.ModelDTO.GameDTO;


public class GameDTONotify implements ClientNotify{
	
	private GameDTO updatedGame;
	
	public GameDTONotify(GameDTO updatedGame) {
		this.updatedGame=updatedGame;
	}

	@Override
	public void act(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate=this.updatedGame;
		System.out.println(gameDTOtoupdate);
	}


	
}
