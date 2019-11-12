package TDALista;

import java.util.Iterator;

import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;

/**
 * Implementa una Lista doblemente enlazada con sentinelas.
 * @author Maria Luz Cabral y Gonzalo Perez.
 *
 */
public class ListaDobleEnlace<E> implements PositionList<E> 
{
	protected int size;
	protected DNodo<E> header;
	protected DNodo<E> tailer;
	
	/**
	 * Crea una lista vacia.
	 */
	public ListaDobleEnlace()
	{
		size = 0;
		header = new DNodo<E>();
		tailer = new DNodo<E>();
		header.setNext(tailer);
		tailer.setPrev(header);
	}

	public int size() 
	{
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Position<E> first() throws EmptyListException 
	{
		if(isEmpty())
			throw new EmptyListException("Lista Vacia");
		return header.getNext();
	}

	public Position<E> last() throws EmptyListException 
	{
		if(isEmpty())
			throw new EmptyListException("Lista Vacia");
		return tailer.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException 
	{
		DNodo<E> pos = checkPosition(p);
		return pos.getNext();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException
	{
		DNodo<E> pos = checkPosition(p);
		return pos.getPrev();
	}

	public void addFirst(E element) 
	{
		DNodo<E> nuevo = new DNodo<E>(element,header.getNext(),header);
		header.getNext().setPrev(nuevo);
		header.setNext(nuevo);
		size++;
	}

	public void addLast(E element) 
	{
		DNodo<E> nuevo = new DNodo<E>(element, tailer,tailer.getPrev());
		tailer.getPrev().setNext(nuevo);
		tailer.setPrev(nuevo);
		size++;
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException 
	{
		DNodo<E> pos = checkPosition(p);
		DNodo<E> next = pos.getNext();
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setPrev(pos);
		nuevo.setNext(next);
		size++;
		}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException 
	{
		DNodo<E> pos = checkPosition(p);
		DNodo<E> prev = pos.getPrev();
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setPrev(prev);
		nuevo.setNext(pos);
		size++;
	}

	public E remove(Position<E> p) throws InvalidPositionException 
	{
		DNodo<E> pos = checkPosition(p);
		if(isEmpty())
			throw new InvalidPositionException("Lista Vacia");
		else
		{
			boolean encontre = false;
			DNodo<E> aux = header.getNext();
			while(aux != tailer && !encontre)
			{
				if(aux.equals(pos))
					encontre = true;
				else
					aux = aux.getNext();
			}
			if(!encontre)
				throw new InvalidPositionException("La posicion no pertenece a la lista");
			else
			{
				E toRet = aux.element();
				aux.getPrev().setNext(aux.getNext());
				aux.getNext().setPrev(aux.getPrev());
				aux.setNext(null);
				aux.setNext(null);
				size--;
				return toRet;
				
			}
		}
	}

	public E set(Position<E> p, E element) throws InvalidPositionException 
	{
		DNodo<E> pos = checkPosition(p);
		if(isEmpty())
			throw new InvalidPositionException("Lista Vacia");
		else
		{
			boolean encontre = false;
			DNodo<E> aux = header.getNext();
			while(aux != tailer && !encontre)
			{
				if(aux.equals(pos))
					encontre = true;
				else
					aux = aux.getNext();
			}
			if(!encontre)
				throw new InvalidPositionException("La posicion no pertenece a la lista");
			else
			{
				E toRet = aux.element();
				aux.setElement(element);
				return toRet;
			}
		}
	}

	public Iterator<E> iterator() 
	{
		return new ElementIterator<E>(this);
	}

	public Iterable<Position<E>> positions() 
	{
		PositionList<Position<E>> toreturn = new ListaDobleEnlace<Position<E>>();
		if (!isEmpty()) {
			try
			{
				Position<E> aux = first();
				while (aux != last())
				{
					toreturn.addLast(aux);
					aux = next(aux);
				}
				toreturn.addLast(aux);
			}
			catch (InvalidPositionException|BoundaryViolationException|EmptyListException e)
			{
				e.printStackTrace();
			}
		}
		return toreturn;
	}

	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException
	{
		if(p == null|| p == header || p == tailer)
			throw new InvalidPositionException("Posicion Invalida");
		try
		{
		return (DNodo<E>)p;
		}
		catch (ClassCastException e)
		{
			throw new InvalidPositionException("Posicion Invalida");
		}
	}
}
