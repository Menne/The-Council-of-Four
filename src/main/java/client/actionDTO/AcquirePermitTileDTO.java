package client.actionDTO;

import java.util.List;

import client.ModelDTO.RegionDTO;
import model.gameTable.CardColour;

public class AcquirePermitTileDTO implements ActionDTO {
	

	private final int numberOfPermitTiles;
	private final RegionDTO choosenRegion;
	private final List<CardColour> politicsCardsToDiscard;
	
	public AcquirePermitTileDTO(int numberOfPermitTiles, RegionDTO choosenRegion, List<CardColour> politicsCardsToDiscard){
		this.numberOfPermitTiles=numberOfPermitTiles;
		this.choosenRegion=choosenRegion;
		this.politicsCardsToDiscard=politicsCardsToDiscard;
	}

	public int getNumberOfPermitTiles() {
		return numberOfPermitTiles;
	}

	public RegionDTO getChoosenRegion() {
		return choosenRegion;
	}

	public List<CardColour> getPoliticsCardsToDiscard() {
		return politicsCardsToDiscard;
	}
	
	@Override
	public String toString() {
		return "m2: acquire a permit tile";
	}
	
}
