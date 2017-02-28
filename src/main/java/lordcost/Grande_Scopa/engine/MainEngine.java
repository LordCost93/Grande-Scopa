package lordcost.Grande_Scopa.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import lordcost.Grande_Scopa.board.MainBoard;
import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.cards.MainDeck;
import lordcost.Grande_Scopa.cards.SupportDeck;
import lordcost.Grande_Scopa.player.Player;

public class MainEngine extends Observable {

	//list<observers> serve solo se ci fosse un connection Handler
	private int playerDoingTurnNumber;
	private int numberOfPlayers;
	private MainDeck mainDeck;
	private SupportDeck supportDeck;
	private MainBoard mainBoard;
	private ArrayList<Player> players;
	private GameState gamestate;
	private SputacchioGameEngine sputacchioGameEngine;
	private CucuGameEngine cucuGameEngine;
	private ClassicGameEngine classicGameEngine;
	private PeppaGameEngine peppaGameEngine;
	private WattenGameEngine wattenGameEngine;
	private AssoloGameEngine assoloGameEngine;
	private LieGameEngine lieGameEngine;
	private ScalaGameEngine scalaGameEngine;
	private LastGameEngine lastGameEngine;
	private EngineUtility engineUtility;
	private Filter filter;
	private static final int FIRSTPLAYER=0;
	private static final int INITIALHANDNUMBER=0;
	
	public MainEngine(int numberOfPlayers, Filter filter) {
		this.mainDeck = new MainDeck();
		this.supportDeck = new SupportDeck();
		this.mainBoard = new MainBoard();
		this.gamestate = new GameState();
		this.numberOfPlayers=0;//inizializzato a 0!
		this.playerDoingTurnNumber=FIRSTPLAYER;
		this.players = new ArrayList<Player>();
		this.filter = filter;
		for(int k=0; k<numberOfPlayers; k++)// players ids partono da 0 come playerDoingTurnNumber
			this.players.add(new Player(k));//Guardare Remote Callable Client che c'è setId() del client.
	}
	
	public void givePersonalJolly(){
		MainCard personalJolly;
		for(Player pl : this.players){
			personalJolly = this.mainDeck.pickSingleCard();
			pl.setPersonalJolly(personalJolly);
		}
	}
	
	public void gameEngineInitializer() {//called only by serverRoom one time
		
		this.sputacchioGameEngine = new SputacchioGameEngine(supportDeck, mainDeck, mainBoard, players, gamestate, playerDoingTurnNumber);// da implementare alla fine
		this.classicGameEngine = new ClassicGameEngine(supportDeck, mainDeck, mainBoard, players, gamestate, playerDoingTurnNumber, engineUtility, filter);
		
		this.gamestate.setCurrentGameType(GameType.CLASSICGAME);//si dovrebbe iniziare dal cucù ma facciamo dopo
		this.gamestate.setCurrentHandNumber(INITIALHANDNUMBER);
		this.givePersonalJolly();
		
		this.classicGameEngine.distributeHands();//queste due si fanno all'inizio di ogni mano, non sono da fare solo la prima volta e basta
		this.classicGameEngine.substituteJolly();
		
		mainEngine();
		//TODO ci vuole un metodo che, a parte la prima chiamata di Server Room, chiama il sotto-engine giusto in base a game type. Se classic game engine fa una return per segnalare
		//che la mano è finita perchè nessuno ha carte in mano, game engine deve ridare le 6 carte o settare l'ultima mano, o la mano della scala obbligatoria, e cambiare chi inizia a cucù.
		//Poi penso che debba chiamare Filter per mostrare a entrambi cosa accade, stampare le nuove carte e dichiarare l'inizio del cucù.
	}

	public void mainEngine() {
				
		if(this.gamestate.getCurrentGameType()== GameType.CLASSICGAME /*&& gamestate.isCucuFinished()== true*/)//cucuFinished è da rimettere a false quando non si hanno più carte in mano, alla fine di ogni mano.
			classicGameEngine.classicGameEngine();
		else classicGameEngine.classicGameEngine();//TODO ci sono tantissimi else basati sul cucù, mani da chiamare, peppa già chiamata.
	}

	/**Getter
	 * @return the id of the player who is doing the turn at the moment
	 */
	public int getPlayerDoingTurnNumber() {
		return playerDoingTurnNumber;
	}
	
	public GameState getGamestate() {
		return gamestate;
	}

	/**Getter that when is called increments the number of players in the game
	 * @return the number of players in a match
	 */
	public int incrementPlayersNumber(){//setter/incrementer di numberofplayers chiamato da ServerRoom: qua ho cambiato! è qui che si setta il client id, e il primo della stanza ha id=1 scritta come era prima!
		int idToSend=numberOfPlayers;
		numberOfPlayers++;
		return idToSend;
	}
		
	public MainDeck getMainDeck() {
		return mainDeck;
	}

	public SupportDeck getSupportDeck() {
		return supportDeck;
	}

	public MainBoard getMainBoard() {
		return mainBoard;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public SputacchioGameEngine getSputacchioGameEngine() {
		return sputacchioGameEngine;
	}

	public ClassicGameEngine getClassicGameEngine() {
		return classicGameEngine;
	}

	public PeppaGameEngine getPeppaGameEngine() {
		return peppaGameEngine;
	}

	public WattenGameEngine getWattenGameEngine() {
		return wattenGameEngine;
	}

	public AssoloGameEngine getAssoloGameEngine() {
		return assoloGameEngine;
	}

	public LieGameEngine getLieGameEngine() {
		return lieGameEngine;
	}

	public ScalaGameEngine getScalaGameEngine() {
		return scalaGameEngine;
	}

	public LastGameEngine getLastGameEngine() {
		return lastGameEngine;
	}
}
