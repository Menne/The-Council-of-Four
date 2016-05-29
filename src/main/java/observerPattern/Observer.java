package observerPattern;

@FunctionalInterface
public interface Observer<C> {

	public abstract void update(C o);

}