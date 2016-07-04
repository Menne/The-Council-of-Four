package server.model.gameTable;

import server.model.bonuses.ScoreBonus;

/**
 * models the king bonus tile
 * its attribute is the ScoreBonus of the tile(super)
 * @author cg31
 *
 */
public class KingBonusTile extends BonusTile {

	/**
	 * constructor of KingBonusTile
	 * @param bonus is the bonus of the tile
	 */
	public KingBonusTile(ScoreBonus bonus) {
		super(bonus);
	}
}
