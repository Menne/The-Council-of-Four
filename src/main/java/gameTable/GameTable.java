package gameTable;
 
import java.util.List;
import java.util.Set; 
import gameStuff.CouncilBalcony;
import gameStuff.CouncillorsReserve;
import gameStuff.KingRewardTile;
import gameStuff.Map;
import gameStuff.PoliticsDeck;
import gameStuff.RegionBoard;
 
public class GameTable {
 
	private final Map map;
	private final List<RegionBoard> regionBoards;
	private final CouncilBalcony councilOfKing;
	private final CouncillorsReserve councilReserve;
	private final PoliticsDeck politicsDeck;
	private final Set<KingRewardTile> kingRewardTiles;
               
	public GameTable(Map map, List<RegionBoard> regionBoards, CouncilBalcony councilOfKing,
			CouncillorsReserve councilReserve, PoliticsDeck politicsDeck, Set<KingRewardTile> kingRewardTiles){
		this.map=map;
		this.regionBoards= regionBoards;
		this.councilOfKing=councilOfKing;
		this.councilReserve=councilReserve;
		this.politicsDeck=politicsDeck;
		this.kingRewardTiles=kingRewardTiles;
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

	public Set<KingRewardTile> getKingRewardTiles() {
		return kingRewardTiles;
	}
               
}