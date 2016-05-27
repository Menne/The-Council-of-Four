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

	private final static int PORT=29999;
	private final List<Player> currentPlayerList;
	private Game currentGame;
	private Controller currentController;
	
	public Server(){
		this.currentPlayerList=new ArrayList<Player>();
		this.currentGame=new Game();
		this.currentController=new Controller(currentGame);
	}
	
	public void startSocket() throws IOException, InterruptedException{
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		ServerSocket serverSocket=new ServerSocket(PORT);
		
		System.out.println("Server SOCKET READY ON PORT "+PORT);
		
		while(true){
			
			Socket socket=serverSocket.accept();
			System.out.println("Client Socket Accepted!");
			
			Player player=new Player();
			this.currentPlayerList.add(player);
			player.setPlayerNumber(currentPlayerList.size());
					
			ServerSocketView view=new ServerSocketView(socket, currentGame, player);
			this.currentGame.registerObserver(view);
			view.registerObserver(currentController);
			executor.submit(view);
			
			if(this.currentPlayerList.size()==2){
				currentGame.start(currentPlayerList);
				this.currentGame=new Game();
				this.currentController=new Controller(currentGame);
				this.currentPlayerList.clear();
			}
			
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
