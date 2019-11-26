package TDALista;

/**
 * Clase que representa a un nodo que se encuentra dentro de una posición.
 * @author Luz Cabral y Gonzalo Perez
 *
 * @param <E> Es el tipo del elemento asociado al nodo.
 */
public class DNodo<E> implements Position<E> {
  private E elemento;
  private DNodo<E> anterior, siguiente;

  /**
   * Crea un nuevo nodo con el elemento pasado por parámetro y con los nodos anterior y 
   * siguiente pasados por parámetro.
   * @param ant Es el nodo anterior del nodo referenciado.
   * @param sig Es el nodo siguiente del nodo referenciado.
   * @param elem Es el elemento asociado al nodo.
   */
  public DNodo(DNodo<E>ant, DNodo<E> sig, E elem) {
	anterior=ant;
	siguiente=sig;
	elemento=elem;
  }
  //Comandos
  /**
   * Setea un nuevo elemento al nodo.
   * @param elem Es el nuevo elemento del nodo.
   */
  public void setElemento(E elem) {
    elemento=elem;
  }
  /**
   * Setea el nodo siguiente del nodo actual.
   * @param sig Es el nodo siguiente a setear del nodo actual.
   */
  public void setSiguiente(DNodo<E> sig) {
	siguiente=sig;
  }
  /**
   * Setea el nodo anterior del nodo actual.
   * @param ant Es el nodo anterior a setear del nodo actual.
   */
  public void setAnterior(DNodo<E> ant) {
	anterior=ant;
  }
  //Consultas
  /**
   * Consulta el nodo siguiente del nodo actual.
   * @return Retorna el nodo siguiente del nodo actual.
   */
  public DNodo<E> getSiguiente() {
    return siguiente;
  }
  /**
   * Consulta el nodo anterior del nodo actual.
   * @return Retorna el nodo anterior del nodo actual.
   */
  public DNodo<E> getAnterior() {
	return anterior;
  }
  @Override
  public E element() {
	return elemento;
  }
}