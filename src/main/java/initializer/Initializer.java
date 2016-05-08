package initializer;

import java.io.File;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlElement;

import gameStuff.Map;
import gameTable.GameTable;


public class Initializer{
	
	public static void mapInitializer(String[] args) throws Exception {
    	JAXBContext jc = JAXBContext.newInstance(GameTable.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        GameTable gameTable= (GameTable) unmarshaller.unmarshal(new File("src/main/gameTable.xml"));
	}
	
	

}
