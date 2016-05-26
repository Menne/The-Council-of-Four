package server.model.gameTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.model.bonus.Bonus;

/**
 * It's a List of sets of bonuses.
 * @author Luca
 *
 */
public class NobilityTrack {

	private final List<Set<Bonus>> track;
	
	/**
	 * Initialize the track with empty sets of bonuses.
	 * The length of the track is given in input.
	 * @param lenght of the track.
	 */
	public NobilityTrack(int lenght){
		this.track=new ArrayList<Set<Bonus>>();
		for(int i=0; i<lenght; i++)
			this.track.add(new HashSet<Bonus>());
	}

	public List<Set<Bonus>> getTrack() {
		return track;
	}
	
	/**
	 * Adds a given bonus at the specified index
	 * @param index where to put the set of bonuses
	 * @param bonus to add
	 */
	public void addBonus(int index, Bonus bonus){
		this.track.get(index).add(bonus);
	}

	@Override
	public String toString() {
		return "NobilityTrack [track=" + track + "]";
	}
	
	
	
	

}