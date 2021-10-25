/*
	Tarpan Patel
	CIS252
	Project 4
	5/8/19
 */

public class BSTNode<T>
{
	private BSTNode<T> left, right, prev;
	private T info;

	public void setLeft(BSTNode<T> left)
	{
		this.left = left;
	}

	public void setRight(BSTNode<T> right)
	{
		this.right = right;
	}

	public void setPrev(BSTNode<T> prev)
	{
		this.prev = prev;
	}

	public void setInfo(T info)
	{
		this.info = info;
	}

	public BSTNode<T> getLeft()
	{
		return left;
	}

	public BSTNode<T> getRight()
	{
		return right;
	}

	public BSTNode<T> getPrev()
	{
		return prev;
	}

	public T getInfo()
	{
		return info;
	}
}
