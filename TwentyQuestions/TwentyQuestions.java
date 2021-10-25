/*
	Tarpan Patel
	CIS252
	Project 4
	5/8/19

	This class builds a tree that is used to get questions from.
 */
import java.io.IOException;
import java.util.Scanner;

public class TwentyQuestions<T>
{
	Scanner scnr = new Scanner(System.in);

	private BSTNode<T> current, root; //Declare current and root of BSTNode
	private BSTree tree; //Declare tree of BSTree
	private char userChoice; //Declare userChoice to store answer
	private String userAnswer, userQuestion; ////Declare userAnswer and userQuestion to create new question
	private boolean isLeaf = false; ////Declare and initialize isLeaf to check if current has children

	public TwentyQuestions() throws IOException
	{
		tree = new BSTree(); //initalize to create BSTree
		root = tree.readFromFile(); //Call readFromFile function and get the root of the tree
		current = root; //set current to root
	}

	public void play()
	{
		System.out.println("lets play 20 questions");

		do
		{
			System.out.println("Is it " + current.getInfo() + "?"); //print the question from the tree
			userChoice = scnr.next().charAt(0); //prompt user to enter their answer


			if(userChoice == 'y' || userChoice == 'Y') //if y is entered
			{
				if(!tree.isALeaf(current)) //check to see if there are more questions in tree based on the given answer
				{
					current = current.getRight(); //if yes, get the 'yes' question by getting question from the right
				}
				else
				{
					isLeaf = true; //else, we don't have more questions to ask, set isLeaf to true
				}
			}
			else if(userChoice == 'n' || userChoice == 'N') //check to see if there are more questions in tree based on the given answer
			{
				if(!tree.isALeaf(current))
				{
					current = current.getLeft(); //if tree has more question, get the 'no' question by getting question from the left
				}
				else
				{
					isLeaf = true; //else, we don't have more questions to ask, set isLeaf to true
				}
			}
		}while(isLeaf != true); //while the computer has questions to ask

		winnerIs(userChoice); //call winnerIs with serChoice pass through as argument
	}

	/*
		winnerIs method prints out the winner of the game. Searches if the answer is in the tree.
		If the answer is not found, it will added to the tree
	 */
	private void winnerIs(char userChoice)
	{
		if(userChoice == 'y') //The computer wins this time
		{
			System.out.println("I WIN THIS TIME");
		}
		else if(userChoice == 'n') //if answer is not found
		{
			System.out.println("What were you thinking of?"); //ask user of their guess
			scnr.nextLine();
			userAnswer = scnr.nextLine(); //store input in userAnswer

			boolean found = tree.searchTree(userAnswer, root); //call searchTree method to see if the users guess contains some there in the tree.

			if(!found) //if not found
			{
				System.out.println("How is it different from: " + current.getInfo()); //prompt user to enter in question to differentiate their guess
				userQuestion = scnr.nextLine();
				tree.newQuestion(userAnswer, userQuestion, current); //call newQuestion to add the users answer to the tree
			}
			else
			{
				//else the answer was found in the tree
				System.out.println("Here is a path to the answer. From answer to root");
				tree.pathToTarget(); //call pathToTarget method to print out the location of the answer
			}
		}
	}
}
