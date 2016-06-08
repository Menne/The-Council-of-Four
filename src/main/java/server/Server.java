      package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import players.Player;
import server.controller.Controller;
import server.model.Game;
import server.view.RMIView;
import server.view.RMIViewRemote;
import server.view.ServerSocketView;
import server.view.View;


public class Server {

	private final static int SOCKET_PORT=29999;
	
	private final String NAME="CoF";
	private final static int RMI_PORT=52365;
	private Registry registry;
	private RMIView currentRMIView;
	
	private final Map<Game, Set<View>> gamesMap;
	private Game currentGame;
	private final List<Player> playerList;

	
	
	public Server() throws RemoteException, AlreadyBoundException{
		this.gamesMap=new HashMap<>();
		this.currentGame=new Game();
		this.gamesMap.put(currentGame, new HashSet<>());
		this.playerList=new ArrayList<>();
		
		this.registry = LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");
		this.currentRMIView=new RMIView(this, currentGame);
		RMIViewRemote rmiViewRemote=(RMIViewRemote) UnicastRemoteObject.exportObject(currentRMIView,0);
		registry.bind(NAME, currentRMIView);
	}
	
	
	public void newReadyPlayer(View view, Player player) throws IOException{
		
		this.gamesMap.get(currentGame).add(view);
		if(view instanceof ServerSocketView){
			ServerSocketView serverSocketView= (ServerSocketView) view;
			serverSocketView.setGame(currentGame);
		}
		this.playerList.add(player);
		player.setPlayerNumber(playerList.size());
		
		if(this.playerList.size()==2){
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					try {
						
						startGame();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}, 2*1000);
			
		}
	}
	
	public void startGame() throws IOException{
		Controller controller=new Controller(currentGame);
		for(View gameView : this.gamesMap.get(currentGame)){
			currentGame.registerObserver(gameView);
			gameView.registerObserver(controller);
		}
		currentGame.start(new ArrayList<>(playerList));
		
		playerList.clear();
		this.currentGame=new Game();
		this.gamesMap.put(currentGame, new HashSet<>());
		this.currentRMIView=new RMIView(this, currentGame);
		RMIViewRemote rmiViewRemote=(RMIViewRemote) UnicastRemoteObject.exportObject(currentRMIView,0);
		registry.rebind(NAME, currentRMIView);
	}
	
	public void startSocket() throws IOException, InterruptedException{
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		ServerSocket serverSocket=new ServerSocket(SOCKET_PORT);
		
		System.out.println("Server SOCKET READY ON PORT "+SOCKET_PORT);
		
		while(true){
			
			Socket socket=serverSocket.accept();
			System.out.println("Client Socket Accepted!");
					
			ServerSocketView view=new ServerSocketView(socket, this);
			
			executor.submit(view);
			 
		}
	}
	
	public static void main(String[] args) throws IOException, AlreadyBoundException{
		Server server=new Server();
		try {
			
			server.startSocket();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
