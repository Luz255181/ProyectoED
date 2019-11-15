package Auxiliar;

/**
 * Clase InvalidEntryException que extiende de la clase Exception.
 * Modela la excepción que se produce ante una entrada que es invalida.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 */
public class InvalidEntryException extends Exception {
  /**
   * Inicializa una excepción por entrada invalida y describe su origen.
   * @param msj Mensaje que describe el origen del disparo de la excepción.
   */
  public InvalidEntryException(String msj) {
	super(msj);
  }
}