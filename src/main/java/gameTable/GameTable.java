package gameTable;
 
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import gameStuff.CouncilBalcony;
import gameStuff.CouncillorsReserve;
import gameStuff.Map;
import gameStuff.NobilityTrack;
import gameStuff.PoliticsDeck;
import gameStuff.RegionBoard;
import gameStuff.RewardToken;
import bonus.ScoreBonus;

@XmlRootElement(name="GameTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameTable {

	//@XmlElementWrapper(name="rewardTokens")
	//@XmlElement(name="RewardToken")
	//private final List<RewardToken> rewardTokens;
	
	private final Map map;
	
	@XmlElementWrapper(name="map")
	@XmlElement(name="regionBoard")
	private final List<RegionBoard> regionBoards;
	private final CouncilBalcony councilOfKing;
	
	@XmlElement(name="CouncillorsReserve")
	private final CouncillorsReserve councilReserve;
	
	@XmlElement(name="PoliticsDeck")
	private final PoliticsDeck politicsDeck;
	
	@XmlElement(name="")
	private final NobilityTrack nobilityTrack;
	
	@XmlElementWrapper(name="kingRewardTiles")
	@XmlElement(name="ScoreBonus")
	private final List<ScoreBonus> kingRewardTiles;
               
	public GameTable(Map map, List<RegionBoard> regionBoards, CouncilBalcony councilOfKing,
			CouncillorsReserve councilReserve, PoliticsDeck politicsDeck, NobilityTrack nobilityTrack){
	//	this.rewardTokens=rewardTokens;
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
		return "map=" + map + "\nregionBoards=" + regionBoards + "\n councilOfKing=" + councilOfKing
				+ "\n councilReserve=" + councilReserve +  "\n nobilityTrack="
				+ nobilityTrack + "\n kingRewardTiles=" + kingRewardTiles;
	}
               
}