public class Player
{
	private String name; //Declare name
	private LinkedStack<Card> discardStack = new LinkedStack<>(); //Declare discardStack LinkedStack
	private LinkedStack<Card> drawStack = new LinkedStack<>(); //Declare drawStack LinkedStack

	//Constructor Player has string as parameter
	public Player(String name)
	{
		this.name = name; //assign name to name
	}

	public Card draw()
	{
		Card drew = (Card) drawStack.peek(); //Get card from drawStack and cast it to Card and assign it to drew
		drawStack.pop(); //remove card from drawStack

		return drew; //return drew card to caller
	}

	//disCard method adds uCards to discardStack (discard pile)
	public void disCard(Card uCards)
	{
		discardStack.push(uCards);
	}

	//This method checks if player has cards in drawStack and discardStack returns true or false
	public boolean hasCards()
	{
		boolean hasCard = true;

		//Checks if the drawStack and discardStack has any cards
		if(discardStack.size() == 0 && drawStack.size() == 0)
		{
			hasCard = false; //if no cards in both stack assign hasCard to false
		}
		return hasCard; //return hasCard
	}

	@Override
	public String toString()
	{
		return name + " (drawStack: " + drawStack.size() + " & discardStack: " + discardStack.size() + ")";
	}

	/*
		dealtCards method, takes cards as argument, and adds cards to drawStack
		this method is called when players gets cards at the beginning of the game
	 */
	public void dealtCards(Card cards)
	{
		drawStack.push(cards);
	}

	//getName method returns name of the player when called
	public String getName()
	{
		return name;
	}

	/*
		replenishDrawStack method moves cards from discardStack to drawStack for player when called upon
	 */
	public void replenishDrawStack()
	{
		int discardSize = discardStack.size(); //declare and initialize discardSize to size of discardStack

		if(discardSize > 0) //if discardStack is not empty
		{
			//loop numer of discardSize times
			for (int i = 0; i < discardSize; i++)
			{
				drawStack.push(discardStack.peek()); //look at card from discardStack and move into drawStack
				discardStack.pop(); //remove card from discardStack
			}
		}
	}

	//stackSize returns size of drawStack or discardStack depending on the argument of stack
	public int stackSize(int stack)
	{
		int size = 0;

		if(stack == 0) //if stack is 0
		{
			size = drawStack.size(); //store the size of drawStack into size
		}
		if(stack == 1) //if stack is 1
		{
			size = discardStack.size(); //store the size of discardStack into size
		}
		return size; //return size
	}

	//This method is called when there is war
	public boolean readyForWar()
	{
		boolean ready = false; //declare and initialize to false

		if(hasCards()) //check if player has cards in discardStack or drawStack
		{
			ready = true; //assign true to ready
		}
		if(stackSize(0) == 0 && stackSize(1) >= 4) //if drawStack size is 0 and discardStack has more than 4 cards
		{
			replenishDrawStack(); //call replenishDrawStack to move cards from discardStack to drawStack
			ready = true;
		}

		return ready; //return ready
	}
}
