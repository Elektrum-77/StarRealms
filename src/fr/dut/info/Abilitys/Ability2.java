package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;
import fr.dut.info.SpecialAction.SpecialAction;

public class Ability2 {
	
	private final int fightingP;
	private final int tradingP;
	private final int authorityP;
	private final int nbDraw;
	private final boolean[] disable;
	private final ArrayList<SpecialAction> specialActions;
	private final boolean or;
	
	//private int multipleChoice = 0;
	
	public Ability2(int fightingP, int tradingP, int AuthorityP, int nbDraw, ArrayList<SpecialAction> specialActions, boolean or) {
		this.fightingP = fightingP;
		this.tradingP = tradingP;
		this.authorityP = AuthorityP;
		this.nbDraw = nbDraw;
		disable = new boolean[5];  // Toutes les capacités ne sont pas désactivées
		this.specialActions = specialActions;
		this.or = or;
		initDisable();
		// PAS A UTILISE EN PHASE 1
		//if(fightingP != 0) {multipleChoice++;}
		//if(tradingP != 0) {multipleChoice++;}
		//if(AuthorityP != 0) {multipleChoice++;}
		//if(nbDraw != 0) {multipleChoice++;}
	}
	
	public Ability2(int fightingP, int tradingP, int AuthorityP, int nbDraw, boolean or) {
		this(fightingP, tradingP, AuthorityP, nbDraw, new ArrayList<SpecialAction>(), or);
	}
	
	public Ability2() {
		this(0, 0, 0, 0, new ArrayList<SpecialAction>(), false);
	}
	
	public boolean getOr() {
		return or;
	}
	
	private void initDisable() {
		// toute les capacité à zéro dès la construction sont considérées comme désactivé
		boolean b = false;
		if(authorityP != 0) {disable[0]=b; } else {disable[0]=!b; }
		if(tradingP != 0) {disable[1]=b; } else {disable[1]=!b; }
		if(fightingP != 0) {disable[2]=b; } else {disable[2]=!b; }
		if(nbDraw != 0) {disable[3]=b; } else {disable[3]=!b; }
		if(specialActions.size() !=0) {disable[4]=b; } else {disable[4]=!b; }
	}
	
	
	public void addSpecialAction(SpecialAction speAct) {
		specialActions.add(speAct);
		
	}
	
	
	// Renvoie false si on ne peut rien faire avec l'ability
	public boolean exists() {
		if (fightingP == 0 && tradingP == 0 && authorityP == 0 && nbDraw == 0 && specialActions.size() == 0) {
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
	
	// Revoie la liste des donnée de l'ability
	public ArrayList<Integer> getData(){
		 ArrayList<Integer> list = new ArrayList<>();
		 list.add(fightingP);
		 list.add(tradingP);
		 list.add(authorityP);
		 list.add(nbDraw);
		return list;
	}
	
	public void useOR1(GameBoard gameBoard, Player player) {
		if(authorityP!=0) {
			useAuthority(gameBoard);
		}else if(tradingP!=0) {
			useTrade(gameBoard);
		}else if(fightingP!=0) {
			useCombat(gameBoard);
		}else if(nbDraw!=0) {
			useDraw(gameBoard);
		}else if(specialActions.size()!=0) {
			System.out.println("auth");
			useAllSpecialActions(gameBoard, player);
		}
		disable();	
		
	}
	
	public void useOR2(GameBoard gameBoard, Player player) {
		
		if(specialActions.size()!=0) {
			useAllSpecialActions(gameBoard, player);
		}else if(nbDraw!=0) {
			useDraw(gameBoard);
		}else if(fightingP!=0) {
			useCombat(gameBoard);
		}else if(tradingP!=0) {
			useTrade(gameBoard);
		}else if(authorityP!=0) {
			useAuthority(gameBoard);
		}
		disable();	
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
	
	public void useAllSpecialActions(GameBoard gameBoard, Player player) {
		for(SpecialAction speAct: specialActions) {
			speAct.use(gameBoard, player);
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
	
	public Ability2 copy(Ability2 ab) {
		return new Ability2(ab.getCombat(),ab.getTrade(),ab.getAuthority(),ab.getDraw(), ab.getSpecialActions(), ab.getOr());
	}

	
	
}
