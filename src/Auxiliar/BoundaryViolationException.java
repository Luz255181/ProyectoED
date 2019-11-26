package Auxiliar;

/**
 * Clase BoundaryViolationException que extiende de la clase Exception.
 * Modela la excepción que se produce cuando se intenta avanzar o retroceder más allá de los
 * límites de la lista.
 * @author Luz Cabral y Gonzalo Perez
 * 
 */
public class BoundaryViolationException extends Exception {
  /**
   * Inicializa una excepción al intentar avanzar más allá de los límites de la lista y 
   * describe su origen.
   * @param msj Mensaje que describe el origen de la excepción.
   */
  public BoundaryViolationException(String msj) {
	super(msj);
  }
}