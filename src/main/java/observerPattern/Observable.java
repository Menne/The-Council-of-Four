package observerPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Is the pattern class of the observable.
 * It has the reference to its observers.
 * @author Luca
 *
 */
public abstract class Observable {

	private List<Observer> observers;
	
	/**
	 * It just initialize the list with an arrayList
	 */
	public Observable(){
		observers=new ArrayList<Observer>();
	}
	
	/**
	 * Register a given observer
	 * @param o Is the observer to attach
	 */
	public void registerObserver(Observer o){
		this.observers.add(o);
	}
	/**
	 * Remove a given observer
	 * @param o Is the observer to remove
	 */
	public void deleteObserver(Observer o){
		this.observers.remove(o);
	}
	
	/**
	 * Call the update method of all its observers
	 * @param Is the generic change the the observable did
	 * and want to notify to its observers 
	 */
	public <C> void notifyObservers(C change){
		for(Observer o: this.observers){
			o.update(this, change); 
		}
	}
}
