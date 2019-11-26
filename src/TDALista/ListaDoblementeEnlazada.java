package TDALista;
import java.util.Iterator;
import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;

/**
 * Clase que implementa una lista doblemente enlazada con celdas de encabezamiento.
 * @author Luz Cabral y Gonzalo Perez
 *
 * @param <E> Es el tipo de los elementos de la lista.
 */
public class ListaDoblementeEnlazada<E> implements PositionList<E> {
  protected int longitud;
  protected DNodo<E> header, trailer; //Centinelas o celdas de encabezamiento

  /**
   * Crea una lista doblemente enlazada inicialmente vacía con celdas de encabezamiento e 
   * inicializa sus atributos.
   */
  public ListaDoblementeEnlazada() {
	longitud=0;
	trailer=new DNodo<E>(null, null, null);
	header=new DNodo<E>(null, trailer, null);
	trailer.setAnterior(header);
  }
  //Comandos
  @Override
  public void addFirst(E elem) {
	DNodo<E> nuevoNodo=new DNodo<E>(header, header.getSiguiente(), elem);
	header.getSiguiente().setAnterior(nuevoNodo);
	header.setSiguiente(nuevoNodo);
	longitud++;
  }
  @Override
  public void addLast(E elem) {
	DNodo<E> nuevoNodo=new DNodo<E>(trailer.getAnterior(), trailer, elem);
	trailer.getAnterior().setSiguiente(nuevoNodo);
	trailer.setAnterior(nuevoNodo);
	longitud++;
  }
  @Override
  public void addAfter(Position<E> pos, E elem) throws InvalidPositionException {
	if (longitud==0)
		throw new InvalidPositionException("La lista está vacía.");
	DNodo<E> ant=checkPosition(pos);
	DNodo<E> sig=ant.getSiguiente();
	DNodo<E> nuevo=new DNodo<E>(ant, sig, elem);
    sig.setAnterior(nuevo);
    ant.setSiguiente(nuevo);
    longitud++;
  }
  @Override
  public void addBefore(Position<E> pos, E elem) throws InvalidPositionException {
	if (longitud==0)
		throw new InvalidPositionException("La lista está vacía.");
	DNodo<E> sig=checkPosition(pos);
	DNodo<E> ant=sig.getAnterior();
	DNodo<E> nuevoNodo=new DNodo<E>(ant, sig, elem);
	ant.setSiguiente(nuevoNodo);
	sig.setAnterior(nuevoNodo);
	longitud++;
  }
  //Consultas
  @Override
  public Position<E> first() throws EmptyListException {
	if (longitud==0)
	   throw new EmptyListException("La lista está vacía.");
	return header.getSiguiente();
  }
  @Override
  public Position<E> last() throws EmptyListException {
	if (longitud==0)
	   throw new EmptyListException("La lista está vacía.");
	return trailer.getAnterior();
  }
  @Override
  public Position<E> prev(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
	if (longitud==0)
		throw new InvalidPositionException("La lista está vacía.");
	DNodo<E> n=checkPosition(pos);
	DNodo<E> ant=n.getAnterior();
	if (ant==header)
	   throw new BoundaryViolationException("No se puede retroceder más allá del principio de la lista.");
	return ant;
  }
  @Override
  public Position<E> next(Position<E> pos) throws InvalidPositionException, BoundaryViolationException {
	if (longitud==0)
		throw new InvalidPositionException("La lista está vacía.");
	DNodo<E> n=checkPosition(pos);
	DNodo<E> sig=n.getSiguiente();
	if (sig==trailer)
	   throw new BoundaryViolationException("No se puede avanzar más allá del fin de la lista.");
	return sig;
  }
  @Override
  public E set(Position<E> pos, E elem) throws InvalidPositionException {
	if (longitud==0)
		throw new InvalidPositionException("La lista está vacía.");
	DNodo<E> n=checkPosition(pos);
	E elemAnterior=n.element();
	n.setElemento(elem);
	return elemAnterior;
  }
  @Override
  public E remove(Position<E> pos) throws InvalidPositionException {
	if (longitud==0)
		throw new InvalidPositionException("La lista está vacía.");
	DNodo<E> n=checkPosition(pos);
	DNodo<E> nPrev=n.getAnterior();
	DNodo<E> nSig=n.getSiguiente();
	nPrev.setSiguiente(nSig);
	nSig.setAnterior(nPrev);
	E elem=n.element();
	n.setSiguiente(null);
	n.setAnterior(null);
	n.setElemento(null);
	longitud--;
	return elem;
  }
  @Override
  public int size() {
	return longitud;
  }
  @Override
  public boolean isEmpty() {
	return (longitud==0);
  }
  @Override
  public Iterator<E> iterator() {
	return (new ElementIterator<E>(this));
  }
  @Override
  public Iterable<Position<E>> positions() {
	PositionList<Position<E>> lista=new ListaDoblementeEnlazada<Position<E>>();
	if (longitud!=0) {
	   DNodo<E> pos=header.getSiguiente();
	   while (pos!=trailer) {
	         lista.addLast(pos);
	         pos=pos.getSiguiente();
	   }
	}
	return lista;
  }
  /**
   * Comprueba si la posición es valida para esta lista y la convierte en nodo de ser valida.
   * @param pos Es la posición a validar
   * @return Retorna la posición convertida en nodo.
   * @throws InvalidPositionException Si la posición es invalida.
   */
  private DNodo<E> checkPosition(Position<E> pos) throws InvalidPositionException {
	if (pos==null)
		throw new InvalidPositionException("La posición es invalida");
	try {
		DNodo<E> n=(DNodo<E>)pos;
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
}