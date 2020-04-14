package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;
import fr.dut.info.SpecialAction.SpecialAction;

abstract class AbstractAbility implements Ability{
	
	private final int fightingP;
	private final int tradingP;
	private final int authorityP;
	private final int nbDraw;
	private final boolean[] disable;
	private final ArrayList<SpecialAction> specialActions;
	
	
	// plus une liste de capacités spéciales ?
	//private int multipleChoice = 0;
	
	public AbstractAbility(int fightingP, int tradingP, int AuthorityP, int nbDraw, ArrayList<SpecialAction> specialActions) {
		this.fightingP = fightingP;
		this.tradingP = tradingP;
		this.authorityP = AuthorityP;
		this.nbDraw = nbDraw;
		disable = new boolean[4];  // Toutes les capacités ne sont pas désactivées
		initDisable();
		this.specialActions = specialActions;
		// PAS A UTILISE EN PHASE 1
		//if(fightingP != 0) {multipleChoice++;}
		//if(tradingP != 0) {multipleChoice++;}
		//if(AuthorityP != 0) {multipleChoice++;}
		//if(nbDraw != 0) {multipleChoice++;}
	}
	
	private void initDisable() {
		// toute les capacité à zéro dès la construction sont considérées comme désactivé
		boolean b = false;
		if(fightingP != 0) {disable[0]=b; } else {disable[0]=!b; }
		if(tradingP != 0) {disable[1]=b; } else {disable[1]=!b; }
		if(authorityP != 0) {disable[2]=b; } else {disable[2]=!b; }
		if(nbDraw != 0) {disable[3]=b; } else {disable[3]=!b; }
	}
	
	
	
	abstract String typePhase();
	
	
	public void addSpecialAction(SpecialAction speAct) {
		
		specialActions.add(speAct);
		
	}
	
	public void useAllSpecialActions(GameBoard gameBoard, Player player) {
		for(SpecialAction speAct: specialActions) {
			speAct.use(gameBoard, player);
		}
	}
	
	// Renvoie false si on ne peut rien faire avec l'ability
	public boolean exists() {
		if (fightingP == 0 && tradingP == 0 && authorityP == 0 && nbDraw == 0) {
			return false;
		}
		// On regarde si toutes les capacités  sont désactivées
		boolean b = true;
		for(int i = 0; i < disable.length; i++) {
			if(disable[i]==false) {
				b=false;
			}
		}
		return !b;
	}
	
	// Renvoie true s'il plusieurs action sont possibles
	/*
	public boolean isMultipleChoice() {
		return multipleChoice > 1;
	}
	*/
	
	// Revoie la liste des donnée de l'ability
	public ArrayList<Integer> getData(){
		 ArrayList<Integer> list = new ArrayList<>();
		 list.add(fightingP);
		 list.add(tradingP);
		 list.add(authorityP);
		 list.add(nbDraw);
		return list;
	}
	
	// Action selon les points d'action
	public void use(GameBoard gameBoard) {
		if (fightingP != 0) {
			 useCombat(gameBoard);
		}
		if (tradingP != 0) {
			useTrade(gameBoard);
		}
		if(authorityP != 0) {
			useAuthority(gameBoard);
		}
		if(nbDraw != 0) {
			useDraw(gameBoard);
		}
		for(int i =0; i < disable.length; i++) {
			disable[i]=true;
		}
	}
	
	public void useCombat(GameBoard gameBoard) {
		gameBoard.updateCombatPool(fightingP);
	}
	
	public void useTrade(GameBoard gameBoard) {
		gameBoard.updateTradePool(tradingP);
	}
	
	public void useAuthority(GameBoard gameBoard) {
		gameBoard.updateAuthority(authorityP);
	}
	
	public void useDraw(GameBoard gameBoard) {
		gameBoard.playingPlayerDrawCards(nbDraw);
	}
	
	public void setEnable() {
		for(int i =0; i < disable.length; i++) {
			disable[i]=false;
		}
	}
	
	public ArrayList<SpecialAction> getSpecialActions() {
		return specialActions;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (exists()) {
			
			str.append("______ ").append(typePhase()).append(" ______\n");
			
			if(fightingP>0) {
				str.append("add ").append(fightingP).append(" Combat\n");
			}
			if(tradingP>0) {
				str.append("add ").append(tradingP).append(" Trade\n");
			}
			if(authorityP>0) {
				str.append("add ").append(authorityP).append(" Authority\n");
			}
			if(nbDraw>0) {
				str.append("Draw ").append(authorityP).append(" cards\n");
			}
		}
		return str.toString();
	}
	
	public int getCombat() {
		return fightingP;
	}
	public int getTrade() {
		return tradingP;
	}
	public int getAuthority() {
		return authorityP;
	}
	public int getDraw() {
		return nbDraw;
	}
	
	public Primary copyPrimary(Ability ab) {
		return new Primary(ab.getCombat(),ab.getTrade(),ab.getAuthority(),ab.getDraw(), ab.getSpecialActions());
	}

	public Ally copyAlly(Ability ab) {
		return new Ally(ab.getCombat(),ab.getTrade(),ab.getAuthority(),ab.getDraw(), ab.getSpecialActions());
	}
	
	public Scrap copyScrap(Ability ab) {
		return new Scrap(ab.getCombat(),ab.getTrade(),ab.getAuthority(),ab.getDraw(), ab.getSpecialActions());
	}
	
}
