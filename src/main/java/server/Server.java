   package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import players.Player;
import server.controller.Controller;
import server.model.Game;
import server.view.ServerSocketView;


public class Server {

	private final static int SOCKET_PORT=29999;
	
	private final String NAME="CoF";
	private final static int RMI_PORT=52365;
	
	private List<Game> serverGames;
	private List<Controller> serverControllers;
	private final List<List<Player>> serverPlayerLists;
	private int indexOfGames;
	
	
	public Server(){
		this.serverGames=new ArrayList<>();
		this.serverGames.add(new Game());
		
		this.serverControllers=new ArrayList<>();
		this.serverControllers.add(new Controller(serverGames.get(0)));
		
		this.serverPlayerLists=new ArrayList<>();
		this.serverPlayerLists.add(new ArrayList<>());
		
		this.indexOfGames=0;
	}
	
	public void startSocket() throws IOException, InterruptedException{
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		ServerSocket serverSocket=new ServerSocket(SOCKET_PORT);
		
		System.out.println("Server SOCKET READY ON PORT "+SOCKET_PORT);
		
		while(true){
			
			Socket socket=serverSocket.accept();
			System.out.println("Client Socket Accepted!");
			
			Player player=new Player();
					
			ServerSocketView view=new ServerSocketView(socket, serverGames.get(indexOfGames), player, serverPlayerLists.get(indexOfGames),this);
			this.serverGames.get(indexOfGames).registerObserver(view);
			view.registerObserver(serverControllers.get(indexOfGames));
			executor.submit(view);
			
		}
	}
	
	public void nextGame(){
		this.indexOfGames++;
		this.serverGames.add(new Game());
		this.serverControllers.add(new Controller(serverGames.get(indexOfGames)));
		this.serverPlayerLists.add(new ArrayList<>());
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
