package client.view.notifies;


import client.view.socket.CLIsocket;

@FunctionalInterface
public interface ClientViewNotify {
	
	public void stamp(CLIsocket view);

}
