/*
	Tarpan Patel
	CIS252
	Project 4
	5/8/19

	This class creates and manages the tree made up of nodes.
 */

import java.io.*;
import java.util.Scanner;

public class BSTree<T>
{
	private String question = ""; //Declare String question
	private BSTNode root, current; //Declare root and current of BSTNode
	private boolean targetFound = false; //delcare and initalize targetFound to false

	/*
		readFromFile reads the question from the file and looks for the root of the file. It will throw execption if file not found
		if it has root, it calls the buildTree to store questions in the tree.
	 */

	public BSTNode readFromFile() throws IOException
	{
		Scanner readFile = new Scanner(new File("./BSTBuildFile.txt")); //open file and set readFile buffer at the beginning of the file

		if (readFile.hasNextLine()) //check to see if EOF
		{
			question = readFile.nextLine(); //get question from file and store it in question
			root = new BSTNode(); //Intialize root
			root.setInfo(question.substring(0, question.length() - 2)); //remove the delimiter from question and store in root node

			if (question.charAt(question.length() - 1) != '-') //check to see if is not a leaf
			{
				buildTree(root, readFile); //call buildTree and pass root and readFile to create rest of the tree
			}
			readFile.close(); //close file after EOF
			return root; //return the first question
		}
		else
		{
			System.out.println("Root not found or file is empty");
			return null;
		}

	}

	/*
		buildTree method creates the rest of the tree.
	 */

	private void buildTree(BSTNode current, Scanner readFile) throws IOException
	{
		if (readFile.hasNextLine())
		{
			question = readFile.nextLine(); //get the next question from file
			current.setLeft(new BSTNode()); //create currents left node
			current.getLeft().setInfo(question.substring(0, question.length() - 2)); //remove the delimiter from question and store in currents left node
			current.getLeft().setPrev(current);

			if (question.charAt(question.length() - 1) != '-') //check to see if is not a leaf
			{
				buildTree(current.getLeft(), readFile); //if not a leaf,call function itself and set the currents left with the next question in the file
			}
		}
		if (readFile.hasNextLine())
		{
			question = readFile.nextLine();
			current.setRight(new BSTNode()); //create current right node
			current.getRight().setInfo(question.substring(0, question.length() - 2)); //remove the delimiter from question and store in current right node
			current.getRight().setPrev(current);

			if (question.charAt(question.length() - 1) != '-') //check to see if is not a leaf
			{
				buildTree(current.getRight(), readFile); //if not a leaf,call function itself and set the currents right with the next question in the file
			}
		}
	}

	/*
		isALeaf return boolean if the given node has children. Method used to see if more questions can be asked
	 */
	public boolean isALeaf(BSTNode guessing)
	{
		if (guessing.getRight() == null && guessing.getLeft() == null) //check if guessing node question has childern
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/*
		GetLeft and getRight methods has node as parameters which points to the current question the user is on.
		Returns the left or right question of current question based on users answer
	 */

	public BSTNode getLeftChild(BSTNode node)
	{
		return node.getLeft();
	}

	public BSTNode getRightChild(BSTNode node)
	{
		return node.getRight();
	}

	/*
		printTree calls printPreOrder method as root as parameter
	 */
	private void printTree()
	{
		printPreOrder(root);
	}

	/*
		printPreOrder method prints each question in the tree starting from the root to left than right.
	 */
	private void printPreOrder(BSTNode root)
	{
		if (root != null) //if there is a trer
		{
			if (root.getRight() == null && root.getLeft() == null) //check to see if root has left and right questions
			{
				System.out.println(root.getInfo()); //print current question
			}
			else
			{
				System.out.println(root.getInfo()); //print root question
				printPreOrder(root.getLeft()); //call printPreOrder method as pass left side of question and repeat until isLeaf
				printPreOrder(root.getRight()); //call printPreOrder method as pass right side of question and repeat until isLeaf
			}
		}
	}

	/*
		newQuestion adds the new answer and question that is given by the user
	 */
	public void newQuestion(String userAnswer, String userQuestion, BSTNode current)
	{
		T temp = (T) current.getInfo(); //get the current question and store it in temp
		current.setInfo(userQuestion); //set the current nodes info to userQuestion

		current.setLeft(new BSTNode()); //get the left of currents question and create new node,
		current.getLeft().setInfo(userAnswer); //set the currents left question to userAnswer

		current.setRight(new BSTNode()); //get the right of currents question and create new node
		current.getRight().setInfo(temp); //set the currents right side question to temp

		saveToFile(); //call saveToFile to save the tree to the file
		printTree(); //print the tree
	}

	/*
	SaveToFile method saves the end of the game results back into the file
	 */
	private void saveToFile()
	{
		try
		{
			PrintWriter pt = new PrintWriter("./BSTBuildFile.txt"); //set pt buffer to the beginning of the file
			saveToFile(root, pt); //call saveToFile with root question and file pointer as arguments
			pt.close(); //close file
		} catch (Exception e)
		{
			System.out.println("Something went wrong and was not able to save to file");
		}
	}

	/*
		saveToFile has root and file pointer as parameters. Which are used to save to teh file
	 */
	private void saveToFile(BSTNode root, PrintWriter pw)
	{
		if (root.getRight() == null && root.getLeft() == null) //if root doesn't have any children questions
		{
			pw.println(root.getInfo() + " -"); //print the question to file with dash at the end
		}
		else //else print the rest of the tree
		{
			pw.println(root.getInfo() + " +"); //print the question to file with plus sign at the end
			saveToFile(root.getLeft(), pw); //call saveToFile but with roots left question and repeat until leaf is reached
			saveToFile(root.getRight(), pw); //call saveToFile but with roots right question and repeat until leaf is reached
		}
	}

	/*
		pathToTarget method prints the path from the users answer to the root
	 */
	public void pathToTarget()
	{
		System.out.println("------------------");
		while(current != root.getPrev()) //while current is not null
		{
			if(current != root) //if current is not root
			{
				System.out.print(current.getInfo() + " --> "); //print the question followed by arrow
			}
			else
			{
				System.out.println(current.getInfo()); //print question
			}

			current = current.getPrev(); //set so the current points to the previous node
		}
	}

	/*
		searchTree searches through the whole tree to look for the target starting from the root
	 */
	public boolean searchTree(T target, BSTNode current)
	{
		if (current == null) //if tree is empty
		{
			return targetFound; //return false
		}
		else if ((current.getInfo().toString()).compareTo(target.toString()) == 0) //if the current nodes info matches the target
		{
			this.current = current; //save the location of the question to the current
			targetFound = true; //target found so set targetFound to true
			return targetFound; //return true
		}
		else //else search the left and right of the tree for the target
		{
			 searchTree(target, current.getLeft()); //search the left side of the tree
			return searchTree(target, current.getRight()); //search the right side of the tree
		}
	}
}
