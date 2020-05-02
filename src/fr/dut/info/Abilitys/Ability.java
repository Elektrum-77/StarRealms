package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;
import fr.dut.info.SpecialAction.SpecialAction;

public class Ability {
	
	private final int fightingP;
	private final int tradingP;
	private final int authorityP;
	private final int nbDraw;
	private final boolean[] disable;
	private final ArrayList<SpecialAction> specialActions;
	
	
	// plus une liste de capacit�s sp�ciales ?
	//private int multipleChoice = 0;
	
	public Ability(int fightingP, int tradingP, int AuthorityP, int nbDraw, ArrayList<SpecialAction> specialActions) {
		this.fightingP = fightingP;
		this.tradingP = tradingP;
		this.authorityP = AuthorityP;
		this.nbDraw = nbDraw;
		disable = new boolean[4];  // Toutes les capacit�s ne sont pas d�sactiv�es
		initDisable();
		this.specialActions = specialActions;
		// PAS A UTILISE EN PHASE 1
		//if(fightingP != 0) {multipleChoice++;}
		//if(tradingP != 0) {multipleChoice++;}
		//if(AuthorityP != 0) {multipleChoice++;}
		//if(nbDraw != 0) {multipleChoice++;}
	}
	
	public Ability(int fightingP, int tradingP, int AuthorityP, int nbDraw) {
		this(fightingP, tradingP, AuthorityP, nbDraw, new ArrayList<SpecialAction>());
	}
	
	public Ability() {
		this(0, 0, 0, 0, new ArrayList<SpecialAction>());
	}
	
	
	private void initDisable() {
		// toute les capacit� � z�ro d�s la construction sont consid�r�es comme d�sactiv�
		boolean b = false;
		if(fightingP != 0) {disable[0]=b; } else {disable[0]=!b; }
		if(tradingP != 0) {disable[1]=b; } else {disable[1]=!b; }
		if(authorityP != 0) {disable[2]=b; } else {disable[2]=!b; }
		if(nbDraw != 0) {disable[3]=b; } else {disable[3]=!b; }
	}
	
	
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
		// On regarde si toutes les capacit�s  sont d�sactiv�es
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
	
	// Revoie la liste des donn�e de l'ability
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
	
	public void disable() {
		for(int i =0; i < disable.length; i++) {
			disable[i]=true;
		}
		
	}
	
	public ArrayList<SpecialAction> getSpecialActions() {
		return specialActions;
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
	
	public Ability copy(Ability ab) {
		return new Ability(ab.getCombat(),ab.getTrade(),ab.getAuthority(),ab.getDraw(), ab.getSpecialActions());
	}

	
	
}
