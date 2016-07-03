package server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.AddPlayerDTO;
import client.modelDTO.actionsDTO.ChooseMapDTO;
import client.modelDTO.actionsDTO.QuitDTO;
import clientUpdates.PlayerAcceptedUpdate;
import clientUpdates.QuitUpdate;
import server.Server;
import server.model.Game;
import server.model.actions.Quit;
import server.model.mappers.ActionDTOMapper;
import server.model.player.Player;
import server.serverNotifies.EndGameServerNotify;
import server.serverNotifies.ServerViewNotify;

/**
 * The Socket implementation of Server View
 * @author Luca Scannapieco
 *
 */
public class ServerSocketView extends ServerView implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	private Game game;
	private Player player;
	private ActionDTOMapper actionMapper;
	
	/**
	 * 
	 * @param socket The Socket used for the connection.
	 * @param server The server that is creating the view.
	 * @throws IOException if something goes wrong creating the Objects Stream
	 */
	public ServerSocketView(Socket socket, Server server) throws IOException{
		super(server);
		this.socket=socket;
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
	}


	/**
	 * Sets the instance of the game for this view.
	 * @param game The instance of the game to set.
	 */
	public void setGame(Game game) {
		this.game=game;
		this.actionMapper=new ActionDTOMapper(this.game);
	}

	/**
	 * This method is executed on a different thread.
	 * It listens for DTO actions on the socket connection.
	 * It receive DTO actions, translate the using a visitor pattern and notifies them to the controller.
	 * In case of AddPlayer action it instantiates the new player, informs the server that a new player is ready and informs the player that he has been accepted.
	 * In case of a Quit action it informs the model the the player has left the game and unregister itself as observer of the model.
	 */
	@Override
	public void run() {
		while(!socket.isClosed()){
			
			try {
				Object object = this.socketIn.readObject();
				if(object instanceof AddPlayerDTO){
					AddPlayerDTO notify=(AddPlayerDTO) object;
			  		this.player=new Player(notify.getPlayerName());					
					server.newReadyPlayer(this, player);
					
					this.socketOut.writeObject(new PlayerAcceptedUpdate
							(this.game.getGameMapper().clientPlayerMap(player)));
				}
				else if(object instanceof ChooseMapDTO){
					ChooseMapDTO chooseMapDTO=(ChooseMapDTO)object;
					server.setMap(this, chooseMapDTO.getMapNumber());
				}
				else if(object instanceof QuitDTO){
					this.game.unregisterObserver(this);
					this.socketOut.writeObject(new QuitUpdate());
					socket.close();
					this.notifyObserver(new Quit(this.player));
				}
				
				else{
					ActionDTO actionDTO=(ActionDTO) object;
					this.notifyObserver(actionDTO.startMapper(this.actionMapper));
				}
				
			} catch (ClassNotFoundException | IOException e) {
				break;
			}
		}
	}

	/**
	 * This method receives notifies from the model, 
	 * translate them in client notifies and send the to the client through the socket connection.
	 * 
	 */
	@Override
	public void update(ServerViewNotify notify) {
		try {
			if(notify.sendTo().contains(this.player)){
				this.socketOut.writeObject(notify.toClientNotify());
			}
			if(notify instanceof EndGameServerNotify)
				socket.close();
			
		} catch (IOException e) {
			Logger logger=Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "Failed to close the socket", e);
		}
		
	}

}
