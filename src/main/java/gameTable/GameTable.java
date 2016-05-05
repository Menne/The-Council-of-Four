package gameTable;
 
import java.util.ArrayList;
import java.util.List;
import gameStuff.CouncilBalcony;
import gameStuff.CouncillorsReserve;
import gameStuff.Map;
import gameStuff.NobilityTrack;
import gameStuff.PoliticsDeck;
import gameStuff.RegionBoard;
import bonus.ScoreBonus;
 
public class GameTable {

	private final Map map;
	private final List<RegionBoard> regionBoards;
	private final CouncilBalcony councilOfKing;
	private final CouncillorsReserve councilReserve;
	private final PoliticsDeck politicsDeck;
	private final NobilityTrack nobilityTrack;
	private final List<ScoreBonus> kingRewardTiles;
               
	public GameTable(Map map, List<RegionBoard> regionBoards, CouncilBalcony councilOfKing,
			CouncillorsReserve councilReserve, PoliticsDeck politicsDeck, NobilityTrack nobilityTrack){
		this.map=map;
		this.regionBoards= regionBoards;
		this.councilOfKing=councilOfKing;
		this.councilReserve=councilReserve;
		this.politicsDeck=politicsDeck;
		this.kingRewardTiles=new ArrayList<ScoreBonus>();
		this.nobilityTrack=nobilityTrack;
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

	public List<ScoreBonus> getKingRewardTiles() {
		return kingRewardTiles;
	}
	
	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}

	/**
	 * Adds a the king rewards tile (that are simply score bonuses) on the table.
	 * @param bonus is the score bonus on the king reward tile to add.
	 */
	public void addKingRewardTile(ScoreBonus bonus){
		this.kingRewardTiles.add(bonus);
	}
	
	@Override
	public String toString() {
		return "GameTable [map=" + map + ", regionBoards=" + regionBoards + ", councilOfKing=" + councilOfKing
				+ ", councilReserve=" + councilReserve + ", politicsDeck=" + politicsDeck + ", nobilityTrack="
				+ nobilityTrack + ", kingRewardTiles=" + kingRewardTiles + "]";
	}
               
}