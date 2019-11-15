package Auxiliar;

/**
 * Clase InvalidPositionException que extiende de la clase Exception.
 * Modela la excepci�n que se produce ante una posici�n que es invalida.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 */
public class InvalidPositionException extends Exception {
  /**
   * Inicializa una excepci�n por posici�n invalido y describe su origen.
   * @param msj Mensaje que describe el origen del disparo de excepci�n.
   */
  public InvalidPositionException(String msj) {
	super(msj);
  }
}