package TDALista;
import java.util.*;
import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;

/**
 * Clase que representa a un elemento iterador.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 * @param <E> Es el tipo de los elementos dentro de la posiciones a iterar. 
 */
public class ElementIterator<E> implements Iterator<E> {
  protected PositionList<E> lista;
  protected Position<E> cursor;
  //Constructor
  /**
   * Crea un nuevo elemento iterador inicializando el atributo lista con la lista pasada por 
   * parámetro e inicializando el cursor con el primer elemento posible.
   * @param l Es la lista utilizada para inicializar el atributo
   */
  public ElementIterator(PositionList<E> l) {
	lista=l;
	if (!lista.isEmpty())
	   try {
	        cursor=lista.first();}
	   catch (EmptyListException e) {
		     System.out.println(e.getMessage());
	   }
	else cursor=null;
  }
  /**
   * Consulta si quedan elementos por visitar en la secuencia. 
   */
  public boolean hasNext() {
	return (cursor!=null);
  }
  /**
   * Retorna el proximo elemento a visitar en la secuencia.
   */
  public E next() throws NoSuchElementException {
    if (cursor==null)
       throw new NoSuchElementException("Error: No hay siguiente.");
    try {
        E toReturn=cursor.element();
        cursor=(cursor==lista.last()) ? null : lista.next(cursor);
        return toReturn;}
    catch (EmptyListException e) {
    	  System.out.println(e.getMessage()); return null;
    }
    catch (InvalidPositionException | BoundaryViolationException e) {
    	  System.out.println(e.getMessage()); return null;
    }
  }
}