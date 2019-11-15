package TDALista;

/**
 * Clase que representa a un nodo que se encuentra dentro de una posición.
 * @author Gonzalo  Perez
 *
 * @param <E> Es el tipo del elemento asociado al nodo.
 */
public class Nodo<E> implements Position<E> {
  private E elemento;
  private Nodo<E> anterior, siguiente;
  //Constructores
  /**
   * Crea un nuevo nodo con el elemento pasado por parámetro y el nodo siguiente pasado por parámetro
   * @param elem Es el elemento asociado al nodo.
   * @param sig Es el nodo siguiente del nodo referenciado.
   */
  public Nodo(E elem, Nodo<E> sig) {
    elemento=elem;
    siguiente=sig;
  }
  /**
   * Crea un nuevo nodo con el elemento pasado por parámetro y con los nodos anterior y 
   * siguiente pasados por parámetro.
   * @param ant Es el nodo anterior del nodo referenciado.
   * @param sig Es el nodo siguiente del nodo referenciado.
   * @param elem Es el elemento asociado al nodo.
   */
  public Nodo(Nodo<E>ant, Nodo<E> sig, E elem) {
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
  public void setSiguiente(Nodo<E> sig) {
	siguiente=sig;
  }
  /**
   * Setea el nodo anterior del nodo actual.
   * @param ant Es el nodo anterior a setear del nodo actual.
   */
  public void setAnterior(Nodo<E> ant) {
	anterior=ant;
  }
  //Consultas
  /**
   * Consulta el nodo siguiente del nodo actual.
   * @return Retorna el nodo siguiente del nodo actual.
   */
  public Nodo<E> getSiguiente() {
    return siguiente;
  }
  /**
   * Consulta el nodo anterior del nodo actual.
   * @return Retorna el nodo anterior del nodo actual.
   */
  public Nodo<E> getAnterior() {
	return anterior;
  }
  @Override
  public E element() {
	return elemento;
  }
}