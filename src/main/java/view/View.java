package view;

import observerPattern.Observable;
import observerPattern.Observer;

public abstract class View extends Observable implements Observer {

	@Override
	public <C> void update(Observable o, C change) {
		// TODO Auto-generated method stub

	}

}
