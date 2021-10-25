/*
Tarpan Patel
Project 2
CIS 252
03/03/2019

This deck class implements DeckInterface and creates a deck of cards.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Deck implements DeckInterface
{
	private ArrayList<Card> deckOfCards = new ArrayList<>();

	/*
		This constructor creates an deck of card and stores it deckOfCards
		For each face, make a new card with different suits and store them in
		an array by reference.
	 */
	public Deck()
	{
		for (int i = 2; i <= 14; i++)
		{
			deckOfCards.add(new Card(Card.HEARTS, i));
			deckOfCards.add(new Card(Card.DIAMONDS,i));
			deckOfCards.add(new Card(Card.CLUBS, i));
			deckOfCards.add(new Card(Card.SPADES,i));
		}
	}

	/*
	Card draw method, gets a card from last element in deckOfCards arrayList and
	stores it in drawCard. Remove the last card from deckOfCards and return drawCard to
	caller.
 */
	@Override
	public Card draw()
	{
		Card drawCard = deckOfCards.get(deckOfCards.size()-1);
		deckOfCards.remove(deckOfCards.size()-1);

		return drawCard;
	}

	@Override
	public void shuffle()
	{
		//Using Collection library and its method .shuffle() to randomly assign
		//cards to different element
		Collections.shuffle(deckOfCards);
	}

	@Override
	public int size()
	{

		//Return the size of deckOfCards ArrayList
		return deckOfCards.size();
	}
}
