/*
Tarpan Patel
Project 2
CIS 252
03/03/2019

This LinkedStack takes generic object. LinkedStack implements StackInterface
 */
public class LinkedStack<T> implements StackInterface<T>
{
	LinkedNode<T> top = null;
	private int deckSize = 0;

	public boolean push(T data)
	{
		LinkedNode<T> current = new LinkedNode<>(data);
		current.setNext(top);
		top = current;

		deckSize++;

		return true;
	}

	public void pop()
	{
		top = top.getNext();
		deckSize--;
	}

	public T peek()
	{
		return top.getData();
	}

	public void clear()
	{
		top = null;
	}

	public int size()
	{
		return deckSize;
	}

}
