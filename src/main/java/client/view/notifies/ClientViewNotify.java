package client.view.notifies;

import client.view.ClientView;

@FunctionalInterface
public interface ClientViewNotify {
	
	public void updateView(ClientView view);

}
