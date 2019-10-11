package TDAPila;
import Auxiliar.EmptyStackException;

/**
 * Clase que implementa una pila con enlaces.
 * @author Maria Luz Cabral y Gonzalo Perez.
 *
 */
public class PilaEnlazada<E> implements Stack<E>
{
	protected Nodo<E> top;
	protected int size;
	
	/**
	 * Crea una pila vacia.
	 */
	public PilaEnlazada()
	{
		top = new Nodo<E>();
		size = 0;
	}

	public int size() 
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	public E top() throws EmptyStackException 
	{
		if (size == 0)
			throw new EmptyStackException("Pila Vacia");
		return top.getElement();
	}

	public void push(E element) 
	{
		Nodo<E> nuevo = new Nodo<E>(element,top);
		top = nuevo;
		size++;
	}

	public E pop() throws EmptyStackException 
	{
		if(size == 0)
			throw new EmptyStackException("Pila vacia");
		size--;
		E aux = top.getElement();
		top = top.getNext();
		return aux;
	}

}
