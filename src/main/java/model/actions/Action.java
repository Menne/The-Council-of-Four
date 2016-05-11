package model.actions;

/**
 * It's the class that models the generic action.
 * All the actions will operate on the protected attribute inherited from this class.
 * @author Luca
 *
 */
public abstract interface Action {

	
	public abstract boolean executeAction();
	
	public abstract void decrementAction();
}
