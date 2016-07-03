package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;

/**
 * This class provides all the informations about a card colour, but without logic
 * @author cg31
 *
 */
public class CardColourDTO implements ModelDTO {

	private static final long serialVersionUID = 8929965571917578063L;
	private String name;
	
	/**
	 * No-arguments constructor of CardColourDTO
	 */
	public CardColourDTO() {
		//non-arguments constructor
	}
	
	/**
	 * Constructor of CardColourDTO
	 * @param name is the name of the colour
	 */
	public CardColourDTO(String name) {
		this.name=name;
	}

	/**
	 * @return the name of the colour of the card or councillor 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the colour name of the card or councillor
	 * @param colour is the colour name
	 */
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CardColourDTO other = (CardColourDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
}
