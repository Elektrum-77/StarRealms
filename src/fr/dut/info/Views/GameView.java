//package fr.dut.info.Views;
//
//import java.awt.Graphics2D;
//import fr.dut.info.Game.GameBoard;
//import fr.umlv.zen5.ApplicationContext;
//
///**
// * The GameView class is in charge of the graphical view of a clicky game.
// */
//public interface GameView {
//	
//
//	/**
//	 * Draws the game board from its data, using an existing Graphics2D object.
//	 * 
//	 * @param graphics a Graphics2D object provided by the default method
//	 *                 {@code draw(ApplicationContext, GameData)}
//	 * @param data     the GameData containing the game data.
//	 */
//	public void draw(Graphics2D graphics, GameBoard gameBoard);
//
//	/**
//	 * Draws the game board from its data, using an existing
//	 * {@code ApplicationContext}.
//	 * 
//	 * @param context the {@code ApplicationContext} of the game
//	 * @param data    the GameData containing the game data.
//	 */
//	public default void draw(ApplicationContext context, GameBoard gameBoard) {
//		context.renderFrame(graphics -> draw(graphics, gameBoard));
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	public void removeClickableObject(int i);
//	
//}
