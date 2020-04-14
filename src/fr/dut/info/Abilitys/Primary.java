package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.SpecialAction.SpecialAction;

public class Primary extends AbstractAbility{

	public Primary(int fightingP, int tradingP, int AuthorityP, int nbDraw) {
		super(fightingP, tradingP, AuthorityP, nbDraw, new ArrayList<SpecialAction>());	
	}
	
	public Primary(int fightingP, int tradingP, int AuthorityP, int nbDraw, ArrayList<SpecialAction> speAct) {
		super(fightingP, tradingP, AuthorityP, nbDraw, speAct);
	}
	
	@Override
	String typePhase() {
		return "Primary";
	}
}
