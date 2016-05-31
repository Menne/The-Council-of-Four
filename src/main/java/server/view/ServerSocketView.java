  package server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.clientNotifies.ErrorDTONotify;
import modelDTO.clientNotifies.PlayerAcceptedDTONotify;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import modelDTO.playerDTO.ClientPlayerDTO;
import players.Player;
import server.Server;
import server.model.Game;
import server.view.notifies.ViewNotify;

public class ServerSocketView extends View implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	private final Game game;
	private final List<Player> serverPlayerList;
	private final Player player;
	private final Server server;
	
	public ServerSocketView(Socket socket, Game game, Player player, List<Player> serverPlayerList, Server server) throws IOException{
		this.socket=socket;
		this.game=game;
		this.player=player;
		this.server=server;
		this.serverPlayerList=serverPlayerList;
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		while(true){
			
			try {

				Object object = this.socketIn.readObject();
				
				if(object instanceof AddPlayerDTO){
					AddPlayerDTO notify=(AddPlayerDTO) object;
					this.player.setName(notify.getPlayerName());					
					this.serverPlayerList.add(player);
					this.player.setPlayerNumber(serverPlayerList.size());
					this.startGame();
										
					GenericPlayerDTO genericPlayerDTO=new GenericPlayerDTO();
					ClientPlayerDTO clientPlayerDTO=new ClientPlayerDTO();
					genericPlayerDTO.map(player);
					clientPlayerDTO.map(player);
					this.socketOut.writeObject(new PlayerAcceptedDTONotify(genericPlayerDTO));
				}
				
				else if(this.player.equals(game.getCurrentPlayer())){
						ActionDTO actionDTO=(ActionDTO) object;				
						this.notifyObserver(actionDTO.map(this.game));
				}
					else
						this.socketOut.writeObject(new ErrorDTONotify("Sorry, is not your turn!"));
					
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}


	@Override
	public void update(ViewNotify notify) {
		try {
			if(notify.sendTo().contains(this.player)){
				this.socketOut.writeObject(notify.toClientNotify());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void startGame() throws IOException{
		if(this.serverPlayerList.size()==2){
			this.game.start(serverPlayerList);
			server.nextGame();
		}
	}

}
