package client.connections;


import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import client.modelDTO.actionsDTO.ActionDTO;
import clientUpdates.ClientUpdate;
import observerPattern.Observable;
/**
 * The abstract connection. The implementations for our game are Socket and RMI.
 * @author cg31
 *
 */
public abstract class Connection extends Observable<ClientUpdate> {

	private final Timer timer=new Timer();
	private TimerTask timerTask;

	/**
	 * @return the timer used from the connections to disconnect the client.
	 */
	public Timer getTimer() {
		return timer;
	}
	
	/**
	 * @return the current timer task that will be scheduled from the timer
	 */
	public TimerTask getTimerTask() {
		return timerTask;
	}
	
	/**
	 * Sets a new timer task
	 * @param timerTask the new timer task
	 */
	public void setTimerTask(TimerTask timerTask) {
		this.timerTask = timerTask;
	}

	/**
	 * Connections use this method to send action to the server.
	 * @param action is the action to send to the server
	 * @throws RemoteException if, in the RMI implementation, something goes wrong calling the server stub
	 */
	public abstract void sendAction(ActionDTO action) throws RemoteException;
}
