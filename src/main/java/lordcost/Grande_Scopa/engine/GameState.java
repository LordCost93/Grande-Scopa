package lordcost.Grande_Scopa.engine;

public class GameState {
	
	private GameType currentGameType;
	private int specialHandYetToCall;
	private boolean assoloPlayed;
	private boolean wattenPlayed;
	private boolean lieGamePlayed;
	private boolean scalaCalled;
	private int currentHandNumber;
	private boolean obligatoryHandCalled;
	private boolean peppaCalled;
	private boolean cucuFinished;
	
	public GameType getCurrentGameType() {
		return currentGameType;
	}
	public void setCurrentGameType(GameType currentGameType) {
		this.currentGameType = currentGameType;
	}
	public int getSpecialHandYetToCall() {
		return specialHandYetToCall;
	}
	public void setSpecialHandYetToCall(int specialHandYetToCall) {
		this.specialHandYetToCall = specialHandYetToCall;
	}
	public boolean isAssoloPlayed() {
		return assoloPlayed;
	}
	public void setAssoloPlayed(boolean assoloPlayed) {
		this.assoloPlayed = assoloPlayed;
	}
	public boolean isWattenPlayed() {
		return wattenPlayed;
	}
	public void setWattenPlayed(boolean wattenPlayed) {
		this.wattenPlayed = wattenPlayed;
	}
	public boolean isLieClassicGamePlayed() {
		return lieGamePlayed;
	}
	public void setLieClassicGamePlayed(boolean lieClassicGamePlayed) {
		this.lieGamePlayed = lieClassicGamePlayed;
	}
	public boolean isScalaCalled() {
		return scalaCalled;
	}
	public void setScalaCalled(boolean scalaCalled) {
		this.scalaCalled = scalaCalled;
	}
	public boolean isPeppaCalled() {
		return peppaCalled;
	}
	public void setPeppaCalled(boolean peppaCalled) {
		this.peppaCalled = peppaCalled;
	}
	public int getCurrentHandNumber() {
		return currentHandNumber;
	}
	public void setCurrentHandNumber(int currentHandNumber) {
		this.currentHandNumber = currentHandNumber;
	}
	public boolean isObligatoryHandCalled() {
		return obligatoryHandCalled;
	}
	public void setObligatoryHandCalled(boolean obligatoryHandCalled) {
		this.obligatoryHandCalled = obligatoryHandCalled;
	}	
	public boolean isCucuFinished() {
		return cucuFinished;
	}
	public void setCucuFinished(boolean cucuFinished) {
		this.cucuFinished = cucuFinished;
	}
	public GameState() {
		this.currentGameType = GameType.NOTHING;
		this.specialHandYetToCall = -1;
		this.obligatoryHandCalled = false;
		this.assoloPlayed = false;
		this.wattenPlayed = false;
		this.lieGamePlayed = false;
		this.scalaCalled = false;
		this.peppaCalled = false;
		this.currentHandNumber = 0;
		this.cucuFinished = false;
	}
	

}
