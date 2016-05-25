package client.clientController;

import client.UpdateNotify;
import client.ModelDTO.GameDTO;
import client.actionDTO.ActionDTO;
import observerPattern.Observer;

public class ClientController implements Observer<ActionDTO> {

	private boolean send;
	private ActionDTO selectedAction;
	private GameDTO clientGame;

	public ClientController(GameDTO clientGame) {
		this.clientGame=clientGame;
		this.send=false;
	}
	
	public boolean isSend() {
		return send;
	}
	
	public void setSend(boolean send) {
		this.send = send;
	}

	public ActionDTO getSelectedAction() {
		return selectedAction;
	}
	
	public void updateGame(UpdateNotify updatedGame) {
		this.clientGame=updatedGame.getUpdatedGame();
	}
	
	@Override
	public void update(ActionDTO selectedAction) {
		this.selectedAction=selectedAction;
		this.send=true;
	}

}
