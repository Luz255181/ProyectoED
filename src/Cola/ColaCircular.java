package Cola;
import Exception.*;

public class ColaCircular<E> implements Queue<E>
{
	protected int size;
	protected int head;
	protected int tail;
	protected E[] arreglo;
	
	public ColaCircular()
	{
		size = 0;
		head = 0;
		tail = 0;
		arreglo = (E[]) new Object[100];
	}
	
	public int size() 
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	public E front() throws EmptyQueueException 
	{
		if(size == 0)
			throw new EmptyQueueException("Cola Vacia");
		return arreglo[head];
	}

	public void enqueue(E element) 
	{
		
			if(head == tail && !isEmpty())
			{
				resize();
			}
			arreglo[tail] = element;
			size++;
			tail = (tail + 1)% arreglo.length;
	}
	
	private void resize()
	{
		E[] aux = (E[]) new Object[size];
		for(int i = 0; i<arreglo.length; i++)
		{
			aux[i] = arreglo[head];
			head = (head+1)%arreglo.length;
		}
		arreglo = (E[]) new Object[size*2];
		for(int i = 0; i<aux.length; i++)
		{
			arreglo[i] = aux[i];
		}
		head = 0;
		tail = size;
	}

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
