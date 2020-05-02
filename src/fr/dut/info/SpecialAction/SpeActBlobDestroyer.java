package fr.dut.info.SpecialAction;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class SpeActBlobDestroyer implements SpecialAction{

	@Override
	public void use(GameBoard gameBoard, Player player) {
		gameBoard.updateFreeDestroyBasePool(1);
		gameBoard.updateScrapTradeRowPool(1);
	}

}
