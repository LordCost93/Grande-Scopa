package lordcost.Grande_Scopa.moveController;

import java.util.ArrayList;

import lordcost.Grande_Scopa.board.MainBoard;
import lordcost.Grande_Scopa.player.Player;

public class ScopaLikeMoveController {

	private ArrayList<Player> players;
	private MainBoard mainBoard;
	
	
	public MainBoard getMainBoard() {
		return mainBoard;
	}

	public ScopaLikeMoveController(ArrayList<Player> players, MainBoard mainBoard) {
		this.players = players;
		this.mainBoard = mainBoard;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	
}	