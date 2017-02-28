package lordcost.Grande_Scopa.moveController;

import java.util.ArrayList;
import java.util.List;

import lordcost.Grande_Scopa.board.MainBoard;
import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.hand.ClassicGameHand;
import lordcost.Grande_Scopa.player.Player;

public class ClassicGameMoveController extends ScopaLikeMoveController {
	
	//private PlayerCardsGained pcg; sono gli attributi ereditati!
	//private MainBoard mainBoard;
	
	public ClassicGameMoveController(ArrayList<Player> player, MainBoard mainBoard) {
		super(player, mainBoard);
	}

	public List<Move> calculatePossibleMoves(ClassicGameHand hand, MainBoard mainBoard){
		
		List<Move> moves= new ArrayList<Move>();
		for(Move move : singleCardTaken(hand, mainBoard))//aggiungo tutte le mosse possibili con prese singole all'array finale moves
			moves.add(move);
		return moves;
	}
	
	private List<Move> singleCardTaken(ClassicGameHand hand, MainBoard mainBoard){
		
		List<Move> moves = new ArrayList<Move>();
		if(!simpleSingleCardTaken(hand, mainBoard).isEmpty())
			for(Move move : simpleSingleCardTaken(hand, mainBoard))//aggiungo tutte le mosse con prese singole semplici all'array delle prese singole
				moves.add(move);
		if(doppioneSingleCardTaken(hand, mainBoard) != null)
			moves.add(doppioneSingleCardTaken(hand, mainBoard));
		
		return moves;				
	}
	
	private List<Move> simpleSingleCardTaken(ClassicGameHand hand, MainBoard mainBoard){
		
		Move move;
		List<Move> moves = new ArrayList<Move>();
		List<MainCard> cardTaken = new ArrayList<MainCard>();
		for(MainCard card : mainBoard.getBoard())
			if(hand.getCardPlayed().getRank()==card.getRank() && !hand.getCardPlayed().getSuit().equals(card.getSuit())){
				cardTaken.add(card);//quando le rimuovo dal board e le aggiungo in PCG?
				move = new Move((MainCard) hand.getCardPlayed(),cardTaken,0,0);
				moves.add(move);
				return moves;
			}	
		return null;			
	}
	
	private Move doppioneSingleCardTaken(ClassicGameHand hand,MainBoard mainBoard){
		
		List<MainCard> cardTaken = new ArrayList<MainCard>();
		for(MainCard card : mainBoard.getBoard())
			if(hand.getCardPlayed().getRank()==card.getRank() && hand.getCardPlayed().getSuit().equals(card.getSuit())){
				cardTaken.add(card);
				return new Move((MainCard) hand.getCardPlayed(),cardTaken,1,0);// non ci sono possibilità di più doppioni semplici con stessa card played
			}	
		return null;		
	}

	/*public void prizeRibattutaToPcg(int playerGainRib) {//TODO
		this.getPlayers().get(playerGainRib).getPcg().;
		
	}*/ //bisogna passargli una nuova struttura dati ottenibile da Move, dove al posto di card played e taken ho una lista di carte ordinate che dice l'ordine in cui segnare le eventuali scope
	//così come deciso dall'utente (gli si manda una lista di carte a schermo e il numero di carte da scegliere come scope, e lui ad esempio scrive 4 3 1 indicando che vuole la quarta carta da
	//sinistra come la scopa più in alto. Poi gli si chiede se c'è bisogno quali carte vuole mettere come terzi di punto, e le carte rimanenti (se ci sono) diventano le carte comuni da mettere
	//sopra l'ultima scopa. La struttura quindi ha List<MainCard> Scope (ordinato l'inserimento!), List<MainCard> thirds, List<MainCard> othercards
		
}
