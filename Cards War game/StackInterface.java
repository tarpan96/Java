public interface StackInterface<T>
{
	public boolean push(T data);
	public void pop();
	public T peek();
	public void clear();
	public int size();


}