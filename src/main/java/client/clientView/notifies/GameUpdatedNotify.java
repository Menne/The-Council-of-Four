package client.clientView.notifies;

import client.ModelDTO.GameDTO;
import client.clientView.CLI;

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
