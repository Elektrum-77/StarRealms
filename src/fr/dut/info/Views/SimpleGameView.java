package fr.dut.info.Views;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.ImageIcon;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Deck;
import fr.dut.info.Player.Player;
import fr.dut.info.starRealms.ClickableObject;
import fr.dut.info.starRealms.Coordinates;
import fr.dut.info.starRealms.SimpleGameController;

/**
 * @author Windows
 *
 */
public class SimpleGameView implements GameView {
	public String loc = "././././././res/";
	private final int xOrigin;
	private final int yOrigin;
	private final int width;
	private final int length;
	private Coordinates coordo; // Coordonnées de la souris sur le plateau
	@SuppressWarnings("unused")
	private Coordinates coordoMiniMenu; // Coordonnées de la souris sur le plateau
	private Coordinates coordoPointerUp;
	//  Liste de tous les objets cliquables
	private final ArrayList<ClickableObject> visibleObjects;
	// Liste de tous les sous-objets cliquables du minimenu
	private final ArrayList<ClickableObject> miniMenu;
	private ClickableObject selectedSubMenu;
	// L'object selectionné
	private ClickableObject selectedObject;
	
	// L'object selectionné par défaut (quand rien n'est séléctionné)
	private final ClickableObject selectedDefault = new ClickableObject(-1, -1, -1, -1);
	private final Image background;
	
	
	/**
	 * Built a view for the game
	 * @param xOrigin define an x origin
	 * @param yOrigin define an y origin
	 * @param width define a width
	 * @param length defin a length
	 * @param background the background of the scene
	 */
	public SimpleGameView(int xOrigin, int yOrigin, int width, int length) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.width = width;
		this.length = length;
		coordo=new Coordinates(0,0);
		visibleObjects = new ArrayList<>();
		miniMenu = new ArrayList<>();
		this.background=randomBg();
		coordoMiniMenu = new Coordinates(0,0);
		coordoPointerUp = new Coordinates(0,0);
		selectedObject = selectedDefault;
	}
	
	private Image randomBg() {
		// Background Aléatoire
					HashSet<String> pathListBg = new HashSet<>();
					ArrayList<Image> bgList = new ArrayList<>();
					pathListBg.add("img/background/bg.jpg");
					pathListBg.add("img/background/bg2.jpg");
					pathListBg.add("img/background/bg4.jpg");
					pathListBg.add("img/background/bg6.jpg");
					pathListBg.add("img/background/bg7.jpg");
					pathListBg.add("img/background/bg8.jpg");
					pathListBg.add("img/background/bg9.jpg");
					for(String path: pathListBg) {
						bgList.add(new ImageIcon(SimpleGameController.class.getClassLoader().getResource(path)).getImage());
					}
					Random random = new Random();
					int i = random.nextInt(bgList.size());
					// Nouveau background defini aléatoirement
					return bgList.get(i);
	}
	
	/**
	 * @return the current selected object in the board
	 */
	public ClickableObject getSelectedObject() {
		return selectedObject;
	}
		
		
	
	/**
	 * @return the mouse's coordinates (when clicked or moved during click)
	 */
	public Coordinates getCoordo() {
		return coordo;
	}
		
	
	public boolean isEndOfTurn(int x, int y) {
		ClickableObject object = isAnObjectHere(x,y,visibleObjects);
		if(object!=null) {
			selectedObject=object;
			if(object.getLabel()=="End turn") {
				return true;
			}
		}
		return false;
	}
		
	
	/**Set the new coordinates of the mouse on the game board.
	 * if the coordinates match with a present object 
	 * of the game board, set the new selected object
	 * 
	 * @param x of the mouse
	 * @param y of the mouse
	 * 
	 */
	public void setCoordo(int x, int y) {
		selectedSubMenu = selectedDefault;
		coordo = new Coordinates(x, y);
		ClickableObject object = isAnObjectHere(x,y,visibleObjects);
		if(object!=null) {
			selectedObject=object;
		}else {
			selectedObject=selectedDefault;
		}
	}
	

	/**Sets the coordinates of the mini menu.
	 * @param x of the mouse
	 * @param y of the mouse
	 * @return returns true to allow redraw of the display if mouse is on the mini menu and only if it changed of submenu.
	 */
	public boolean setCoordoMiniMenu(int x, int y) {
		coordoMiniMenu = new Coordinates(x, y);
		ClickableObject object = isAnObjectHere(x,y,miniMenu);
		if(object!=null && !(selectedSubMenu.equals(object))) {
			selectedSubMenu=object;
			return true;
		}
		return false;
	}
	
	// Set les coordonnées de coordoPointerUp à la levée du clic
	public boolean setCoordoPointerUp(int x, int y) {
		coordoPointerUp = new Coordinates(x, y);
		ClickableObject obj= isAnObjectHere(x,y,miniMenu);
		if(obj!=null) {
			if(obj.getLabel()=="See") {
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	
	//Returns the object if the coordinates target an object in an gived object list.
	private ClickableObject isAnObjectHere(int x, int y, ArrayList<ClickableObject> objects) {
		for(ClickableObject object: objects) {
			if(coordoInZone(x,y,object.getX1(),object.getY1(),object.getX2(),object.getY2())) {
				//System.out.println(x+","+y+","+object.getX1()+","+object.getY1()+","+object.getX2()+","+object.getY2());
				//System.out.println("In it");
				return object;
			}
		}
		return null;
	}


	// Renvoie true si les coordonnées sont dans la zone donnée
	static boolean coordoInZone(int x, int y, int x1, int y1, int x2, int y2) {
		if( (x1 <= x) && (x <= x2) && (y1 <= y) && (y <= y2) ) {
			return true;
		}
		return false;
	}
	
	
	/** Call all the draw functions of the game
	 *
	 */
	@Override
	public void draw(Graphics2D graphics, GameBoard gameBoard) {
		
		
		//On met à jour les données
		gameBoard.updateTradeRow();
		
		
		int x, y, w, l;
		//On vide la liste des objects cliquables
		
		visibleObjects.clear();
		miniMenu.clear();
		

		// Affiche un rectangle noir en fond
		graphics.setColor(Color.BLACK);
		graphics.fill(new Rectangle2D.Float(xOrigin, yOrigin, width, length));
		
		// Affiche le Background
		graphics.drawImage(background, xOrigin-10, yOrigin-10, width+30, length, null);
		Coordinates c = coordo;
		drawSelectedObject(graphics);
		
		// Affiche les coordonnées du clic
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.PLAIN, 12));
		graphics.drawString(c+"", width-100, 50);
		graphics.drawString("Quit : q", width-200, 50);
		graphics.drawString("Reload game : r x2", width-200, 100);
		graphics.drawString("Illegal change of turn : space", width-200, 150);
		graphics.drawString("Illegal Combat/Trade pool : UP", width-200, 200);
		
		// Dessine les cartes en jeu des joueurs
		x = width/6; y = (length/5-20) + 30;
		drawInPlayCards(graphics, gameBoard.getPlayers().get(0), x, y);
		y = (length-(length/5-20)-20)-(length/8)-20;                //(length/2)+120; 
		drawInPlayCards(graphics, gameBoard.getPlayers().get(1), x, y);
		
		// Dessine les deck de joueur
		x = width/3-70; y = 20; w = width/14-20; l = length/5-20; // 110 180
		drawPlayerDeck(graphics, gameBoard,gameBoard.getPlayers().get(0), x, y, w, l);
		y = length-l-20;
		drawPlayerDeck(graphics, gameBoard,gameBoard.getPlayers().get(1), x, y, w, l);
		
		// Dessine les points des joueurs
		w = width/20; x -= (length/5-20)+w; y = 20 ;  l = w; //80 80
		Player player = gameBoard.getPlayers().get(0);
		drawPlayerPoints(graphics, player, x,  y,  w,  l);
		y = length-(length/5-20)-20;
		player = gameBoard.getPlayers().get(1);
		drawPlayerPoints(graphics, player, x,  y,  w,  l);
	
		// Dessine les cartes de la main des joueurs
		x = ((width/3)-70)+(width/14-20)+10 ; y= 20; w = width/16; l = length/6; // 140 210
		drawHand(graphics, gameBoard,gameBoard.getPlayers().get(0), x, y, w, l);
		y= length-l-20;
		drawHand(graphics, gameBoard,gameBoard.getPlayers().get(1), x, y, w, l);
		
		
		// Afficher la pile de deck du jeu (TradeDeck)
		x = 80; y = (length/2)-70; w = width/14; l = length/5;
		drawTradeDeck(graphics, gameBoard, x, y, w, l);

		// Dessine la ligne d'achat
		x+= (width/14) + 12; //140 220
		drawTradeRow(graphics, gameBoard, x, y, w, l);
	
		// Dessine la pile d'Explorers
		x = (width-2*(length/5)-5); y += 10;//x = 1470; y = 450; w = 140; l = 220;
		drawExplorers(graphics, gameBoard, x, y, w, l);
		
		// Dessine les points du tour
		w = l; x= width-w-5; y = length/2-70;  l = length/10;
		drawPoolPoints(graphics,  gameBoard,  x,  y,  w,  l);
		
		// Créer un bouton pour terminer son tour
		y+=l+10;
		createEndTurnButton( graphics, gameBoard, x, y, w, l);
		
		
		// test image "Menu"
		//Image img2 = new ImageIcon("././././img/background/menuu.png").getImage();
		//graphics.drawImage(img2, 10, 10, 210, 100, null);
		
		// On affiche le mini menu
		x = coordo.getI(); y = coordo.getJ();
		drawMiniMenu(graphics, gameBoard, x, y);
		pimpSubMenu(graphics);
		
		
		
		if(actionMiniMenu(graphics,gameBoard, coordoPointerUp)) {
			clean();
		}else {
			coordoPointerUp = new Coordinates(0,0);
			
		}
		
		if(gameBoard.isEndOfGame()) {
			graphics.setColor(Color.CYAN);
			graphics.setFont(new Font("Arial", Font.PLAIN, 40));
			graphics.drawString("One player was defeated | q : quit / r: restart", width/2, (length/2)-200);
		}
		
	}
	
	
	// Supprime toute object selectionnés et tous les objets 
	public void clean() {
		coordo=new Coordinates(0,0);
		visibleObjects.clear();
		selectedSubMenu=selectedDefault;
		selectedObject=selectedDefault;
		coordoMiniMenu = new Coordinates(0,0);
		coordoPointerUp = new Coordinates(0,0);
		miniMenu.clear();
	}
	
	
	private void createEndTurnButton(Graphics2D graphics, GameBoard gameBoard, int x, int y, int w, int l) {
		// Bouton pour terminer son tour
		graphics.setColor(Color.DARK_GRAY);
		graphics.fill(new Rectangle2D.Float(x, y, w, l));
		drawCardStroke(graphics, x, y, w, l, Color.BLACK);
		
		graphics.setFont(new Font("Arial", Font.BOLD, 40));
		graphics.drawString("End turn", x + 7, y +63);
		visibleObjects.add(new ClickableObject("End turn",x,y,x+w,y+l));
	}
	
	
	private void drawPoolPoints(Graphics2D graphics, GameBoard gameBoard, int x, int y, int w, int l) {
		// Boite grise
		graphics.setColor(Color.DARK_GRAY);
		graphics.fill(new Rectangle2D.Float(x, y, w, l));
		drawCardStroke(graphics, x, y, w, l, Color.BLACK);
		
		String str = gameBoard.getTradePool()+"";
		graphics.setFont(new Font("Arial", Font.BOLD, w/(3*1+(str.length()/2)) ));
		
		x += 3; y+=3;
		// Trade
		graphics.setColor(new Color(245, 225, 10));
		Shape trade = new Ellipse2D.Float(x, y, l-5, l-5);
		graphics.fill(trade);
		graphics.setColor(Color.BLACK);
		graphics.drawString(str, x + 5, y +60);
		
		x += l;
		// Combat
		graphics.setColor(new Color(227, 41, 16));
		Shape comba = new Ellipse2D.Float(x, y, l-5, l-5);
		graphics.fill(comba);
		//graphics.draw(new Rectangle2D.Float(x-5, y+(l/2)-7, 90, 2));
		//graphics.draw(new Rectangle2D.Float(x+37, y-5, 2, 90));
		graphics.setColor(Color.BLACK);
		graphics.drawString(gameBoard.getCombatPool()+"", x + 1, y +60);
	}
	
	
	private void drawPlayerPoints(Graphics2D graphics, Player player, int x, int y, int w, int l) {
		graphics.setColor(new Color(23, 209, 83));
		graphics.fill(new Rectangle2D.Float(x, y, w, l));
		visibleObjects.add(new ClickableObject("Player", x, y, x+w, y+l, player));
		
		Polygon p = new Polygon(new int[] {x,x+w,x+(w/2)} , new int[] {y+l,y+l,y+l+20} , 3);
		graphics.fillPolygon(p);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial", Font.BOLD, 50));
		graphics.drawString(player.getAuthority()+"", x + 12,y +60);
	}
	
	
	// Affiche la pile deck des joueurs et leur défausse
	private void drawPlayerDeck(Graphics2D graphics, GameBoard gameBoard,Player player, int x, int y, int w, int l) {
		int xi = x;
		int yi = y;
		if(!player.getDeck().isEmpty()) {
			for(int j = 0; j < player.getDeck().getSize(); j++) {
				graphics.drawImage(new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Other/Star-Realms-Back.jpg")).getImage(), x, y, w, l,null);
				drawCardStroke(graphics, x, y, w, l);
				x-=2;y-=2;
			}
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Arial", Font.BOLD, 50));
			graphics.drawString(player.getDeck().getSize()+"", x + 10,y +50);
		}else {
			graphics.setColor(Color.DARK_GRAY);
			graphics.fill(new Rectangle2D.Float(x, yi, w, l));
			drawCardStroke(graphics, x, yi, w, l, new Color(159, 173, 196));
		}
		x = xi - l + 20;
		if(!player.getDiscard().isEmpty()) {
			Deck discardDeck = player.getDiscard();
			for(int k = 0; k < discardDeck.getSize(); k++) {
				graphics.drawImage(discardDeck.getCard(k).getImage(),x,y, w, l,null);
				drawCardStroke(graphics, x, y, w, l);
				x-=1;y-=1;
			}
		}else {
			graphics.setColor(Color.DARK_GRAY);
			graphics.fill(new Rectangle2D.Float(x, yi, w, l));
			drawCardStroke(graphics, x, yi, w, l, new Color(159, 173, 196));
		}
	}
	
	
	// Affiche les mains de joueurs
	private void drawHand(Graphics2D graphics, GameBoard gameBoard, Player player, int x, int y, int w, int l) {
		int wf = w; int lf = l;
		int yf = y; 
		int marginRightI = w + 10; //
		int marginRight = (player.equals(gameBoard.getPlayingPlayer())) ? 150: 20;
		// Les cartes du joueur qui ne joue pas sont regroupées entre elles (changement de marge droite)
		// Main du joueur
		Deck hand = player.getHand();
		for(int i = 0; i < hand.getSize(); i++) {
			if(player.equals(gameBoard.getPlayingPlayer())) {
				if(hand.getCard(i).cardType()=="Base") {
					y= yf + 30; w = lf; l = wf; marginRight= marginRightI + 70;
				}else{
					y= yf; w = wf; l = lf; marginRight = marginRightI;
				}
				graphics.drawImage(hand.getCard(i).getImage(),x,y, w, l,null);
				drawCardStroke(graphics, x, y, w, l);
				// Ajout de la carte aux objects visibles/cliquables
				visibleObjects.add(new ClickableObject(x,y,x+w,y+l,hand.getCard(i),i));
			}else {
				wf = (int)w/2; lf = (int) l/2;
				yf = ((length-y) < (length/2) )? y + lf : y; 
				graphics.drawImage(new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Other/Star-Realms-Back.jpg")).getImage(),x,yf, wf, lf,null);
				drawCardStroke(graphics, x, yf, wf, lf);
			}
			x+=marginRight;
		}
	}
	
	
	public void drawInPlayCards(Graphics2D graphics, Player player, int x, int y) {
		Deck inPlayCards = player.getInPlayCards();
		int w = width/21 ; int l = length/8; // w = width/14; l = length/5;
		int wf = w; int lf = l;
		int yf = y; 
		// Les cartes du joueur qui ne joue pas sont regroupées entre elles (changement de marge droite)
		int marginRightI = w + 10; //
		int marginRight;
		// Main du joueur
		
		for(int i = 0; i < inPlayCards.getSize(); i++) {
			
			if(inPlayCards.getCard(i).cardType()=="Base") {
				y= yf + 30; w = lf; l = wf; marginRight = marginRightI + 40;
			}else{
				y= yf; w = wf; l = lf; marginRight = marginRightI;
			}
			graphics.drawImage(inPlayCards.getCard(i).getImage(), x,y, w, l,null);
			// Ajout de la carte aux objects visibles/cliquables
			visibleObjects.add(new ClickableObject(x,y,x+w,y+l,inPlayCards.getCard(i),i));

			drawCardStroke(graphics, x, y, w, l);
			x+=marginRight;
		}
	}
	
	// Affiche le deck du jeu et le tas de ferraille
	public void drawTradeDeck(Graphics2D graphics, GameBoard gameBoard, int x, int y, int w, int l) {
		int xi = x;
		int yi = y;
		// Affiche Le deck d'achat
		if(!gameBoard.getTradeDeck().isEmpty()) {
			// Affichage de l'image
			for(int i = 0; i < gameBoard.getTradeDeck().getSize(); i++ ) {
				graphics.drawImage(new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Other/Star-Realms-Back.jpg")).getImage(),x,y, w, l,null);
				drawCardStroke(graphics, x, y, w, l);
				//décalage à chaque nouvelle carte pour faire style paquet
				x-=1;y-=1;
			}
		}else {
			graphics.setColor(Color.DARK_GRAY);
			graphics.fill(new Rectangle2D.Float(x, y, w, l));
			drawCardStroke(graphics, x, yi, w, l, new Color(159, 173, 196));
		}
		x = xi;
		y = yi + l + 20;
		// Affiche le tas de ferraille 
		if(!gameBoard.getScarpHeap().isEmpty()) {
			// Affichage de l'image
			for(int i = gameBoard.getScarpHeap().getSize()-1; i >= 0; i--) {
				graphics.drawImage(gameBoard.getScarpHeap().getCard(i).getImage(),x,y, w, l,null);
				drawCardStroke(graphics, x, y, w, l);
				x-=2;y-=2;
			}
		}else {
			graphics.setColor(Color.DARK_GRAY);
			graphics.fill(new Rectangle2D.Float(x, y, w, l));
			drawCardStroke(graphics, x, y, w, l, new Color(159, 173, 196));
		}
	}
	
	
	// Affiche la ligne d'achat
	private void drawTradeRow(Graphics2D graphics, GameBoard gameBoard, int x, int y, int w, int l) {
		int yi = y;
		int wi = w;
		int li = l;
		int marginRightI = width/13; //160
		int marginRight;

		if(!gameBoard.getTradeRow().isEmpty()) {
			// Main du joueur
			Deck tradeRow = gameBoard.getTradeRow();
			for(int i = 0; i < tradeRow.getSize(); i++) {
				if(tradeRow.getCard(i).cardType()=="Base") {
					y= yi + 30; w = li; l = wi; marginRight= marginRightI+80;
				}else{
					y= yi; w = wi; l = li; marginRight = marginRightI;
				}
				// Affichage de l'image
				graphics.drawImage(tradeRow.getCard(i).getImage(),x,y, w, l,null);
				drawCardStroke(graphics, x, y, w, l);
				// Ajout de la carte aux objects visibles/cliquables
				visibleObjects.add(new ClickableObject(x,y,x+w,y+l,tradeRow.getCard(i),i));
				x+=marginRight;
			}
		
		}
	}
	
	
	// Affiche la pile d'explorers
	private void drawExplorers(Graphics2D graphics, GameBoard gameBoard, int x, int y, int w, int l) {
		
		
		if(!gameBoard.getExplorers().isEmpty()) {
			visibleObjects.add(new ClickableObject(x, y, x+w, y+l, gameBoard.getExplorers(), 0));
			// Affichage de l'image
			for(int i = 0; i < gameBoard.getExplorers().getSize(); i++) {
				graphics.drawImage(new ImageIcon(SimpleGameController.class.getClassLoader().getResource("img/Core-set/Explorer.jpg")).getImage(),x,y, w, l,null);
				drawCardStroke(graphics, x, y, w, l);
				x+=2;y-=2;
			}
			
		}else {
			graphics.setColor(Color.DARK_GRAY);
			graphics.fill(new Rectangle2D.Float(x, y, w, l));
			drawCardStroke(graphics, x, y, w, l, new Color(159, 173, 196));
		}
	}	
		
	
	// Dessine une bordure de carte noire
	private void drawCardStroke(Graphics2D graphics, int x, int y, int w, int l) {
		drawStroke(graphics, x, y, w, l, Color.BLACK, 0);
	}
	
	
	// Dessine la bordure d'une carte avec la couleur donnée
	private void drawCardStroke(Graphics2D graphics, int x, int y, int w, int l, Color color) {
		drawStroke(graphics, x, y, w, l, color, 0);
	}
	
	
	// Dessine une bordure
	private void drawStroke(Graphics2D graphics, int x, int y, int w, int l, Color color, int weight) {
			weight = (weight == 0) ? 4 : weight;
			graphics.setColor(color);
			Stroke stroke = new BasicStroke(weight);
			graphics.setStroke(stroke);
			graphics.draw(new RoundRectangle2D.Float((float)x,(float)y,(float)w,(float)l,(float)10,(float)10));
	}
	
	
	// Dessine une bordure autour de l'object selectionné (fond cyan)
	private void drawSelectedObject(Graphics2D graphics) {
		if(selectedObject!=null) {
			graphics.setColor(Color.CYAN);
			graphics.fill(new Rectangle2D.Float(
					selectedObject.getX1()-3,selectedObject.getY1()-4,
					selectedObject.getWidth()+7, selectedObject.getLength()+7)
					);
		}
	}
		

	// Créer un petit menu d'options à l'endroit du clic
	public void drawMiniMenu(Graphics2D graphics, GameBoard gameBoard, int x, int y) {
		x+=5;y+=5;
		Deck deck = null;
		Card card = null;
		Player player = null;
		graphics.setFont(new Font("Franklin Gothic", Font.PLAIN, 20));
		
		if(!selectedDefault.equals(selectedObject)) {
			HashMap<String, String> list = new HashMap<>();
			// ceci est très TRES moche, mais bon.. vois pas comment faire
			if(selectedObject.getCard()!=null) { // Est une carte 
				card = selectedObject.getCard();
				
				if(gameBoard.cardIsInAHand(card.getId())) { // Si la carte est dans la main
					if(card.getPrimary().exists()) { 
						if(card.getPrimary().getOr()==true) {
							list.put("Play(2nd op)", "Play(2nd op)");
							list.put("Play(1st op)", "Play(1st op)");
						}else {
							list.put("Play", "Play");
						}
					}
					
				}else if(gameBoard.cardIsInTradeRow(card.getId())) { // Si la carte est dans la liste d'achat
					if(gameBoard.getTradePool() >= card.getCost()) {list.put("Buy for "+card.getCost(),"Buy");}
					if(gameBoard.getScrapTradeRowPool()>=1) { list.put("Scrap", "Scrap"); }
					if(gameBoard.getFreeShipPool()>=1 && card.cardType()=="Ship") {list.put("Get for free", "Get for free"); }
					
				}else if(gameBoard.getPlayingPlayer().getInPlayCards().isCardIdIn(card.getId())) { // Si la carte lui fait partie de ses cartes en jeu
					if(card.getPrimary().exists()) {
						if(card.getPrimary().getOr()) {
							list.put("Primary(2nd op)", "Primary(2nd op)" );
							list.put("Primary(1st op)", "Primary(1st op)" );
						}else {
							list.put("Primary", "Primary");
						}
					}
					if(card.getAlly().exists() && gameBoard.getPlayingPlayer().isPossibleAlly(card)) {
						if(card.getAlly().getOr()) {
							list.put("Ally(2nd op)", "Ally(2nd op)" );
							list.put("Ally(1st op)", "Ally(1st op)" );
						}else {
						list.put("Ally", "Ally"); 
						}
					}
					if(card.getScrap().exists()) {
						if(card.getScrap().getOr()) {
							list.put("Scrap(2nd op)", "Scrap(2nd op)" );
							list.put("Scrap(1st op)", "Scrap(1st op)" );
						}else {
						list.put("Scrap", "Scrap");
						}
					}
					
				}else if (!gameBoard.cardIsInTradeRow(card.getId())){// la carte n'est pas dans ses cartes en jeu ni dans la ligne d'achat
					if(card.cardType()=="Base") {
						if(gameBoard.getCombatPool() >= card.getDefense()) { list.put("Destroy", "Destroy"); }
						if(gameBoard.getFreeDestroyBasePool() >= 1) {list.put("Free Destroy", "Free Destroy"); }
					}
				}
				
				list.put("See", "See");
			}else if(selectedObject.getDeck()!=null) { // Est un deck
				
				deck = selectedObject.getDeck();
				// si c'est le deck explorers et que trade pool > prix de la carte
				if(deck.getName()=="Explorers" && gameBoard.getTradePool() >= deck.getCard(0).getCost()) {list.put("Buy", "Buy"); }
				
			// si le joueur lié à l'icone de joueur n'est pas le joueur qui effectue son tour
			}else if(selectedObject.getLinkedPlayer()!=null && !(selectedObject.getLinkedPlayer().equals(gameBoard.getPlayingPlayer()))) { 
				player = selectedObject.getLinkedPlayer();
				// Si le joueur dont l'icone et selectionnée ne possède pas de base "outPost" et que le combat pool est > 0
				if(player.hasNoOutPostBase() && gameBoard.getCombatPool() > 0 ) {
					list.put("Attack 1", "Attack 1");
					list.put("Attack all", "Attack all");
				}
			}
			int w = 140;
			int l = 50;
			for(Entry<String, String> str: list.entrySet()) {
				graphics.setColor(Color.DARK_GRAY);
				graphics.fill(new Rectangle2D.Float((float)x,(float)y,(float)w,(float)l));
				if(card!=null) {
					miniMenu.add(new ClickableObject(str.getValue(), x,y,x+w,y+l, card));
				}else if(player!=null) {
					miniMenu.add(new ClickableObject(str.getValue(), x,y,x+w,y+l, player));
				}else {
					miniMenu.add(new ClickableObject(str.getValue(), x,y,x+w,y+l, deck));
				}
				graphics.setColor(new Color(208, 221, 242));
				graphics.drawString(str.getKey(), x+5,y+30);
				drawCardStroke(graphics, x, y, w, l, new Color(114, 125, 143));
				y+=50;
			}
		}
	}
	
	
	public boolean actionMiniMenu(Graphics2D graphics, GameBoard gameBoard, Coordinates coordo) {
		int x =  width;
		int w; 
		int l;
		ClickableObject object = isAnObjectHere(coordo.getI(),coordo.getJ(), miniMenu);
		Card card;
		if(object != null) {
			// Faudrait faire un enum dans la classe ClickableObject pour les labels
			String label = object.getLabel();
			if(label=="See") {
				//removeClickableObjectById(object.getLinkedCard().getId());
				w = selectedObject.getWidth()*3;
				l = selectedObject.getLength()*3;
				graphics.drawImage(object.getLinkedCard().getImage(),x-w , 0 , w, l, null);
				drawCardStroke(graphics, x-w, 0, w, l, Color.BLACK);
				drawStroke(graphics, x-(w+1), 0, w+1, l-1, new Color(34, 36, 38), 13);
				return true;
				
			}else if(label == "Buy"){
				if(object.getLinkedCard() !=null) {
					gameBoard.getPlayingPlayer().addCardtoDiscardPile(object.getLinkedCard());
					gameBoard.getTradeRow().removeCard(object.getLinkedCard().getId());
					gameBoard.updateTradePool(object.getLinkedCard().getCost()*(-1));
					
				}else if(object.getLinkedDeck().getName()=="Explorers"){
					card=gameBoard.getExplorers().getCard(0);
					gameBoard.getPlayingPlayer().addCardtoDiscardPile(card);
					gameBoard.getExplorers().removeCard(card.getId());
				}
				
				return true;
			}else if(label == "Play" || label == "Play(1st op)" || label == "Play(2nd op)") { 
				
				card = object.getLinkedCard();
				if(label == "Play(1st op)") {
					card.getPrimary().useOR1(gameBoard, gameBoard.getPlayingPlayer());

				}else if(label == "Play(2nd op)") {
					card.getPrimary().useOR2(gameBoard, gameBoard.getPlayingPlayer());

				}else {
					card.getPrimary().useAllSpecialActions(gameBoard, gameBoard.getPlayingPlayer());
					card.getPrimary().use(gameBoard);
					
				}
				gameBoard.getPlayingPlayer().addInPlayCard(card.getId());
				gameBoard.getPlayedCardTurn().add(card.getFaction());
				clean();
				
			}else if (label == "Primary" || label == "Primary(1st op)" || label == "Primary(2nd op)") {
				card = object.getLinkedCard();
				if(label == "Primary(1st op)") {
					card.getPrimary().useOR1(gameBoard, gameBoard.getPlayingPlayer());
				}else if(label == "Primary(2nd op)") {
					card.getPrimary().useOR2(gameBoard, gameBoard.getPlayingPlayer());
				}else {
					card.getPrimary().useAllSpecialActions(gameBoard, gameBoard.getPlayingPlayer());
					card.usePrimary(gameBoard);
				}
				return true;
			}else if(label == "Ally" || label == "Ally(1st op)" || label == "Ally(2nd op)") {
				card = object.getLinkedCard();
				if(label == "Ally(1st op)") {
					card.getAlly().useOR1(gameBoard, gameBoard.getPlayingPlayer());
				}else if(label == "Ally(2nd op)") {
					card.getAlly().useOR2(gameBoard, gameBoard.getPlayingPlayer());
				}else {
					card.getAlly().useAllSpecialActions(gameBoard, gameBoard.getPlayingPlayer());
					card.useAlly(gameBoard);
				}
				return true;
				
			}else if(label == "Attack 1") { // On attaque avec un point de combat
				Player player = object.getLinkedPlayer();			
				player.updateAuthority(-1);
				gameBoard.updateCombatPool(-1);
				return true;
			
			}else if(label == "Attack all") { // On attaque avec tous les points de combat dispo
				Player player = object.getLinkedPlayer();		
				int i = gameBoard.getCombatPool();
				player.updateAuthority((-1)*i);
				gameBoard.updateCombatPool((-1)*i);
				return true;
				
			}else if(label == "Scrap" || label == "Scrap(1st op)" || label == "Scrap(2nd op)") {
				card = object.getLinkedCard();
				if(gameBoard.cardIsInTradeRow(card.getId())) {
					card.disableAllAbilitys();
					// on réactive les capacités de la carte avant de la jeter
					card.resetAbilitys();
					gameBoard.getScarpHeap().addCard(card);
					gameBoard.getTradeRow().removeCard(card.getId());
					gameBoard.updateScrapTradeRowPool(-1);
				}else {
					if(label == "Scrap (1st op)") {
						card.getScrap().useOR1(gameBoard, gameBoard.getPlayingPlayer());
					}else if(label == "Scrap (2nd op)") {
						card.getScrap().useOR2(gameBoard, gameBoard.getPlayingPlayer());
					}else {
						card.useScrap(gameBoard);
						card.getScrap().useAllSpecialActions(gameBoard, gameBoard.getPlayingPlayer());
					}
					// on réactive les capacités de la carte avant de la jeter
					card.resetAbilitys();
					gameBoard.getScarpHeap().addCard(card);
					gameBoard.getPlayingPlayer().getInPlayCards().removeCard(card.getId());
				}
				
				return true;
			}else if(label == "Destroy") {
				card = object.getLinkedCard();
				Player player;
				for(Player plyer: gameBoard.getPlayers()) {
					if(plyer.getInPlayCards().isCardIdIn(card.getId())){
						player = plyer;
						gameBoard.updateCombatPool((-1)*card.getDefense());
						player.getInPlayCards().removeCard(card.getId());
						player.addCardtoDiscardPile(card);
						break;
					}
				}
			return true;
			} else if(label == "Get for free") {
				card = object.getLinkedCard();
				gameBoard.getPlayingPlayer().addCardToTopOfDeck(object.getLinkedCard(), 1);
				gameBoard.getTradeRow().removeCard(object.getLinkedCard().getId());
				gameBoard.updateFreeShipPool(-1);
				
			} else if(label == "Free Destroy") {
				card = object.getLinkedCard();
				Player player;
				for(Player plyer: gameBoard.getPlayers()) {
					if(plyer.getInPlayCards().isCardIdIn(card.getId())){
						player = plyer;
						gameBoard.updateFreeDestroyBasePool(-1);
						player.getInPlayCards().removeCard(card.getId());
						player.addCardtoDiscardPile(card);
						break;
					}
				}
			
			}
		}
		return false;
	}
	
	// Utile !
	@SuppressWarnings("unused")
	private void removeClickableObjectById(String id) {
		for(int i = 0; i < visibleObjects.size(); i++) {
			if(visibleObjects.get(i).getCard()!=null) {
				if(visibleObjects.get(i).getCard().getId()==id) {
					visibleObjects.remove(i);
				}
			}
		}
	}
	
	// Bleute le sous menu quand la souris passe dessus
	public void pimpSubMenu(Graphics2D graphics) {
		if(selectedSubMenu!=null) {
			graphics.setColor(new Color(0, 255, 242));
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,5* 0.1f));
			graphics.fill(new Rectangle2D.Float(
					selectedSubMenu.getX1(),selectedSubMenu.getY1(),
					selectedSubMenu.getWidth(), selectedSubMenu.getLength())
					);
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
		}
	}
	
	// Obsolete ?? à verif si utilisé ou non
	// Détruit un objet (le conteneur) Cliquable de la liste des objets cliquables
	public void removeClickableObject(int i) {
		try {
			visibleObjects.remove(i);
		}catch(IndexOutOfBoundsException e) {
			System.out.println("The object position doesn't exist.");
		}
	}

	
	
}
