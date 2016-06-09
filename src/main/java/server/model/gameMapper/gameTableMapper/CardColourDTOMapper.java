package server.model.gameMapper.gameTableMapper;

import modelDTO.gameTableDTO.CardColourDTO;
import server.model.gameTable.CardColour;

public class CardColourDTOMapper {
	
	public CardColourDTO map(CardColour realObject) {
		CardColourDTO cardColourDTO=new CardColourDTO();
		cardColourDTO.setName(realObject.getColour());
		return cardColourDTO;
	}

}
