package client.view.notifies;

import java.rmi.RemoteException;
import java.util.List;
import java.util.TimerTask;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.QuitDTO;
import client.view.ClientView;

public class ClientAvailableActionsNotify implements ClientViewNotify {

	private List<ActionDTO> availableActions;
	private String message;

	public ClientAvailableActionsNotify(List<ActionDTO> availableActions, String message) {
		this.availableActions=availableActions;
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMessage(this.message);
		view.displayAvailableActions(this.availableActions);
		
		if(!availableActions.isEmpty()){
			view.getConnection().setTimerTask(new TimerTask() {
				
				@Override
				public void run() {
					try {
						view.getConnection().sendAction(new QuitDTO());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}
			});
			view.getConnection().getTimer().schedule(view.getConnection().getTimerTask(), 300*1000);
		}
		
	}

}
