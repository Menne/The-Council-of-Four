package observerPattern;

import java.rmi.RemoteException;

@FunctionalInterface
public interface Observer<C> {

	public abstract void update(C o);

}