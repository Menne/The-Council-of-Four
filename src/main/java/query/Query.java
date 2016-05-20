package query;

import java.io.Serializable;

import model.Game;
import view.ViewNotify;

public abstract class Query implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1099218937646447301L;
	
	public abstract ViewNotify perform(Game game);

}
