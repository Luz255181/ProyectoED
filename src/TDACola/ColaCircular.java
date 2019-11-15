package TDACola;
import Auxiliar.*;

/**
 * Clase que implementa una cola con arreglo circular.
 * @author Maria Luz Cabral & Gonzalo Perez.
 *
 */
public class ColaCircular<E> implements Queue<E>
{
	protected int size;
	protected int head;
	protected int tail;
	protected E[] arreglo;
	
	/**
	 * Crea una cola vacia.
	 */
	public ColaCircular()
	{
		size = 0;
		head = 0;
		tail = 0;
		arreglo = (E[]) new Object[100];
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
	public E front() throws EmptyQueueException 
	{
		if(size == 0)
			throw new EmptyQueueException("Cola Vacia");
		return arreglo[head];
	}
	@Override
	public void enqueue(E elem) {
		int cantOcupados=size;
		if (size==(arreglo.length-1)) {
			E[] aux=(E[])new Object[arreglo.length*2];
			for (int i=0; i<cantOcupados; i++) {
				aux[i]=arreglo[head];
				head=(head+1)%arreglo.length;
			}
			tail=cantOcupados;
			head=0;
			arreglo=aux;
		}
		arreglo[tail]=elem;
		tail=(tail+1)%arreglo.length;
		size++;
	  }
	@Override
	public E dequeue() throws EmptyQueueException 
	{
		if(size == 0)
			throw new EmptyQueueException("Cola Vacia");
		E aux = arreglo[head];
		head = (head + 1)% arreglo.length;
		size--;
		return aux;
	}
}