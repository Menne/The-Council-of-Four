package gameStuff;

import java.util.List;

public class CouncillorsReserve {
	
	private final List<Councillor> councillors;
	
	public CouncillorsReserve(List<Councillor> councillors){
		this.councillors=councillors;
	}

	/**
	 * 
	 * @param c
	 */
	public void addConcullor(Councillor c) {

	}

	public List<Councillor> getCouncillors() {
		return councillors;
	}

	/**
	 * 
	 * @param c
	 */
	public void removeCouncillor(Councillor c) {

	}

	@Override
	public String toString() {
		return "CouncillorsReserve [councillors=" + councillors + "]";
	}

}