package model.gameTable;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
/**
 * Models the councillor reserve of game
 * @author Luca
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CouncillorsReserve {
	
	@XmlElementWrapper(name="councillors")
	@XmlElement(name="Councillor")
	private final List<Councillor> councillors;
	
	public CouncillorsReserve(List<Councillor> councillors){
		this.councillors=councillors;
		Collections.shuffle(this.councillors);
	}
	
	public List<Councillor> getCouncillors() {
		return councillors;
	}
	
	/**
	 * Adds a given councillor in the reserve
	 * @param c is councillor to add
	 */
	public void addConcullor(Councillor c) {
		this.councillors.add(c);
	}



	/**
	 * Remove a given councillor from the reserve
	 * @param c is the councillor to remove
	 */
	public void removeCouncillor(Councillor c) throws IndexOutOfBoundsException{
		if(!this.councillors.isEmpty())
			this.councillors.remove(c);
		throw new IndexOutOfBoundsException("No councillors in the reserve");
	}

	@Override
	public String toString() {
		return "CouncillorsReserve [councillors=" + councillors + "]";
	}

}