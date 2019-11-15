package TDAPila;
/**
 * Clase que representa a un nodo.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 * @param <E>
 */
public class Nodo<E> {
  private E elemento;
  private Nodo<E> siguiente;
  
  //Constructor
  /**
   * Crea un nuevo nodo inicializando el elemento con el elemento pasado por parámetro y 
   * el nodo siguiente con el nodo pasado por parámetro.
   * @param elem Es el elemento asociado al nodo.
   * @param sig Es el nodo siguiente del nodo referenciado.
   */
  public Nodo(E elem, Nodo<E> sig) {
    elemento=elem;
    siguiente=sig;
  }
  /**
   * Crea un nuevo nodo inicilizando el elemento con el elemento pasado por parámetro y
   * el nodo siguiente con valor nulo.
   * @param elem Es el elemento asociado al nodo.
   */
  public Nodo(E elem) {
    this(elem, null);
  }
  //Comandos
  /**
   * Modifica el elemento que se encuentra dentro del nodo por el elemento pasado por parámetro
   * @param elem Es el nuevo elemento que va a estar dentro del nodo.
   */
  public void setElemento(E elem) {
    elemento=elem;
  }
  /**
   * Modifica la referencia del nodo siguiente por la referencia del nodo pasado por parámetro.
   * @param sig Es la referencia del nodo que va a ser el nuevo siguiente del nodo.
   */
  public void setSiguiente(Nodo<E> sig) {
	siguiente=sig;
  }
  //Consultas
  /**
   * Consulta el elemento que se encuentra dentro del nodo y lo retorna.
   * @return Retorna el elemento que se encuentra dentro del nodo.
   */
  public E getElemento() {
	return elemento;
  }
  /**
   * Consulta cual es el nodo siguiente del nodo actual
   * @return El nodo siguiente al actual.
   */
  public Nodo<E> getSiguiente() {
    return siguiente;
  }
}