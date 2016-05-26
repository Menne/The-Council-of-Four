package modelDTO.clientNotifies;

import modelDTO.GameDTO;

public class GameDTONotify implements ClientNotify{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2738890859980426145L;
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
