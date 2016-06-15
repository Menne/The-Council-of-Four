package server.view;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.QuitDTORMI;
import client.modelDTO.clientNotifies.PlayerAcceptedDTONotify;
import client.view.rmi.ClientRMIViewRemote;
import server.Server;
import server.model.Game;
import server.model.actions.Quit;
import server.model.player.Player;
import server.view.actionMapperVisitor.ActionDTOMapper;
import server.view.notifies.ViewNotify;

public class ServerRMIView extends View implements RMIViewRemote {
	
	private final Map<ClientRMIViewRemote, Player> clientsMap;
	private final Game game;
	private ActionDTOMapper actionMapper;
	
	
	public ServerRMIView(Server server, Game game){
		super(server);
		this.game=game;
		this.clientsMap=new HashMap<>();
		this.actionMapper=new ActionDTOMapper(this.game);
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
			
			clientStub.updateClient(new PlayerAcceptedDTONotify
					(this.game.getGameMapper().clientPlayerMap(player)));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	@Override
	public void receiveAction(ActionDTO actionDTO) throws RemoteException {
		if(actionDTO instanceof QuitDTORMI){
			ClientRMIViewRemote quittingConnection=
					 ((QuitDTORMI) actionDTO).getQuittingView();
			this.notifyObserver(new Quit(clientsMap.get(quittingConnection)));
			this.clientsMap.remove(quittingConnection);
			if(clientsMap.isEmpty())
				game.unregisterObserver(this);
		}
		else
			this.notifyObserver(actionDTO.startMapper(this.actionMapper));
	}

}
