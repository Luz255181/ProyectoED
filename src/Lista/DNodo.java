package Lista;

public class DNodo<E> implements Position<E>
{
	protected E data;
	protected DNodo<E> next;
	protected DNodo<E> prev;
	
	public DNodo()
	{
		data = null;
		next = null;
		prev = null;
	}
	
	public DNodo(E dato)
	{
		data = dato;
		next = null;
		prev = null;
	}

	public DNodo(E dato, DNodo<E> sig, DNodo<E> ant)
	{
		data = dato;
		next = sig;
		prev = ant;
	}
	
	public E element() 
	{
		return data;
	}

	public DNodo<E> getNext()
	{
		return next;
	}
	
	public DNodo<E> getPrev()
	{
		return prev;
	}
	
	public void setNext(DNodo<E> sig)
	{
		next = sig;
	}
	
	public void setElement(E dato)
	{
		data = dato;
	}
	
	public void setPrev (DNodo<E> ant)
	{
		prev = ant;
	}
}
