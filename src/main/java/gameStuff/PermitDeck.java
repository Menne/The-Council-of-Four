package gameStuff;

import java.util.List;

/**	
* deck of permit tiles
* @author Emanuele
*
*/

public class PermitDeck {

	private final List<PermitTile> permitTiles;

	/**
	 * constructor of the permit tiles deck
	 * @param permit tiles are the tiles of the deck
	 */
	public PermitDeck(List<PermitTile> permitTiles) {
		this.permitTiles=permitTiles;
	}
	
	public PermitTile pickPermitTile() {
		PermitTile pickedPermitTile=this.permitTiles.get(this.permitTiles.indexOf(this.permitTiles.size()));
		this.permitTiles.remove(this.permitTiles.indexOf(this.permitTiles.size()));
		return pickedPermitTile;
	}
	
	public void addOnBottom (PermitTile permitTilesToAdd) {
		this.permitTiles.add(0,permitTilesToAdd);
	}

	public List<PermitTile> getPermitTiles() {
		return permitTiles;
	}
	
}