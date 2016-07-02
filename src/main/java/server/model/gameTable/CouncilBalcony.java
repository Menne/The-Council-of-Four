package server.model.gameTable;

import java.util.Arrays;

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
	public CouncilBalcony(CouncillorsReserve reserve){
		this.councillors=new Councillor[numberOfCouncillors];
		for(int i=0; i<numberOfCouncillors; i++)
			this.councillors[i]=reserve.getCouncillors().remove(i);
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
 * @throws NullPointerException if councillor c is null
 */
	public Councillor substituteCouncillor(Councillor c){
		if(c==null)
			throw new NullPointerException("councillor can't be null");
		Councillor[] temp=new Councillor[numberOfCouncillors];
		Councillor old=this.councillors[numberOfCouncillors-1];
		temp[0]=c;
		for(int i=1; i<numberOfCouncillors; i++)
			temp[i]=this.councillors[i-1];
		this.councillors=temp;
		return old;
		
	}

@Override
public String toString() {
	return Arrays.toString(councillors);
}

}