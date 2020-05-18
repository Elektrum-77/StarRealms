package fr.dut.info.SpecialAction;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.Deck;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class SpeActEmbassyYacht implements SpecialAction{
	
	/* +2 carte si deux bases en jeu au tour du joueur*/
	
	public void use(GameBoard gameBoard, Player player) {
		ArrayList<Card> inPlay = player.getInPlayCards();
		int countBase = 0;
		for(int i = 0; i < inPlay.getSize(); i++){
			
			if(inPlay.get(i).cardType()=="Base") {
				System.out.println("++++1");
				countBase+=1;
			}
			if(countBase>=2) {
				player.drawCards(2);
				break;
			}
		}
	}

	
	
}
