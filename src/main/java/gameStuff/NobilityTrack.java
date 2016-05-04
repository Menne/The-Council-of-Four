package gameStuff;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import bonus.Bonus;

/**
 * It's a List of sets of bonuses.
 * @author Media
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
	 * Adds a given set of bonuses at the specified index
	 * @param index where to put the set of bonuses
	 * @param bonuses to add
	 */
	public void setBonus(int index, Set<Bonus> bonuses){
		this.track.add(index, bonuses);
	}
	
	
	
	

}