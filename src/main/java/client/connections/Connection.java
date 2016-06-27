package client.connections;


import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.clientNotifies.ClientNotify;
import observerPattern.Observable;

public abstract class Connection extends Observable<ClientNotify> {

	private final Timer timer=new Timer();
	private TimerTask timerTask;

	public Timer getTimer() {
		return timer;
	}

	public TimerTask getTimerTask() {
		return timerTask;
	}
	
	
	public void setTimerTask(TimerTask timerTask) {
		this.timerTask = timerTask;
	}

	public abstract void sendAction(ActionDTO action) throws RemoteException;
}
