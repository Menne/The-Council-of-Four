package client.controller;

import java.io.IOException;

import client.ClientOutHandler;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.clientNotifies.ClientNotify;
import modelDTO.gameTableDTO.PlayerDTO;
import observerPattern.Observer;

public class ClientController implements Observer<ActionDTO> {

	private GameDTO clientGame;
	private PlayerDTO playerDTO;
	private final ClientOutHandler clientOutHandler;

	public ClientController(GameDTO clientGame, ClientOutHandler clientOutHandler) {
		this.clientGame=clientGame;
		this.clientOutHandler=clientOutHandler;
		this.playerDTO=new PlayerDTO();
	}
	
	
	public void updateFromIn(ClientNotify clientNotify){
		clientNotify.act(this.clientGame, this.playerDTO);
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
