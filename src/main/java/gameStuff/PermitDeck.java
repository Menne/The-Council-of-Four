package gameStuff;

import java.util.ArrayList;
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
	public PermitDeck() {
		this.permitTiles=new ArrayList<PermitTile>();
	}
	
	public PermitTile pickPermitTile() {
		return this.permitTiles.remove(0);
	}
	
	public void addOnBottom (PermitTile permitTilesToAdd) {
		int size=this.permitTiles.size();
		this.permitTiles.add(size-1,permitTilesToAdd);
	}

	public List<PermitTile> getPermitTiles() {
		return permitTiles;
	}
	
	
}