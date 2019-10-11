package Auxiliar;

/**
 * Clase InvalidKeyException que extiende de Exception.
 * Modela la excepción que se produce ante una clave invalida.
 * @author Gonzalo  Perez
 *
 */
public class InvalidKeyException extends Exception {
  /**
   * Inicializa la excepción que se produce ante una clave invalida y describe su origen.
   * @param msj Mensaje que describe el evento que disparó la excepción.
   */
  public InvalidKeyException(String msj) {
	super(msj);
  }
}