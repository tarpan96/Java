/*
Tarpan Patel
Project 2
CIS 252
03/03/2019

This Card class creates each card for a deck.
 */

public class Card implements Comparable
{
	public static final String CLUBS = "Clubs";
	public static final String HEARTS = "Hearts";
	public static final String DIAMONDS = "Diamonds";
	public static final String SPADES = "Spades";
	public static final int ACE = 14;
	public static final int KING = 13;
	public static final int QUEEN = 12;
	public static final int JACK = 11;

	private String suit;
	private int face;

	public Card(String suit, int face)
	{
		this.suit = suit;
		this.face = face;
	}

	public String getSuit()
	{
		return suit;
	}

	public void setSuit(String suit)
	{
		this.suit = suit;
	}

	public int getFace()
	{
		return face;
	}

	public void setFace(int face)
	{
		this.face = face;
	}

	public String toString()
	{
		return "Card{" + "suit=" + suit + ", face=" + face + '}';
	}

	public boolean equals(Card other)
	{
		boolean result = false;
		try
		{
			boolean suitMatch = this.suit.equals(other.getSuit());
			boolean faceMatch = this.face == other.getFace();

			if(suitMatch && faceMatch){
				result = true;
			}
		}
		catch (NullPointerException e)
		{
			result = false;
		}
		return result;
	}

	@Override
	public int compareTo(Object o)
	{


		return 0;
	}
}
