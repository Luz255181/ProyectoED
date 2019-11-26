package Auxiliar;

/**
 * Clase EmptyListException que extiende de la clase Exception.
 * Modela la excepci�n que se produce ante una lista vac�a.
 * @author Luz Cabral y Gonzalo Perez
 *
 */
public class EmptyListException extends Exception {
  /**
   * Inicializa una excepci�n por lista vac�a describiendo su origen.
   * @param msj Mensaje que describe el origen del disparo de la excepci�n.
   */
  public EmptyListException(String msj) {
	super(msj);
  }
}