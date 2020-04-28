package fr.dut.info.starRealms;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;

import fr.dut.info.Abilitys.Ability;
import fr.dut.info.Card.Base;
import fr.dut.info.Card.Card;
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
	
	public static void main(String[] args) {
		// pour changer de jeu, remplacer stupidGame par le nom de la mÃ©thode de jeu
		// (elle doit avoir extaement la mieme en-tÃªte).
		Application.run(Color.BLACK, SimpleGameController::simpleGame); // attention, utilisation d'une lambda.
	}
	
	static void simpleGame(ApplicationContext context) {
		while(true) { // boucle du programme
			
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
	
			SimpleGameView view = new SimpleGameView(0, 10, width, height);
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

	
	
	public static GameBoard concreteCards(GameBoard gameBoard) {
		// Ajout de joueurs
		gameBoard.addPlayer(new Player("Joueur 1"));
		gameBoard.addPlayer(new Player("Joueur 2"));
		
		// Enregistre le nombres pour chaque carte
		Path rules = Path.of("./res/dataCards.txt");
		HashMap<String, Integer> numberByCard = new HashMap<>();
		try(InputStream in = Files.newInputStream(rules); Scanner sc = new Scanner(in);){
			while(sc.hasNextLine()) {
				String[] line = sc.nextLine().split("<|, |>");
				
				if(!line[0].equals("###")) {
					numberByCard.put(line[1], Integer.parseInt(line[2]));
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// Création des cartes:
		Path initCards = Path.of("./res/initCards.txt");
		HashMap<String, Card> allCards = new HashMap<>();
		try(InputStream in = Files.newInputStream(initCards);  Scanner sc = new Scanner(in);){
			Ability p;
			Ability a;
			Ability s;
			while(sc.hasNextLine()) {
				String[] tab = sc.nextLine().split("<|; |>");
				if(!tab[0].equals("###")) {
					if(!tab[1].equals("b")) { // traitement vaisseaux
						/*1 path *2 nom *3 faction *4 sous-factions *5 cout *6 primary *7 action spé primary *8 ally *9 actions spé ally *10 scrap *11 actions spé scrap * 
						*/
						p = createAbilitWithSpeAct(tab[6], tab[7]);
						a = createAbilitWithSpeAct(tab[8], tab[9]);
						s = createAbilitWithSpeAct(tab[10], tab[11]);
						allCards.put(tab[1], new Ship("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource(tab[1])).getImage(),tab[2], tab[3],Integer.parseInt(tab[5]),p,a,s));
						
					}else { // traitement bases
						/*1 marqueur d'objet "base" *2 path *3 nom *4 faction *5 sous-factions *6 cout *7 defence *8 boolean outpost *9 primary *10 action spé primary *11 ally *12 actions spé ally *13 scrap *14 actions spé scrap * */
						p = createAbilitWithSpeAct(tab[9], tab[10]);
						a = createAbilitWithSpeAct(tab[11], tab[12]);
						s = createAbilitWithSpeAct(tab[13], tab[14]);
						Base b = new Base("-1", new ImageIcon(SimpleGameController.class.getClassLoader().getResource(tab[2])).getImage(),tab[3], tab[4],Integer.parseInt(tab[6]), Integer.parseInt(tab[7]), Boolean.parseBoolean(tab[8]),p,a,s);
						allCards.put(tab[3], b);
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Card c: allCards.values()) {
			String name = c.getName();
			int v = numberByCard.get(name);
			if(name.equals("Scout") || name.equals("Viper")) {
				gameBoard.addCardToAllPlayersDeck(c, v);
			}else if(name.equals("Explorer")) {
				gameBoard.initialiseExplorers(c, v);
			}else {
				gameBoard.addCardToTradeDeck(c, v);
			}
			
		}
		return gameBoard;
	}
	
	// Fonction qui créé l'ability et associe des actions spéciales si elles existent
	public static Ability createAbilitWithSpeAct(String values, String actions) {
		String[] acts = actions.split(",");
		HashMap<String, SpecialAction> allSpecialActions = new HashMap<>();
		allSpecialActions.put("SpeActEmbassyYacht", new SpeActEmbassyYacht());
		String[] v = values.split(",");
		Ability ab = new Ability(Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]), Integer.parseInt(v[3]));
		if(actions!="/") {
			for(String name: acts) {
				if(allSpecialActions.containsKey(name)) {
					ab.addSpecialAction(allSpecialActions.get(name));
				}
			}
		}
		return ab;
	}
}
