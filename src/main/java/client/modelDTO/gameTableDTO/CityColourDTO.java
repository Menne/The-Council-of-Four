package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;

/**
 * This class provides all the informations about a city colour, but without logic
 * @author cg31
 *
 */
public class CityColourDTO implements ModelDTO{

	private static final long serialVersionUID = -2914098238189404754L;
	private String name;

	/**
	 * @return the name of the colour of the city DTO
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the colour name of the city DTO
	 * @param colour is the colour name
	 */
	public void setName(String name) {
		this.name = name;
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
		CityColourDTO other = (CityColourDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
