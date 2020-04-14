package fr.dut.info.starRealms;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import fr.dut.info.Abilitys.Ally;
import fr.dut.info.Abilitys.Primary;
import fr.dut.info.Abilitys.Scrap;
import fr.dut.info.Card.Base;
import fr.dut.info.Card.Ship;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;
import fr.dut.info.SpecialAction.*;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class SimpleGameController {

	static void simpleGame(ApplicationContext context) {
		while(true) { // boucle du programme
			// Background Aléatoire
			Image bg1 = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/background/bg.jpg")).getImage();
			Image bg2 = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/background/bg2.jpg")).getImage();
			Image bg3 = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/background/bg4.jpg")).getImage();
			Image bg4 = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/background/bg6.jpg")).getImage();
			Image bg5 = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/background/bg7.jpg")).getImage();
			Image bg6 = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/background/bg8.jpg")).getImage();
			Image bg7 = new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/background/bg9.jpg")).getImage();
			
			ArrayList<Image> bgList = new ArrayList<>();
			bgList.add(bg1);
			bgList.add(bg2);
			bgList.add(bg3);
			bgList.add(bg4);
			bgList.add(bg5);
			bgList.add(bg6);
			bgList.add(bg7);
			Random random = new Random();
			int i = random.nextInt(bgList.size());
			// Nouveau background defini aléatoirement
			Image background = bgList.get(i);
			// get the size of the screen
			ScreenInfo screenInfo = context.getScreenInfo();
			int width = (int) screenInfo.getWidth();
			int height = (int) screenInfo.getHeight();
			// Création du plateau
			GameBoard gameBoard = new GameBoard();
			// On ajoute les joueurs et les cartes
			gameBoard = concreteCards(gameBoard);
			// Mélanger tous chaque deck des joueurs
			gameBoard.shuffleAllPlayersDeck();
			// Mélanger le deck du jeu
			gameBoard.getTradeDeck().shuffle();
	
			SimpleGameView view = new SimpleGameView(0, 10, width, height, background);
			// On affiche la vue
			view.draw(context, gameBoard);
			// Nouveau point pour la location de la souris
			Point2D.Float location;
			
			// On initialise le premier joueur en le désignant aléatoirement
			int posPlayerInQueue = gameBoard.randomPlayer();
			gameBoard.setThePlayer(posPlayerInQueue);
			Player firstPlayer= gameBoard.getPlayingPlayer();
			
			// Tout le monde pioche 5 carte sauf le joueur qui commence pioche 3 cartes (voir règles)
			gameBoard.everybodyDrawCardsExcept(firstPlayer,5);
			firstPlayer.drawCards(3);
			gameBoard.updateTradeRow();
			
			
			view.draw(context, gameBoard);
			while (true) { // boucle de la partie
				Event event = context.pollOrWaitEvent(10); // modifier pour avoir un affichage fluide
				if (event == null) { // no event
					continue;
				}
				
				Action action = event.getAction();
				if (action == Action.KEY_PRESSED) {
					
					if(event.getKey().toString() == "SPACE") {
						posPlayerInQueue+=1; // on passe le tour donc pos du joueur + 1
						// même si la valeur envoyée est grande elle est traitée avec un modulo
						gameBoard.setThePlayer(posPlayerInQueue); 
						view.setCoordo(0,0);
						
					}else if(event.getKey().toString() == "Q") {
						context.exit(0);
						return;
					}else if(event.getKey().toString() == "R") { // restart game
						break;
					}else if(event.getKey().toString() == "UP") { // cheat pool points
						gameBoard.updateCombatPool(1);
						gameBoard.updateTradePool(1);
					}
				}
					
				if(action == Action.POINTER_DOWN) {
					location = event.getLocation();
					view.setCoordo((int)location.x,(int)location.y);
					
					if(view.isEndOfTurn((int)location.x,(int)location.y)) {
						posPlayerInQueue+=1; // on passe le tour donc pos du joueur + 1
						gameBoard.endOfTurn(posPlayerInQueue); // même si la valeur envoyée est grande elle est traitée avec un modulo
						view.draw(context, gameBoard);
					}
					
				}else if(action == Action.POINTER_MOVE) {
					location = event.getLocation();
					if(view.setCoordoMiniMenu((int)location.x,(int)location.y)) {
						view.draw(context, gameBoard);
					}
				}else if(action == Action.POINTER_UP) {
					location = event.getLocation();
					if(view.setCoordoPointerUp((int)location.x,(int)location.y)) {
						view.draw(context, gameBoard);
					}

					
				}else {
					view.setCoordoMiniMenu((int) 0, (int) 0);
				}
	
				if(!(action == Action.POINTER_MOVE)) {
					view.draw(context, gameBoard);
				}
				
				if(gameBoard.isEndOfGame()) {
					break;
				}
				
			}
			
			// quitter le jeu ou recommencer
			while (true) {
				Event event = context.pollOrWaitEvent(10); 
				if (event == null) { // no event
					continue;
				}
				
				Action action = event.getAction();
				if (action == Action.KEY_PRESSED) {
					if(event.getKey().toString() == "Q") {
						context.exit(0);
						return;
					}else if(event.getKey().toString() == "R") {
						break;
					}
				}
				
			}
			
			
			
		}
	}

	public static void main(String[] args) {
		// pour changer de jeu, remplacer stupidGame par le nom de la mÃ©thode de jeu
		// (elle doit avoir extaement la mieme en-tÃªte).
		Application.run(Color.BLACK, SimpleGameController::simpleGame); // attention, utilisation d'une lambda.
	}
	
	public static GameBoard concreteCards(GameBoard gameBoard) {
		// Création des cartes:
		
		/* BLOB */
		Ship battleBlob = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Battle-blob.jpg")).getImage(),"Battle Blob","Blob",6,new Primary(8,0,0,0), new Ally(0,0,0,1), new Scrap(4,0,0,0));

		Ship ram = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Ram.jpg")).getImage(),"Ram","Blob",3,new Primary(5,0,0,0), new Ally(2,0,0,0), new Scrap(0,3,0,0));

		Ship tradePod = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Trade-Pod.jpg")).getImage(),"Trade Pod","Blob", 2, new Primary(0,3,0,0), new Ally(2,0,0,0));
		tradePod.addDescription("\nThe loading and offloading process \nis efficient, but disgusting.");

		Base blobWheel = new Base("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Blob-Wheel.jpg")).getImage(),"Blob Wheel", "Blob", 3, 5,false, new Primary(1,0,0,0),new Scrap(0,3,0,0));

		/* STAR EMPIRE */
		Ship corvette = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Corvette.jpg")).getImage(),"Corvette","Star Empire", 2, new Primary(1,0,0,1), new Ally(2,0,0,0));

		Ship dreadnaught = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Dreadnaught.jpg")).getImage(),"Dreadnaught","Star Empire", 7, new Primary(7,0,0,0), new Ally(0,0,0,1),new Scrap(5,0,0,0));

		Base spaceStation = new Base("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Space-Station.jpg")).getImage(),"Space Station", "Star Empire", 4, 4,true, new Primary(2,0,0,0), new Ally(2,0,0,0), new Scrap(0,4,0,0));

		Base warWorld = new Base("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/War-World.jpg")).getImage(),"War World", "Star Empire", 5, 4,true, new Primary(3,0,0,0),new Ally(4,0,0,0));

		/* TRADE FEDERATION */
		Ship cutter = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Cutter.jpg")).getImage(),"Cutter","Trade Federation", 2, new Primary(0,2,4,0), new Ally(4,0,0,0));
		cutter.addDescription("Built for cargo,\narmed for conflict\nVersatility for an"
				+ "\nunpredictable unvierse.\nPremier Aerospace Cargo\nEnterprises.\n");

		// Création d'un primary avec action spéciale
		Primary embassyYachtPrimary = new Primary(0,2,3,0);
		embassyYachtPrimary.addSpecialAction(new SpeActEmbassyYacht()); // ajout d'une action spéciale au primary
		
		Ship embassyYacht = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Embassy-Yacht.jpg")).getImage(),"Embassy Yacht","Trade Federation", 3, embassyYachtPrimary);
		cutter.addDescription("War should always \nbe a last resort,\nit's bad for the bottom line.\n");

		Ship federationShuttle = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Federation-Shuttle.jpg")).getImage(),"Federation Shuttle","Trade Federation", 1, new Primary(0,2,0,0), new Ally(0,0,4,0));
		cutter.addDescription("Fast? This baby doesn't just\nhaul cargo. She hauls...\n");

		Ship flagShip = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Flagship.jpg")).getImage(),"Flagship","Trade Federation", 6, new Primary(5,0,0,1), new Ally(0,0,5,0));

		/* UNALIGNED */
		Ship explorer = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Explorer.jpg")).getImage(),"Explorer","Unaligned",2,new Primary(0,2,0,0), new Scrap(2,0,0,0));
		Ship scout = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Scout.png")).getImage(),"Scout", "Unaligned", 0, new Primary(0,1,0,0));
		Ship viper = new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Viper.jpg")).getImage(),"Viper","Unaligned", 0, new Primary(1,0,0,0));


		// Ajout de joueurs
		gameBoard.addPlayer(new Player("Joueur 1"));
		gameBoard.addPlayer(new Player("Joueur 2"));


		// Création de la pile Explorers
		gameBoard.initialiseExplorers(explorer, 10);

		// Initialisation des decks joueurs
		gameBoard.addCardToAllPlayersDeck(scout, 8);
		gameBoard.addCardToAllPlayersDeck(viper, 2);


		// Ajout des cartes au deck d'achat
		gameBoard.addCardToTradeDeck(battleBlob);
		gameBoard.addCardToTradeDeck(blobWheel,3);
		gameBoard.addCardToTradeDeck(ram,2);
		gameBoard.addCardToTradeDeck(tradePod,3);
		gameBoard.addCardToTradeDeck(corvette,2);
		gameBoard.addCardToTradeDeck(dreadnaught);
		gameBoard.addCardToTradeDeck(spaceStation,2);
		gameBoard.addCardToTradeDeck(warWorld);
		gameBoard.addCardToTradeDeck(cutter,3);
		gameBoard.addCardToTradeDeck(embassyYacht,2);
		gameBoard.addCardToTradeDeck(federationShuttle,3);
		gameBoard.addCardToTradeDeck(flagShip);

		return gameBoard;
	}
}
