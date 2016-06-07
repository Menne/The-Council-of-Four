package server.view;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import client.view.rmi.ClientRMIViewRemote;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.clientNotifies.PlayerAcceptedDTONotify;
import modelDTO.playerDTO.ClientPlayerDTO;
import players.Player;
import server.Server;
import server.model.Game;
import server.model.actions.Quit;
import server.view.notifies.ViewNotify;

public class RMIView extends View implements RMIViewRemote {
	
	private final Map<ClientRMIViewRemote, Player> clientsMap;
	private final Game game;
	
	
	public RMIView(Server server, Game game){
		super(server);
		this.game=game;
		this.clientsMap=new HashMap<>();
	}
	
	
	@Override
	public void update(ViewNotify notify){
		for(Player player : notify.sendTo())
			for(Map.Entry<ClientRMIViewRemote, Player> entry : this.clientsMap.entrySet())
				if(entry.getValue().equals(player))
					try {
						
						entry.getKey().updateClient(notify.toClientNotify());
						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	}

	@Override
	public void registerClient(ClientRMIViewRemote clientStub, String playerName) throws RemoteException {
		
		try {
			
			Player player=new Player(playerName);
			this.clientsMap.put(clientStub, player);
			server.newReadyPlayer(this, player);
			System.out.println("client registered");
			
			ClientPlayerDTO clientPlayerDTO=new ClientPlayerDTO();
			clientPlayerDTO.map(player);
			clientStub.updateClient(new PlayerAcceptedDTONotify(clientPlayerDTO));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	@Override
	public void receiveAction(ActionDTO actionDTO) throws RemoteException {
		
		this.notifyObserver(actionDTO.map(this.game));
		
	}


	@Override
	public void quitPlayer(ClientRMIViewRemote quittingView) throws RemoteException {
		this.notifyObserver(new Quit(clientsMap.get(quittingView)));
		this.clientsMap.remove(quittingView);
		if(clientsMap.isEmpty())
			game.unregisterObserver(this);
		
	}


}
