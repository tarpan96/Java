/*
	Tarpan Patel
	CIS252
	Project 4
	5/8/19

	BSTMain creates and starts twentyQuestion game.
 */
import java.io.IOException;

public class BSTMain
{
	public static void main(String[] args) throws IOException
	{
		//Create TwentyQuestions game
		TwentyQuestions questions = new TwentyQuestions();
		//Start the game
		questions.play();
	}
}
