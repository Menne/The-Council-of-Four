package observerPattern;

/**
 * Is the pattern class of the observers.
 * It has not the reference to its observable
 * @author Luca
 *
 */
public interface Observer {
	public <C> void update(Observable o, C change);
}
