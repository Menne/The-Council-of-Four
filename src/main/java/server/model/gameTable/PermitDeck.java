package server.model.gameTable;

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
		this.permitTiles=new ArrayList<>();
	}
	
	/**
	 * picks the first tile from the deck
	 * @return the tile picked from the deck
	 * @throws IndexOutOfBoundsException if the deck is empty 
	 */
	public PermitTile pickPermitTile(){
		if(!this.permitTiles.isEmpty())
			return this.permitTiles.remove(0);
		throw new IndexOutOfBoundsException("The deck is empty");
	}
	
	/**
	 * adds to the bottom of the list the permit tile
	 * @param permitTilesToAdd is the permit tile to add to the bottom
	 * @throws IllegalArgumentException if param permit tile is null
	 */
	public void addOnBottom(PermitTile permitTilesToAdd){
		if(permitTilesToAdd==null)
			throw new NullPointerException("permit tile can't be null");
		int size=this.permitTiles.size();
		this.permitTiles.add(size-1,permitTilesToAdd);
	}

	public List<PermitTile> getPermitTiles() {
		return permitTiles;
	}

}