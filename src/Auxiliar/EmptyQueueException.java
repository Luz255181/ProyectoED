package Auxiliar;

/**
 * Clase EmptyQueueException que extiende de Exception.
 * Modela la excepción que se produce ante una cola vacía.
 * @author Luz Cabral y Gonzalo Perez
 * 
 */
public class EmptyQueueException extends Exception {
  /**
   * Inicializa una excepción por cola vacía describiendo su origen.
   * @param msj Mensaje que describe el origen del disparo la excepción.
   */
  public EmptyQueueException(String msj) {
	super(msj);
  }
}