package server.model.gameTable;
 
import java.util.Collections;
import java.util.List;

/**
 * models the game table of the game and its attributes are: Map, regionBoard, councilOfKing, politicsDeck, 
 * nobilityTrack, kingRewardTiles, king
 * @author cg31
 *
 */
public class GameTable {
	
	private final Map map;
	private final List<RegionBoard> regionBoards;
	private final CouncilBalcony councilOfKing;
	private final CouncillorsReserve councilReserve;
	private final PoliticsDeck politicsDeck;
	private final NobilityTrack nobilityTrack;
	private final List<KingBonusTile> kingRewardTiles;
	private final King king;
               
	/**
	 * is the constructor of the GameTable.
	 * initializes the king searching for the "KingColour" in the cities of the regions
	 * throws illegal argument exception if there is no king or there are too many
	 * @param map map the cities
	 * @param regionBoards of the game
	 * @param councilOfKing is the balcony of the king
	 * @param councilReserve is the list of councillors available
	 * @param politicsDeck is the deck of politics cards
	 * @param nobilityTrack is the list of bonuses of the nobilityTrack
	 * @param kingRewardTiles is the list of bonus tiles
	 */
	public GameTable(Map map, List<RegionBoard> regionBoards, CouncilBalcony councilOfKing,
			CouncillorsReserve councilReserve, PoliticsDeck politicsDeck, NobilityTrack nobilityTrack,
			List<KingBonusTile> kingRewardTiles){
		this.map=map;
		this.regionBoards= regionBoards;
		this.councilOfKing=councilOfKing;
		this.councilReserve=councilReserve;
		this.politicsDeck=politicsDeck;
		this.kingRewardTiles=kingRewardTiles;
		this.nobilityTrack=nobilityTrack;
		for(RegionBoard region : this.regionBoards){
			Collections.shuffle(region.getRegionPermitDeck().getPermitTiles());
			region.uncoverPermitTiles();
		}
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

	public List<KingBonusTile> getKingRewardTiles() {
		return kingRewardTiles;
	}
	
	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}

	
	@Override
	public String toString() {
		return  regionBoards + "\n" + councilOfKing
				+ "\n" + councilReserve +  "\n"
				+ nobilityTrack + "\n" + kingRewardTiles + "\n" + king.getCity();
	}
               
}