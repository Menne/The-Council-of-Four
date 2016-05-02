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
	private final Set<City> regionCities;
	private boolean presenceBonusRegionBoard;
	private final PermitDeck regionPermitDeck;
	private static final int numberOfUncoveredPermitTiles=2;
	private PermitTile[] uncoveredPermitTiles;
	private final CouncilBalcony regionBalcony;
	private final RegionBoardBonusTile regionBoardBonusTile;
	
	public RegionBoard(String name, boolean presenceBonusRegionBoard, Set<City> regionCities,
			PermitDeck regionPermitDeck, PermitTile[] uncoveredPermitTiles, 
			CouncilBalcony regionBalcony, RegionBoardBonusTile regionBoardBonusTile){
		if(uncoveredPermitTiles.length!=numberOfUncoveredPermitTiles)
			throw new IllegalArgumentException("uncovered permit tiles array must be composed of"
		+uncoveredPermitTiles+ "tiles!");
		
		this.uncoveredPermitTiles=uncoveredPermitTiles;
		this.name=name;
		this.presenceBonusRegionBoard=presenceBonusRegionBoard;
		this.regionCities=regionCities;
		this.regionPermitDeck=regionPermitDeck;
		this.regionBalcony=regionBalcony;
		this.regionBoardBonusTile=regionBoardBonusTile;
	}
	
	public String getName() {
		return name;
	}

	public boolean isPresenceBonusRegionBoard() {
		return presenceBonusRegionBoard;
	}

	public PermitDeck getRegionPermitDeck() {
		return regionPermitDeck;
	}

	public PermitTile[] getUncoveredPermitTiles() {
		return uncoveredPermitTiles;
	}

	public CouncilBalcony getRegionBalcony() {
		return regionBalcony;
	}

	public RegionBoardBonusTile getRegionBoardBonusTile() {
		return regionBoardBonusTile;
	}

	public Set<City> getRegionCities() {
		return regionCities;
	}

	public void setPresenceBonusRegionBoard(boolean presenceBonusRegionBoard) {
		this.presenceBonusRegionBoard = presenceBonusRegionBoard;
	}
	
	/**
	 * Picks a chosen permit tile of the two uncovered, then replaces it with the first tile of the deck
	 * @param numberOfPermitTile is the permit tile you have to pick
	 * @return the permit tile you want to pick
	 */
	public PermitTile pickUncoveredPermitTile(int numberOfPermitTile) {
		PermitTile pickedUncoveredPermitTile=this.uncoveredPermitTiles[numberOfPermitTile];
		this.uncoveredPermitTiles[numberOfPermitTile]=this.regionPermitDeck.pickPermitTile();
		return pickedUncoveredPermitTile;
	}
	
	/**
	 * Substitutes both of the uncovered permit tiles on the region board
	 * by invoking the method addOnBottom of the class PermitDeck ad picking
	 */
	public void substitutePermitTiles() {
		for (int i=0; i<=numberOfUncoveredPermitTiles; i++) {
			this.regionPermitDeck.addOnBottom(uncoveredPermitTiles[i]);
			this.uncoveredPermitTiles[i]=this.regionPermitDeck.pickPermitTile();
		}
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
	public boolean checkEmporiums(Player player) {
		if(regionCities.contains(City.containsEmporium(Emporium.getEmporiumsPlayer=player)!=true))
			return false;
		else
			return true;
	}

	
}