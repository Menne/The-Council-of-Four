package client.view.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import client.view.ClientView;
import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.clientNotifies.ClientNotify;
import modelDTO.parser.Parser;


public class CLIsocket extends ClientView implements Runnable{

	private final Parser parser;
	private final Scanner scanner;
	
	private final ObjectOutputStream socketOut;
	private final ObjectInputStream socketIn;
	
	public CLIsocket(Parser parser, ObjectOutputStream socketOut, ObjectInputStream socketIn) throws IOException {
		
		this.parser=parser;
		this.socketOut=socketOut;
		this.socketIn=socketIn;
		this.scanner=new Scanner(System.in);
	}
	
	public Scanner getScanner() {
		return this.scanner;
	}
	
	

	public ObjectOutputStream getSocketOut() {
		return socketOut;
	}

	@Override
	public void update(ClientViewNotify notify) {
		notify.stamp(scanner);
	}
	
	@Override
	public void input() {
		while (true) {

			String input=this.scanner.nextLine();
			if (this.parser.availableActions().contains(input)) {
				ActionDTO selectedAction=this.parser.actionParser(input);
				this.checkIfParametersNeeded(selectedAction);
			}
			else
				System.out.println("Sorry, action not available!");	
		}
	}
	
	private void checkIfParametersNeeded(ActionDTO selectedAction) {
		if (selectedAction instanceof ActionWithParameters) {
			ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
			this.insertParameters(actionWithParameters);
		}
		else{
			try {
				socketOut.writeObject(selectedAction);
				socketOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void insertParameters(ActionWithParameters selectedAction) {
		this.parser.parametersParser(selectedAction);
		if (selectedAction.checkIfParametersSetted()){
			try {
				
				socketOut.writeObject(selectedAction);
				socketOut.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void welcome(){
		AddPlayerDTO actionDTO=new AddPlayerDTO();
		System.out.println("Welcome to a new game of CoF! Please, tell me your name:");
		String input=scanner.nextLine();
		actionDTO.setPlayerName(input);
		try {
			
			socketOut.writeObject(actionDTO);
			socketOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true){
			try {
				Object object = socketIn.readObject();
				ClientNotify clientNotify=(ClientNotify) object;
				this.notifyObserver(clientNotify);
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

}
