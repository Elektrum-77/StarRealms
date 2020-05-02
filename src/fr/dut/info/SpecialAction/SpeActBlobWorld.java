package fr.dut.info.SpecialAction;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class SpeActBlobWorld implements SpecialAction{

	@Override
	public void use(GameBoard gameBoard, Player player) {
		int i = 0;
		for(String c: gameBoard.getPlayedCardTurn()) {
			System.out.println(c);
			if(c.equals("Blob")) {
				i++;
			}
		}
		player.drawCards(i);
	}

}
