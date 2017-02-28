package lordcost.Grande_Scopa.engine;

/**
 * @author Andrea
 * This class implements all the methods called by CommandHandler and calls the methods of the model (those methods are almost all defined in the class Gioco). Along
 * with Filter this class can be considered the controller of MVC
 */
public class Controller{
	MainEngine game;
	public static final int FINEGIOCO=9;
		
	/**Constructor
	 * @param partita instance of the class Gioco, the match the controller supervise
	 */
	public Controller(MainEngine game) {
		this.game=game;
	}
	//NOTA: in gioco esiste una funzione che ti porta ad uno stato del gioco successivo
	//NON faccio il cambiamento di stato!! 
	//In ogni caso quando cambio lo stato filter se ne accorge

	
	//askController Se Y attacca, altrimenti pesca il suono
	/** Controller of the phase of the game when an alien has to answer if he wants to attack or pick a sound card
	 * @param idGioc Id of the player who is doing the turn
	 * @param value string containing "Y" or "X". In the first case the method attacco() is called, in the second case the next phase of the turn becomes DRAWSTATE and the method suono() is called
	 */
	/*public void ask(int idGioc, String value){
		if("Y".equals(value)){
			attacco(idGioc);
		}
		if("N".equals(value)){
			suono(idGioc);
		}
		return;

	}*/
	
	//endController: viene invocata quando un giocatore ha terminato il proprio turno: quello che fa è chiamare il prossimo giocatore che deve svolgere il proprio turno
	
	/**Controller that let the start of the first turn of the game happen and controls if the match is over or if another turn has to be played (calling eseguiPartita() from class Gioco)
	 * @param idGioc id of the player
	 * @param value string containing "End"
	 */
	public void end() {//TODO da dove rifaccio partire il turno? In teoria da classicGameEngine method, ma bisogna modificarlo e mettere solo in certi casi distributeHands() all'inizio
		//Poi bisogna usare GameState o GameType per fare ripartire il tutto da classicGameEngine() o dal mainEngine o da altri Engine.
		game.mainEngine();
		//in eftaios noi avevamo un controllo sulla fine della partita che qua non dovrebbe esserci
	}
		
	/**Getter for the actual player turn number
	 * @param idGioc id of the player
	 */
	public int playerTurn() {
		return game.getPlayerDoingTurnNumber();
	}


	public void setMadWomanRank(String value) {
		game.getClassicGameEngine().verifyValidMadWomanRank(value);
		
	}


	public void playCard(String value) {
		int cardNumberWroteByClient = Integer.parseInt(value);
		int cardNumber = cardNumberWroteByClient - 1;// client scrive 1 per la prima carta, è da tradurre in 0 nel mondo informatico.
		game.getClassicGameEngine().playCard(cardNumber);
		
	}
	
	
	//attaccoController
	/**Controller of the attack. It also set the next phase of the turn (StatiGioco)
	 * @param idGioc id of the player
	 */
/*	private void attacco(int idGioc) {
		game.attacco(idGioc);
		StatiGioco prossimoStato=game.nextPhase(StatiGioco.ATTACKSTATE,StatiGioco.DONTKNOW, idGioc);
		game.getPlayers().getGiocatore(idGioc).setStatoAttuale(prossimoStato);
	}*/
	
	
	//oggettoController
	 /**Controller of the part of the turn where a human player has to choose to visualize his object cards or not
	 * @param idGioc id of the player
	 * @param value string containing "Y" or "N"
	 */
/*	public void mostraOggetto(int idGioc, String value){
		 if("Y".equals(value))
			 game.getPlayers().getGiocatore(idGioc).mostraEquipaggiamento();
		 if("N".equals(value)){
			 StatiGioco prossimoStato=game.nextPhase(StatiGioco.OBJECTSTATE,StatiGioco.DONTKNOW, idGioc);
			 game.getPlayers().getGiocatore(idGioc).setStatoAttuale(prossimoStato);
			 if(prossimoStato.equals(StatiGioco.DRAWSTATE))
				suono(idGioc); 
			}
			return;

	 }*/
	
}
	
	
	