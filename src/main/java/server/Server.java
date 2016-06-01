     package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import players.Player;
import server.controller.Controller;
import server.model.Game;
import server.view.ServerSocketView;
import server.view.View;


public class Server {

	private final static int SOCKET_PORT=29999;
	
	private final String NAME="CoF";
	private final static int RMI_PORT=52365;
	
	private final Map<Game, List<View>> gamesMap;
	private Game currentGame;
	private final List<Player> playerList;
	
	
	public Server(){
		this.gamesMap=new HashMap<>();
		this.currentGame=new Game();
		this.gamesMap.put(currentGame, new ArrayList<>());
		this.playerList=new ArrayList<>();
	}
	
	public void newReadySocketPlayer(ServerSocketView view) throws IOException{
		
		this.gamesMap.get(currentGame).add(view);
		view.setGame(currentGame);
		System.out.println("settato game della view");
		this.playerList.add(view.getPlayer());
		System.out.println("aggiunto player alla playerList");
		view.getPlayer().setPlayerNumber(playerList.size());
		
		if(this.gamesMap.get(currentGame).size()==2){
			Controller controller=new Controller(currentGame);
			for(View gameView : this.gamesMap.get(currentGame)){
				currentGame.registerObserver(gameView);
				gameView.registerObserver(controller);
			}
			currentGame.start(new ArrayList<>(playerList));
			playerList.clear();
			this.currentGame=new Game();
			this.gamesMap.put(currentGame, new ArrayList<>());			
		}
	}
	
	public void startSocket() throws IOException, InterruptedException{
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		ServerSocket serverSocket=new ServerSocket(SOCKET_PORT);
		
		System.out.println("Server SOCKET READY ON PORT "+SOCKET_PORT);
		
		while(true){
			
			Socket socket=serverSocket.accept();
			System.out.println("Client Socket Accepted!");
			
			Player player=new Player();
					
			ServerSocketView view=new ServerSocketView(socket, player, this);
			
			executor.submit(view);
			 
		}
	}
	
	public static void main(String[] args) throws IOException{
		Server server=new Server();
		try {
			server.startSocket();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
