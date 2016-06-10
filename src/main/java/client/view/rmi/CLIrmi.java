package client.view.rmi;

import java.rmi.RemoteException;
import java.util.Scanner;

import client.view.ClientView;
import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.EndGameDTONotifies;
import modelDTO.parser.Parser;
import server.view.RMIViewRemote;

public class CLIrmi extends ClientView implements ClientRMIViewRemote{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1023456563506023542L;
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
		if(clientNotify instanceof EndGameDTONotifies){
			System.out.println("GAME OVER\n FINAL RANKING TABLE: \n"+((EndGameDTONotifies)clientNotify).getPlayers());
			scanner.close();
		}
		else
			this.notifyObserver(clientNotify);		
	}

	@Override
	public void input() throws RemoteException {
		String input="";
		while (!"quit".equals(input)) {
			try{
				input=this.scanner.nextLine();
			}catch(IllegalStateException e){
				break;
			}
			if (this.parser.availableActions().contains(input)) {
				ActionDTO selectedAction=this.parser.actionParser(input);
				if (parametersNeeded(selectedAction)) {
					ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
					this.insertParametersAndSend(actionWithParameters);
				}
				else
					sendAction(selectedAction);
			}
			else if("quit".equals(input)){
				serverStub.quitPlayer(this);
			}
			else
				System.out.println("Sorry, action not available!");	
		}		
	}
	
	@Override
	protected void sendAction(ActionDTO action) throws RemoteException{
		this.serverStub.receiveAction(action);
	}


	private boolean parametersNeeded(ActionDTO selectedAction) throws RemoteException {
		return (selectedAction instanceof ActionWithParameters);			
	}


	private void insertParametersAndSend(ActionWithParameters actionWithParameters) throws RemoteException {
		this.parser.parametersParser(actionWithParameters);
		if (actionWithParameters.checkIfParametersSetted())
			sendAction(actionWithParameters);
		
	}
	
	public void welcome(String name) throws RemoteException{
		this.serverStub.registerClient(this, name);
	}
	
	
	
	
}
