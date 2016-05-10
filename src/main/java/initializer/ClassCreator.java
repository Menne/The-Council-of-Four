package initializer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import gameStuff.CardColour;

@XmlRootElement(name="ClassCreator")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassCreator {
	
	@XmlElementWrapper(name="CardColours")
	@XmlElement(name="CardColour")
	List<String> cardColours;
	
	List<String> cities;
	
	public void createGame(){
	
	List<CardColour> cardColourList=new ArrayList<CardColour>();
	
	for(String stringColour : cardColours);
	}
}

