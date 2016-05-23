package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.Controller;
import model.Game;


public class Server {

	private final static int PORT=29999;
	private final Game game;
	private final Controller controller;
	
	public Server() throws IOException{
		this.game=new Game();
		this.controller=new Controller(this.game);
	}
	
	public void startSocket() throws IOException{
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		ServerSocket serverSocket=new ServerSocket(PORT);
		
		System.out.println("Server SOCKET READY ON PORT "+PORT);
		
		while(true){
			
			Socket socket=serverSocket.accept();
			ServerSocketView view=new ServerSocketView(socket);
			
			this.game.registerObserver(view);
			view.registerObserver(controller);
			
			executor.submit(view);
			
		}
	}
	
	public static void main(String[] args) throws IOException{
		Server server=new Server();
		server.startSocket();
	}
}
