package gameStuff;
/**
 * Is the schema for the balconies of the game.
 * @author andreapasquali
 *
 */
public class CouncilBalcony {

	private static final int numberOfCouncillors=4;
	private Councillor[] councillors;
	
	/**
	 * controls the number of councillors
	 * @param councillors
	 */
	public CouncilBalcony(Councillor[] councillors){
		if(councillors.length!=numberOfCouncillors)
			throw new IllegalArgumentException("Council Balcony must be composed of"
		+numberOfCouncillors+ "councillors!");
		this.councillors=councillors;
	}
	
public Councillor[] getCouncillors() {
		return councillors;
	}

public static int getNumberofcouncillors() {
	return numberOfCouncillors;
}

/**
 * Put a new councillor into the balcony following the balcony rules 
 * @param c Is the new councillor
 * @return The removed councillor
 */
	public Councillor substituteCouncillor(Councillor c) {
		Councillor[] temp=new Councillor[numberOfCouncillors];
		Councillor old=this.councillors[numberOfCouncillors-1];
		temp[0]=c;
		for(int i=1; i<numberOfCouncillors; i++)
			temp[i]=this.councillors[i-1];
		this.councillors=temp;
		return old;
		
	}

}