package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import server.model.gameTable.CardColour;

public class CardColourDTO implements ModelDTO<CardColour>, MarketableDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929965571917578063L;
	private String name;
	


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name=name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	
	
}
