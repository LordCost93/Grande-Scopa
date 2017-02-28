package lordcost.Grande_Scopa.engine;

import java.rmi.RemoteException;
import java.util.ArrayList;

import lordcost.Grande_Scopa.board.MainBoard;
import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.cards.MainDeck;
import lordcost.Grande_Scopa.cards.SupportDeck;
import lordcost.Grande_Scopa.constants.Constants;
import lordcost.Grande_Scopa.hand.ClassicGameHand;
import lordcost.Grande_Scopa.hand.Hand;
import lordcost.Grande_Scopa.moveController.ClassicGameMoveController;
import lordcost.Grande_Scopa.player.Player;

public class ClassicGameEngine {

	private MainDeck mainDeck;
	private SupportDeck supportDeck;
	private MainBoard mainBoard;
	private ArrayList<Player> players;
	private GameState gamestate;
	private int playerDoingTurnNumber;
	private EngineUtility engineUtility;
	private Filter filter;
	private ClassicGameMoveController cgController;
	private static final int CARDSINHAND=6;
	private static final int FIRSTPLAYER=0;
	
	public ClassicGameEngine(SupportDeck supportDeck, MainDeck mainDeck,
			MainBoard mainBoard, ArrayList<Player> players, GameState gamestate,
			int playerDoingTurnNumber, EngineUtility engineUtility, Filter filter) {
		super();
		this.mainDeck = mainDeck;
		this.supportDeck = supportDeck;
		this.mainBoard = mainBoard;
		this.players = players;
		this.gamestate = gamestate;
		this.playerDoingTurnNumber = playerDoingTurnNumber;
		this.engineUtility= engineUtility;
		this.filter= filter;
		this.cgController= new ClassicGameMoveController(this.players, this.mainBoard);
	}
	
	public void distributeHands() {//inside there is the constructor of the hand of each player
		int i;
		ArrayList<MainCard> cardsPicked = new ArrayList<MainCard>();
		for(Player pl : this.players){
			for(i=0;i<CARDSINHAND;i++)
				cardsPicked.add(mainDeck.pickSingleCard());
			Hand hand = new ClassicGameHand(cardsPicked, cgController);
			pl.setCGHand(hand);
		}
	}	
	
	public void substituteJolly() {
		
		for(Player pl : this.players)
			pl.getCGHand().substituteJolly(mainDeck, pl);		
					
	}
	
	public void classicGameEngine() {
		int i, cardsRemained=0, numCardsInHand;
		
		for(i=0; i<players.size();i++)
			cardsRemained= this.players.get(i).getCGHand().getHand().size() + cardsRemained; // conto le carte totali rimaste in mano ai due o più giocatori
		
		if(cardsRemained !=0){
			
			if(this.playerDoingTurnNumber==this.players.size())//pdtn starts at 0, players size is 2 if there are 2 players
				this.playerDoingTurnNumber=FIRSTPLAYER;
			
			filter.changeNotifier(this.playerDoingTurnNumber);
			numCardsInHand = this.players.get(this.playerDoingTurnNumber).getCGHand().getHand().size();
			filter.announceToPlayCard(numCardsInHand);
			
			this.playerDoingTurnNumber++;
		}
		else{
			;//TODO cosa si fa? si mette in ENDSTATE? In realtà non si deve arrivare a questo punto. INVECE Sì, l'if non si può metter prima. 
		}
			
		
		
		
	}

	public void verifyValidMadWomanRank(String chosenValue) {// manca però che cosa faccio se il rank va bene. Devo fare la presa con quella carta!//TODO
		ArrayList<Integer> madWomanRanks= mainBoard.getMadWomanRanks();
		int finalRank;
		int chosenRank = engineUtility.parseMadWomanValue(chosenValue);
		if(chosenRank==0){// richiedo l'input
			filter.reAssignRankToMadwoman(madWomanRanks);
			return;
		}	
		finalRank = engineUtility.verifyPresenceInMadWomanRanks(chosenRank, madWomanRanks);
		if(finalRank==0){//richiedo l'input
			filter.reAssignRankToMadwoman2(madWomanRanks);
			return;
		}
		
		
	}

	public void playCard(int cardNumber) {
		
		int playerGainRibattuta = otherPlayerPrizeRibattuta(this.playerDoingTurnNumber);
		
		MainCard cardPlayed= this.players.get(this.playerDoingTurnNumber).getCGHand().getHand().get(cardNumber);
		
		Constants constant = this.players.get(this.playerDoingTurnNumber).getCGHand().playCardCGRibattuta(cardPlayed, this.players.get(this.playerDoingTurnNumber));// contains setCardPlayed.
		
		if(constant == Constants.IMPOSSIBLEREPLY){//current player is already in PAUSESTATE
			try {
				filter.getNotifierForRibattuta(this.playerDoingTurnNumber);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			filter.askPlayerWhichScopaChoose(this.mainBoard.getPrizeRibattuta());
		}
			//cgController.prizeRibattutaToPcg(playerGainRibattuta);//TODO non è così semplice, bisogna chiedere all'altro giocatore quali scope scegliere, non si deve semplicemente buttare le carte
			//nel pcg avversario senza chiederglielo
			
			
		this.players.get(this.playerDoingTurnNumber).getCGHand().playCardCGMadWomanRank(cardPlayed, this.players.get(this.playerDoingTurnNumber));// activate only if card is madwoman
		
	}
	
	private int otherPlayerPrizeRibattuta(int pdtn){
		if(pdtn >=1)
			return pdtn - 1; //il giocatore che ha giocato prima è quello con l'id inferiore di 1 a lui. si guarda la posizione nell'array players che parte da 0 (come l'id)
		return this.players.size() - 1; // è il giocatore con l'ultima posizione nell'array players, nel caso di pdtn == 0
		
	}

	
	
	
	
	
}
