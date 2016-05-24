package client.actionDTO;

import java.util.List;

import client.ModelDTO.GameDTO;
import client.ModelDTO.RegionDTO;
import client.parser.AcquirePermitTileParser;
import client.parser.ActionParserVisitor;
import model.gameTable.CardColour;

public class AcquirePermitTileDTO implements ActionDTO {

	private Integer numberOfPermitTile;
	private RegionDTO chosenRegion;
	private List<CardColour> cardsToDescard;

	public int getNumberOfPermitTiles() {
		return numberOfPermitTile;
	}

	public RegionDTO getChoosenRegion() {
		return chosenRegion;
	}

	public List<CardColour> getCardsToDescard() {
		return cardsToDescard;
	}
	
	public void setNumberOfPermitTile(Integer numberOfPermitTile) {
		this.numberOfPermitTile=numberOfPermitTile;
	}

	public void setCardsToDescard(List<CardColour> cardsToDescard) {
		this.cardsToDescard=cardsToDescard;
	}

	public void setChosenRegion(RegionDTO chosenRegion) {
		this.chosenRegion=chosenRegion;
	}

	@Override
	public String toString() {
		return "m2: acquire a permit tile";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new AcquirePermitTileParser(this, game);
	}

}
