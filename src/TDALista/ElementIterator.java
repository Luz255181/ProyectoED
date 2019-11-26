package TDALista;
import java.util.Iterator;
import java.util.NoSuchElementException;
import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;

/**
 * Clase que representa a un elemento iterador.
 * @author Luz Cabral y Gonzalo Perez
 *
 * @param <E> Es el tipo de los elementos dentro de la posiciones a iterar. 
 */
public class ElementIterator<E> implements Iterator<E> {
  protected PositionList<E> lista;
  protected Position<E> cursor;

  /**
   * Crea un nuevo elemento iterador inicializando los atributos.
   * @param l Es la lista utilizada para inicializar el atributo.
   */
  public ElementIterator(PositionList<E> l) {
	lista=l;
	if (!lista.isEmpty())
	   try {
	        cursor=lista.first();
	   }
	   catch (EmptyListException e) {
		     System.out.println(e.getMessage());
	   }
	else cursor=null;
  }
  /**
   * Consulta si quedan elementos por visitar en la secuencia.
   *  @return Verdadero si quedan elementos por visitar, falso en caso contrario.
   */
  public boolean hasNext() {
	return (cursor!=null);
  }
  /**
   * Consulta y retorna el proximo elemento a visitar en la secuencia.
   * @return Retorna el proximo elemento de la secuencia.
   */
  public E next() throws NoSuchElementException {
    if (cursor==null)
       throw new NoSuchElementException("No hay siguiente elemento a visitar.");
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