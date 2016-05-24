package client.actionDTO;

import java.util.List;

import client.ModelDTO.RegionDTO;
import model.gameTable.CardColour;

public class AcquirePermitTileDTO implements ActionDTO {
	

	private int numberOfPermitTiles;
	private RegionDTO choosenRegion;
	private List<CardColour> politicsCardsToDiscard;


	public int getNumberOfPermitTiles() {
		return numberOfPermitTiles;
	}

	public RegionDTO getChoosenRegion() {
		return choosenRegion;
	}

	public List<CardColour> getPoliticsCardsToDiscard() {
		return politicsCardsToDiscard;
	}
	
	public void setNumberOfPermitTiles(int numberOfPermitTiles) {
		this.numberOfPermitTiles = numberOfPermitTiles;
	}

	public void setChoosenRegion(RegionDTO choosenRegion) {
		this.choosenRegion = choosenRegion;
	}

	public void setPoliticsCardsToDiscard(List<CardColour> politicsCardsToDiscard) {
		this.politicsCardsToDiscard = politicsCardsToDiscard;
	}

	@Override
	public String toString() {
		return "m2: acquire a permit tile";
	}
	
}
