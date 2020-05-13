package fr.dut.info.Abilitys;

import java.util.ArrayList;
import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

interface AbillityTargetInterface extends Ability {
	public ArrayList<Card> getTarget(GameBoard gameBoard);
}
