package server.model.gameTable;
 
import java.util.List;

import server.model.bonus.ScoreBonus;

public class GameTable {
	
	private final Map map;
	private final List<RegionBoard> regionBoards;
	private final CouncilBalcony councilOfKing;
	private final CouncillorsReserve councilReserve;
	private final PoliticsDeck politicsDeck;
	private final NobilityTrack nobilityTrack;
	private final List<ScoreBonus> kingRewardTiles;
	private final King king;
               
	
	public GameTable(Map map, List<RegionBoard> regionBoards, CouncilBalcony councilOfKing,
			CouncillorsReserve councilReserve, PoliticsDeck politicsDeck, NobilityTrack nobilityTrack,
			List<ScoreBonus> kingRewardTiles){
		this.map=map;
		this.regionBoards= regionBoards;
		this.councilOfKing=councilOfKing;
		this.councilReserve=councilReserve;
		this.politicsDeck=politicsDeck;
		this.kingRewardTiles=kingRewardTiles;
		this.nobilityTrack=nobilityTrack;
		for(RegionBoard region : this.regionBoards)
			region.uncoverPermitTiles();
		City kingCity=null;
		int i=0;
		for(RegionBoard region : this.regionBoards)
			for(City city : region.getRegionCities())
				if("KingColour".equals(city.getColour().getName())){
					i++;
					kingCity=city;
				}
		if(i!=1)
			throw new IllegalArgumentException("king not present or too many kings present");
		this.king=new King(kingCity);
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

	public King getKing() {
		return king;
	}

	public List<ScoreBonus> getKingRewardTiles() {
		return kingRewardTiles;
	}
	
	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}

	
	@Override
	public String toString() {
		return  regionBoards + "\n" + councilOfKing
				+ "\n" + councilReserve +  "\n"
				+ nobilityTrack + "\n" + kingRewardTiles;
	}
               
}