package gameStuff;

import java.util.Map;

import bonus.Bonus;
public class NobilityTrack {

	private final Map<Integer,Bonus> track;
	
	public NobilityTrack(Map<Integer, Bonus> track){
		this.track=track;
	}

	public Map<Integer, Bonus> getTrack() {
		return track;
	}
	
	

}