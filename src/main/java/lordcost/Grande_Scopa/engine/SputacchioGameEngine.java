package lordcost.Grande_Scopa.engine;

import java.util.List;

import lordcost.Grande_Scopa.board.MainBoard;
import lordcost.Grande_Scopa.cards.MainDeck;
import lordcost.Grande_Scopa.cards.SupportDeck;
import lordcost.Grande_Scopa.player.Player;

public class SputacchioGameEngine {
	
	private SupportDeck supportDeck;
	private MainDeck mainDeck;
	private MainBoard mainBoard;
	private List<Player> players;
	private GameState gamestate;
	private int playerDoingTurnNumber;

	public SputacchioGameEngine(SupportDeck supportDeck, MainDeck mainDeck,
			MainBoard mainBoard, List<Player> players, GameState gamestate,
			int playerDoingTurnNumber) {
		this.supportDeck = supportDeck;
		this.mainDeck = mainDeck;
		this.mainBoard = mainBoard;
		this.players = players;
		this.gamestate = gamestate;
		this.playerDoingTurnNumber = playerDoingTurnNumber;				
	}
	
	
}
