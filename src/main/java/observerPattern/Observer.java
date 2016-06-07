package observerPattern;


public interface Observer<C> {

	public abstract void update(C o);

	public default void update(C c, Observable<C> observable) throws IllegalStateException{
		throw new IllegalStateException();
	}

}