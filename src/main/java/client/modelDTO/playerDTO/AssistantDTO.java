package client.modelDTO.playerDTO;

import client.modelDTO.ModelDTO;
import client.modelDTO.marketDTO.MarketableDTO;

/**
 * This class provides all the informations about an assistant, but without logic
 * @author cg31
 *
 */
public class AssistantDTO implements MarketableDTO, ModelDTO {

	private static final long serialVersionUID = 447991061441414920L;
	private final int id;
	
	/**
	 * Constructor of AssistantDTO
	 */
	public AssistantDTO() {
		this.id=0;
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
