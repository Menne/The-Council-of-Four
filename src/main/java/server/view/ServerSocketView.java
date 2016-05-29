  package server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.clientNotifies.ErrorDTONotify;
import modelDTO.clientNotifies.PlayerDTONotify;
import modelDTO.playerDTO.PlayerDTO;
import players.Player;
import server.model.Game;
import server.view.notifies.ViewNotify;

public class ServerSocketView extends View implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	private final Game game;
	private final Player player;
	
	public ServerSocketView(Socket socket, Game game, Player player) throws IOException{
		this.socket=socket;
		this.game=game;
		this.player=player;
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
					PlayerDTO playerDTO = new PlayerDTO();
					playerDTO.map(player);
					System.out.println("scrivendo...");
					this.socketOut.writeObject(new PlayerDTONotify(playerDTO));
					System.out.println("scritto!");
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
				System.out.println("view: notifica inviata al player "+this.player.getName());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
