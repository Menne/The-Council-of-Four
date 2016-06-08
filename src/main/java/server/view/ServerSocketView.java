   package server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.actionsDTO.QuitDTO;
import modelDTO.clientNotifies.PlayerAcceptedDTONotify;
import modelDTO.clientNotifies.QuitNotify;
import modelDTO.playerDTO.ClientPlayerDTO;
import players.Player;
import server.Server;
import server.model.Game;
import server.model.actions.Quit;
import server.view.mapperVisitor.ActionDTOMapper;
import server.view.notifies.EndGameNotify;
import server.view.notifies.ViewNotify;

public class ServerSocketView extends View implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	private Game game;
	private Player player;
	private ActionDTOMapper mapper;
	
	public ServerSocketView(Socket socket, Server server) throws IOException{
		super(server);
		this.socket=socket;
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
	}


	public void setGame(Game game) {
		this.game=game;
		this.mapper=new ActionDTOMapper(this.game);
	}

	@Override
	public void run() {
		while(!socket.isClosed()){
			
			try {

				Object object = this.socketIn.readObject();
				if(object instanceof AddPlayerDTO){
					AddPlayerDTO notify=(AddPlayerDTO) object;
			  		this.player=new Player(notify.getPlayerName());					
					server.newReadyPlayer(this, player);
					
					ClientPlayerDTO clientPlayerDTO=new ClientPlayerDTO();
					clientPlayerDTO.map(player);
					this.socketOut.writeObject(new PlayerAcceptedDTONotify(clientPlayerDTO));
				}
				
				else if(object instanceof QuitDTO){
					this.game.unregisterObserver(this);
					this.socketOut.writeObject(new QuitNotify());
					socket.close();
					this.notifyObserver(new Quit(this.player));
				}
				
				else{
					ActionDTO actionDTO=(ActionDTO) object;
					System.out.println("mi è arrivata l'azione");
					this.notifyObserver(actionDTO.startVisitor(this.mapper));
					System.out.println("ho mappato l'azione");
				}
					
				
			} catch (ClassNotFoundException | IOException e) {
				break;
			}
		}
		

	}


	@Override
	public void update(ViewNotify notify) {
		try {
			if(notify.sendTo().contains(this.player)){
				this.socketOut.writeObject(notify.toClientNotify());
			}
			if(notify instanceof EndGameNotify)
				socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
