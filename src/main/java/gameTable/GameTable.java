package gameTable;
 
import java.util.Set;
 
import gameStuff.ColourBonusTile;
import gameStuff.CouncilBalcony;
import gameStuff.CouncillorsReserve;
import gameStuff.KingRewardTile;
import gameStuff.Map;
import gameStuff.PoliticsDeck;
import gameStuff.RegionBoard;
 
public class GameTable {
 
                private final Map map;
                private final RegionBoard[] regionBoards;
                private final CouncilBalcony councilOfKing;
                private final CouncillorsReserve councilReserve;
                private final PoliticsDeck politicsDeck;
                private final PoliticsDeck politicsDiscard;
                private final Set<ColourBonusTile> colourBounsTiles;
                private final Set<KingRewardTile> kingRewardTiles;
               
                public GameTable(Map map, RegionBoard[] regionBoards, CouncilBalcony councilOfKing,
                                               CouncillorsReserve councilReserve, PoliticsDeck politicsDeck, PoliticsDeck politicsDiscard,
                                               Set<ColourBonusTile> colourBounsTiles, Set<KingRewardTile> kingRewardTiles){
                               this.map=map;
                               this.regionBoards=regionBoards;
                               this.councilOfKing=councilOfKing;
                               this.councilReserve=councilReserve;
                               this.politicsDeck=politicsDeck;
                               this.politicsDiscard=politicsDiscard;
                               this.colourBounsTiles=colourBounsTiles;
                               this.kingRewardTiles=kingRewardTiles;
                }
               
}