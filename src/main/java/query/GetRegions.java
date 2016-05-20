package query;

import model.Game;
import view.ViewNotify;

public class GetRegions extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6342790451434417939L;

	@Override
	public ViewNotify perform(Game game) {
//		return game.getGameTable().getRegionBoards();
		return null;
	}

}
