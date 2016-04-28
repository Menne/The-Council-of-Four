package gameStuff;

import bonus.ScoreBonus;
/**
 * 
 * @author andreapasquali
 *
 */
public class ColourBonusTile {
	
	private final CityColour colour;
	private final ScoreBonus bonus;
	
	public ColourBonusTile(CityColour colour, ScoreBonus bonus){
		this.colour=colour;
		this.bonus=bonus;
	}

	public CityColour getColour() {
		return colour;
	}

	public ScoreBonus getBonus() {
		return bonus;
	}
	
}