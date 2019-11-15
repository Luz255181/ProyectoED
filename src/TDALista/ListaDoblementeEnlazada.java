package TDALista;
import java.util.Iterator;
import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;

/**
 * Clase que implementa la interfaz PositionList.
 * Modela uns lista doblemente enlazada con nodos centinelas.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 * @param <E> Es el tipo de los elementos dentro de la lista.
 */
public class ListaDoblementeEnlazada<E> implements PositionList<E> {
  protected int numElem;
  protected Nodo<E> header, trailer; //Centinelas o celdas de encabezamiento
  
  //Constructor
  /**
   * Crea una lista doblemente enlazada vacía que inicialmente contiene a los nodos centinelas.
   */
  public ListaDoblementeEnlazada() {
	numElem=0;
	trailer=new Nodo<E>(null, null, null);
	header=new Nodo<E>(null, trailer, null);
	trailer.setAnterior(header);
  }
  //Comandos
  @Override
  public void addFirst(E elem) {
	Nodo<E> nuevoNodo=new Nodo<E>(header, header.getSiguiente(), elem);
	header.getSiguiente().setAnterior(nuevoNodo);
	header.setSiguiente(nuevoNodo);
	numElem++;
  }
  @Override
  public void addLast(E elem) {
	Nodo<E> nuevoNodo=new Nodo<E>(trailer.getAnterior(), trailer, elem);
	trailer.getAnterior().setSiguiente(nuevoNodo);
	trailer.setAnterior(nuevoNodo);
	numElem++;
  }
  @Override
  public void addAfter(Position<E> pos, E elem) throws InvalidPositionException {
	Nodo<E> ant=checkPosition(pos);
	Nodo<E> sig=ant.getSiguiente();
	Nodo<E> nuevo=new Nodo<E>(ant, sig, elem);
    sig.setAnterior(nuevo);
    ant.setSiguiente(nuevo);
    numElem++;
  }
  @Override
  public void addBefore(Position<E> pos, E elem) throws InvalidPositionException {
	Nodo<E> sig=checkPosition(pos);
	Nodo<E> ant=sig.getAnterior();
	Nodo<E> nuevoNodo=new Nodo<E>(ant, sig, elem);
	ant.setSiguiente(nuevoNodo);
	sig.setAnterior(nuevoNodo);
	numElem++;
  }
  //Consultas
  @Override
  public Position<E> first() throws EmptyListException {
	if (numElem==0)
	   throw new EmptyListException("La lista está vacía.");
	return header.getSiguiente();
  }
  @Override
  public Position<E> last() throws EmptyListException {
	if (numElem==0)
	   throw new EmptyListException("La lista está vacía.");
	return trailer.getAnterior();
  }
  @Override
  public Position<E> prev(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
	Nodo<E> n=checkPosition(pos);
	Nodo<E> ant=n.getAnterior();
	if (ant==header)
	   throw new BoundaryViolationException("No se puede retroceder más allá del principio de la lista.");
	return ant;
  }
  @Override
  public Position<E> next(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
	Nodo<E> n=checkPosition(pos);
	Nodo<E> sig=n.getSiguiente();
	if (sig==trailer)
	   throw new BoundaryViolationException("No se puede avanzar más allá del fin de la lista.");
	return sig;
  }
  @Override
  public E set(Position<E> pos, E elem) throws InvalidPositionException {
	if (numElem==0)
		throw new InvalidPositionException("La lista está vacía.");
	Nodo<E> n=checkPosition(pos);
	E elemAnterior=n.element();
	n.setElemento(elem);
	return elemAnterior;
  }
  @Override
  public E remove(Position<E> pos) throws InvalidPositionException {
	if (numElem==0)
		throw new InvalidPositionException("La lista está vacía.");
	Nodo<E> n=checkPosition(pos);
	Nodo<E> nPrev=n.getAnterior();
	Nodo<E> nSig=n.getSiguiente();
	nPrev.setSiguiente(nSig);
	nSig.setAnterior(nPrev);
	E nElem=n.element();
	n.setSiguiente(null);
	n.setAnterior(null);
	n.setElemento(null);
	numElem--;
	return nElem;
  }
  @Override
  public int size() {
	return numElem;
  }
  @Override
  public boolean isEmpty() {
	return (numElem==0);
  }
  /**
   * Comprueba si la posición es válida para está lista y la convierte en Nodo de ser valida.
   * @param pos Es la posición a validar
   * @return Retorna la posición convertida en Nodo.
   * @throws InvalidPositionException Si la posición es invalida.
   */
  private Nodo<E> checkPosition(Position<E> pos) throws InvalidPositionException {
	if (pos==null)
		throw new InvalidPositionException("La posición es invalida");
	try {
		Nodo<E> n=(Nodo<E>)pos;
		if (n.element()==null || n.getAnterior()==null || n.getSiguiente()==null)
			throw new InvalidPositionException("La posición es invalida");
		if (n==header || n==trailer)
			throw new InvalidPositionException("La posición es invalida.");
	    return n;
	}
	catch (ClassCastException e) {
		throw new InvalidPositionException("La posición es invalida.");
	}
  }
  @Override
  public Iterator<E> iterator() {
	return (new ElementIterator<E>(this));
  }
  @Override
  public Iterable<Position<E>> positions() {
	PositionList<Position<E>> lista=new ListaDoblementeEnlazada<Position<E>>();
	if (numElem!=0) {
	   Nodo<E> pos=header.getSiguiente();
	   while (pos!=trailer) {
	         lista.addLast(pos);
	         pos=pos.getSiguiente();
	   }
	}
	return lista;
  }
}