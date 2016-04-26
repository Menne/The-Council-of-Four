package model;

import java.util.Set;

import players.*;

/**
 * 
 * @author andreapasquali
 *
 */
public class CityColour {

	private final String name;
	private boolean presenceBonusColour;
	private final Set<City> colourCities;

	public CityColour(String name, boolean presenceBonusColour, Set<City> colourCities){
		this.name=name;
		this.presenceBonusColour=presenceBonusColour;
		this.colourCities=colourCities;
	}

	
	/**
	 * returns true if there is an emporium e in every city of this colour.
	 * @param e
	 * @return
	 */
	public boolean checkEmporium(Emporium e) {
	if(colourCities.contains(c.containsEmporium(e)!=true))
		return false;
	else 
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