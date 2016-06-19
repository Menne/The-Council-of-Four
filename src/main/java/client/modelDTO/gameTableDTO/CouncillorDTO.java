package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;

public class CouncillorDTO implements ModelDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3297590450164485862L;
	
	private CardColourDTO colour;
	
	public CouncillorDTO(){	
	}
	
	public CouncillorDTO(CardColourDTO cardColourDTO){
		this.colour=cardColourDTO;
	}

	public CardColourDTO getColour() {
		return colour;
	}

	public void setColour(CardColourDTO colour) {
		this.colour = colour;
	}

	
}
