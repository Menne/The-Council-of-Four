package client.view.notifies;


import client.view.socket.CLI;

@FunctionalInterface
public interface ClientViewNotify {
	
	public void stamp(CLI view);

}
