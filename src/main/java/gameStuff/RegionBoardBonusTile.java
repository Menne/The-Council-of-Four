package gameStuff;

import bonus.ScoreBonus;
/**
 * 
 * @author andreapasquali
 *
 */
public class RegionBoardBonusTile {
	private final RegionBoard regionBoard;
	private final ScoreBonus scoreBonus;
	
	public RegionBoardBonusTile(RegionBoard regionBoard, ScoreBonus scoreBonus){
		this.regionBoard=regionBoard;
		this.scoreBonus=scoreBonus;
	}

	public RegionBoard getRegionBoard() {
		return regionBoard;
	}

	public ScoreBonus getScoreBonus() {
		return scoreBonus;
	}
	
	
}