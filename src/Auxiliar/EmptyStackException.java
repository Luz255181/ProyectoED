package Auxiliar;

/**
 * Clase EmptyStackException que extiende de la clase excepci�n.
 * Modela la excepci�n que se produce ante una pila vac�a.
 * @author Luz Cabral y Gonzalo Perez
 */
public class EmptyStackException extends Exception {
  /**
   * Inicializa una excepci�n por pila vac�a descrbiendo su origen.
   * @param msj Mensaje que describe el origen del disparo de la excepci�n.
   */
  public EmptyStackException(String msj) {
	super(msj);
  }
}