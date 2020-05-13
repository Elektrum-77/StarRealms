package fr.dut.info.Abilitys;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class AuthorityPoint extends AbstractAbilityPoint {
	
	public AuthorityPoint(int p) { super(p); }

	@Override
	public void use(GameBoard gameBoard) {
		gameBoard.updateAuthority(point);
	}
	

	@Override
	public Ability copy() {
		return new TradePoint(point);
	}

}
