package fr.dut.info.SpecialAction;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class SpeActBlobCarrier implements SpecialAction{

	/*  récup un ship gratuitement et le place au dessus du deck du joueur  */
	
	@Override
	public void use(GameBoard gameBoard, Player player) {
		gameBoard.updateFreeShipPool(1);
		
	}

}
