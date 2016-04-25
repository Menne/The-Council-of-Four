package gameStuff;

import java.util.Set;
/**
 * 
 * @author andreapasquali
 *
 */
public class RegionBoard {

	private final String name;
	private boolean presenceBonusRegionBoard;
	private PermitDeck regionDeck;
	private Set<PermitTile> uncoveredTiles;
	private CouncilBalcony regionBalcony;
	private final RegionBoardBonusTile regionBoardBonusTile;
	
	public RegionBoard(String name, boolean presenceBonusRegionBoard, PermitDeck regionDeck, Set<PermitTile> 
	uncoveredTiles, CouncilBalcony regionBalcony, RegionBoardBonusTile regionBoardBonusTile){
		this.name=name;
		this.presenceBonusRegionBoard=presenceBonusRegionBoard;
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



	public Set<PermitTile> getUncoveredTiles() {
		return uncoveredTiles;
	}



	public CouncilBalcony getRegionBalcony() {
		return regionBalcony;
	}



	public RegionBoardBonusTile getRegionBoardBonusTile() {
		return regionBoardBonusTile;
	}



	public void changePermitTile() {
		// TODO - implement RegionBoard.changeBusinessTile
		throw new UnsupportedOperationException();
	}

	public void substitutePermitTile() {
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
	 * 
	 * @param colour
	 */
	public boolean checkEmporiums(PlayerColour colour) {
		// TODO - implement RegionBoard.checkEmporiums
		throw new UnsupportedOperationException();
	}

}