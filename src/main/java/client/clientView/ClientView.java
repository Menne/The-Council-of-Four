package client.clientView;

import client.ModelDTO.GameDTO;
import client.actionDTO.ActionDTO;
import client.clientView.notifies.ClientViewNotify;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class ClientView extends Observable<ActionDTO> implements Observer<ClientViewNotify> {

	public ClientView(GameDTO clientGame){
		clientGame.registerObserver(this);
	}
	
	@Override
	public abstract void update(ClientViewNotify notify);

}