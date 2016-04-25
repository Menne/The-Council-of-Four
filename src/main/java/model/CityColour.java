package model;

import players.*;

/**
 * 
 * @author andreapasquali
 *
 */
public class CityColour {

	private final String name;
	private boolean presenceBonusColour;

	public CityColour(String name, boolean presenceBonusColour){
		this.name=name;
		this.presenceBonusColour=presenceBonusColour;
	}

	
	/**
	 * TODO scorrere il set di città di questo colore e darmi true se il player le ha tutte
	 * @param p
	 * @return
	 */
	public boolean checkEmporium(Player p) {
	return true;
	}
	
	public String getName() {
		return name;
	}


	public boolean getPresenceBonusColour() {
		return presenceBonusColour;
	}


	/**
	 * it changes the presence of Bonus Colour tile from true to false;
	 * @returns true if it can be done; false if it wasn't already present
	 */
	public boolean removeBonusColourTile() {
		if	(presenceBonusColour==true){
			presenceBonusColour=false;
			return true;}
		else 
			return presenceBonusColour;
	}

}