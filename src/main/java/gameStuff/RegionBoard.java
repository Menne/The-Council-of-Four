package gameStuff;

import java.util.Set;

import players.Player;
/**
 * 
 * @author andreapasquali
 *
 */
public class RegionBoard {

	private final String name;
	private final Set<City> citiesOfTheRegion;
	private boolean presenceBonusRegionBoard;
	private final PermitDeck regionDeck;
	private static final int numberOfPermitTiles=2;
	private PermitTile[] uncoveredTiles;
	private final CouncilBalcony regionBalcony;
	
	
	public Set<City> getCitiesOfTheRegion() {
		return citiesOfTheRegion;
	}



	public void setPresenceBonusRegionBoard(boolean presenceBonusRegionBoard) {
		this.presenceBonusRegionBoard = presenceBonusRegionBoard;
	}

	private final RegionBoardBonusTile regionBoardBonusTile;
	
	public RegionBoard(String name, boolean presenceBonusRegionBoard, Set<City> citiesOfTheRegion,
			PermitDeck regionDeck, PermitTile[] uncoveredTiles, 
			CouncilBalcony regionBalcony, RegionBoardBonusTile regionBoardBonusTile){
		if(uncoveredTiles.length!=numberOfPermitTiles)
			throw new IllegalArgumentException("uncovered permit tiles array must be composed of"
		+uncoveredTiles+ "tiles!");
		this.uncoveredTiles=uncoveredTiles;
		this.name=name;
		this.presenceBonusRegionBoard=presenceBonusRegionBoard;
		this.citiesOfTheRegion=citiesOfTheRegion;
		this.regionDeck=regionDeck;
		this.uncoveredTiles=uncoveredTiles;
		this.regionBalcony=regionBalcony;
		this.regionBoardBonusTile=regionBoardBonusTile;
	}

	
	public String getName() {
		return name;
	}



	public boolean isPresenceBonusRegionBoard() {
		return presenceBonusRegionBoard;
	}



	public PermitDeck getRegionDeck() {
		return regionDeck;
	}



	public PermitTile[] getUncoveredTiles() {
		return uncoveredTiles;
	}



	public CouncilBalcony getRegionBalcony() {
		return regionBalcony;
	}



	public RegionBoardBonusTile getRegionBoardBonusTile() {
		return regionBoardBonusTile;
	}


/**
 * IMPLEMETARE TAKECARD IN PERMIT DECK!!
 * @param i can be only 0 or 1 (it rappresents the card to remove)
 */
	
	
	public PermitTile[] pickPermitTile(int i) {
		//if(i!=0 && i!=1)
		//throw new IllegalArgumentException("index parameter must be 0 or 1");
		
		//permitTile[i]=PermitDeck.pickCard();
		return uncoveredTiles;
	}
	
	public void SubstitutePermitTile() {
		//TODO 
	}

	public void substituteBothPermitTiles() {
		// TODO - implement RegionBoard.substituteBusinessTile
		throw new UnsupportedOperationException();
	}

	/**
	 * it removes the region bonus board tile.
	 * @return true if the tile was present, false if it was impossible to remove tile because it wasn't there
	 */
	public boolean removeBonusRegionBoardTile() {
		
			if	(presenceBonusRegionBoard==true){
				presenceBonusRegionBoard=false;
				return true;}
			else 
				return presenceBonusRegionBoard;
	}

	/**
	 * it returns true if player has an emporium on each city of the region; false otherwise
	 * @param colour
	 */
	/*public boolean checkEmporiums(Player player) {
		if(citiesOfTheRegion.contains(City.containsEmporium(Emporium.getEmporiumsPlayer=player)!=true))
			return false;
		else
			return true;
	}*/

	
}