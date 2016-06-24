package client.modelDTO.playerDTO;

import client.modelDTO.ModelDTO;
import client.modelDTO.marketDTO.MarketableDTO;

public class AssistantDTO implements MarketableDTO, ModelDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 447991061441414920L;
	private final int id;
	
	public AssistantDTO() {
		id=0;
	}

	@Override
	public String toString() {
		return "AssistantDTO";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssistantDTO other = (AssistantDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
