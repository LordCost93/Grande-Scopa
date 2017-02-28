package lordcost.Grande_Scopa.client;

import lordcost.Grande_Scopa.engine.StatiGioco;




/**
 * @author Andrea
 * This class contains the methods that transforms the format of the input of the client, captured by the method "readline" in the class Client, in order to let
 * those strings to be understood by the class Command Handler on the server side
 */
public class CodificaClient {
	
	private static final String PLAYERSTRING= "player=";
	private static final String ACTIONSTRING= "&action=";
	private static final String VALUESTRING= "&value=";
	/**
	 * Private constructor, never called
	 */
	private CodificaClient() {
		
	}
	//in base al tipo di comando codifico l'informazione in maniera diversa
	/** Method called by the class Client to form a string readable and understandable by the class Command Handler on the server, for every type of command in every phase of the turn of a player
	 * @param string the string written by the user and captured in the class Client, who calls this method
	 * @param numRoom number of server room that identifies the match that the client is playing, among all other matches
	 * @param idGioc identifier of the player (number from 1 to eight at maximum)
	 * @param statoAttuale current phase of the turn played by the client in exam
	 * @return a string ready to be received by the server through the rmi interface
	 */
	public static String codifica(String string,int numRoom,int idGioc,StatiGioco statoAttuale){
		String risposta="";
		String id=String.valueOf(idGioc);
		String roomNum=String.valueOf(numRoom);
		if(StatiGioco.STARTCLASSICGAME.equals(statoAttuale))
			risposta=startClassicGame(string,id);
		else if(StatiGioco.SETMADWOMANRANK.equals(statoAttuale))
			risposta=setMadWomanRank(string,id);
		else if(StatiGioco.ASKREPLY.equals(statoAttuale))
			risposta=askReply(string,id);
		/*
		else if(StatiGioco.ASKSTATE.equals(statoAttuale))
			risposta=ask(string, id);
		else if(StatiGioco.ENDSTATE.equals(statoAttuale) && string=="End")
			risposta=end(string, id);
		else if(StatiGioco.DISCARDSTATE.equals(statoAttuale) && string=="End")
			risposta=discard(string, id);*/
		else if(StatiGioco.PAUSESTATE.equals(statoAttuale))
			risposta="Aspetta che l'avversario segni i suoi punti";
		else if(StatiGioco.ENDSTATE.equals(statoAttuale) && string!="End")
			risposta="Non è il tuo turno,aspetta";
		String server= "serverRoom=".concat(roomNum);
		risposta=server.concat("&"+risposta);
		return risposta;
	}
	
	private static String startClassicGame(String numCard, String id) {
		String string= new String();
		string= PLAYERSTRING.concat(id); 
		string= string.concat(ACTIONSTRING).concat("playCard");
		string= string.concat(VALUESTRING).concat(numCard);
		return string;
	}
	
	private static String setMadWomanRank(String rank, String id){
		String string= new String();
		string= PLAYERSTRING.concat(id); 
		string= string.concat(ACTIONSTRING).concat("setMadWomanRank");
		string= string.concat(VALUESTRING).concat(rank);
		return string;
	}
	
	private static String askReply(String yn, String id){
		String string= new String();
		string= PLAYERSTRING.concat(id);
		string= string.concat(ACTIONSTRING).concat("askReply");
		string= string.concat(VALUESTRING).concat(yn);
		return string;
	}
	
	
	/** Private method called by "codifica" to handle the string of the client, containing "End"
	 * @param oggetto string containing "End"
	 * @param id id of the player
	 * @return a string of the format "player=..idOfThePlayer..&action=ask&value=.."End".., where the terms ..xxx.. are variables
	 */
	private static String end(String oggetto, String id){
		String string= new String();
		string= PLAYERSTRING.concat(id);
		string= string.concat(ACTIONSTRING).concat("End");
		string= string.concat(VALUESTRING).concat(oggetto);
		return string;
	}
	
	

	
	
	
	
}
