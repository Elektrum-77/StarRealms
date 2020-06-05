package fr.dut.info.Game;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.dut.info.Abilitys.Ability;
import fr.dut.info.Abilitys.AndConditionalAbility;
import fr.dut.info.Abilitys.AuthorityPoint;
import fr.dut.info.Abilitys.DrawCard;
import fr.dut.info.Abilitys.FightingPoint;
import fr.dut.info.Abilitys.TradePoint;
import fr.dut.info.Card.Base;
import fr.dut.info.Card.Card;
import fr.dut.info.Card.Ship;
import fr.dut.info.starRealms.SimpleGameController;

public class CardLoader {

	static final int explorerCount = 10;
	static final int ScoutCountPerPlayer = 8;
	static final int ViperCountPerPlayer = 2;
	
	static Deck loadExplorerDeck() {
		Deck list = new Deck();
		ImageIcon image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Explorer.jpg").getImage());
		Ship explorers = new Ship(image, "Explorer", "Unaligned", 2, new TradePoint(2), null, new FightingPoint(2));
		for (int i = 0; i < explorerCount; i++) {
			list.addCard(explorers.copy());
		}
		return list;
	}
	
	static Deck loadStartingPlayerDeck() {
		Deck list = new Deck();
		ImageIcon image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Scout.jpg").getImage());
		Ship scouts = new Ship(image, "Scout", "Unaligned", 2, new TradePoint(1), null, null);
		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Viper.jpg").getImage());
		Ship viper = new Ship(image, "Viper", "Unaligned", 2, new FightingPoint(1), null, null);
		for (int i = 0; i < ScoutCountPerPlayer; i++) {
			list.addCard(scouts.copy());
		}
		for (int i = 0; i < ViperCountPerPlayer; i++) {
			list.addCard(viper.copy());
		}
		list.shuffle();
		return list;
	}
	
	static Deck loadTradeDeck() {
		Deck list = new Deck();

		Ship ship=null;
		Base base=null;
		ImageIcon image=null;
		
		// ----------------Blob----------------
		
		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Battle-blob.jpg").getImage());
		ship = new Ship(image, "Battle-Blob", "Blob", 6, new FightingPoint(8), new DrawCard(1), new FightingPoint(4));
		for (int i = 0; i < 1; i++) {
			list.addCard(ship.copy());
		}
		
		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Blob-Wheel.jpg").getImage());
		base = new Base(image, "Blob Wheel", "Blob", 3, 5, false, new FightingPoint(1), null, new TradePoint(3));
		for (int i = 0; i < 3; i++) {
			list.addCard(base.copy());
		}

		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Ram.jpg").getImage());
		ship = new Ship(image, "Ram", "Blob", 3, new FightingPoint(5), new FightingPoint(3), new TradePoint(4));
		for (int i = 0; i < 2; i++) {
			list.addCard(ship.copy());
		}

		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Trade-Pod.jpg").getImage());
		ship = new Ship(image, "Trade Pod", "Blob", 2, new TradePoint(3), new FightingPoint(2), null);
		for (int i = 0; i < 3; i++) {
			list.addCard(ship.copy());
		}
		
		// ----------------STAR EMPIRE----------------

		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Corvette.jpg").getImage());
		ship = new Ship(image, "Corvette", "Star Empire", 2, new AndConditionalAbility(new FightingPoint(1), new DrawCard(1)), new FightingPoint(2), null);
		for (int i = 0; i < 2; i++) {
			list.addCard(ship.copy());
		}
		
		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Dreadnaught.jpg").getImage());
		ship = new Ship(image, "Dreadnaught", "Star Empire", 7, new AndConditionalAbility(new FightingPoint(7), new DrawCard(1)), null, new FightingPoint(5));
		for (int i = 0; i < 1; i++) {
			list.addCard(ship.copy());
		}

		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Space-Station.jpg").getImage());
		base  = new Base(image, "Space Station", "Star Empire", 4, 4, true, new FightingPoint(2), new FightingPoint(2), new TradePoint(4));
		for (int i = 0; i < 2; i++) {
			list.addCard(base.copy());
		}
		
		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/War-World.jpg").getImage());
		base = new Base(image, "War World", "Star Empire", 5, 4, true, new FightingPoint(3), new FightingPoint(4), null);
		for (int i = 0; i < 1; i++) {
			list.addCard(base.copy());
		}
		
		// ----------------Trade Federation---------------- //

		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Cutter.jpg").getImage());
		ship = new Ship(image, "Cutter", "Trade Federation", 2, new AndConditionalAbility(new AuthorityPoint(4), new TradePoint(2)), new FightingPoint(4), null);
		for (int i = 0; i < 3; i++) {
			list.addCard(ship.copy());
		}
		
		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Embassy-Yacht.jpg").getImage());
		ship = new Ship(image, "Embassy Yacht", "Trade Federation", 3, new AndConditionalAbility(new AuthorityPoint(3), new TradePoint(2)), null, null);
		for (int i = 0; i < 2; i++) {
			list.addCard(ship.copy());
		}

		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Federation-Shuttle.jpg").getImage());
		ship = new Ship(image, "Federation Shuttle", "Trade Federation", 1, new TradePoint(2), new AuthorityPoint(4), null);
		for (int i = 0; i < 3; i++) {
			list.addCard(ship.copy());
		}
		
		image = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Flagship.jpg").getImage());
		ship = new Ship(image, "Flagship", "Trade Federation", 6, new AndConditionalAbility(new FightingPoint(5), new DrawCard(1)), new AuthorityPoint(5), null);
		for (int i = 0; i < 1; i++) {
			list.addCard(ship.copy());
		}
		
		list.shuffle();
		return list;
	}
}
