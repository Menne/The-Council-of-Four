package model;
/**
 * 
 * @author andreapasquali
 *
 */
public class CouncilBalcony {

	private final int numberOfCouncillors;
	private CardColour[] councillors;
	
	public CouncilBalcony(int numberOfCouncillors, CardColour[] councillors){
		this.numberOfCouncillors=numberOfCouncillors;
		this.councillors=councillors;
	}
	
	public int getNumberOfCouncillors() {
		return numberOfCouncillors;
	}

	public CardColour[] getCouncillors() {
		return councillors;
	}

	/**
	 * 
	 * @param c
	 */
	public Councillor substituteCouncillor(Councillor c) {
		// TODO - implement CouncilBalcony.substituteCouncillor
		throw new UnsupportedOperationException();
	}

}