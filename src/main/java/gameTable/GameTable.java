package gameTable;
 
import java.util.List;


import gameStuff.CouncilBalcony;
import gameStuff.CouncillorsReserve;
import gameStuff.Map;
import gameStuff.NobilityTrack;
import gameStuff.PoliticsDeck;
import gameStuff.RegionBoard;
import bonus.Bonus;


public class GameTable {

	
	private final Map map;
	

	private final List<RegionBoard> regionBoards;
	private final CouncilBalcony councilOfKing;
	private final CouncillorsReserve councilReserve;
	private final PoliticsDeck politicsDeck;
	private final NobilityTrack nobilityTrack;
	private final List<Bonus> kingRewardTiles;
               
	public GameTable(Map map, List<RegionBoard> regionBoards, CouncilBalcony councilOfKing,
			CouncillorsReserve councilReserve, PoliticsDeck politicsDeck, NobilityTrack nobilityTrack,
			List<Bonus> kingRewardTiles){
		this.map=map;
		this.regionBoards= regionBoards;
		this.councilOfKing=councilOfKing;
		this.councilReserve=councilReserve;
		this.politicsDeck=politicsDeck;
		this.kingRewardTiles=kingRewardTiles;
		this.nobilityTrack=nobilityTrack;
		for(RegionBoard region : this.regionBoards)
			region.uncoverPermitTiles();
	}

	public Map getMap() {
		return map;
	}

	public List<RegionBoard> getRegionBoards() {
		return regionBoards;
	}

	public CouncilBalcony getCouncilOfKing() {
		return councilOfKing;
	}

	public CouncillorsReserve getCouncilReserve() {
		return councilReserve;
	}

	public PoliticsDeck getPoliticsDeck() {
		return politicsDeck;
	}

	public List<Bonus> getKingRewardTiles() {
		return kingRewardTiles;
	}
	
	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}

	
	@Override
	public String toString() {
		return "map=" + map + "\nregionBoards=" + regionBoards + "\n councilOfKing=" + councilOfKing
				+ "\n councilReserve=" + councilReserve +  "\n nobilityTrack="
				+ nobilityTrack + "\n kingRewardTiles=" + kingRewardTiles;
	}
               
}