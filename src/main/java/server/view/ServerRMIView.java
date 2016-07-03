package server.view;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.connections.ClientRMIViewRemote;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ChooseMapDTO;
import client.modelDTO.actionsDTO.QuitDTORMI;
import clientUpdates.PlayerAcceptedUpdate;
import server.Server;
import server.model.Game;
import server.model.actions.Quit;
import server.model.mappers.ActionDTOMapper;
import server.model.player.Player;
import server.serverNotifies.ServerViewNotify;

/**
 * The RMI implementation of the Server view
 * @author Luca Scannapieco
 *
 */
public class ServerRMIView extends ServerView implements RMIViewRemote {
	
	private final Map<ClientRMIViewRemote, Player> clientsMap;
	private final Game game;
	private ActionDTOMapper actionMapper;
	
	/**
	 * @param server The server that is creating the view
	 * @param game The instance of the game model.
	 */
	public ServerRMIView(Server server, Game game){
		super(server);
		this.game=game;
		this.clientsMap=new HashMap<>();
		this.actionMapper=new ActionDTOMapper(this.game);
	}
	
	/**
	 * This method receive the notifies from the model, 
	 * translate them in client notifies and send the translated notifies to the client.
	 */
	@Override
	public void update(ServerViewNotify notify){
		for(Player player : notify.sendTo())
			for(Map.Entry<ClientRMIViewRemote, Player> entry : this.clientsMap.entrySet())
				if(entry.getValue().equals(player))
					try {
						
						entry.getKey().updateClient(notify.toClientNotify());
						
					} catch (RemoteException e) {
						Logger logger=Logger.getAnonymousLogger();
						logger.log(Level.SEVERE, "Failed to send notify with RMI", e);
					}
	}

	/**
	 * Remote method called from the client when a new player is ready.
	 * It creates a new entry in the clientsMap that associates the client stub to the player.
	 * Informs the server that a new player is ready.
	 * Informs the client that he has been accepted.
	 */
	@Override
	public void registerClient(ClientRMIViewRemote clientStub, String playerName) throws RemoteException {
		
		try {
			
			Player player=new Player(playerName);
			this.clientsMap.put(clientStub, player);
			server.newReadyPlayer(this, player);
			System.out.println("client registered");
			
			clientStub.updateClient(new PlayerAcceptedUpdate
					(this.game.getGameMapper().clientPlayerMap(player)));
			
		} catch (IOException e) {
			Logger logger=Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "Failed", e);
		}				
	}

	/**
	 * Remote method called from the client when he wants to do an action.
	 * It translates the DTO action using the visitor pattern and notifies the action to the controller.
	 * In case of QuitActon it also remove the entry from the clientMap, and unregister the view as observer of the game if there are no more RMI players in this game. 
	 */
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
		else if(actionDTO instanceof ChooseMapDTO){
			ChooseMapDTO chooseMapDTO=(ChooseMapDTO)actionDTO;
			server.setMap(this, chooseMapDTO.getMapNumber());
		}
		else
			this.notifyObserver(actionDTO.startMapper(this.actionMapper));
	}

}
