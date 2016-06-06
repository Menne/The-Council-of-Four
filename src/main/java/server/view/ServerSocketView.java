   package server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.actionsDTO.QuitDTO;
import modelDTO.clientNotifies.PlayerAcceptedDTONotify;
import modelDTO.playerDTO.ClientPlayerDTO;
import players.Player;
import server.Server;
import server.model.Game;
import server.model.actions.Quit;
import server.view.notifies.ViewNotify;

public class ServerSocketView extends View implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	private Game game;
	private Player player;
	
	public ServerSocketView(Socket socket, Server server) throws IOException{
		super(server);
		this.socket=socket;
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
	}


	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		while(true){
			
			try {

				Object object = this.socketIn.readObject();
				System.out.println("scritta azione");
				if(object instanceof AddPlayerDTO){
					AddPlayerDTO notify=(AddPlayerDTO) object;
					this.player=new Player(notify.getPlayerName());					
					server.newReadyPlayer(this, player);
										
					ClientPlayerDTO clientPlayerDTO=new ClientPlayerDTO();
					clientPlayerDTO.map(player);
					this.socketOut.writeObject(new PlayerAcceptedDTONotify(clientPlayerDTO));
				}
				
				else if(object instanceof QuitDTO)
					this.notifyObserver(new Quit(this.player));
				else{
					ActionDTO actionDTO=(ActionDTO) object;
					this.notifyObserver(actionDTO.map(this.game));
				}
					
				
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

}
