package model;
 
import java.util.List;
 
import gameTable.GameTable;
import players.Player;
 
public class Game {
               
                private final List<Player> players;
                private Player currentPlayer;
                private final GameTable gameTable;
               
                public Game(List<Player> players, GameTable gameTable){
                               this.players=players;
                               this.gameTable=gameTable;
                               this.currentPlayer=this.players.get(0);
                }
               
}