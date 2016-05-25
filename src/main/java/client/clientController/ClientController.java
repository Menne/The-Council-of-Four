package client.clientController;

import java.io.IOException;

import client.ClientNotify;
import client.ClientOutHandler;
import client.ModelDTO.GameDTO;
import client.actionDTO.ActionDTO;
import observerPattern.Observer;

public class ClientController implements Observer<ActionDTO> {

	private GameDTO clientGame;
	private final ClientOutHandler clientOutHandler;

	public ClientController(GameDTO clientGame, ClientOutHandler clientOutHandler) {
		this.clientGame=clientGame;
		this.clientOutHandler=clientOutHandler;
	}
	
	
	public void updateFromIn(ClientNotify clientNotify){
		clientNotify.act(this.clientGame);
	}
	
	@Override
	public void update(ActionDTO selectedAction) {
		try {
			
			this.clientOutHandler.getSocketOut().writeObject(selectedAction);
			this.clientOutHandler.getSocketOut().flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
