package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;


public class CityColourDTO implements ModelDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2914098238189404754L;
	private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	
	
	
}
