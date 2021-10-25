/*
Tarpan Patel
Project 2
CIS 252
03/03/2019
 */

public class LinkedNode<T>
{
	private T data;
	private LinkedNode next;

	public LinkedNode(T data)
	{
		this.data = data;
		next = null;
	}

	public LinkedNode(T data, LinkedNode next)
	{
		this.data = data;
		this.next = next;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public LinkedNode getNext()
	{
		return next;
	}

	public void setNext(LinkedNode next)
	{
		this.next = next;
	}
}
