package fr.dut.info.SpecialAction;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class SpeActBattlePod implements SpecialAction{
	
	/* Le joueur peut scrap des cartes du la ligne d'achat - ses effets ne sont alors pas appliqués  */

	@Override
	public void use(GameBoard gameBoard, Player player) {
		gameBoard.updateScrapTradeRowPool(1);
	}

}
