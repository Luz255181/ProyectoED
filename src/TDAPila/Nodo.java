package TDAPila;

/**
 * Clase que implementa un nodo de una pila.
 * @author Maria Luz Cabral y Gonzalo Perez.
 *
 */
public class Nodo<E> 
{
	protected E dato; //dato del nodo.
	protected Nodo<E> sig; //nodo siguiente.
	
	/*
	 * Crea un nodo vacio.
	 */
	public Nodo() 
	{
		dato = null;
		sig = null;
	}
	
	/*
	 * Crea un nodo con dato.
	 * @param data Dato del nodo.
	 */
	public Nodo(E data)
	{
		dato = data;
		sig = null;
	}
	
	/*
	 * Crea un nodo con dato y siguiente.
	 * @param data Dato del Nodo.
	 * @param siguiente Nodo siguiente.
	 */
	public Nodo(E data, Nodo<E> siguiente)
	{
		dato = data;
		sig = siguiente;
		
	}
	
	/*
	 * Devulve el dato del Nodo.
	 * @return dato del nodo.
	 */
	public E getElement()
	{
		return dato;
	}
	
	/*
	 * Setea el elemento.
	 * @param data Dato del Nodo'
	 */
	public void setElement(E data)
	{
		dato = data;
	}
	
	/*
	 * Devuelve el nodo siguiente.
	 * @return Nodo siguiente.
	 */
	public Nodo<E> getNext()
	{
		return sig;
	}
	
	/*
	 * Setea el nodo siguiente.
	 * @param siguiente Nodo siguiente.
	 */
	public void setNext(Nodo<E> siguiente)
	{
		sig = siguiente;
	}
}
