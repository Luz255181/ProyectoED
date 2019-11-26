package Auxiliar;

/**
 * Clase EmptyListException que extiende de la clase Exception.
 * Modela la excepción que se produce ante una lista vacía.
 * @author Luz Cabral y Gonzalo Perez
 *
 */
public class EmptyListException extends Exception {
  /**
   * Inicializa una excepción por lista vacía describiendo su origen.
   * @param msj Mensaje que describe el origen del disparo de la excepción.
   */
  public EmptyListException(String msj) {
	super(msj);
  }
}