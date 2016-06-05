package client.view.rmi;

import java.rmi.RemoteException;
import java.util.Scanner;

import client.view.ClientView;
import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.clientNotifies.ClientNotify;
import modelDTO.parser.Parser;
import server.view.RMIViewRemote;

public class CLIrmi extends ClientView implements ClientRMIViewRemote{
	
	private final Parser parser;
	private final Scanner scanner;
	private final RMIViewRemote serverStub;
	
	public CLIrmi(Parser parser, RMIViewRemote serverStub) {
		this.parser=parser;
		this.serverStub=serverStub;
		this.scanner=new Scanner(System.in);
	}
	

	@Override
	public void update(ClientViewNotify notify) {
		notify.stamp(scanner);		
	}

	@Override
	public void updateClient(ClientNotify clientNotify) throws RemoteException {
		this.notifyObserver(clientNotify);		
	}

	@Override
	public void input() throws RemoteException {
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


	private void checkIfParametersNeeded(ActionDTO selectedAction) throws RemoteException {
		if (selectedAction instanceof ActionWithParameters) {
			ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
			this.insertParameters(actionWithParameters);
		}
		else
			this.serverStub.receiveAction(selectedAction);
	}


	private void insertParameters(ActionWithParameters actionWithParameters) throws RemoteException {
		this.parser.parametersParser(actionWithParameters);
		if (actionWithParameters.checkIfParametersSetted())
			this.serverStub.receiveAction(actionWithParameters);
		
	}
	
	public void welcome() throws RemoteException{
		AddPlayerDTO actionDTO=new AddPlayerDTO();
		System.out.println("Welcome to a new game of CoF! Please, tell me your name:");
		String input=scanner.nextLine();
		actionDTO.setPlayerName(input);
		this.serverStub.registerClient(this, input);
	}
	
	
	
	
}
