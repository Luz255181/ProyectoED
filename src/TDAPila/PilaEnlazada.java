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
	 * Crea una pila vacía, el nodo head se inicializa null y el tamaño se inicializa en 0.
	 */
	public PilaEnlazada()
	{
		top = null;
		size = 0;
	}
	@Override
	public int size() 
	{
		return size;
	}
	@Override
	public boolean isEmpty() 
	{
		return size == 0;
	}
	@Override
	public E top() throws EmptyStackException 
	{
		if (size == 0)
			throw new EmptyStackException("Pila Vacia");
		return top.getElemento();
	}
	@Override
	public void push(E element) 
	{
		Nodo<E> nuevo = new Nodo<E>(element,top);
		top = nuevo;
		size++;
	}
	@Override
	public E pop() throws EmptyStackException 
	{
		if(size == 0)
			throw new EmptyStackException("Pila vacia");
		size--;
		E aux = top.getElemento();
		top = top.getSiguiente();
		return aux;
	}
}