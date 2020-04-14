package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;
import fr.dut.info.SpecialAction.SpecialAction;

public interface Ability {
	
	boolean exists();
	//boolean isMultipleChoice();
	ArrayList<Integer> getData();
	void use(GameBoard gameBoard);
	void useCombat(GameBoard gameBoard);
	void useTrade(GameBoard gameBoard);
	void useAuthority(GameBoard gameBoard);
	void useDraw(GameBoard gameBoard);
	void useAllSpecialActions(GameBoard gameBoard, Player player);
	void addSpecialAction(SpecialAction speAct);
	ArrayList<SpecialAction> getSpecialActions();
	
	int getCombat();
	int getTrade();
	int getAuthority();
	int getDraw();

	Primary copyPrimary(Ability ab);
	Ally copyAlly(Ability ab);
	Scrap copyScrap(Ability ab);
	
	void setEnable();
}
