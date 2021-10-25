/*
/*
Tarpan Patel
Project 2
CIS 252
03/03/2019

This warManager class manages the whole card game of War. From creating new Players to the end of the game it
game until winner is declared.
 */
import java.util.Scanner;

public class WarManager
{
	LinkedStack<Card> warStack = new LinkedStack<>(); //Declare LinkedStack warStack f
	DeckInterface deckOfCards = new Deck(); //Create new Deck of cards
	Scanner scnr = new Scanner(System.in);
	private Player player1, player2; //Declare Players player1 and player2
	private Card p1, p2; //Declare Cards p1 and p2

	/*
		setup method creates 2 Players and prompts user to enter in players names
	 */

	public void setup()
	{
		System.out.println("enter first player's name: ");
		player1 = new Player(scnr.nextLine());

		System.out.println("Enter second player's name: ");
		player2 = new Player(scnr.nextLine());
	}

	public void deal()
	{
		deckOfCards.shuffle(); //shuffles the deck
		deckOfCards.shuffle();

		int deckSize = deckOfCards.size();
		//loop half of deckOfCards size and alternate in giving each player a card
		for (int i = 0; i <26; i++)
		{
			player1.dealtCards(deckOfCards.draw()); //player1 dealtCard method adds the card to drawStack
			player2.dealtCards(deckOfCards.draw()); //player2 dealtCard method adds the card to drawStack
		}
	}

	public void play()
	{
		Player player = null; //Declare player and initialize to null

		//Call hasCards method to check if both players have cards either in drawStack and discardStack and loop while either player runs out
		while(player1.hasCards() && player2.hasCards())
		{
			p1 = player1.draw(); //initialize p1 to player1 draw card
			p2 = player2.draw(); //initialize p2 to player1 draw card

			//Print what was drawn from both players
			System.out.println(player1.getName() + " " + p1 + " | " +  player2.getName() + " " + p2);

			//Check if both players drew cards with same faces if yes,
			if(p1.getFace() == p2.getFace())
			{
				//add the drawn cards into warStack
				warStack.push(p1);
				warStack.push(p2);

				//Call war method and pass warStack LinkedStack as argument, assign player to the winner of the battle
				player = war(warStack);

				int warStackSize = warStack.size(); //get the size of warStack

				//move cards in warStack to winner (player) to the discardStack and remove from warStack
				for (int i = 0; i < warStackSize; i++)
				{
					player.disCard(warStack.peek());
					warStack.pop();
				}
			}
			else if(p1.getFace() > p2.getFace()) //if player1 card face is higher than player2
			{
				player1.disCard(p2); //add player2 drawn card to player1 discard pile
				player1.disCard(p1); //add player1 drawn card to discard pile
			}
			else //vise versa
			{
				player2.disCard(p1); //add player1 drawn card to player2 discard pile
				player2.disCard(p2); //add player2 drawn card to discard pile
			}

			//if player1 or player2 don't have any cards left in hands, move cards from discard pile(discardStack) to hand(drawStack)
			if(player1.stackSize(0) == 0)
			{
				player1.replenishDrawStack();
			}
			if(player2.stackSize(0) == 0)
			{
				player2.replenishDrawStack();
			}
		}
		//If player1 doesn't have any cards left in drawStack and discardStack, declare player2 as winner,else if player2 don't have cards declare player1 as winner
		if(!player1.hasCards())
		{
			System.out.println("PLAYER2: " + player2.getName() + " WINS THE GAME");
		}
		else
		{
			System.out.println("PLAYER1: " + player1.getName() + " WINS THE GAME");
		}
		//print size of drawStack and discardStack for both player
		System.out.println("STATS");
		System.out.println(player1.toString());
		System.out.println(player2.toString());
	}

	/*
	This war method handles when there is war. It has LinkedStack warStack as argument
	 */
	public Player war(LinkedStack warStack)
	{
		boolean p1Ready = false, p2Ready = false; //declare and initialize p1Ready and p2Ready
		Player winner = null; //Declare and initialize Player winner

		//Restock on cards in draw hand from discard pile if players run out
		if(player1.stackSize(0) == 0)
		{
			player1.replenishDrawStack(); //Call method replenishDrawStack to move cards from discard pile to draw pile
		}
		if(player2.stackSize(0) == 0)
		{
			player2.replenishDrawStack();
		}

		//check if both players have cards in draw pile
		if(player1.stackSize(0) > 0 && player2.stackSize(0) > 0)
		{
			p1 = player1.draw();
			p2 = player2.draw();

			warStack.push(p1);
			warStack.push(p2);

			/*
			Call each players readyForWar method to see if they have enough cards for war, store results in p1Ready or p2Ready
			 */
			p1Ready = player1.readyForWar();
			p2Ready = player2.readyForWar();

			//Check if both players drew cards with same faces if yes,
			if (p1.getFace() == p2.getFace())
			{
				if (p1Ready && p2Ready) //If both players have enough cards for war
				{
					//Draw 3 cards faced down
					for (int i = 0; i < 3; i++)
					{
						try
						{
							//add the drawn cards into warStack
							warStack.push(player1.draw());
							warStack.push(player2.draw());
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
						}
					}
					//call war method again with warStack parameter containing more cards
					war(warStack);
				}
			}

			if (p1.getFace() > p2.getFace() || !p2Ready) //if player1 card face is higher than player2 or player2 doesn't have enough cards
			{
				//Declare player1 has winner and assign player1 to winner
				System.out.println("player 1 wins this war round");
				winner = player1;
			}
			else //player2 has higher face card and declare player2 winner and assign player2 to winner
			{
				System.out.println("player 2 wins this war round");
				winner = player2;
			}
		}
		else
		{
			//If either player doesn't have enough cards or lost, assign winner to opponent
			if(!player1.readyForWar())
			{
				System.out.println("player 1 wins this war round");
				winner = player1;
			}
			else
			{
				System.out.println("player 2 wins this war round");
				winner = player2;
			}
		}
		return winner; //return winner of this war round
	}
}
