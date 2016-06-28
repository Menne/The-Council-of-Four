package server.model.gameTable;

import java.util.Collections;
import java.util.List;

/**
 * Models the councillor reserve of game
 * @author Luca
 *
 ***/

public class CouncillorsReserve {

	private final List<Councillor> councillors;
	
	/**
	 * constructor of CouncillorsRserve; initializes a List of Councillor and shuffles it)
	 * @param councillors is the list of councillors to add to the councillor reserve
	 */
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
	 * @throws IndexOutOfBoundsException if councillors reserve is empty or doesn't contain the councillor c
	 */
	public void removeCouncillor(Councillor c) throws IndexOutOfBoundsException{
		if(!this.councillors.isEmpty() && this.councillors.contains(c))
			this.councillors.remove(c);
		else
		throw new IndexOutOfBoundsException("No councillors in the reserve");
	}

	@Override
	public String toString() {
		return "CouncillorsReserve= " + councillors + "]";
	}

}