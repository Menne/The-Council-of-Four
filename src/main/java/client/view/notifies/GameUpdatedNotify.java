package client.view.notifies;

import client.view.CLI;
import modelDTO.GameDTO;

public class GameUpdatedNotify implements ClientViewNotify {
	
	private GameDTO gameUpdated;
	
	public GameUpdatedNotify(GameDTO gameDTO) {
		this.gameUpdated=gameDTO;
	}
	
	@Override
	public void stamp(CLI view) {
		System.out.println(this.gameUpdated);
	}

}
