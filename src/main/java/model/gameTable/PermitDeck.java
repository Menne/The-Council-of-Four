package model.gameTable;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**	
* deck of permit tiles
* @author Emanuele
*
*/

@XmlAccessorType(XmlAccessType.FIELD)
public class PermitDeck {
	
	@XmlElementWrapper(name="permitTiles")
	@XmlElement(name="PermitTile")
	private final List<PermitTile> permitTiles;

	/**
	 * constructor of the permit tiles deck
	 * @param permit tiles are the tiles of the deck
	 */
	public PermitDeck() {
		this.permitTiles=new ArrayList<PermitTile>();
	}
	
	public PermitTile pickPermitTile() throws IndexOutOfBoundsException{
		if(!this.permitTiles.isEmpty())
			return this.permitTiles.remove(0);
		throw new IndexOutOfBoundsException("The deck is empty");
	}
	
	public void addOnBottom (PermitTile permitTilesToAdd) {
		int size=this.permitTiles.size();
		this.permitTiles.add(size-1,permitTilesToAdd);
	}

	public List<PermitTile> getPermitTiles() {
		return permitTiles;
	}
	
	
}