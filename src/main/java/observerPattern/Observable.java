package observerPattern;

import java.util.ArrayList;
import java.util.List;


public abstract class Observable<C> {
	
	private List<Observer<C>> observers;
	
	public Observable(){
		this.observers = new ArrayList<>();
	}
	
	public void registerObserver(Observer<C> o){
		this.observers.add(o);
	}
	
	public void unregisterObserver(Observer<C> o){
		this.observers.remove(o);
	}

	public void notifyObserver(C c){		//è cambiato un oggetto e sta a noi dire di che tipo è l'oggetto e quindi castarlo a seconda del tipo
											//
		for(Observer<C> o: this.observers){
			 o.update(c);
		}
	}
	
}

