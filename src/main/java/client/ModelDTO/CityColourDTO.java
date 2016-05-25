package client.ModelDTO;

import server.model.gameTable.CityColour;

public class CityColourDTO implements ModelDTO<CityColour>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2914098238189404754L;
	private String name;

	@Override
	public void map(CityColour realObject) {
		this.name=realObject.getName();
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CityColourDTO [name=" + name + "]";
	}

	
	
	
}
