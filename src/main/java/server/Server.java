      package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
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

import server.controller.Controller;
import server.model.Game;
import server.model.player.Player;
import server.view.ServerRMIView;
import server.view.RMIViewRemote;
import server.view.ServerSocketView;
import server.view.View;
/**
 * 
 * @author Luca Scannapieco
 *It's the main class to launch on the server side. 
 *It support Socket and RMI connection.
 */

public class Server {

	private final static int SOCKET_PORT=29999;
	
	private final String NAME="CoF";
	private final static int RMI_PORT=52365;
	private Registry registry;
	private ServerRMIView currentRMIView;
	
	private final Map<Game, Set<View>> gamesMap;
	private Game currentGame;
	private final List<Player> playerList;

	
	/**
	 * Instantiates the first game, and create a new entry in the games map (that associates to a game the views of that game)
	 * Creates the registry for RMI connections and exports on it the first game.
	 * @throws RemoteException if something goes wrong exporting or creating registry  
	 * @throws AlreadyBoundException if something goes wrong binding the view with the NAME.
	 */
	public Server() throws RemoteException, AlreadyBoundException{
		this.gamesMap=new HashMap<>();
		this.currentGame=new Game();
		this.gamesMap.put(currentGame, new HashSet<>());
		this.playerList=new ArrayList<>();
		
		this.registry = LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");
		this.currentRMIView=new ServerRMIView(this, currentGame);
		RMIViewRemote rmiViewRemote=(RMIViewRemote) UnicastRemoteObject.exportObject(currentRMIView,0);
		registry.bind(NAME, rmiViewRemote);
	}
	
	/**
	 * When a new player has done the login (inserting his name), this method is called from the ServerView.
	 * It adds the view in the entry of the gamesMap having currentGame as key.
	 * If there are two readyPlayers, a timer of 20 second will start.
	 * When time's up the current game will start.
	 * @param view is the ServerView used from the ready player
	 * @param player is the ready player
	 */
	public void newReadyPlayer(View view, Player player){
		
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
			}, 2*1000L);
			
		}
	}
	
	/**
	 * Creates the controller for the game.
	 * Registers the views as observer of the game, and the controller as observer of the views.
	 * Start the current game.
	 * Creates the new current game and its relative entry in the games map.
	 * @throws IOException if something goes wrong initializing the game
	 */
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
		this.currentRMIView=new ServerRMIView(this, currentGame);
		RMIViewRemote rmiViewRemote=(RMIViewRemote) UnicastRemoteObject.exportObject(currentRMIView,0);
		registry.rebind(NAME, currentRMIView);
	}
	
	public void setMap(View view, int mapNumber){
		if(gamesMap.get(currentGame).contains(view))
			currentGame.setMapNumber(mapNumber);
	}
	
	/**
	 * Server wait for a Socket connection. When he receive a new connection request 
	 * from a client, he simply create the new Server Socket View thread dedicated to
	 * that client
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
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
