/*
Tarpan Patel
Project 2
CIS 252
03/03/2019

This creates new manager to start the game.
 */
public class WarMain
{
	public static void main(String[] args)
	{
		WarManager game = new WarManager(); //Create new game WarManager

		game.setup(); //call setup method of game
		game.deal(); //call deal method of game
		game.play(); //call play method of game
	}
}
