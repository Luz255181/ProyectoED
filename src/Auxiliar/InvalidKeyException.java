package Auxiliar;

/**
 * Clase InvalidKeyException que extiende de Exception.
 * Modela la excepci�n que se produce ante una clave invalida.
 * @author Gonzalo  Perez
 *
 */
public class InvalidKeyException extends Exception {
  /**
   * Inicializa la excepci�n que se produce ante una clave invalida y describe su origen.
   * @param msj Mensaje que describe el evento que dispar� la excepci�n.
   */
  public InvalidKeyException(String msj) {
	super(msj);
  }
}